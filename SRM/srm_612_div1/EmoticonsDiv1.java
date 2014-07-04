package srm_612_div1;

public class EmoticonsDiv1{
	public int printSmiles(int smiles){
		for(int i=0;i<1001;i++){
			for(int j=0;j<1001;j++)
				dp[i][j] = Integer.MAX_VALUE;
		}
		findMin(smiles, 1, 1, 1);
		return minSteps;
	}
	public int minSteps = Integer.MAX_VALUE;
	public int[][] dp = new int[1001][1001];
	public void findMin(int smiles, int steps, int clipboard, int currSmiles){
		if(steps >= minSteps || currSmiles>smiles || currSmiles<1) return;
		int left = smiles - currSmiles;
		if(dp[clipboard][currSmiles] > steps) dp[clipboard][currSmiles] = steps;
		else return;
		if(left == 0){
			if(steps<minSteps) minSteps = steps;
			return;
		}
		if(left == clipboard){
			if(steps+1<minSteps) minSteps = steps+1;
			return;
		}
		findMin(smiles, steps+1, clipboard, currSmiles+clipboard);
		findMin(smiles, steps+1, currSmiles, currSmiles);
		findMin(smiles, steps+1, clipboard, currSmiles-1);
	}
}
