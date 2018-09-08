import math
# -------------------------------------------------------
#  Attributes of the Python language.
# -------------------------------------------------------
# High-level, dynamic-typed, object oriented.

# -------------------------------------------------------
# Basics
# -------------------------------------------------------
# Run this in the terminal to check the current version.
# python --version

# -------------------------------------------------------
# Basic data types
# -------------------------------------------------------
x = 5
print(x ** 2)
print(type(x))

x += 1
# x++ # Python doesn't have unary increment / decrement.

pos = True
neg = False
print(pos and neg) # Use "and" / "or" instead of "&&" / "||"

# Converting between integers(ascii code) and characters
print(ord('a'))  # 'a' -> 97
print(chr(98))  # 98 -> 'b'

# printf-style string formatting
str = "%s %s %d" % ("hello", "world!", 12345)
print('"', str, '", ', "Length: ", len(str))
# Here are some useful methods for strings:
print(str.capitalize())
print(str.upper())
print(str.rjust(30))
print(str.center(30))
print(str.replace('l', 'm'))
print(("   " + str).strip())

# -------------------------------------------------------
# Containers
# -------------------------------------------------------
# Lists
arr = list(range(2, 20))
another_arr = [ele * 2 for ele in arr if ele != 2]
print(another_arr)

arr.append(100)
print(arr)
ele = arr.pop()
print(ele, arr)
ele = arr.pop(2)
print(ele, arr)
del arr[-2]
print(arr)
# idx is the index of elements in sliced array arr[3:10], so idx starts from 0, not 3 !!
for idx, ele in enumerate(arr[3:10]):
    print(idx, ele)
# Important notes about slicing:
'''
It creates a copy of part of the original array; specifically, arr[:] is a full copy of arr, and changing arr
wouldn't affect arr[:]. Here are two examples:
1) If we call a function in this way: foo(my_arr[:]), then we shouldn't expect my_arr will be different after the 
execution of that function;
2) [EVEN MORE IMPORTANT] When solving a backtracking problem, we probably want to remove the last element of an array
current_state after recursive calling. In this case, remember to use del current_state[-1] or current_state.pop() 
instead of current_state = current_state[:-1], which creates a new local variable and points it to a copy, while data
in the original address isn't affected.
'''

# Dictionaries
my_dict = {"a": "apple", "b": "baby"}
another_dict = {x: -x**2 for x in another_arr if x % 3 == 1}
print(another_dict)

my_dict["c"] = "cat"
print(my_dict.get("d", "dog"))
print(my_dict.get("c", "chat"))
del my_dict['c']
# del my_dict['d'] # Executing this would cause an error, because my_dict doesn't contain 'd'
for letter, word in my_dict.items():
    print("'%s' starts with '%s'" % (word, letter))


# Sets
my_set = {"apple", "banana", "strawberry", "lemon"}
another_set = {"apple", "strawberry", "watermelon", "peach"}
third_set = {math.sqrt(num) for num in arr}
for ele in third_set:
    print("%.4f, " % ele, end="")
print()

print(my_set & another_set, my_set | another_set)
my_set.add("mango")
another_set.remove("apple")
print(my_set, another_set)
# another_set.remove("kiwifruit") # Executing this would cause an error, since "kiwifuit" isn't in another_set
for idx, fruit in enumerate(my_set):
    print("The %d%s fruit is %s" % (idx + 1, "th" if (idx != 0 and idx != 1) else ("st" if idx == 0 else "nd"), fruit))

# Tuples
tup = (1, 2)
my_set.add(tup)
print(my_set)
# my_set.add([1,2]) # This is incorrect, because lists are mutable, and they can't be the elements of sets.

# -------------------------------------------------------
# Functions
# -------------------------------------------------------