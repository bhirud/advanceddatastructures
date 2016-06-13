import java.util.*;

// Node structure for binomial Heap

class Node
{
	int data = 0;
	int degree = 0;
	
	Node child = null;
	Node sibling = null;
	
	public Node ( int d)
	{
		this.data = d;
		
	}
}

// min will point to the current min element of the BInomial Heap

public class Binomial
{
	Node min = null;
	Node table[];
	
	public Binomial()
	{
	table = new Node[500];
	for (int i =0; i<500; i++)
		table[i] = null;
	}

	
/*//Create new Node and insert it into the circular list.
//	If the new node is smaller than the previous minimum then new node is new minimum.
*/



	public void insert(int d)
		{
			
			Node p = new Node (d);
		
			if (min == null)
			{
				min = p;
				min.sibling = min;
			}
			
			else
			{
				p.sibling = min.sibling;
				min.sibling = p;
			
				if(p.data < min.data)
					min = p;
			}
		}//end of insert

	
/*// removeMin deletes the min node and combines the circular list at top level with the circular list of children of min.
// Pairwise combine is done if there are more than one element of the same degree.
// New minimum is searched from the newly formed circular linked list.
// There can be four cases of removeMin.
// 1. There is only one element in the circular list with no child.
// 2. There is only one element in the circular list one or more no children.
// 3. There is more than one element in the circular list with no child.
// 4. There is more than one element in the circular list with one or more no children.
*/
	public void removeMin()
		{
		
			if(min == null)
				return;

			  Node tmpChild = min.child;
			  Node tmpLink = min.sibling;
			
			  if (min == min.sibling && tmpChild == null)
			  {
				  min = null;   
			  } 
			  else if (min == min.sibling && tmpChild != null)
			  {

				  min = null;
			  } 
			  else if ((min != min.sibling && tmpChild != null) ||
					  (min != min.sibling && tmpChild == null)) {
				  min.data = min.sibling.data;
				  min.child = min.sibling.child;
				  min.degree = min.sibling.degree;
				  min.sibling = min.sibling.sibling;

			  }
			
			  	meld(min, tmpChild);
				pairwiseCombine(min);
				
				createList(table);
			
		}//end of removeMin
	
	
	 
/*// Melds the two circular linked lists in to single circular list.
// 1. If both top circular linked list and child circular list are null then min is null.
// 2. If top circular linked list is not null and child circular list is null then min is top circular linked list.
// 3. If top circular linked list is  null and child circular list is not null then min is child circular linked list.
// 4. If both top circular linked list and child circular list are not null then both of then are melded together.
	*/  
	public void meld(Node a, Node b)
	{
		
		 if (a != null && b == null)
		        min = a;
		    else if (a == null && b != null)
		        min = b;
		    else if (a == null && b == null)
		        return;
		
		    else
		    {

		    	Node temp = a.sibling;
		    	a.sibling = b.sibling;
		    	b.sibling = temp;
		    }
		

	}// end of meld
				
	
	
/*// pairwiseCombine sends every node to method combine which until every node from the linked list is not sent to the method combine. 
*/	
	public void pairwiseCombine(Node p)
	{	
		
		min = null;
		
		while (p != null)
		{
		
			if ( p.sibling != p)
			{
			Node temp = new Node(p.data);
			temp.degree = p.degree;
			temp.child = p.child;
			temp.sibling = temp;
		
			
			p.data = p.sibling.data;
			p.degree = p.sibling.degree;
			p.child = p.sibling.child;

			p.sibling = p.sibling.sibling;
			
			combine(temp);
			
			}else{
				combine(p);
				p=null;
			}
			
		}
			
		
	}//end of pairwiseCombine
	
	
	
/*//	 create new circular linked list after pairwise combine of the nodes.
//	This will also find the latest min node from the list.
*/	 
	public void createList(Node table[])
	{ 

		Node p = null;
		for (int i = 0; i<500; i++)
		{
			if(table[i] != null)
			{
				if(p ==null)
				{
					
					p = table[i];
					p.sibling = p;
					
					min =p;
					
				}
				else
				{
					table[i].sibling = p.sibling;
					p.sibling = table[i];
				
					if(table[i].data < min.data)
						min = table[i];
				}
			}
			table[i] = null;
		}
			
	}
				

	
/*//	print nodes of the Binomial heap level by level
//	Every node is added to stack and then removed from the stack which is then inserted in the queue.
//	While removing every node from the queue it is printed in the corresponding level.
//	After removal of the node the child circular linked list is then added to the stack.
//	The process continues until every node is printed.
*/	 
	public void print ()
	{
		
		LinkedList <Node> l = new LinkedList<Node>();
		Deque<Node> s = new ArrayDeque<Node>(); 
				
		
			if(min != null)
			{
				
				Node ptr = min;
				
				do{
					s.push(ptr);
					ptr = ptr.sibling;
				} while(ptr != min);

				
				Node t;
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
						
						if(t.child != null)
						{
							Node p = t.child;
							do{
								s.push(p);
								p = p.sibling;
							} while(p != t.child);
						}
						
					}
					
					System.out.println("]");
					i++;
					
				}
			}
	}// end of print
	

/*	// Elements with the same degree are combined together.
//	If there is no node in the table of the same degree then it is added to the table.
//	If there is a node of same degree then check values of both nodes.
//	Make child which has value greater than the other node.
*/	
	public void combine(Node p)
	{
		
		if(p == null)
			return;
		
		int d = p.degree;
		
		if(table[d] == null)
		{
			table[d] = p;
			
			return;	
		}
		else
		{
				Node t = table[d];
				
				table[d] = null;
				if(t.data < p.data)
				{
					if(t.child == null)
					{
						t.child = p;
						p.sibling = p;
						t.degree = 1;
					}
					else
					{
						p.sibling = t.child.sibling;
						t.child.sibling = p;
						t.degree += 1;
					}
					combine(t);
				}
				else
				{
					if(p.child == null)
					{
						p.child = t;
						t.sibling = t;
						p.degree = 1;
					}
					else
					{
						t.sibling = p.child.sibling;
						p.child.sibling = t;
						p.degree += 1;		
					}
					combine(p);
				}
		}
	}
				
}// end of Binomial class
