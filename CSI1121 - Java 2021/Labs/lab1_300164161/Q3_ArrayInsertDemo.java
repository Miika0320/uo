public class Q3_ArrayInsertDemo{
	
	public static int[] insertIntoArray(int[] beforeArray, int indexToInsert, int valueToInsert){
		int beforeLength = beforeArray.length +1;
		int[] afterArray = new int [beforeLength];
		for (int i = 0; i < afterArray.length; i++)
		{
			if( i < indexToInsert)
			{
				afterArray[i] = beforeArray[i];
			}
			else if (i == indexToInsert)
			{
				afterArray[i] = valueToInsert;
			}
			else
			{
				afterArray[i] = beforeArray[i-1];
			}
		}
		return afterArray;
	}
	
	public static void main(String[] args){
		int[] before = {1,5,4,7,9,6};
		int index = 3;
		int newNum = 15;
		int[] after = insertIntoArray(before, index, newNum);
		
		System.out.println("Array before insertion:");
		for (int i = 0; i < before.length; i++)
		{
			System.out.println(before[i]);
		}
		System.out.println("Array after insertion of "+newNum+" at position "+index+":");
		for (int i = 0; i < after.length; i++)
		{
			System.out.println(after[i]);
		}
	}
}
