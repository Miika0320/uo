
A4

This archive contains the 14 files for Assignment 4: that is, this file (README.txt),
the files DecisionTree.java, ActualDataSet.java, VirtualDataSet.java, DataReader.java, CSVReader.java, Attribute.java, AttributeType.java, DataSet.java, Util.java, EntropyEvaluator.java,
GainInfoItem.java, InformationGainCalculator.java and StudentInfo.java.

CSVReader.java creates a dataset from a CSV file. DataSet.java organizes the dataset using Attribute.java.
ActualDataSet.java and VirtualDataSet.java extend java into more detailed datasets.

EntropyEvaluator.java calulates the entropy of a spilt on a dataset. GainInfoItem.java stores gain data per attribute name and type.
InformationGainCalculator.java finds the information gain per attribute and displays it.

DecisionTree.java takes in an ActualDataSet and prints a decision tree based on highest gain.