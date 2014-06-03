package srm_612_div2;

import java.util.Arrays;

public class PowersOfTwo {
	
	private long[][] dp = new long[64][76];

	private int findsBit(long p){
		long i=1;
		int bit = 0;
		for(i=1;;i = i<<1,bit++){
			if((p&i) != 0)
				break;
		}
		return bit;
	}
	
	private long getSolutions(int[] bits, int i, int c){
		if(dp[i][c]!=-1)
			return dp[i][c];
		if(i == 63)
			return 1;
		long res = 0;
		res += getSolutions(bits, i+1, bits[i+1] + c/2);
		if(c>0)
			res += getSolutions(bits, i+1, bits[i+1] + (c-1)/2);
		dp[i][c] = res;
		return res;
	}
	
	public long count(long[] powers){
		int[] bits = new int[64];
		for(int i=0;i<64;i++)
			Arrays.fill(dp[i], -1);
		for(int i=0;i<powers.length;i++)
			bits[findsBit(powers[i])] += 1;
		return getSolutions(bits, 0, bits[0]);
	}
}
