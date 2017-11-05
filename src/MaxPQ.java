///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MedianStream.java
// File:             MaxPQ.java
// Semester:         Fall 2017
//
// Author:           Jeffrey Keyser	jkeyser@wisc.edu
// CS Login:         keyser
// Lecturer's Name:  Debra Deppeler
// Lab Section:      N/A
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Ben Hayes
// Email:            bhayes6@wisc.edu
// CS Login:         bhayes6
// Lecturer's Name:  Debra Deppeler
// Lab Section:      N/A
//

/**CLASS COMMENT
 * This class represents is Max Priority Queue where the maximum value is stored
 * at the top of the Priority Queue. It has methods to add and remove, but it 
 * always keep the order by reheapifying as learned in lecture.
 * The internal data structure for this ADT is an array.
 */

public class MaxPQ<E extends Comparable<E>> implements PriorityQueueADT<E>
{
	
		//internal data structure is an array with initial size
	 	private E[] items;
	    private static final int INITIAL_SIZE = 10;
	    private int numItems = 0;

	    // Default Constructor
	    public MaxPQ()
	    {
	    	//must initialize with comparable items
	        this.items = (E[]) new Comparable[INITIAL_SIZE];
	        numItems = 0;
	    }

		/**
		 * Checks the queue to indicate if it is empty
		 *
		 * @return true if the queue is empty, false otherwise
		 */
		public boolean isEmpty() {
			
			// If there are no items
			// The list must be empty
			if (numItems == 0)
				return true;
			
			return false;
		}

		/**
		 * Inserts a new item to the MaxPQ. After insertion, the 
		 * maxPQ "reheapifies" to ensure that each child is less then
		 * or equal to the parent
		 *
		 * @param (E item) Item to be inserted. Can assume it is type Double 
		 */
		public void insert(E item) {
			
			
			boolean done = false;
			
			// If item is not of type Double, throw a IllegalArguementException
			if (!((Double) item instanceof Double) )
				throw new IllegalArgumentException();
			
			
			// If the number of items exceeds the size of the array, 
			// expand the array
			if (numItems == items.length - 1)
				expandArray();
			
			// Add the item to the end of the list
			items[numItems + 1] = item;

			// Store the last index as the child
			E child = item;
			
			// store index at one after numItems because increment comes later
			int childIndex = numItems + 1;
			
			// Parent index is the childIndex divided by two
			int parentIndex = childIndex / 2;
			
			// Store parent
			E parent = items[parentIndex];
			
			// "Reheapify"
			while (!done)
			{
				// If the parent is null, stop "Reheapify"
				if ((Double) parent == null)
					done = true;
				
				// If the parent is greater then the child, stop "Reheapify"
				else if (((Double) parent).compareTo((Double)child) >= 0)
				{
					done = true;
				}
				// The only other case is if the parent is less then the child
				else
				{
					// storing child into temporary variable and swapping the values of child 
					// and parent in the array
					E temp; 
					temp = child;
					items[childIndex] = parent;
					items[parentIndex] = temp;
					
					// Reassigning the new child to the old parent and it's index
					child = items[parentIndex];
					childIndex = parentIndex;
					
					// Reassigning new parent in the tree
					parentIndex = childIndex / 2;
					parent = items[parentIndex];
				}
				
			}
			// Increment the number of items
				numItems++;
			}

		/**
		 *Returns the maximum in the priority queue, but does not change the the structure of the queue. 
		 */
		public E getMax() throws EmptyQueueException {
			
			// If there are no items, return a EmptyQueueException
			if (numItems == 0)
				throw new EmptyQueueException();
			
			return items[1];
		}

		/**
		 *Returns the maximum in the priority queue and then reheapifies the queue
		 *to keep the structure, to keep the maximum value on the top. 
		 */
		public E removeMax() throws EmptyQueueException {
			
			// If there are no items, return a EmptyQueueException
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
			
			// Store the initial parent
			int parentIndex = 1;
			E parent = items[parentIndex];
			
			// While we are within the number of items
			// in the array
			while (childIndex < numItems)
			{	
			try
			{
			// If the first child's index is not within the array
			// The second child is definitely not within the array
			// This is a signal to stop "reheapify"
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
			// Enter this condition to check which child is null
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
				else if (child2 == null)
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
			
			// Find the greatest value between child and child2, and check to see if child2 is greater than parent
			else if(((Double) child).compareTo((Double)child2) <= 0 
					&& ((Double) child2).compareTo((Double)parent) >= 0){
				
				// Set the index of the child we are switching with
				childIndex = parentIndex * 2 + 1;
				
				// Do the swap
				E temp = parent;
				items[parentIndex] = child2;
				items[childIndex] = temp;
				
				
				// Set the new indexes
				parentIndex = childIndex;

				
			}
			// Find the greatest value between child and child2, and check to see if child is greater than parent
			else if (((Double) child).compareTo((Double)child2) > 0 
					&& ((Double) child).compareTo((Double)parent) > 0){
				
				// Set the index of the child we are switching with
				childIndex = parentIndex * 2;
				
				// Do the swap
				E temp = parent;
				items[parentIndex] = child;
				items[childIndex] = temp;
				
				
				// Set the new indexes
				parentIndex = childIndex;
			}
			
			// If the parent is greater then child & child2
			// Stop the "reheapify" method
			else if (((Double) parent).compareTo((Double)child2) >= 0
					&& ((Double) parent).compareTo((Double)child2) >= 0)
			{
				break;
			}
				
				
			}
			// max has been removed so must decrease items in the array
			numItems--;
			return max;
				
			}
			
		
			
	
		/**
		 * Returns the number of items
		 */
		public int size() {
			// Return the number of items
			return numItems;
		}
		/**
		 * Expands the current array to twice it's size and copies all the elements over.
		 */
		private void expandArray(){
			// Expands the array by a factor 2 * currentLength
			//Array must have items that are comparable
			 E[]expand = (E[]) new Comparable[items.length * 2];
			 for(int k = 0; k <= numItems; k++){
				 expand[k] = items[k];
			 }
			 items = expand;
		}
	}
