package srm_625_div2;

public class AddMultiply {
	public int[] makeExpression(int y){
		int[] res = new int[3];
		boolean find = false;
		for(int i=-1000;i<=1000;i++){
			for(int j=-1000;j<=1000;j++){
				if(i == 0 || i == 1 || j==0||j==1)
					continue;
				int temp = i*j;
				int other = y-temp;
				if(other != 0 && other != j && other>=-1000 && other<=1000){
					res[0] = i;
					res[1] = j;
					res[2] = other;
					find = true;
					break;
				}
			}
			if(find)
				break;
		}
		return res;
	}
}
