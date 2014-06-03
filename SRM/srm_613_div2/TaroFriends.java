package srm_613_div2;

import java.util.Arrays;

public class TaroFriends {
	public int getNumber(int[] coordinates, int X){
		Arrays.sort(coordinates);
		int len = coordinates.length;
		int res = coordinates[len-1] - coordinates[0];
		for(int i=1;i<len;i++){
			int l1 = coordinates[0]+X;
			int l2 = coordinates[i-1]+X;
			int r1 = coordinates[i]-X;
			int r2 = coordinates[len-1]-X;
			int left = Math.min(l1,r1);
			int right = Math.max(l2,r2);
			if(right - left<res)
				res = right-left;
		}
		return res;
	}
}
