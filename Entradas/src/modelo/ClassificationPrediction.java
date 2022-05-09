package modelo;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.supervised.attribute.AddClassification;


public class ClassificationPrediction {
	
	DataSource source;
	Instances traindata;
	Attribute X;
    Attribute Y;
    Instance newInst;
    NaiveBayes nb;
    DataSource source2;
	Instances testdata;
	
	public void ini() throws Exception{
		source = new DataSource("iris.arff");
		traindata = source.getDataSet();
		traindata.setClassIndex(traindata.numAttributes()-1);
		int numClasses = traindata.numClasses();
		for (int i=0;i<numClasses;i++){
			String classValue = traindata.classAttribute().value(i);
			System.out.println("the "+i+"th class value:"+classValue);
		}
		nb = new NaiveBayes();
		nb.buildClassifier(traindata);

		source2 = new DataSource("iris-unknown.arff");
		testdata = source2.getDataSet();
		testdata.setClassIndex(testdata.numAttributes()-1);
		
		

		X = new Attribute("X");		
	    Y = new Attribute("Y");
	    newInst = new DenseInstance(3);
	    newInst.setDataset(testdata);
	}
	
	public void predict(double x, double y) throws Exception {
		
		    
			newInst.setValue(0, x); 
			newInst.setValue(1, y); 
			newInst.setMissing(2);
			
			double preNB = nb.classifyInstance(newInst);
			String predString = traindata.classAttribute().value((int) preNB);
			System.out.println(predString);
		}
	

}
