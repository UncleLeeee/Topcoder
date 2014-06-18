package srm_615_div2;

import java.math.BigDecimal;

public class LongLongTripDiv2{
	public String isAble(long D, int T, int B){
		double b = (double)(D-T)/(double)(B-1);
		BigDecimal t = new BigDecimal(T);
		BigDecimal bl = new BigDecimal((long)b);
		BigDecimal bb = new BigDecimal(b);
		if(bl.compareTo(bb)!=0 || bb.compareTo(t)>0 || b<0)
			return "Impossible";
		return "Possible";
	}
}
