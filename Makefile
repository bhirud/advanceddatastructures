CLASSES = \
	Heap.java \
	Binomial.java \
	LeftistTree.java \
	TreeLinkedList.java \
	TreeStack.java

RM = rm
default:
	javac $(CLASSES)

clean:
	$(RM) *.class