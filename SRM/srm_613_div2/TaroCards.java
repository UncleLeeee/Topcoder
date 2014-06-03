package srm_613_div2;

import java.util.Arrays;

public class TaroCards {
	
	private long[][][] dp = new long[41][51][1024];
	private int[] first;
	private int[] second;
	private int K;
	
	private long getWays(int uNums, int index, int vecs){
		if(dp[uNums][index][vecs] != -1)
			return dp[uNums][index][vecs];
		if(index >= first.length){
			if(uNums + countingBits(vecs) <= K)
				return 1;
			else return 0;
		}
		long res = 0;
		int[] pairs = {first[index], second[index]};
		int nuNums = uNums;
		int nVecs = vecs;
		for(int i:pairs){
			if(i<=10){
				nVecs |= 1<<(i-1);
			}else{
				nuNums += 1;
			}
		}
		res += getWays(nuNums, index+1, nVecs);
		res += getWays(uNums, index+1, vecs);
		dp[uNums][index][vecs] = res;
		return res;
	}
	
	private int countingBits(int n){
		int res = 0;
		for(int i=1;i<1024;i = i<<1){
			if((n&i)!=0)
				res += 1;
		}
		return res;
	}
	
	
	public long getNumber(int[] first, int[] second, int K){
		this.first = first;
		this.second = second;
		this.K = K;
		for(int i=0;i<41;i++){
			for(int j=0;j<51;j++)
				Arrays.fill(dp[i][j], -1);
		}
		return getWays(0,0,0);
	}
}
