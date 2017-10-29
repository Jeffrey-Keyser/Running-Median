/**
 * GENERAL DIRECTIONS -
 *
 * 1. You may add private data fields and private methods as you see fit.
 * 2. Implement ALL the methods defined in the PriorityQueueADT interface.
 * 3. DO NOT change the name of the methods defined in the PriorityQueueADT interface.
 * 4. DO NOT edit the PriorityQueueADT interface.
 * 5. DO NOT implement a shadow array.
 */
import java.util.*;
public class MinPQ<E extends Comparable<E>> implements PriorityQueueADT<E>
{
    private E[] items;
    private static final int INITIAL_SIZE = 10;
    private int numItems = 0;

    //ADD MORE DATA PRIVATE DATA FIELDS AS YOU NEED.

    public MinPQ()
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
		
		while (!done)
		{
			E parent = items[(numItems + 1) / 2];
			if ((Integer) parent == 0)
				done = true;
			
			
			if (items[numItems + 1].compareTo(parent) > 0)
			
			
		}
		
			// IF ITEM > PARENT
			
			
			
			
			
			
		}
			
		
		
		
		
		
	}

	@Override
	public E getMax() throws EmptyQueueException {
		return items[1];
	}

	@Override
	public E removeMax() throws EmptyQueueException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return numItems;
	}
}
