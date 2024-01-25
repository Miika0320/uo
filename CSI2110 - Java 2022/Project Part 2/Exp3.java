//Mikaela Dobie 300164161
import java.util.List;
import java.util.ArrayList;

import java.io.*;  
import java.util.Scanner;  

public class Exp3{
	
	
	public static void main(String[] args)throws Exception{
		Scanner choose = new Scanner (System.in);
		System.out.println("0 for linear, 1, for KD, or anything else to exit");
		String choice = choose.nextLine();
		
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
		
		if (choice.equals("0")){
			startTime = System.nanoTime();
			DBScan.main(args);
			endTime = System.nanoTime();
			
			duration = (endTime - startTime);
		}else if (choice.equals("1")){
			startTime = System.nanoTime();
			DBScan1.main(args);
			endTime = System.nanoTime();
			
			duration = (endTime - startTime);
		}else{
			System.exit(0);
		}
		
		System.out.println("Total runtime: "+ duration);
	}

}