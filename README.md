# DataStructureAndAlgorithms

## O(1)

O(1) describes an algorithm that will always execute in the same time (or space) regardless of the size of the input data set.

```java
bool IsFirstElementNull(IList<string> elements) {
    return elements[0] == null;
}
```

## O(N)

O(N) describes an algorithm whose performance will grow linearly and in direct proportion to the size of the input data set. The example below also demonstrates how Big O favours the worst-case performance scenario; a matching string could be found during any iteration of the for loop and the function would return early, but Big O notation will always assume the upper limit where the algorithm will perform the maximum number of iterations.

```java
bool ContainsValue(IList<string> elements, string value) {
    foreach (var element in elements)
    {
        if (element == value) return true;
    }

    return false;
}
```

## O(N2)

O(N2) represents an algorithm whose performance is directly proportional to the square of the size of the input data set. This is common with algorithms that involve nested iterations over the data set. Deeper nested iterations will result in O(N3), O(N4) etc.

```java
bool ContainsDuplicates(IList<string> elements) {
    for (var outer = 0; outer < elements.Count; outer++)
    {
        for (var inner = 0; inner < elements.Count; inner++)
        {
            // Don't compare with self
            if (outer == inner) continue;

            if (elements[outer] == elements[inner]) return true;
        }
    }

    return false;
}
```

## O(2N)

O(2N) denotes an algorithm whose growth doubles with each additon to the input data set. The growth curve of an O(2N) function is exponential - starting off very shallow, then rising meteorically. An example of an O(2N) function is the recursive calculation of Fibonacci numbers:

```java
int Fibonacci(int number) {
    if (number <= 1) return number;

    return Fibonacci(number - 2) + Fibonacci(number - 1);
}
```

## Logarithms

This type of algorithm is described as O(log N). The iterative halving of data sets described in the binary search example produces a growth curve that peaks at the beginning and slowly flattens out as the size of the data sets increase e.g. an input data set containing 10 items takes one second to complete, a data set containing 100 items takes two seconds, and a data set containing 1000 items will take three seconds. Doubling the size of the input data set has little effect on its growth as after a single iteration of the algorithm the data set will be halved and therefore on a par with an input data set half the size. This makes algorithms like binary search extremely efficient when dealing with large data sets.

```java
boolean binarySearch(int[] data, int size, int key) {
	int low = 0;
	int high = size - 1;

	while(high >= low) {
		int middle = (low + high) / 2;
		if(data[middle] == key) {
			return true;
		}
		if(data[middle] < key) {
			low = middle + 1;
		}
		if(data[middle] > key) {
			high = middle - 1;
		}
	}
	return false;
}
```