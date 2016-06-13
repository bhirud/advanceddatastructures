import java.io.*;
import java.util.*;


public class Heap {
	
	static int m;
	static double start, stop, lTotal, bTotal;
	static double[] lTime, bTime;
	static int[] no, operation, value;
	
	public static void main (String args[])
	{
			
			//  working with the random input			
			if (args [0].compareToIgnoreCase("-r") == 0)
			{
				
				// Initialize all the variables
				initialize();
				
				// calculate time in random mode
				randomMode();
			}
			
			
			//  User mode for Leftist Tree			
			else if (args [0].compareToIgnoreCase("-il") == 0)
			{
				String arg = args[1];
				// perform operation on Leftist tree from user input file
				userModeLeftist(arg);
				
			}
			
			
			
			// create Binomial Heap
			else if (args [0].compareToIgnoreCase("-ib") == 0)
			{
				String arg = args[1];
				// perform operation on Binomial Heap from user input file
				userModeBinomial(arg);
			}
			else
				System.out.println("Wrong input.");
			
		
		
	}
	
// Generate random operations and random values.
 public static void randomGenerate(int n)
 	{
	 	Random generate = new Random();
	 	
	 	for (int i = 0; i<m; i++)
	 	{
	 		operation [i] = generate.nextInt(2);
	 		value [i] = generate.nextInt(n);
	 	}
 	}
// Randomize the values.
 public static int[] randomPermute (int[] number)
	{
		int index, t;
		Random generator = new Random();
		
		for (int i = 0; i<number.length; i++)
			number[i] = i;
		
		for (int i = 0; i<number.length; i++)
		{	
			index = generator.nextInt( number.length );
			t = number[index];
			number[index] =  number[i];
			number[i] = t;
		}
		
		
		return number;
	}

// Initialize variables.
 public static void initialize()
 {
	 m = 5000;
	 lTotal = 0;
	 bTotal = 0;
	 lTime = new double [8];
	 bTime = new double [8];
	 operation = new int [m];
	 value = new int [m];
	 no = new int [] {5000, 5000, 4000, 3000, 2000, 1000, 500, 100};
	 
	 for (int i = 0; i<8; i++){
		 lTime[i] = 0;
		 bTime[i] = 0;
	 }
	 for (int i = 0; i<m; i++){
		 operation[i] = 0;
		 value[i] = 0;
	 }
	 
	 
 }
 
 
 // calculate time in random mode
 public static void randomMode()
	 {
		 
		System.out.println("Average time of single operation :");
		for (int i = 0; i< 8; i++)
		{
			
			// Generate random operations and random numbers
			randomGenerate(no[i]);
			
			int number[] = new int[no[i]];
			number = randomPermute(number);
	
		    // Random mode for Leftist tree
			

			for (int j = 0; j< 5; j++)
			{
				LeftistTree lt = new LeftistTree();

				for (int l = 0; l<number.length; l++)
					lt.insert(number[l]);
				
				start = System.currentTimeMillis();
				
				for (int k = 0; k< m; k++)
				{

					if( operation[k] == 1)
						lt.deleteMin();
					else
						lt.insert(value[k]);
				}
				
				stop = System.currentTimeMillis();
				lTime[i] += (stop - start);
				
			}
			
			lTotal += lTime[i];
			
			
			// Random mode for Binomial Heap
			for (int j = 0; j< 5; j++)
			{
				Binomial bh = new Binomial();

				for (int l = 0; l<number.length; l++)
					bh.insert(number[l]);
				
				start = System.currentTimeMillis();
				
				for (int k = 0; k< m; k++)
				{

					if( operation[k] == 1)
						bh.removeMin();
					else
						bh.insert(value[k]);
				}
				
				stop = System.currentTimeMillis();
				bTime[i] += (stop - start);
				
			}
			
			bTotal += bTime[i];
			
			
		}
		for (int i = 7; i>0; i--)
		{
			System.out.println("n = "+no[i]);
			System.out.print("Leftist Tree: "+lTime[i]/25000.0+"\t\tBinomial Heap: "+bTime[i]/25000.0+"\n");
		}
		lTotal = lTotal - lTime[0];
		bTotal = bTotal - bTime[0];
		System.out.println("Time required for single operation on Leftist Tree is :"+ lTotal/(7*25000.0));
		System.out.println("Time required for single operation on Binomial Heap is :"+ bTotal/(7*25000.0));
		
	 }
	 
	 
	 public static void userModeLeftist(String arg)
		 {
			 
			// Leftist tree object				
			LeftistTree lt = new LeftistTree();
			
			// Read input from file line by line 			
			try
			{
				Scanner sc = new Scanner(new File(arg));
				while(sc.hasNextLine())
				{
					// Stop when file reaches "*".
					
					 String line = sc.nextLine();
					 if(line.compareToIgnoreCase("*") == 0)
						 break;
					// If line contains "D" operation is deleteMin	
					 if(line.compareToIgnoreCase("D") == 0)
					 {
						 lt.deleteMin();
					 }
					 else
					 {
						 // insert new node in the tree
						 String[] words = line.split(" ");
						 lt.insert(Integer.parseInt(words[1]));
					 }
				
				}
			}
			
			catch(Exception e) {}
			
			lt.print();
		 }
		 
		 
	 public static void userModeBinomial(String arg)
		 {
			 
			// Binomial Heap object				
			Binomial bh = new Binomial();
			
			// Read input from file line by line 			
			try
			{
				Scanner sc = new Scanner(new File(arg));
				
				while(sc.hasNextLine())
				{
					
					// Stop when file reaches "*".
					String line = sc.nextLine(); 
					if(line.compareToIgnoreCase("*") == 0)
						break;
				 
				 
				 	 // If line contains "D" operation is removeMin				 
					 if(line.compareToIgnoreCase("D") == 0)
					 {
						  bh.removeMin();
					 }
					 
					 // insert new node in heap
					 else 
					 {
						 String[] words = line.split(" ");
						 bh.insert(Integer.parseInt(words[1]));
						 
					 }
				}
			}
			
			catch(Exception e) 
			{
				System.out.println(e.toString());
			}
			
			bh.print();
			
		 }


}





