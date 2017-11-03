import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

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
     * Override Default Constructor
     *
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
    	
    	
    	Scanner in = new Scanner(System.in);
    	boolean done = false;
    	double d = 0;
    	
    	
    	while (!done)
    	{
    		System.out.println(s.PROMPT_NEXT_VALUE);
    		
    		String input = in.nextLine();
    		input = input.trim();
    		
    		try {
    		
    		
    			d = Double.parseDouble(input);
    			s.currentMedian = s.getMedian(d);
    			System.out.println(MEDIAN + s.currentMedian);
    		}
    		catch(NumberFormatException e)
    		{
    			if (input.equals("q"))
    			done = true;
    	
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
    	String[] filenameArray = filename.split("\\.");
    	String name = filenameArray[0];
    	String extension = filenameArray[1];
    	try 
    	{
    	writer = new PrintWriter(name + "_out." + extension, "UTF-8");
    	}
    	catch (IOException e)
    	{
    		System.out.println(FNF_MESSAGE);
    	}
    	
    	MedianStream s = new MedianStream();
    	File inFile = new File(filename);
    	Scanner input = null;
    	
    	try{
    		input = new Scanner(inFile);
    		

    		
    		while(input.hasNextInt()){
    			double store = input.nextInt();
    			s.currentMedian = s.getMedian(store);
    			
    			System.out.printf(DOUBLE_FORMAT, s.currentMedian);
    			System.out.println();
    			
    			writer.printf(DOUBLE_FORMAT, s.currentMedian);
    			
    			
    	//		System.out.println();
    			
    		}
    	}
    	catch (FileNotFoundException e)
    	{
    		System.out.println(FNF_MESSAGE);
    	}
    	
    	writer.close();

    }

    /**
     * YOU ARE NOT COMPULSORILY REQUIRED TO IMPLEMENT THIS METHOD.
     *
     * That said, we found it useful to implement.
     *
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
    	
    	// The average of the two middle terms
    	if(this.maxHeap.size() == this.minHeap.size()){
    		return ((this.maxHeap.getMax() + this.minHeap.getMax()) / 2);
		}
    	
    	
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
    	
    	
  		/*if(this.maxHeap.size() == this.minHeap.size()){
			this.minHeap.insert(newReading);
			this.currentMedian = this.minHeap.getMax();
			return currentMedian;
		}
		else if (this.minHeap.size() == this.maxHeap.size() - 1)
		{
			this.minHeap.insert(newReading);
			return ((this.maxHeap.getMax() + this.minHeap.getMax()) / 2);
		}
		else if (this.minHeap.size() == this.maxHeap.size() + 1)
		{
			double maxVal = this.minHeap.removeMax();
			this.maxHeap.insert(maxVal);
			this.minHeap.insert(newReading);
			return ((this.maxHeap.getMax() + this.minHeap.getMax()) / 2);
		}
		*/
		
    	
	//	return currentMedian;
    	
    	
    }

    // DO NOT EDIT THE main METHOD.
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
