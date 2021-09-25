# JAVA CHEATSHEET

## Variables and Basics

```java
int a = Integer.MIN_VALUE; // Integer.MIN_VALUE is static int variable, not Integer.
```
Due to overflow / underflow of Integer variables, the following statements return true:
```java
Integer.MIN_VALUE == - Integer.MIN_VALUE
Integer.MIN_VALUE - 1 == Integer.MAX_VALUE
```
See more explanations [here](https://softwareengineering.stackexchange.com/questions/348172/in-java-why-does-integer-min-value-integer-min-value).

"%" operator returns the remainder in Java unlike Python in which it returns modulus.
```java
// Java: a % b = a - roundTowardsZero(a/b) * b
// a % b < 0 if a < 0

3 % 2 == 1 // 3 - (3/2) * 2 = 1
(-3) % 2 == -1 // (-3) - (-3/2) * 2 = -1, -3/2 = -1 in Java.
3 % (-2) == 1 // 3 - (3/(-2)) * (-2) = 1
(-3) % (-2) == -1 // (-3) - ((-3)/(-2)) * (-2) = -1

Math.floorMod(a, b) // returns modulus as in Python.
```

```Python
# Python: a % b = a - floor(a/b) * b
# a % b < 0 if b < 0
3 % 2 == 1 # floor(3/2) = 1, 3 - 2 * 1 = 1
(-3) % 2 == 1 # (-3) - floor(-3/2) * 2 = 1, floor(-3/2) = -2
3 % (-2) == -1 # 3 - floor(3/(-2)) * (-2) = -1
(-3) % (-2) == -1 # (-3) - floor((-3)/(-2)) * (-2) = -1
```
References: [this](https://stackoverflow.com/a/5385053) and [this](https://stackoverflow.com/a/25830153).

```java
// Round up
int n = a / b + ((a % b == 0) ? 0 : 1); // Recommended
int n = (int) Math.ceil((double) a / b)); // NOT recommended, because of precision loss in double division
```

### Conversion between numbers
```java
// int and char
int i = 3;
char ch1 = (char) (i + '0'); // char '3'
char ch1 = (char) i; // char represented by ASCII Code 3

char ch2 = '3';
int j = ch2 - '0'; // 3
int j = ch2; // 51, ASCII Code representing '3'

// Numbers
int i = 3;
Integer myInt = i;
int j = myInt;

char ch = (char) i;
double d = i;

Character myChar = (char) i;
Double myVal = (double) i;

Character myChar = (char) (int) myInt;
Double myVal = (double) (int) myInt;

// Numbers and Strings
String str = i + ""; // Or: myInt + ""
String str = String.valueOf(i); // Or: String.valueOf(myInt);
String str = myInt.toString();

Integer.parseInt(str); // This is an int variable.
Integer.valueOf(str); // This is an Integer.
```

### The "Class" Class
```java
// Object.getClass(). Do NOT apply to primitive types.
List<Integer> list = new ArrayList<>();
Class c1 = list.getClass();
System.out.println(c1.getName()); // "java.util.ArrayList"
// Class c11 = int.getClass(); // Compile-time Error!

// type.class
Class c2 = int[].class;
System.out.println(c2.getName()); // "[I"
System.out.println(c2.getTypeName()); "int[]"

Class c3 = Character.class;
System.out.println(c3.getName()); // java.lang.Character
System.out.println(c3.getSuperclass().getName()); // java.lang.Object

// is instance of
Integer i = 1;
Character ch = 'a';
boolean b1 = Number.class.isInstance(ch); // false
boolean b2 = Number.class.isInstance(i); // true
// boolean b3 = ch instanceof Number; // runtime exception (incompatible types)
boolean b4 = i instanceof Number; // true
```
---

## String Operations

### Basics

```java
// Creation
String str = new String("Hello world");
String str = "Hello World!";

// Escape sequences: \\, \', \", \n, \b, \r, \t, \f
char ch = '\"';
String str = "\'"; // str.length() == 1
// "\", "'", """, '\', '"', ''' ---> It won't compile wihtout escaping.
// "\.", "\[", "\e" ---> Won't compile because of illegal escaping.

// Traversal
// toCharArray returns a char[], not Character[] or a List.
for (char ch : str.toCharArray()) {/*process ch*/}
for（int i = 0; i < str.length(); i ++) {char ch = str.charAt(i);}

// Basics
int len = str.length(); // not size()
String firstHalf = str.substring(0, len / 2); // not "subString"
String secondHalf = str.substring(len / 2, len);

String lowerCase = str.toLowerCase();
str1.compareTo(str2) // returns a negative integer if str1 < str2 lexicographically.
if(str.startsWith("abc")) {/*do something*/} // also str.endsWith(suffix)

int idx1 = str.indexOf('o'); // The first occurrence of char 'o'.
int idx2 = str.indexOf("World"); // Takes O(m * n) time to execute. See below.

String newStr = str.replace('o', 'a'); // A new String "Hella Warld!".

// Sort a String -- this will result in a new object, as String is immutable.
char[] chars = str.toCharArray();
Arrays.sort(chars);
String sortedStr = new String(chars);

// Reverse a String
new StringBuilder(str).reverse().toString();

```

Notes: Read [this](https://stackoverflow.com/questions/12752274/java-indexofstring-str-method-complexity) about Java indexOf implementation.

### Split a String

> str.split(regex) yeilds the same result as Pattern.compile(regex).split(str, 0).
> See [this](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html) about Patterns.

```java
String[] parts;

// Literal Characters
String str = "foo:and:boo";
parts = str.split(":"); // {"foo", "and", "boo"}
parts = str.split("o"); // {"f", "", ":and:b"}, the trailing empty strings are discarded!

// Multiple delimiters
String s = "a:-b:c-d";
String[] parts = s.split("[:-]+"); // {"a", "b", "c", "d"}, "+" treats consecutive delimiters as one.
String[] parts = s.split("[:-]"); // {"a", "", "b", "c", "d"}

// Special Characters
parts = str.split("\\t"); // split by tab, represented as "\t";
parts = str.split("\\n");
parts = str.split("\\\\"); // split by backslack "\", represented as "\\";

// Predefined character classes
parts = str.split("\\s"); // LOWERCASE s: split by whitespace characters.
parts = str.split("\\W"); // UPPERCASE W: split by non-word characters. Anything but a-z, A-Z, 0-9, _.
```

Read more about regular expressions at [here](https://stackoverflow.com/questions/1635764/string-parsing-in-java-with-delimiter-tab-t-using-split) and [here](http://www.regular-expressions.info/characters.html).

### StringBuilder

```java

// Creation
StringBuilder sb = new StringBuilder();
StringBuilder sb = new StringBuilder(str);

// Appending
sb.append(true); // boolean -> "true"
sb.append('a'); // character -> "a"
sb.append(20); // int -> "20"
sb.append("abc"); // String or CharSequence, StringBuiler implements CharSequence, so can be appended as well.

// Clear a StringBuilder
sb.setLength(0);
sb = new StringBuilder(); // slower.

// Traverse and modify
for (int i = 0; i < sb.length(); i ++) {
  char ch = sb.charAt(i);
  char newCh = ch + 1;
  sb.setCharAt(i, newCh);
}

StringBuilder reversed = sb.reverse(); // The char sequence in the original object is reversed. Also returns itself.

sb.insert(0, 'a');
sb.insert(1, "abc");

```
---

## Arrays

```java
// Initialization
int[] a = new int[5]; // Both "int a[]" or "int[] a" works
int[] a = new int[]{1,2,3,5,7};
int[] a = {1,3,5,7,9};

int[][] b = new int[3][2]; // or int[3][];
int[][] b = new int[][]{{1,2}, {3,4}, {5,6}};
int[][] b = {{1,2}, {3,4}, {5,6}};
int[][] b = new int[3][]; b[0] = new int[4]; b[1] = {1,2}; // ...

Integer[] arr = new Integer[10]; // arr of objects
//  List<Integer>[] arr = new ArrayList<Integer>[4]; // Won't compile!
List<Integer>[] arr = new ArrayList[10]; // array of Lists (NOT recommended)
List<List<Integer>> list = new ArrayList(); // Recommended: create list of lists

// Init with a value other than the default value (0 for integers).
int[] a = new int[10];
Arrays.fill(a, -1);
Arrays.fill(a, 0, 4, -1); // fills a[0 .. 3] with -1.

// Visualization
System.out.println(a); // prints something like "[I@6d06d69c";
System.out.println(Arrays.toString(a)); // prints "[1, 3, 5, 7, 9]"
```
### Sorting
See more in [Comparators](#comparators).


```java
Arrays.sort(a);
Arrays.sort(a, 0, 4); // sorts a[0 .. 3].

// Comparator<Integer> cannot be applied to compare int variables.
Integer[] boxed = Arrays.stream(a).boxed().toArray(Integer[]::new);
Arrays.sort(boxed, new IntegerComparator());
Arrays.sort(boxed, Collections.reverseOrder());

// Sort 2D arrays
int[][] arr = {{5,2},{3,6},{3,4}};
Arrays.sort(arr, (a, b) -> (a[0] - b[0])); // Lambda expression
Arrays.sort(arr, (a, b) -> {return a[0] - b[0];}); // Lambda expression using code block
Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
Arrays.sort(arr, Comparator.comparingInt(a -> a[0])); // define key extractor

// The compiler is not able to infer the generic type parameters when adding thenComparing
// Below are two ways to explicitly specify the type.
Arrays.sort(arr, Comparator.<int[]>comparingInt(a -> a[0]).thenComparing(b -> b[0]));
Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[0]).thenComparing(b -> b[0]));

Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[0]).reversed());
```

### Conversion
```java
int[] a = {1,2,3,4,5};

// Array with primitive types to boxed array: (Java8)
Integer[] boxed = Arrays.stream(a).boxed().toArray(Integer[]::new); // or:
Integer[] boxed = IntStream.of(a).boxed().toArray(Integer[]::new); // java.util.stream.IntStream

// Array with primitive types to boxed list (java.util.ArrayList)
List<Integer> list = Arrays.stream(a).boxed().collect(Collectors.toList()); // or:
List<Integer> list = IntStream.of(a).boxed().collect(Collectors.toList());

// Using Arrays.asList
Integer[] boxed = Arrays.stream(a).boxed().toArray(Integer[]::new);
List<Integer> list = Arrays.asList(boxed); // Fixed-sized Arrays$arraysarraylist
// list.add(2); list.remove(2); // This would throw UnsupportedOperationException!
List<Integer> list = new ArrayList(Arrays.asList(boxed)); // Better
```

**Side notes**
- Arrays.asList takes a parameter of T... array, where T cannot be primitive.
- Arrays.asList returns java.util.Arrays$ArrayList which is a nested class under Arrays,
and it's a fixed-sized list unlike java.util.ArrayList. ([Ref](https://mkyong.com/java/what-is-java-util-arraysarraylist/))

---

## Collection
![Diagram](https://static.javatpoint.com/images/java-collection-hierarchy.png)

Note: Collection is an Interface, while Collections is a class providing static methods.

### Interfaces

- List: add(ele), add(idx, ele), get(idx), indexOf(obj), isEmpty(), remove(idx), size(), set(idx, ele)

- Queue:
  - insert: offer(ele) vs add(ele) (throws exception due to capacity restrictions)
  - remove: poll() vs remove() (throws exception when empty)
  - get: peek() vs element() (throws exception when empty)

- Deque (extends Queue)
    - offerFirst(e), offerLast(e), pollFirst(), pollLast(), peekFirst(), peekLast()

- Set: add(ele), contains(obj), remove(obj)


```java
// Any Collection
Collection collection = myCollection; // Any implementation of Collection.
collection.clear();
boolean empty = collection.isEmpty();
int size = collection.size();
System.out.println(collection.toString()); // prints e.g., "[1, 2, 3]"
Integer[] array = collection.toArray(new Integer[0]); // The specified array is used to determine return types.
for (Integer ele : collection) { /* process element */ } // traversal

// Lists
List<String> list = myList; // Any implementation of List.
Collections.reverse(list); // Reverse the list in place.
Collections.fill(list, obj); // Use THE SAME OBJECT to replace all existing elements.
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
int position = list.indexOf(obj); // First occurrence of obj or -1.
```

### Implementations

**ArrayList**

```java
// Initialization
List<Integer> list = new ArrayList<>();
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2));
List<Integer> list = new ArrayList<>(collection); // any implementation of collection.

// A list of List
List<List<Integer>> lists = new ArrayList<>();
List<Integer> curList;
for (int i = 0; i < 10; i ++) {
  curList = createList();
  lists.add(new ArrayList<Integer>(curList)); // This creates a copy of curList instead of its reference.
}

// Convert to int[]
int [] ints = list.stream().mapToInt(Integer::intValue).toArray();
```

**ArrayDeque and LinkedList**

ArrayDeque is a better implementation of Deque than LinkedList. ([Ref](https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist))

```java
Deque<String> deque = new ArrayDeque();
Deque<String> deque = new ArrayDeque(collection); // any implementation of collection.
// Declare it to be Deque (not List) so methods like peekFirst() are available.
Deque<String> deque = new LinkedList();
```

**Heap / Priority Queue**

See more in [Comparators](#comparators).

```java
// First argument: (initial) capacity, will be automatically adjusted
// Second argument: custom comparator)
PriorityQueue<String> myHeap = new PriorityQueue<>(10, myComparator);

String topItem = myHeap.peek(); // return but not remove. CAN BE NULL!
myHeap.offer("Hi world"); // same as myHeap.add()

while (true) {
    String str = myHeap.poll(); // return and remove. CAN BE NULL!
    if (str == null) break;
    // process str
}

// To avoid null return value, an alternative way is to use .isEmpty()
if (!myHeap.isEmpty()) {top = myHeap.peek();} // won't be null.
```
**HashSet**
```java
Set<Character> set = new HashSet<>();
Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

// In Java 8:
Set<String> strSet = Stream.of("a", "b", "c") // Interface: java.util.stream
  .collect(Collectors.toCollection(HashSet::new));

// Using Guava (https://github.com/google/guava):
Set<String> strSet = ImmutableSet.of("a", "b", "c");
```
---

## Map

```java
// Counter
Map<String, Integer> counter = new HashMap<>();
for (String str: stringArray) {
  counter.put(str, counter.getOrDefault(str, 0) + 1);
}

for (String key : counter.keySet()) {
  System.out.println(key + " " + counter.get(key));
}
```

---

## Misc.

### Pair

Java util library doesn’t have a class of Pair. But it can be imported from some 3rd party libraries, e.g:

```java
import javafx.util.Pair;

// This pair is immutable
Pair<Integer, String> pair = new Pair<>(1, "abc");
System.out.println(pair.getKey());
System.out.println(pair.getValue());
```

Or: use implementation of Map.Entry:

```java
// AbstractMap is an abstract class, but AbstractMap.SimpleEntry is
// its nested concrete static class, so can be instantiated.
Map.Entry<Integer, String> pair = AbstractMap.SimpleEntry<>(1, "abc");
pair.getKey();
pair.getValue();
pair.setValue("dec"); // cannot reset key.
```

If the key and the value are of the same type, an easier way is to just use array:

```java
int[] pair = new int[]{k, v};
Set<int[]> mySet = new HashSet<>(Arrays.asList(pair));
```

### Comparators

```java
public class KeyComparator implements Comparator<Map.Entry<Integer, Integer>> {

    @Override
    int compare(Map.Entry<Integer, Integer> entry1,
                Map.Entry<Integer, Integer> entry2 ) {
                return entry1.getKey() - entry2.getKey();
    }
}

// In Java 8: using Lambda expression instead of defining class
people.sort((p1, p2) -> (p1.getName().compareTo(p2.getName())));
```

## Reflection
https://stackoverflow.com/questions/8894258/fastest-way-to-iterate-over-all-the-chars-in-a-string
