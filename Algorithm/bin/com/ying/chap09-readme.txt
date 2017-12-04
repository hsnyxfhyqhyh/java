×××××××××××××××××××××
chap09-readme
×××××××××××××××××××××

>> 9.2 - priority queue

在某些情况下， 不可能遵循FIFO的原则。 那么必须用comparable interface 来实现元素之间的比较。 

Entry.java
PriorityQueue.java
DefaultComparator.java
AbstractPriorityQueue.java
UnsortedPriorityQueue.java  - (assumming knowledge from Posistion.java, PositionalList.java, then LinkedPositionalList.java) 
SortedPriorityQueue.java  - (assumming knowledge from Posistion.java, PositionalList.java, then LinkedPositionalList.java)

在priority queue中， 作者提供了两种实现的方式， UnsortedPriorityQueue/SortedPriorityQueue。 
UnsortedPriorityQueue： 比较快速， 但是removeMin 花的时间是O(n)， 因为需要scan所有元素，以保证最小的元素被选出来remove掉。 
SortedPriorityQueue: 比较快速， 但是insert 花的时间是O(n), 因为需要在加入新元素的时候保持原有元素的排序。

>> 9.3 - Heap

Heap 数据结构实际上是binary tree, parent的order小于children, 而且每一层都需要达到最大的capacity才能往下走。 
 