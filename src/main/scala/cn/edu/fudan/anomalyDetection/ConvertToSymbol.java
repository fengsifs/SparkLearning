package cn.edu.fudan.anomalyDetection;

import java.util.ArrayList;
import java.util.List;

public class ConvertToSymbol {
	
	private static double computeAVG(List<Double> data){
		double sum=0;
		for(double i : data){
			sum += i;
		}
		
		return sum/data.size();
	}
	
	private static double computeSTD(List<Double> data){
		double avg = computeAVG(data);
		double sum = 0;
		for(double i : data){
			sum += Math.pow(i-avg, 2);
		}
		return Math.sqrt(sum/data.size());
	}
	
	private static double[] paaSegments(List<Double> data, int winSize){
		int length = data.size();
		int nseg = length / winSize;
		double[] PAA = new double[nseg];
		if(length % winSize != 0){
			System.out.println("winSize must be divisible by data length!");
			return null;
		}
		else{
			double avg = computeAVG(data);
			double std = computeSTD(data);
			for(int i=0; i<data.size(); i++){
				data.set(i, (data.get(i)-avg) / std);
			}
			
			for(int i=0; i<nseg; i++){
				double sum = 0;
				for(int j=0; j<winSize; j++){
					sum += data.get(i*winSize+j);
				}
				PAA[i] = sum / winSize;
			}
			return PAA;			
		}
	}
	
