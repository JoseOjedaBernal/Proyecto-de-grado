package modelo;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.supervised.attribute.AddClassification;


public class ClassificationPrediction {
	
	DataSource source;
	Instances traindata;
    public Instance newInst;
    public NaiveBayes nb;
    public DataSource source2;
	Instances testdata;
	Instances more;
	
	public void ini() throws Exception{
		source = new DataSource("iris.arff");
		traindata = source.getDataSet();

		traindata.setClassIndex(traindata.numAttributes()-1);
		int numClasses = traindata.numClasses();

		nb = new NaiveBayes();
		nb.buildClassifier(traindata);

		source2 = new DataSource("iris-unknown.arff");
		testdata = source2.getDataSet();
		testdata.setClassIndex(testdata.numAttributes()-1);
		
		

		more = source.getStructure();
	    newInst = new DenseInstance(3);
	    newInst.setDataset(testdata);
	    newInst.setValue(0, 1); 
		newInst.setValue(1, 1); 
		newInst.setValue(2, "NO");
	    more.add(newInst);



	}
	
	public String predict(double x, double y) throws Exception {
		
		    
			newInst.setValue(0, x); 
			newInst.setValue(1, y); 
			newInst.setMissing(2);
			
			double preNB = nb.classifyInstance(newInst);
			String predString = traindata.classAttribute().value((int) preNB);
                        System.out.println(x  +","+ y+ ","+predString);
			return predString;
		}
	

}
