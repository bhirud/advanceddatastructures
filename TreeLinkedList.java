class QNode
{
	TreeNode tn = null;
	QNode next = null;
	QNode prev = null;
	
	public QNode (TreeNode d)
	{
		this.tn = d;
	}
}// end of class QNode

public class TreeLinkedList 
{
	
	QNode first = null;
	QNode last = null;
	
	// add the node in linked list.
	public void add(TreeNode d)
	{
		QNode p = new QNode(d);
		if(last == null)
		{
			first =p;
			last = p;
		}
		else
		{
			last.next = p;
			p.prev = last;
			last =p;
		}
	}// end of add
	
	// remove last node from the linked list.
	public TreeNode removeLast()
	{
		TreeNode node = null;
		if (last != null)
		{
			 node = last.tn;
			 
			if (first == last)
			{
				first = null;
				last = null;
			}
			else
			{
				last.prev.next = null;
				last = last.prev;
				last.next = null;
				
			}
			
		}
		
		return node;
	}//end of removeLast

	// check whether linked is empty or not.
	public boolean isEmpty()
	{
		if (first == null)
			return true;
		else
			return false;
	}//end of isEmpty
	
}//end of class TreeLinkedList
