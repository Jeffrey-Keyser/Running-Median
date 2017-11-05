///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Running Median
// Files:            MedianStream.java , MinPQ.java , MaxPQ.java ,
//					 EmptyQueueExcepetion.java , PriorityQueueADT.java
// Semester:         Fall 201
//
// Author:           Jeffrey Keyser
// Email:            jkeyser@wisc.edu
// CS Login:         keyser
// Lecturer's Name:  Debra Deppeler
// Lab Section:      N/A
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Ben Hayes
// Email:            bhayes6@wisc.edu
// CS Login:         bhayes6
// Lecturer's Name:  Debra Deppeler
// Lab Section:      N/A
//

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**CLASS COMMENT
 * This class represents contains the main method for P3. It
 * has two modes where either the user enters data or the data is scanned in from a file.
 * When the user enters 'q'  the program is ended. In file mode if are the program executes the
 * media values are written to a file.
 */
public class MedianStream
{

    private static final String PROMPT_NEXT_VALUE = "Enter next value or q to quit: ";
    private static final String MEDIAN = "Current median: ";
    private static final String EXIT_MESSAGE = "That wasn't a double or 'q'. Goodbye!";
    private static final String FNF_MESSAGE = " not found.";

    /**
     * Use this format to ensure that double values are formatted correctly.
     * Double doubleValue = 1412.1221132
     * System.out.printf(DOUBLE_FORMAT, doubleValue);
     */
    private static final String DOUBLE_FORMAT = "%8.3f\n";

    private Double currentMedian;
    private MaxPQ<Double> maxHeap;
    private MinPQ<Double> minHeap;

    /**
     *  Initialize the currentMedian = 0.0
     *  Create a new MaxPQ and MinPQ.
     */
    public MedianStream()
    {
        this.currentMedian = 0.0;
        this.maxHeap = new MaxPQ<Double>();
        this.minHeap = new MinPQ<Double>();
    }

    /**
     * This method is called if the user passes NO command line arguments.
     * The method prompts the user for a double value on each iteration.
     *
     * If the input received is a double, the current median is updated.
     * After each iteration, print the new current median using MEDIAN string
     * as declared and initialized with the data members above.
     *
     * If the input is the character 'q', return from the method.
     *
     * If the input is anything else, then you print an error using EXIT_MESSAGE
     * string as declared and initialized with the data members above and
     * then return from the method.
     *
     * For the purposes of calculating the median, every input received since
     * the beginning of the method execution is part of the same stream.
     */
    private static void runInteractiveMode()
    {

    	MedianStream s = new MedianStream();
    	
    	//creates scanner for user input
    	Scanner in = new Scanner(System.in);
    	boolean done = false;
    	double d = 0;
    	
    	//user interaction loop exits when user enters 'q'
    	while (!done)
    	{
    		System.out.print(s.PROMPT_NEXT_VALUE);
    		
    		String input = in.nextLine();
    		input = input.trim();
    		
    		try {
    		
    			
    			d = Double.parseDouble(input);
    			s.currentMedian = s.getMedian(d);
    			
    			//output to user
    			System.out.print(MEDIAN);
    			System.out.printf(DOUBLE_FORMAT, s.currentMedian);
    		}
    		catch(NumberFormatException e)
    		{
    			//end the loop if the user inputs q
    			if (input.equals("q"))
    			done = true;
    	
    			//if the user inputs something other then q
    			//display the exit message
    			else
    		{
    			System.out.println(EXIT_MESSAGE);
    			done = true;
    		}

    		}
    	}
    }
    

