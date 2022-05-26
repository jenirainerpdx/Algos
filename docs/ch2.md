2.2 Linked list is better because: many inserts and deletes.  Only reading from the first element, so that's also quick.

2.3 Array - because linked lists are sequential rather than random access.  (answer says : sorted array)

2.4 Downsides of array for inserts:  inserts require creation of a new memory block for all records and binary search needs elements sorted.

2.5 I wonder if this is leaning towards a trie?  so, like when the linked list for the letter gets too large, break it down into another sharded set of elements with the first and second letters... 
Not sure.  It is an array of LinkedLists... the array is fixed - we are not changing the order here or inserting or deleting. 
So, random access for first letter is fast.  

Let's take an example: username is Zzabo.
Searching: 
1. Array:  
   1. have to sort it (not sure what the O notation is for that, but it's at least O(n)).
   2. then, binary search:  O(log n)
   3. So, minimum: O(n log n)
2. Linked List: 
   1. Can't binary search that; so, O(n)
3. Array of LL: 
   1. Navigate to Z linked list - that's O(1)
   2. Linked list will require brute force search O(n)
   3. So, O(n)

For Inserts: 
1. Array: 
   1. Create new array O(1)
   2. Write each element O(n)
   3. Do I have to re-sort?? if so, O(n)
   4. This would mean O(2n) or O(n) (I think... )
2. Linked List: 
   1. Inserts are O(1)
3. Array of LL: 
   1. Find LL in the Array - O(1)
   2. Insert into the LL - O(1)
   3. O(1)
Seems like you get the best of both worlds with this.

Answer says: slower than array for searching, faster than linkedlist.
Faster than arrays for inserting.  Same time as linked list.  (I got the 2nd one right; but not the first.)