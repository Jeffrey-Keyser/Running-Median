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
 * This class represents is Minimum Priority Queue where the minimum value is stored
 * at the top of the Priority Queue. It has methods to add and remove, but it 
 * always keep the order by "reheapifying" as learned in lecture.
 * The internal data structure for this ADT is an array.
 */

import java.util.*;
public class MinPQ<E extends Comparable<E>> implements PriorityQueueADT<E>
{
	//internal data structure is an array with initial size
    private E[] items;
    private static final int INITIAL_SIZE = 10;
    private int numItems = 0;

    
    //default constructor
    public MinPQ()
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
		if (numItems == 0)
			return true;
		
		return false;
	}

	/**
	 * Inserts a new item to the MinPQ. After insertion, the 
	 * minPQ "reheapifies" to ensure that each child is less then
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
		
		//store index at one after numItems because increment comes later
		int childIndex = numItems + 1;
		
		
		int parentIndex = childIndex / 2;
		E parent = items[parentIndex];
		
		// "Reheapify"
		while (!done)
			{
			
				if ((Double) parent == null)
					done = true;
				
				else if (((Double) parent).compareTo((Double)child) <= 0)
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

	/**
	 *Returns the value at the of minPQ, but does not change the the structure of the queue. 
	 */
	public E getMax() throws EmptyQueueException {
		if (numItems == 0)
			throw new EmptyQueueException();
		
	    E maximum = items[1];
	    return maximum;
	}

	/**
	 *Returns the minimum in the minPQ and then "reheapifies" the queue
	 *to keep the structure, to keep the minimum value on the top. 
	 */
	public E removeMax() throws EmptyQueueException {
		
		if (numItems == 0)
			throw new EmptyQueueException();
		
		E max = items[1];
		int childIndex = 1;
		E child;

		
		// Swap the last value with the front
		// Set the last value to null
		items[1] = items[numItems];
		items[numItems] = null;
		
		int parentIndex = 1;
		E parent = items[parentIndex];
		
		
		// While we are within the number of items
		// in the array
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
				if (((Double) child2).compareTo((Double)parent) <= 0)
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
				if (((Double) child).compareTo((Double)parent) <= 0)
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
		
		
		// If child2 is the lowest between its parent and its sibling
		// swap child2 and parent
		else if(((Double) child).compareTo((Double)child2) >= 0 && ((Double) child2).compareTo((Double)parent) <= 0){
			// Set the index of the child we are switching with
			childIndex = parentIndex * 2 + 1;
			
			// Do the swap
			E temp = parent;
			items[parentIndex] = child2;
			items[childIndex] = temp;
			
			
			// Set the new indexes
			parentIndex = childIndex;

			
		}
		// If child is the lowest between its parent and its sibling
		// Swap child and parent
		else if (((Double) child).compareTo((Double)child2) < 0 && ((Double) child).compareTo((Double)parent) < 0){
			childIndex = parentIndex * 2;
			
			// Do the swap
			E temp = parent;
			items[parentIndex] = child;
			items[childIndex] = temp;
			
			
			// Set the new indexes
			parentIndex = childIndex;
		}
		// If parent is less then both child & child2
		// Stop the reheapify method
		else if (((Double) parent).compareTo((Double)child2) <= 0 && ((Double) parent).compareTo((Double)child2) <= 0)
		{
			break;
		}
			
			
		}
		numItems--;
		return max;
			
		}

	/**
	 * Returns the number of items
	 */
	public int size() {
		return numItems;
	}
	
	
	/**
	 * Expands the current array to twice it's size and copies all the elements over.
	 */
	private void expandArray(){
		 E[]expand = (E[]) new Comparable[items.length * 2];
		 for(int k = 0; k <= numItems; k++){
			 expand[k] = items[k];
		 }
		 items = expand;
	}
}
