package srm_614_div2;

public class MinimumSquareEasy {
	public static void main(String[] args) {
		MinimumSquareEasy m = new MinimumSquareEasy();
		int[] x = {1000000000, -1000000000, 1000000000, -1000000000};
		int[] y = {1000000000, 1000000000, -1000000000, -1000000000};
		System.out.println(m.minArea(x, y));
	}
	public long minArea(int[] x, int[] y){
		int len = x.length;
		long minSquare = Long.MAX_VALUE;
		for(int i=0;i<len;i++){
			for(int j=i+1;j<len;j++){
				int leftMost = Integer.MAX_VALUE;
				int rightMost = Integer.MIN_VALUE;
				int upMost = Integer.MIN_VALUE;
				int downMost = Integer.MAX_VALUE;
				for(int k=0;k<len;k++){
					if(k == i || k == j)
						continue;
					if(x[k]<leftMost)
						leftMost = x[k];
					if(x[k]>rightMost)
						rightMost = x[k];
					if(y[k]>upMost)
						upMost = y[k];
					if(y[k]<downMost)
						downMost = y[k];
				}
				long lm = (long)leftMost - 1;
				long rm = (long)rightMost + 1;
				long um = (long)upMost + 1;
				long dm = (long)downMost - 1;
				long area = Math.max(rm-lm, um-dm) * Math.max(rm-lm, um-dm);
				if(area < minSquare)
					minSquare = area;
			}
		}
		return minSquare;
	}
}
