import java.util.Scanner;

public class Q6{
	public static void main(String[] args){
		double[] notes = new double [10];
		Scanner grades = new Scanner(System.in);
		for (int i = 0; i < 10; i++)
		{
			//user input grades "Please enter a grade: 
			System.out.println("Please enter a grade: ("+(i+1)+"/10):");
			notes[i] = grades.nextDouble();
			if (notes[i]<0 || notes[i]>100){
				System.out.println("Please enter a number between 0 and 100:");
				notes[i] = grades.nextDouble();
			}
		}
		
		double avg = calculateAverage(notes);
		double med = calculateMedian(notes);
		int fail = calculateNumberFailed(notes);
		int pass = calculateNumberPassed(notes);
		
		System.out.println("The average of the grades is: "+avg);
		System.out.println("The median of the grades is: "+med);
		System.out.println("The number of students that failed is: "+fail);
		System.out.println("The number of students that passed is: "+pass);
	}
	public static double calculateAverage(double[] notes){
		double result;
			result = 0;
			int num = 1;
			for (int i = 0; i < notes.length; i++)
			{
				result += notes[i];
				num ++;
			}
			result = result/num;
			return result;
	}
	public static double calculateMedian(double[] notes){
		double temp;
		double med;
		for (int i = 0; i < notes.length; i++)
		{
			for (int j = i+1; j < notes.length; j++)
			{
				if (notes[i] > notes[j]){
				temp = notes[i];
				notes[i] = notes[j];
				notes[j] = temp;
				}
			}
		}
		med = (notes[4]+notes[5])/2;
		return med;
	}
	public static int calculateNumberFailed(double[] notes){
		int num = 0;
		for (int i = 0; i < notes.length; i++)
		{
			if (notes[i] < 50){
				num+=1;
			}
		}
		return num;
	}
	public static int calculateNumberPassed(double[] notes){
		int num = 0;
		for (int i = 0; i < notes.length; i++)
		{
			if (notes[i] >= 50){
				num+=1;
			}
		}
		return num;
	}
}