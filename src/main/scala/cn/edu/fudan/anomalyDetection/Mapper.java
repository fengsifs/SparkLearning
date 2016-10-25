package cn.edu.fudan.anomalyDetection;

import java.util.HashMap;

public class Mapper {
	private HashMap<String,Integer> Map = new HashMap<String,Integer>();
	
	public String mapToString(int key){
		HashMap<Integer,String> Map = new HashMap<Integer,String>();
		Map.put(0, "a");
		Map.put(1, "b");
		Map.put(2, "c");
		Map.put(3, "d");
		Map.put(4, "e");
		Map.put(5, "f");
		Map.put(6, "g");
		Map.put(7, "h");
		Map.put(8, "i");
		Map.put(9, "j");
		Map.put(10, "k");
		Map.put(11, "l");
		Map.put(12, "m");
		Map.put(13, "n");
		Map.put(14, "o");
		Map.put(15, "p");
		Map.put(16, "q");
		Map.put(17, "i");
		Map.put(18, "s");
		Map.put(19, "t");
		return Map.get(key);
	}
	
	public int mapToInteger(String key){
		Map.put("a", 0);
		Map.put("b", 1);
		Map.put("c", 2);
		Map.put("d", 3);
		Map.put("e", 4);
		Map.put("f", 5);
		Map.put("g", 6);
		Map.put("h", 7);
		Map.put("i", 8);
		Map.put("j", 9);
		Map.put("k", 10);
		Map.put("l", 11);
		Map.put("m", 12);
		Map.put("n", 13);
		Map.put("o", 14);
		Map.put("p", 15);
		Map.put("q", 16);
		Map.put("i", 17);
		Map.put("s", 18);
		Map.put("t", 19);
		return Map.get(key);
	}

}
