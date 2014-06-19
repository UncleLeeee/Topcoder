package srm_625_div2;

import java.util.Arrays;

public class ConundrumReloaded {
	private int[][][] dp = new int[51][2][2];
	private String S;
	private int findSolutions(int i, int pre, int first){
		if(i == S.length()){
			if(pre!=first)
				return 100;
			else
				return 0;
		}
		if(dp[i][pre][first]!=-1)
			return dp[i][pre][first];
		char say = S.charAt(i);
		int res = Integer.MAX_VALUE;
		if(say == 'H'){
			if(pre == 0){
				res = findSolutions(i+1, 0, first);
			}else{
				res = findSolutions(i+1, 1, first);
			}
		}else if(say == 'L'){
			if(pre == 0){
				res = findSolutions(i+1, 1, first);
			}else{
				res = findSolutions(i+1, 0, first);
			}
		}else{
			res = Math.min(findSolutions(i+1, 1, first), findSolutions(i+1, 0, first));
		}
		
		if(pre == 0)
			dp[i][pre][first] = res;
		else
			dp[i][pre][first] = res+1;
		return dp[i][pre][first];
	}
	public int minimumLiars(String answers){
		this.S = answers;
		for(int i=0;i<51;i++)
			for(int j=0;j<2;j++)
				Arrays.fill(dp[i][j], -1);
		int res = Math.min(findSolutions(0, 1, 1), findSolutions(0, 0, 0));
		if(res>50)
			return -1;
		else
			return res;
	}
}
