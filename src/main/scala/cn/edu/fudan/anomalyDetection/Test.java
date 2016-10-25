package cn.edu.fudan.anomalyDetection;

import java.util.ArrayList;
import java.util.List;

public class Test {
	String init(String filename, int alphabetSize, int winSize){

		IO input = new IO();
		List<Double> data = input.readFromTxt(filename);
		System.out.println("original data points:"+input.getLength());
		
		int length = input.getLength();
		if(length % winSize != 0){
			for(int i=0; i<length%winSize; i++){
				data.remove(length - length%winSize);
			}
		}
		return ConvertToSymbol.convertToSym(data, alphabetSize, winSize);
	}

	public static List<Integer> anomalies(String data, String test, int level, int alphabetSize,
										  int MinCount, double theta) {
		PST tree = new PST();
		ArrayList<Node> T = tree.construct(data, level, alphabetSize, MinCount);

		findingAnomaly A = new findingAnomaly();
		List<Integer> abnormalPoints = A.detectAnomaly(test, T, level, alphabetSize,theta, MinCount);

		System.out.println("number of anomaly points: "+abnormalPoints.size());
		System.out.println();

		Fmeasure f1 = new Fmeasure();
		f1.computeFmeasure(abnormalPoints);
		System.out.println("P: "+f1.getP()+",	R: "+f1.getR()+",	F-Measure: "+f1.computeFmeasure(abnormalPoints));

		return abnormalPoints;
	}
	
	List<Integer> anomalyDetection(String filename, String testfilename, String path,
			int alphabetSize, int level, int MinCount,int winSize, double theta){

//		String data = init(filename, alphabetSize, winSize);
//		String test = init(testfilename, alphabetSize, winSize);
		String data = "abcbcbab";
		String test = "abc";
		
		if(data!=null && test!=null){
			PST tree = new PST();
			ArrayList<Node> T = tree.construct(data, level, alphabetSize, MinCount);

			findingAnomaly A = new findingAnomaly();
			List<Integer> abnormalPoints = A.detectAnomaly(test, T, level, alphabetSize,theta, MinCount);

			System.out.println("number of anomaly points: "+abnormalPoints.size());
			System.out.println();
			
			Fmeasure f1 = new Fmeasure();
			f1.computeFmeasure(abnormalPoints);
			System.out.println("P: "+f1.getP()+",	R: "+f1.getR()+",	F-Measure: "+f1.computeFmeasure(abnormalPoints));
			
//			IO output = new IO();
//			output.writeToFile(path, abnormalPoints);
			
			return abnormalPoints;
		}
		else{
			return null;
		}
				
		
	}
		
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		int alphabetSize=3;
		int winSize = 1;
		int level = 3;
		int MinCount = 30;
		double theta = 0.05;
		String filename = "D:\\IdeaProjects\\SparkLearning\\out\\input.txt";
		String testfilename = "D:\\IdeaProjects\\SparkLearning\\out\\test.txt";
		String path = "D:\\IdeaProjects\\SparkLearning\\out\\output.txt";

		Test temp = new Test();
		List<Integer> abnormalPoints = temp.anomalyDetection(filename, testfilename, path, alphabetSize, level, MinCount, winSize, theta);
		
		long stop = System.currentTimeMillis();
		long time = (stop - start) / 1000;
		long millis = (stop - start) % 1000;
		System.out.println("runtime: "+time+"second, "+millis+"millisecond");
		if(abnormalPoints != null){
			System.out.println("anomaly: "+abnormalPoints.size());
			abnormalPoints.forEach(System.out::println);
		}
		
	}

	
}
