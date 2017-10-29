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

	    //ADD MORE DATA PRIVATE DATA FIELDS AS YOU NEED.

	    public MaxPQ()
	    {
	        this.items = (E[]) new Comparable[INITIAL_SIZE];

	        // TO-DO: Complete the constructor for any private data fields that you add.
	    }

		@Override
		public boolean isEmpty() {
			if (numItems == 0)
				return true;
			
			return false;
		}

		@Override
		public void insert(E item) {
			
			boolean done = false;
			
			if (item == null)
				throw new IllegalArgumentException();
			if (numItems == items.length - 1)
				expandArray();
			
			items[numItems + 1] = item;
			
		//	if (items[numItems + 1].compareTo(items[(numItems + 1) / 2]) > 0)	// FIX LATER
		//	{
			E child = items[(numItems + 1)];
			int index = numItems + 1;
			
			index = index / 2;
			
			E parent = items[index];
			
			while (!done)
			{
			
				if ((Integer) parent == 0)
					done = true;
				
				else if (parent.compareTo(child) >= 0)
				{
					done = true;
				}
				else
				{
					//storing child into temporary variable and swapping the values of child and parent in the 
					//array
					E temp; 
					temp = child;
					items[index * 2] = parent;
					items[index] = temp;
					
					//reassigning the parent and child for the next time through the while loop
					child = parent;
					index = index / 2;
					parent = items[index];
				}
				numItems++;
			}
				
			}

		@Override
		public E getMax() throws EmptyQueueException {
			return items[1];
		}


		public E removeMax() throws EmptyQueueException {
			E max = items[1];
			boolean done = false;
			items[1] = items[numItems];
			int index = 0;
			E parent = items[1];
			
			E child = items[2];
			E child2 = items[3];
			index = 2;
			//making sure it is being swapped with the highest value
			if(child.compareTo(child2) < 0){
				child = child2;
				index = 3;
			}
			 
			
			while (!done)
			{
	
				if (parent.compareTo(child) >= 0)
				{
					done = true;
				}
				else
				{
					//storing child into temporary variable and swapping the values of child and parent in the 
					//array
					E temp; 
					temp = child;
					items[index * 2] = parent;
					items[index] = temp;
					
					//reassigning the parent and child for the next time through the while loop
					child = parent;
					index = index / 2;
					parent = items[index];
				}
				numItems++;
			}
				
			}
			
			
	

		@Override
		public int size() {
			return numItems;
		}
		
		private void expandArray(){
			 E[]expand = (E[]) new Comparable[INITIAL_SIZE * 2];
			 for(int k = 0; k <= numItems; k++){
				 expand[k] = items[k];
			 }
			 items = expand;
		}
		
		private int compareTo(E obj){
			if( this < (Integer) obj)
			
		}

	}
