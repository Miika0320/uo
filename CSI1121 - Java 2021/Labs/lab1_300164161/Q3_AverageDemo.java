public class Q3_AverageDemo{
		public static void main(String[] args){
			double[] valuesArray;
			valuesArray = new double[]{100.0,34.0,72.0,56.0,82.0,67.0,94.0};
			System.out.println("The average is: " + calculateAverage(valuesArray));
		}
		
		//method to calculate avg of numbers in array
		public static double calculateAverage(double[] values){
			double result;
			result = 0;
			int num = 1;
			for (int i = 0; i < values.length; i++)
			{
				result += values[i];
				num ++;
			}
			result = result/num;
			return result;
		}
}
