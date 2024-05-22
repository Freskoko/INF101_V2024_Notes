# Stack vs heap

![alt text](/imgs/stackvsheap.png)

Primitives are all the the stack, not heap (since they take so little space)

ints between -128 and +127 are sorted specically 

[visualisers](https://pythontutor.com/iframe-embed.html#code=%2F%2F%20UiB%20INF101%0Apublic%20class%20Foo%20%7B%0A%20%20public%20static%20void%20main(String%5B%5D%20args)%20%7B%0A%20%20%20%20int%20x%20%3D%2012345%3B%0A%20%20%20%20Integer%20y%20%3D%2012345%3B%0A%20%20%7D%0A%7D&cumulative=false&py=java&heapPrimitives=true)
Null is at pos 0 in the stack


primitives cannot have null, have other default values

do check for null before .equals

Object.equals is better, already does the null check