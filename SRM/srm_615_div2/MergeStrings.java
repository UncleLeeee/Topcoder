package srm_615_div2;

public class MergeStrings{
	private String[][][] dp = new String[51][51][51];
	private String S;
	private String A;
	private String B;
	private String findMin(int i, int j, int k){
		if(k == S.length())
			return "[]";
		if(dp[i][j][k]!=null)
			return dp[i][j][k];
		String res = "";
		if(S.charAt(k)!='?'){
			if(i<A.length() && S.charAt(k) == A.charAt(i))
				res = findMin(i+1,j,k+1);
			if(j<B.length() && S.charAt(k) == B.charAt(j)){
				String res2 = findMin(i,j+1,k+1);
				if(res.length() == 0)
					res = res2;
				else if(res2.length()>0)
					res = res.compareTo(res2)<0?res:res2;
			}
			if(res.length() != 0)
				res = S.charAt(k) + res;
		}else{
			if(i<A.length() &&findMin(i+1,j,k+1).length()>0)
				res = A.charAt(i) + findMin(i+1,j,k+1);
			if(j<B.length() && findMin(i,j+1,k+1).length()>0){
				String res2 =  B.charAt(j) + findMin(i,j+1,k+1);
				if(res.length() > 0)
					res = res.compareTo(res2)<0?res:res2;
				else
					res = res2;
			}
		}
		dp[i][j][k] = res;
		return res;
	}
	public String getmin(String S, String A, String B){
		this.S = S;
		this.A = A;
		this.B = B;
		String res = findMin(0,0,0);
		if(res.length() > 0)
			res = res.substring(0,res.length()-2);
		return res;
	}
}
