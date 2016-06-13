import java.util.*;




 /*
  * Node structure for leftist tree
  * s() is the smallest height of the node which will be rightmost path in every case because of the Leftist tree rule.
  * */
class TreeNode
{
	int data;
	int s = 1;
	TreeNode left = null;
	TreeNode right = null;
	
	public TreeNode ( int d)
	{
		this.data = d;
		
	}
}// end of TreeNode


public class LeftistTree 
{
	
	TreeNode min = null;
	
	
	 /*
	  * inserting node in the leftist tree and melding it with the right subtree of the min tree 
	  * */
	public void insert(int d)
	{
		TreeNode p = new TreeNode(d);
		
		if(min == null)
			min = p;
		else
			min = meld(min, p);
	}//end of insert
	
	
	
	 /*
	  * deleting min node from the leftist tree i.e. root node.
	  * Meld the left and right subtrees of the root node to form a new leftist tree
	  */
	public TreeNode deleteMin()
	{
		
			if ( min == null)
	        return null;
	    	    
	    
	    /*
	     * Meld the two subtrees of root
	     * */
	    return min = meld(min.left,min.right);
	    
	}//end of deleteMin
	
	
	
	 /*
	  * //melding the two trees to determine the min node after insertion or removal of the node.
	  * //1.If both trees are null then retun null.
	  * //2.If left tree is not null and right tree is null then return left tree.
	  * //3.If left tree is null and right tree is not null then return right tree.
	  * //4.If left tree is not null and right tree is not null then recursively meld both the tree.
	  * */
	public TreeNode meld(TreeNode a, TreeNode b)
	{
	
		 /*
		  * if the left tree data is smaller then right child of smaller tree is melded with the other tree,
		  * continuing this process until right child of min tree is null.
		  * */

		if ( a == null && b != null)
	        return b;
	    else if ( a != null && b == null)
	        return a;
	    else if ( a == null && b == null)
	        return null;
		
		
		if (b.data < a.data)
		{
			TreeNode t = b;
			b = a;
			a = t;
		}
		
		/*// If a has no right tree then b will be right subtree of a.
		// Otherwise they are recursively melded together.
		// If s() value of right subtree is greater than the left subtree then both the trees are swapped.
		// If left degree is null and right degree is not null then swap both the trees.
		 */		
		if(a.data <= b.data)
		{
			min = a;
			
			if(a.right == null)
			{
				a.right = b;	
			}	
			else
			{
				a.right = meld (a.right, b);	
			}
			
			a.s = a.right.s +1;
			if(a.left != null && a.right!= null)
			{
				if(a.left.s < a.right.s)
				{
					TreeNode p = a.left;
					a.left = a.right;
					a.right = p;
					a.s = a.right.s +1;
				}
			}
			else if (a.left == null)
			{
				a.left = a.right;
				a.right = null;
				a.s = 1;
			}
			return a;
			
		}
		return a;
		
	}// end of meld


	
/*  //print nodes of the leftist tree level by level.
	//	Every node is added to stack and then removed from the stack which is then inserted in the queue.
	//	While removing every node from the queue it is printed in the corresponding level.
	//	After removal of the node the left and right children are then added to the stack.
	//	The process continues until every node is printed.
	 */
	public void print()
	{
		
		TreeLinkedList l = new TreeLinkedList();
		TreeStack s = new TreeStack();
		
		// LinkedList <TreeNode> l = new LinkedList<TreeNode>();
		// Deque<TreeNode> s = new ArrayDeque<TreeNode>(); 
		
		if(min != null)
		{
			s.push(min);

			TreeNode t;
			int i=1;

			while(!s.isEmpty())
			{
				System.out.print("Level "+i+": [");

				while(!s.isEmpty())
					l.add(s.pop());


				while(!l.isEmpty())
				{
					t = l.removeLast();

					if(!l.isEmpty())
						System.out.print(t.data+",");
					else
						System.out.print(t.data);

					if(t.left != null)
						s.push(t.left);

					if(t.right != null)
						s.push(t.right);
				}

				System.out.println("]");
				i++;
			}
		}
		
	}// end of print
	
}// end of class LeftistTree
