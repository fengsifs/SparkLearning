package cn.edu.fudan.anomalyDetection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {
	private int length;
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	ArrayList<Double> readFromTxt(String path){
		length = 0;
		ArrayList<Double> value = new ArrayList<>();
		try{
	//		String path = ".\\input.txt";		
			File filename = new File(path);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			
			String line = "";
			
			while((line = br.readLine()) != null){	
				if(!line.trim().equals("")){
					String[] tem = line.split(" ");
					for (String i : tem) {
						if (!i.equals("")){
							value.add(Double.parseDouble(i));	
							length++;
						}
					}
				}
				
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return value;
	}
	
	void writeToFile(String path, List<Integer> data){
		try{
					File filename = new File(path);
					filename.createNewFile();
					BufferedWriter out = new BufferedWriter(new FileWriter(filename));
					out.write("abnormal data points:");
					out.write("\r\n");
					for (int i=0; i<data.size(); i++) {
						out.write(""+data.get(i)+", ");
				//		out.write("\r\n");
					}
					out.close();
				}catch(Exception e){
					e.printStackTrace();
				}
	}
	
	public static void main(String[] args){
		String path = ".\\input.txt";
		IO input = new IO(); 
		List<Double> data = input.readFromTxt(path);
		data.forEach(System.out::println);
		System.out.println(input.length);
		
	}
}