    /**
     * This method is called if the user passes command line arguments.
     * The method is called once for every filename passed by the user.
     *
     * The method reads values from the given file and writes the new median
     * after reading each new double value to the output file.
     *
     * The name of the output file follows a format specified in the write-up.
     *
     * If the input file contains a non-double value, the program SHOULD NOT
     * throw an exception, instead it should just read the values up to that
     * point, write medians after each value up to that point and then
     * return from the method.
     *
     * If a FileNotFoundException occurs, just print the string FNF_MESSAGE
     * as declared and initialized with the data members above.
     */
    private static void findMedianForFile(String filename)
    {
    	PrintWriter writer = null;
    	  	
    	try{
    	
    		String[] filenameArray = filename.split("\\.");
    	
    		if (filenameArray.length != 2)
    			throw new IOException();
    		
    		String name = filenameArray[0];
    		String extension = filenameArray[1];
    		
    		//writing to file with name of the input file
    		writer = new PrintWriter(name + "_out." + extension, "UTF-8");
    		
    		MedianStream s = new MedianStream();
    		File inFile = new File(filename);
    		Scanner input = null;
    		
    		
    		input = new Scanner(inFile);
    		

    		//getting input from file
    		while(input.hasNextInt()){
    			double store = input.nextInt();
    			s.currentMedian = s.getMedian(store);
    			
    			System.out.printf(DOUBLE_FORMAT, s.currentMedian);
    			
    			writer.printf(DOUBLE_FORMAT, s.currentMedian);
    			
    		}
    		
    		writer.close();
    		input.close();
    	}
 
    	//catching potential error of inputing file
    	catch (FileNotFoundException e)
    	{
    		System.out.println(filename + FNF_MESSAGE);
    		writer.close();
    		

    	}
   
    	catch(IOException e)
    	{
    		System.out.println(filename + FNF_MESSAGE);
    	}
    	
    	

    }

    /**
     * Adds the new temperature reading to the corresponding
     * maxPQ or minPQ depending upon the current state.
     *
     * Then calculates and returns the updated median.
     *
     * @param newReading - the new reading to be added.
     * @return the updated median.
     */
    private Double getMedian(Double newReading)
    {
    	// If both queues are empty add to maxQuene
    	if (maxHeap.size() == 0)
    	{
    		this.maxHeap.insert(newReading);	
    		return maxHeap.getMax();
    	}
    		
    	// All new values above the median go into the maxHeap
    	// Values below the median go into minHeap
    	if (newReading <= this.currentMedian)
    		this.maxHeap.insert(newReading);
    	else 
    		this.minHeap.insert(newReading);
    	
    	
    	// Keeps the heaps within size + 1 of each other
    	// If the size the one of the Heaps is greater by 2 or more
    	// Add the max to the other heap
    	if (this.minHeap.size() > this.maxHeap.size() + 1)
    	{
    		maxHeap.insert(minHeap.removeMax());
    	}
    	else if (this.maxHeap.size() > this.minHeap.size() + 1)
    	{
    		minHeap.insert(maxHeap.removeMax());
    	}
    	
    	// If the two heaps are the same size
    	// The average of the two middle terms is the median
    	if(this.maxHeap.size() == this.minHeap.size()){
    		return ((this.maxHeap.getMax() + this.minHeap.getMax()) / 2);
		}
    	
    	//keeping the two data structures close within 1 size of the other
    	//if max heap is too large insert into min heap
    	else if (this.maxHeap.size() > this.minHeap.size())
    	{
    		minHeap.insert(maxHeap.removeMax());
    		return minHeap.getMax();
    	}
    	else
    	{
    		maxHeap.insert(minHeap.removeMax());
    		return maxHeap.getMax();
    	}
    		
    }

    public static void main(String[] args)
    {
        // Check if files have been passed in the command line.
        // If no files are passed, run an infinite interactive loop taking a double
        // input each time until "q" is entered by the user.
        // After each iteration of the loop, update and display the new median.
        if ( args.length == 0 )
        {
            runInteractiveMode();
        }

        // If files are passed in the command line, open each file.
        // For each file, iterate over all the double values in the file.
        // After reading each new double value, write the new median to the
        // corresponding output file whose name will be inputFilename_out.txt
        // Stop reading the file at the moment a non-double value is detected.
        else
        {
            for ( int i=0 ; i < args.length ; i++ )
            {
                findMedianForFile(args[i]);
            }
        }
    }
}
