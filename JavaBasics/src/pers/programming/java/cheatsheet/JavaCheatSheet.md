# JAVA CHEATSHEET

## Variables

```
int a = Integer.MIN_VALUE;
```

## String Operations

```
// Creation
String str = new String("Hello world");
String str = "Hello World!";

int len = str.length(); // not size()

String firstHalf = str.substring(0, len / 2); // not "subString"
String secondHalf = str.substring(len / 2, len);

String lowerCase = str.toLowerCase();

// toCharArray returns a char[], not Character[] or a List.
for (char ch : str.toCharArray()) {// process ch}
```

## Arrays

```
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

```
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

```
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
```
Set<Integer> intSet = new HashSet<>();
Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

// In Java 8:
Set<String> strSet = Stream.of("a", "b", "c")
  .collect(Collectors.toCollection(HashSet::new));

// Using Guava (https://github.com/google/guava):
Set<String> strSet = ImmutableSet.of("a", "b", "c");
```

### Other Operations

```
for (int value: intSet) {// process value;}
```

---

## Misc.

### Pair

Java util library doesn’t have a class of Pair. But it can be imported from some 3rd party libraries, e.g:

```
import javafx.util.Pair;

// This pair is immutable
Pair<Integer, String> pair = new Pair<>(1, "abc");
System.out.println(pair.getKey());
System.out.println(pair.getValue());
```

Or: use implementation of Map.Entry:

```
// AbstractMap is an abstract class, but AbstractMap.SimpleEntry is
// its nested concrete static class, so can be instantiated.
Map.Entry<Integer, String> pair = AbstractMap.SimpleEntry<>(1, "abc");
pair.getKey();
pair.getValue();
pair.setValue("dec"); // cannot reset key.
```

If the key and the value are of the same type, an easier way is to just use array:

```
int[] pair = new int[]{k, v};
Set<int[]> mySet = new HashSet<>(Arrays.asList(pair));
```

### Comparators

```
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