	public static String convertToSym(List<Double> data, int alphabetSize, int winSize ){
		String symbols="";
		double[] cut_points = new double[alphabetSize-1];
		if(alphabetSize > 20){
			System.out.println("currently alphabet size can't be larger than 20. "
					+ "please update the breakpoint table if you wish to do so!");
			return null;
		}
		else{
			switch (alphabetSize){
		        case 2: 
		        	cut_points[0]  = 0;//{Double.NEGATIVE_INFINITY, 0};
		        	break;
		        case 3: 
		        	cut_points[0] = -0.43;
		        	cut_points[1] =  0.43;
		        	break;
		        case 4: 
		        	cut_points[0] = -0.67;
		        	cut_points[1] =  0;
		        	cut_points[2] =  0.67;
		        	break;
		        case 5: 
		        	cut_points[0] = -0.84;
		        	cut_points[1] =  -0.25;
		        	cut_points[2] =  0.25;
		        	cut_points[3] =  0.84;
		        	break;
		        case 6: 
		        	cut_points[0] = -0.97;
		        	cut_points[1] = -0.43;
		        	cut_points[2] = 0;
		        	cut_points[3] = 0.43;
		        	cut_points[4] = 0.97;
		        	break;
		        case 7: 
		        	cut_points[0] = -1.07;
		        	cut_points[1] = -0.57;
		        	cut_points[2] = -0.18;
		        	cut_points[3] = 0.18;
		        	cut_points[4] = 0.57;
		        	cut_points[5] = 1.07;
		        	break;
		        case 8: 
		        	cut_points[0] = -1.15;
		        	cut_points[1] = -0.67;
		        	cut_points[2] = -0.32;
		        	cut_points[3] = 0;
		        	cut_points[4] = 0.32;
		        	cut_points[5] = 0.67;
		        	cut_points[6] = 1.15;
		        	break;
		        case 9: 
		        	cut_points[0] = -1.22;
		        	cut_points[1] = -0.76;
		        	cut_points[2] = -0.43;
		        	cut_points[3] = -0.14;
		        	cut_points[4] = 0.14;
		        	cut_points[5] = 0.43;
		        	cut_points[6] = 0.76;
		        	cut_points[7] = 1.22;
		        	break;
		        case 10: 
		        	cut_points[0] = -1.28;
		        	cut_points[1] = -0.84;
		        	cut_points[2] = -0.52;
		        	cut_points[3] = -0.25;
		        	cut_points[4] = 0;
		        	cut_points[5] = 0.25;
		        	cut_points[6] = 0.52;
		        	cut_points[7] = 1.84;
		        	cut_points[8] = 1.28;
		        	break;
		        case 11: 
		        	cut_points[0] = -1.34;
		        	cut_points[1] = -0.91;
		        	cut_points[2] = -0.6;
		        	cut_points[3] = -0.35;
		        	cut_points[4] = -0.11;
		        	cut_points[5] = 0.11;
		        	cut_points[6] = 0.35;
		        	cut_points[7] = 0.6;
		        	cut_points[8] = 0.91;
		        	cut_points[9] = 1.34;
		        	break;
		        case 12: 
		        	cut_points[0] = -1.38;
		        	cut_points[1] = -0.97;
		        	cut_points[2] = -0.67;
		        	cut_points[3] = -0.43;
		        	cut_points[4] = -0.21;
		        	cut_points[5] = 0;
		        	cut_points[6] = 0.21;
		        	cut_points[7] = 0.43;
		        	cut_points[8] = 0.67;
		        	cut_points[9] = 0.97;
		        	cut_points[10] = 1.38;
		        	break;
		        case 13: 
		        	cut_points[0] = -1.43;
		        	cut_points[1] = -1.02;
		        	cut_points[2] = -0.74;
		        	cut_points[3] = -0.5;
		        	cut_points[4] = -0.29;
		        	cut_points[5] = -0.1;
		        	cut_points[6] = 0.1;
		        	cut_points[7] = 0.29;
		        	cut_points[8] = 0.5;
		        	cut_points[9] = 0.74;
		        	cut_points[10] = 1.02;
		        	cut_points[11] = 1.43;
		        	break;
		        case 14: 
		        	cut_points[0] = -1.47;
		        	cut_points[1] = -1.07;
		        	cut_points[2] = -0.79;
		        	cut_points[3] = -0.57;
		        	cut_points[4] = -0.37;
		        	cut_points[5] = -0.18;
		        	cut_points[6] = 0.;
		        	cut_points[7] = 0.18;
		        	cut_points[8] = 0.37;
		        	cut_points[9] = 0.57;
		        	cut_points[10] = 0.79;
		        	cut_points[11] = 1.07;
		        	cut_points[12] = 1.47;
		        	break;
		        case 15: 
		        	cut_points[0] = -1.5;
		        	cut_points[1] = -1.11;
		        	cut_points[2] = -0.84;
		        	cut_points[3] = -0.62;
		        	cut_points[4] = -0.43;
		        	cut_points[5] = -0.25;
		        	cut_points[6] = -0.08;
		        	cut_points[7] = 0.08;
		        	cut_points[8] = 0.25;
		        	cut_points[9] = 0.43;
		        	cut_points[10] = 0.62;
		        	cut_points[11] = 0.84;
		        	cut_points[12] = 1.11;
		        	cut_points[13] = 1.5;
		        	break;
		        case 16: 
		        	cut_points[0] = -1.53;
		        	cut_points[1] = -1.15;
		        	cut_points[2] = -0.89;
		        	cut_points[3] = -0.67;
		        	cut_points[4] = -0.49;
		        	cut_points[5] = -0.32;
		        	cut_points[6] = -0.16;
		        	cut_points[7] = 0;
		        	cut_points[8] = 0.16;
		        	cut_points[9] = 0.32;
		        	cut_points[10] = 0.49;
		        	cut_points[11] = 0.67;
		        	cut_points[12] = 0.89;
		        	cut_points[13] = 1.15;
		        	cut_points[14] = 1.53;
		        	break;
		        case 17: 
		        	cut_points[0] = -1.56;
		        	cut_points[1] = -1.19;
		        	cut_points[2] = -0.93;
		        	cut_points[3] = -0.72;
		        	cut_points[4] = -0.54;
		        	cut_points[5] = -0.38;
		        	cut_points[6] = -0.22;
		        	cut_points[7] = -0.07;
		        	cut_points[8] = 0.07;
		        	cut_points[9] = 0.22;
		        	cut_points[10] = 0.38;
		        	cut_points[11] = 0.54;
		        	cut_points[12] = 0.72;
		        	cut_points[13] = 0.93;
		        	cut_points[14] = 1.19;
		        	cut_points[15] = 1.56;
		        	break;
		        case 18: 
		        	cut_points[0] = -1.59;
		        	cut_points[1] = -1.22;
		        	cut_points[2] = -0.97;
		        	cut_points[3] = -0.76;
		        	cut_points[4] = -0.59;
		        	cut_points[5] = -0.43;
		        	cut_points[6] = -0.28;
		        	cut_points[7] = -0.14;
		        	cut_points[8] = 0;
		        	cut_points[9] = 0.14;
		        	cut_points[10] = 0.28;
		        	cut_points[11] = 0.43;
		        	cut_points[12] = 0.59;
		        	cut_points[13] = 0.76;
		        	cut_points[14] = 0.97;
		        	cut_points[15] = 1.22;
		        	cut_points[16] = 1.59;
		        	break;
		        case 19: 
		        	cut_points[0] = -1.62;
		        	cut_points[1] = -1.25;
		        	cut_points[2] = -1;
		        	cut_points[3] = -0.8;
		        	cut_points[4] = -0.63;
		        	cut_points[5] = -0.48;
		        	cut_points[6] = -0.34;
		        	cut_points[7] = -0.2;
		        	cut_points[8] = -0.07;
		        	cut_points[9] = 0.07;
		        	cut_points[10] = 0.2;
		        	cut_points[11] = 0.34;
		        	cut_points[12] = 0.48;
		        	cut_points[13] = 0.63;
		        	cut_points[14] = 0.8;
		        	cut_points[15] = 1;
		        	cut_points[16] = 1.25;
		        	cut_points[17] = 1.62;
		        	break;
		        case 20: 
		        	cut_points[0] = -1.64;
		        	cut_points[1] = -1.28;
		        	cut_points[2] = -1.04;
		        	cut_points[3] = -0.84;
		        	cut_points[4] = -0.67;
		        	cut_points[5] = -0.52;
		        	cut_points[6] = -0.39;
		        	cut_points[7] = -0.25;
		        	cut_points[8] = -0.13;
		        	cut_points[9] = 0;
		        	cut_points[10] = 0.13;
		        	cut_points[11] = 0.25;
		        	cut_points[12] = 0.39;
		        	cut_points[13] = 0.52;
		        	cut_points[14] = 0.67;
		        	cut_points[15] = 0.84;
		        	cut_points[16] = 1.04;
		        	cut_points[17] = 1.28;
		        	cut_points[18] = 1.64;
		        	break;
			}//switch
			
			String table = "abcdefghijklmnopqrst";
			double[] PAA = paaSegments(data, winSize);


			assert PAA != null;
			for(double i : PAA){
				int index = cut_points.length;
				for(int j=0; j< cut_points.length; j++){
					if(i < cut_points[j])
					{
						index = j;
						break;
					}
				}
				symbols += table.charAt(index);
			}

			return symbols;
		}
		
	}
	
	
	
	public static void main(String[] args){
		String symbols;
		ArrayList<Double> data = new ArrayList<>();
		for(double i=0; i<24; i++){
			data.add(i);
		}
//		double[] data = {0,1,2,2,3,4,4,5,6,6,7,8,8,9,10,10,11,12,12,13,14,14,15,16};
		int nseg = 8;
		int alphabetSize=4;
		symbols = convertToSym(data, alphabetSize, nseg);
		System.out.println(symbols);
	}
}
