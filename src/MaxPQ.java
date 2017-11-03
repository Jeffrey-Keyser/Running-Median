/**
 * GENERAL DIRECTIONS -
 *
 * 1. You may add private data fields and private methods as you see fit.
 * 2. Implement ALL the methods defined in the PriorityQueueADT interface.
 * 3. DO NOT change the name of the methods defined in the PriorityQueueADT interface.
 * 4. DO NOT edit the PriorityQueueADT interface.
 * 5. DO NOT implement a shadow array.
 */

public class MaxPQ<E extends Comparable<E>> implements PriorityQueueADT<E>
{
	

	 	private E[] items;
	    private static final int INITIAL_SIZE = 10;
	    private int numItems = 0;


	    public MaxPQ()
	    {
	        this.items = (E[]) new Comparable[INITIAL_SIZE];
	        numItems = 0;
	        // TO-DO: Complete the constructor for any private data fields that you add.
	    }

		@Override
		public boolean isEmpty() {
			if (numItems == 0)
				return true;
			
			return false;
		}


		public void insert(E item) {
			
			 
			boolean done = false;
			
			if (item == null)
				throw new IllegalArgumentException();
			if (numItems == items.length - 1)
				expandArray();
			
			items[numItems + 1] = item;

			E child = item;
			
			//store index at one after numItems because increment comes later
			int childIndex = numItems + 1;
			
			int parentIndex = childIndex / 2;
			
			E parent = items[parentIndex];
			
			while (!done)
			{
			
				if ((Double) parent == null)
					done = true;
				
				else if (((Double) parent).compareTo((Double)child) >= 0)
				{
					done = true;
				}
				else
				{
					//storing child into temporary variable and swapping the values of child and parent in the 
					//array
					E temp; 
					temp = child;
					items[childIndex] = parent;
					items[parentIndex] = temp;
					
					//reassigning the new child to the old parent and it's index
					child = items[parentIndex];
					childIndex = parentIndex;
					
					//reassigning new parent in the tree
					parentIndex = childIndex / 2;
					parent = items[parentIndex];
				}
				
			}
				numItems++;
			}


		public E getMax() throws EmptyQueueException {
			
			if (numItems == 0)
				throw new EmptyQueueException();
			
			return items[1];
			
		}


		public E removeMax() throws EmptyQueueException {
			
			if (numItems == 0)
				throw new EmptyQueueException();
			
			E child;
			E max = items[1];
			int childIndex = 1;
			
			boolean done = false;
			
			// Swap the last value with the front
			// Set the last value to null
			items[1] = items[numItems];
			items[numItems] = null;
			
			int parentIndex = 1;
			E parent = items[parentIndex];
			
			while (childIndex < numItems)
			{
				
			try
			{
			child = items[2*parentIndex];
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				break;
			}
			E child2 = items[(2*parentIndex)+1];
			
			// If there are no more children, stop comparing
			if (child == null && child2 == null)
				break;
				
			// If one of the children is null
			// Enter this condition to check the other child
			if (child == null || child2 == null)
			{
				if (child == null)
				{
					if (((Double) child2).compareTo((Double)parent) >= 0)
					{
						// Set the index of the child we are switching with
						childIndex = parentIndex * 2 + 1;
						
						// Do the swap
						E temp = parent;
						items[parentIndex] = child2;
						items[childIndex] = temp;
						
						
						// Set the new indexes
						parentIndex = childIndex;
					}
					else
						break;
					
				}
				if (child2 == null)
				{
					if (((Double) child).compareTo((Double)parent) >= 0)
					{
						// Set the index of the child we are switching with
						childIndex = parentIndex * 2;
						
						// Do the swap
						E temp = parent;
						items[parentIndex] = child;
						items[childIndex] = temp;
						
						
						// Set the new indexes
						parentIndex = childIndex;
					}
					else
						break;
					
				}
			}
			
			//making sure it is being swapped with the highest value
			
			
			
			else if(((Double) child).compareTo((Double)child2) <= 0 && ((Double) child2).compareTo((Double)parent) >= 0){
				// Set the index of the child we are switching with
				childIndex = parentIndex * 2 + 1;
				
				// Do the swap
				E temp = parent;
				items[parentIndex] = child2;
				items[childIndex] = temp;
				
				
				// Set the new indexes
				parentIndex = childIndex;

				
			}
			else if (((Double) child).compareTo((Double)child2) > 0 && ((Double) child).compareTo((Double)parent) > 0){
				childIndex = parentIndex * 2;
				
				// Do the swap
				E temp = parent;
				items[parentIndex] = child;
				items[childIndex] = temp;
				
				
				// Set the new indexes
				parentIndex = childIndex;
			}
			
			// If the parent is greater then child & child2
			// Stop the reheapify method
			else if (((Double) parent).compareTo((Double)child2) >= 0 && ((Double) parent).compareTo((Double)child2) >= 0)
			{
				break;
			}
				
				
			}
			numItems--;
			return max;
				
			}
			
		
			
	

		public int size() {
			return numItems;
		}
		
		private void expandArray(){
			 E[]expand = (E[]) new Comparable[items.length * 2];
			 for(int k = 0; k <= numItems; k++){
				 expand[k] = items[k];
			 }
			 items = expand;
		}
		
		
		public static void main(String args[])
		
		{
			MaxPQ<Double> quene = new MaxPQ<Double>();
					
			quene.insert(60.0);
			quene.insert(80.0);
			quene.insert(70.0);
			quene.insert(40.0);
			quene.insert(10.0);
			quene.insert(5.0);
			quene.insert(100.0);


			
//		for (int i = 0; i < quene.size(); i++)
//			{
//			System.out.println(items[i]);
//			}
			
			System.out.println(quene.getMax());
			
			System.out.println(quene.removeMax());
			
			
			
		}
		
		
		
		
		
		
		

	}
