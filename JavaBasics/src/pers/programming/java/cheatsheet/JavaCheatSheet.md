# JAVA CHEATSHEET

## Variables

```java
int a = Integer.MIN_VALUE; // Integer.MIN_VALUE is static int variable, not Integer.
```
Due to overflow / underflow of Integer variables, the following statements return true:
```java
Integer.MIN_VALUE == - Integer.MIN_VALUE
Integer.MIN_VALUE - 1 == Integer.MAX_VALUE
```
See more explanations [here](https://softwareengineering.stackexchange.com/questions/348172/in-java-why-does-integer-min-value-integer-min-value).

---

## String Operations

### Basics

```java
// Creation
String str = new String("Hello world");
String str = "Hello World!";

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
> See [this](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#quote-java.lang.String-) about Patterns.

```java
String[] parts;

// Literal Characters
String str = "foo:and:boo";
parts = str.split(":"); // {"foo", "and", "boo"}
parts = str.split("o"); // {"f", "", ":and:b"}, the trailing empty strings are discarded!

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

// Init with a value other than the default value (0 for integers).
int[] a = new int[10];
Arrays.fill(a, -1);
```

---

## Lists

### LinkedList

```java
// Initialization: declare it to be LinkedList (not List) so methods available
// in LinkedList (instead of List interface) can be utilized, e.g. getFirst().

LinkedList<Integer> list = new LinkedList<>();
if (!list.isEmpty()) {
    System.out.println(list.getFirst());
    System.out.println(list.getLast());
}
list.addFirst(2);
list.addLast(3);
list.removeLast();
list.removeFirst();
```

### Heap / Priority Queue

See more in [Comparators](#comparators).

```java
// First argument: (initial) capacity, will be automatically adjusted
// Second argument: custom comparator)
PriorityQueue<String> myHeap = new PriorityQueue<>(10, myComparator);

String topItem = myHeap.peek(); // return but not remove. CAN BE NULL!

myHeap.add("Hello world");
myHeap.offer("Hi world"); // same as myHeap.add()

while (true) {
    String str = myHeap.poll(); // return and remove. CAN BE NULL!
    if (str == null) break;

    // process str
}

// To avoid null return value, an alternative way is to use .isEmpty()
if (!myHeap.isEmpty()) {top = myHeap.peek();} // won't be null.
```
---

## Set

### Initialization
```java
Set<Integer> intSet = new HashSet<>();
Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

// In Java 8:
Set<String> strSet = Stream.of("a", "b", "c")
  .collect(Collectors.toCollection(HashSet::new));

// Using Guava (https://github.com/google/guava):
Set<String> strSet = ImmutableSet.of("a", "b", "c");
```

### Other Operations

```java
for (int value: intSet) {// process value;}
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

## Refection
https://stackoverflow.com/questions/8894258/fastest-way-to-iterate-over-all-the-chars-in-a-string
