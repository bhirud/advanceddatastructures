// Node structure of SNode.
class SNode
{
	TreeNode tn = null;
	SNode next = null;
	
	
	public SNode (TreeNode d)
	{
		this.tn = d;
	}
}//end of class SNode

public class TreeStack {
	
	SNode top = null;
	
	// Push the new node in the stack.
	public void push(TreeNode d)
	{
		SNode p = new SNode(d);
		if(top != null)
			p.next = top;
		
		top = p;
	}//end of push
	
	// pop- remove the topmost element from the stack.
	public TreeNode pop()
	{
		TreeNode node = null;
		
		if(top != null)
		{
			node = top.tn;
			top = top.next;
			
		}
		
		return node;
	}//end of pop
	
	// check whether stack is empty or not.
	public boolean isEmpty()
	{
		if (top == null)
			return true;
		else
			return false;
	}//end of isEmpty

}//end of class TreeStack
