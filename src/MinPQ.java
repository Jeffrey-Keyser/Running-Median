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
		// TODO Auto-generated method stub
		
	}

	@Override
	public E getMax() throws EmptyQueueException {
		if (items[numItems - 1].compareTo(items[numItems]))
			return items[numItems];
		
		return items[numItems - 1];
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
