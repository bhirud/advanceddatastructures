import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
 
public class RandomGenerator {
	public static void main(String[] args) {
		 		    int d,op=0,n=0;
					Scanner s = new Scanner(System.in);
					System.out.println("Enter number of operations to generate");
					op =  s.nextInt();
					
					System.out.println("Enter the range of values to be inserted");
					n = s.nextInt();
					
		
		try {
 		   
			File file = new File("/users/dhanu/Desktop/input.txt");
 
			if (!file.exists()) {
				file.createNewFile();
			}
			
			Random generator = new Random();
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int j = 0; j< op; j++)
							{
								d= generator.nextInt(2);
					
								if( d == 1)
									bw.write("I "+generator.nextInt(n)+"\n");
								else
									bw.write("D\n");
							}
			bw.write("*");
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}