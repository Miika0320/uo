/**
 * This class enables the construction of a decision tree
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */

public class DecisionTree {

	private static class Node<E> {
		E data;
		Node<E>[] children;

		Node(E data) {
			this.data = data;
		}
	}

	Node<VirtualDataSet> root;

	/**
	 * @param data is the training set (instance of ActualDataSet) over which a
	 *             decision tree is to be built
	 */
	public DecisionTree(ActualDataSet data) {
		root = new Node<VirtualDataSet>(data.toVirtual());
		build(root);
	}

	/**
	 * The recursive tree building function
	 * 
	 * @param node is the tree node for which a (sub)tree is to be built
	 */
	@SuppressWarnings("unchecked")
	private void build(Node<VirtualDataSet> node) throws ArrayIndexOutOfBoundsException, NullPointerException{
		// WRITE YOUR CODE HERE!
		boolean edge = false;
		if (node != null)
			if (node.data != null)
				if (node.data.getNumberOfAttributes()>0)
					if (node.data.getNumberOfDatapoints()>0)
						edge = true;
					else
						throw new ArrayIndexOutOfBoundsException();
				else
					throw new ArrayIndexOutOfBoundsException();
			else
				throw new NullPointerException();
		else
			throw new NullPointerException();
		
		if (edge == true){
			int[] attLen = new int[node.data.getNumberOfAttributes()];
			int check = 0;
			for (int z=0;z<node.data.getNumberOfAttributes();z++){
				attLen[z] = node.data.getUniqueAttributeValues(z).length;
				if (attLen[z]<=1)
					check = -1;
			}
			if (node.data.getNumberOfAttributes() == 1)
				return;
			else if (check == -1)
				return;
			else{
				GainInfoItem[] sortedAttributes = InformationGainCalculator.calculateAndSortInformationGains(node.data);
				if (sortedAttributes==null)
					throw new NullPointerException();
				int bestIndex = node.data.getAttribute(sortedAttributes[0].getAttributeName()).getAbsoluteIndex();
				String indexName = sortedAttributes[0].getAttributeName();
				VirtualDataSet[] split;
				
				for (int m=0;m<node.data.getNumberOfAttributes();m++)
					if(indexName.equals(node.data.getAttribute(m).getName()))
						bestIndex = m;
				if (sortedAttributes[0].getAttributeType() == AttributeType.NOMINAL){
					node.children = (Node<VirtualDataSet>[]) new Node[node.data.getUniqueAttributeValues(bestIndex).length];
					split = node.data.partitionByNominallAttribute(bestIndex);
					for(int i=0;i<split.length;i++){
						Node<VirtualDataSet> temp = new Node<VirtualDataSet>(split[i]);
						node.children[i] = temp;
						build(node.children[i]);
					}
				}
				else{
					node.children = (Node<VirtualDataSet>[]) new Node[2];
					int splitAt =-1;
					for (int k=0;k<node.data.getAttribute(sortedAttributes[0].getAttributeName()).getValues().length;k++)
						if (node.data.getAttribute(sortedAttributes[0].getAttributeName()).getValues()[k] == sortedAttributes[0].getSplitAt())
							splitAt = k;
					
					split = node.data.partitionByNumericAttribute(bestIndex,splitAt);
					for(int j=0;j<split.length;j++){
						Node<VirtualDataSet> temp = new Node<VirtualDataSet>(split[j]);
						node.children[j] = temp;
						build(node.children[j]);
					}
				}
				 
			}
		}
	}

	@Override
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * The recursive toString function
	 * 
	 * @param node        is the tree node for which an if-else representation is to
	 *                    be derived
	 * @param indentDepth is the number of indenting spaces to be added to the
	 *                    representation
	 * @return an if-else representation of node
	 */
	private String toString(Node<VirtualDataSet> node, int indentDepth) throws IllegalArgumentException, NullPointerException{
		// WRITE YOUR CODE HERE!
		String str = "";
		if (node == null)
			throw new NullPointerException();
		else if(indentDepth<0)
			throw new IllegalArgumentException();
		if (node.children!= null){
			for (int i=0;i<node.children.length;i++){
				
				if (i==0){
					str += createIndent(indentDepth)+"if ("+node.children[i].data.getCondition()+") {\n";
					str += toString(node.children[i], indentDepth+1)+createIndent(indentDepth)+"}\n";
				}
				else{
					str += createIndent(indentDepth)+"else if ("+node.children[i].data.getCondition()+") {\n";
					str += toString(node.children[i], indentDepth+1)+createIndent(indentDepth)+"}\n";
				}
				
			}
			return str;
		}
		else
			str = createIndent(indentDepth+1)+node.data.getAttribute(node.data.getNumberOfAttributes()-1).getName()+" = "+node.data.getValueAt(0, node.data.getNumberOfAttributes()-1)+"\n";
		return str;
		// Remove the following line once you have implemented the method
		
	}

	/**
	 * @param indentDepth is the depth of the indentation
	 * @return a string containing indentDepth spaces; the returned string (composed
	 *         of only spaces) will be used as a prefix by the recursive toString
	 *         method
	 */
	private static String createIndent(int indentDepth) {
		if (indentDepth <= 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < indentDepth; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

	public static void main(String[] args) throws Exception {
	
		StudentInfo.display();

		if (args == null || args.length == 0) {
			System.out.println("Expected a file name as argument!");
			System.out.println("Usage: java DecisionTree <file name>");
			throw new IllegalArgumentException();
		}

		String strFilename = args[0];

		ActualDataSet data = new ActualDataSet(new CSVReader(strFilename));

		DecisionTree dtree = new DecisionTree(data);

		System.out.println(dtree);
	}
}