package dss;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

public class C45DecisionTree {
	// Our decision tree
	J48 tree;
	String data_train_path = "data/toa_an/toa_an.arff";
	String data_predict_path = "data/toa_an/toa_an_predict.arff";
	InputStream input = getClass().getResourceAsStream("/toa_an.arff");

	public C45DecisionTree() {
		// Get data from arff file
		Instances data = this.getData(this.data_train_path);
		// Create the j48 tree
		this.create_tree(data);
	}

	public int result_label(String input) {
		// Append data to the predict file
		FileWriter fw;
		try {
			// Write the input to the data predict file
			fw = new FileWriter(this.data_predict_path, true);
			fw.write(input);
			fw.close();
			// Get data from arff file
			Instances data_predict = this.getData(this.data_predict_path);
			data_predict.setClassIndex(data_predict.numAttributes() - 1);
			// Get the result label from the tree
			return (int) this.tree.classifyInstance(data_predict.instance(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}



	public Instances getData(String file_path) {
		// Get data from arff file
		DataSource source;
		try {
			source = new DataSource(file_path);
			// Putting data into the format which can be used with WEKA functions
			Instances data = source.getDataSet();
			// By default classIndex is -1
			if (data.classIndex() == -1) {
				// We need to setup classIndex to Number of attributes (total columns) -1
				// because last attribute is usually the target attribute
				data.setClassIndex(data.numAttributes() - 1);
			}
			// Contain the class attr from the classifier
			Instances predict = new Instances(data);
			predict.delete();
			// Write the empty dataset to the arff
			File result = new File(this.data_predict_path);
			PrintWriter writer = new PrintWriter(result, "UTF-8");
			writer.println(predict.toString());
			writer.close();
			// Return our data
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void create_tree(Instances data) {
		// J48 is WEKA implementation of C4.5
		this.tree = new J48();
		// Using an pruned J48 - by default pruning is by Sub Tree Raising method
		tree.setUnpruned(false);
		// Building the classifier model
		try {
			// Train the model with our training data
			tree.buildClassifier(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		C45DecisionTree c45 = new C45DecisionTree();
		DataSource source;
		try {
			source = new DataSource(c45.input);
			// Putting data into the format which can be used with WEKA functions
			Instances data = source.getDataSet();
			// By default classIndex is -1
			if (data.classIndex() == -1) {
				// We need to setup classIndex to Number of attributes (total columns) -1
				// because last attribute is usually the target attribute
				data.setClassIndex(data.numAttributes() - 1);
			}
			// Contain the class attr from the classifier
			Instances predict = new Instances(data);
			predict.delete();
			// Write the empty dataset to the arff
			
//			System.out.println(predict.attribute(4).addStringValue("1"));
			
			
			double[] instanceValue1 = new double[predict.numAttributes()];
//
	        instanceValue1[0] = 1;
	        instanceValue1[1] = 0;
	        instanceValue1[2] = 0;
	        instanceValue1[3] = 0;
	        instanceValue1[4] = Utils.missingValue();
//	        
	        predict.add(new DenseInstance(1.0,instanceValue1));
	        
	        System.out.println(predict);
	        
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
