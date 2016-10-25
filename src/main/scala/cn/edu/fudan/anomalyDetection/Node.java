package cn.edu.fudan.anomalyDetection;

import java.util.Arrays;

public class Node {
	private int[] NextSymbol;
	private int count;
	private double[] branchingProbability;
	String value;		
	
	public Node(int[] NextSymbol, int count, double probability, String value) {
		super();
		this.NextSymbol = NextSymbol;
		this.count = count;
		this.value = value;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int[] getNextSymbol() {
		return NextSymbol;
	}
	public void setNextSymbol(int[] NextSymbol) {
		this.NextSymbol = NextSymbol;
	}
	public double[] getbranchingProbability() {
		return branchingProbability;
	}
	public void setbranchingProbability(double[] branchingProbability) {
		this.branchingProbability = branchingProbability;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Node [NextSymbol="
				+ Arrays.toString(NextSymbol) + ", count=" + count
				+ ", branchingProbability="+ Arrays.toString(branchingProbability) + ", value=" + value + "]";
	}	
	
}
