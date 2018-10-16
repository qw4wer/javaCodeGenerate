package tk.qw4wer.codeGenerate;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class T2 {
	
	public static void main(String[] args) {
		System.out.println(getS(0, 3));
		
		
		
	}
	
	private static  String getS(int dividend,int divisor) {
		double m = (double)dividend / (double)divisor * 100.0;
		BigDecimal l = new BigDecimal(Double.toString(m));
		double res = l.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(res);
	}
}
