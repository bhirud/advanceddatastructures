The project implements two advanced data structures namely Binomial Heap and Leftist Tree.
Project runs in two modes 
random mode: which determines average cost required to perform single operation on both data structures.
user mode: where user performs series of operation on these data structures from input file which also displays the final tree after all operations.

Following commands are used to compile and run the project.
make – This command will compile all the java files and create .class files for each of them.make clean – This command will remove all the previously generated .class files.java Heap –r – This command will execute the project in random mode which will print the average time of execution for single operation on Leftist Tree and Binomial Heap.java Heap –il filename – This command will execute user mode which will perform operations on Leftist tree as given in the input file “filename”.java Heap –ib filename – This command will execute user mode which will perform operations on Binomial Heap as given in the input file “filename”.

*NOTE
input should be in the following format
I 2
I 543
D
I 8
I 90
I 4
I 1
I 15
I 60
I 100
I 344
D
*

where
I - insert an element
D - delete element with minimum value
* - denotes the end of operations

You can use RandomGenerator.java to create input file with your choice of number of operations and range of values.

There is one input file along with this project which contains 1 Million random operations  for testing.