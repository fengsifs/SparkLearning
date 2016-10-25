package cn.edu.fudan.anomalyDetection;

import java.util.List;

public class Fmeasure {
	private double TP;
	private double FP;
	private double FN;
	double P;
	double R;

	private int[] trueAbnormalPoints = { 1091, 1093, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 
			1113, 1114, 1115, 1130, 1131, 1132, 1133, 1135, 1136,  
			1147, 1148, 1149, 1386, 1387, 1390, 1391, 1392, 1393, 1394, 1395, 1396, 1397, 1398, 1399, 1400, 1401, 1402, 1403, 1404, 1405, 
			1434, 1435, 1437, 1439, 1441, 1443, 1445, 1447, 1449, 1481, 1483, 1485, 1487, 1489, 1491, 1493, 1495, 1541, 1542, 1543, 1544, 1545, 1546, 1547, 
			1548, 1549, 1550, 1551, 1552, 1553, 1554, 1555, 1556, 1557, 1558, 1559, 1560, 1561, 1562, 1563, 1564, 1565, 1566, 1567, 
			1568, 1589, 2102, 2103, 2104, 2105, 2106, 2107, 2108, 2109, 2110, 2111, 2117, 2119, 2120, 2121, 2122, 2123, 2124, 2125, 
			2145, 2147, 2148, 2149, 2150, 2151, 2152, 2153, 2154, 2155, 2156, 2157, 2158, 2159, 2160, 2161 };

	public double getTP() {
		return TP;
	}

	public void setTP(double tP) {
		TP = tP;
	}

	public double getFP() {
		return FP;
	}

	public void setFP(double fP) {
		FP = fP;
	}

	public double getFN() {
		return FN;
	}

	public void setFN(double fN) {
		FN = fN;
	}

	public double getP() {
		return P;
	}

	public void setP(double p) {
		P = p;
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	double computeFmeasure(List<Integer> abnormalPoints){
		TP = 0;
		for(int i=0; i<abnormalPoints.size(); i++){
			for(int j=0; j<trueAbnormalPoints.length; j++){
				if(abnormalPoints.get(i) < trueAbnormalPoints[j])
					break;
				if(abnormalPoints.get(i) == trueAbnormalPoints[j]){
					TP++;
					break;
				}
			}
		}
		
		FP = abnormalPoints.size() - TP;
		FN = trueAbnormalPoints.length - TP;
		P = TP / (TP + FP);
		R = TP / (TP + FN);
		return 2 * P * R / (P + R);
	}
	
}
