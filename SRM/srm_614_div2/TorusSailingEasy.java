package srm_614_div2;

public class TorusSailingEasy {
	
	public double expectedTime(int N, int M, int goalX, int goalY){
		int E = 1;
		for(int i=0,j=0;i!=N-1||j!=M-1;i++,j++,E++){
			if(i == N)
				i = i%N;
			if(j == M)
				j = j%M;
		}
		int[] paraUpA = new int[E];
		int[] paraUpB = new int[E];
		int[] paraDownA = new int[E];
		int[] paraDownB = new int[E];
		if(E == 1)
			return 0;
		if(E == 2){
			if(goalX == 1 && goalY == 1)
				return 1;
			else 
				return -1;
		}
		paraUpA[1] = 1;
		paraUpA[2] = 2;
		paraUpB[2] = -2;
		for(int i=3;i<E;i++){
			paraUpA[i] = 2*paraUpA[i-1] - paraUpA[i-2];
			paraUpB[i] = 2*paraUpB[i-1] - paraUpB[i-2] - 2;
		}
		paraDownA[E-1] = 1;
		paraDownA[E-2] = 2;
		paraDownB[E-2] = -2;
		for(int i=E-3;i>=1;i--){
			paraDownA[i] = 2*paraDownA[i+1] - paraDownA[i+2];
			paraDownB[i] = 2*paraDownB[i+1] - paraDownB[i+2] - 2;
		}
		if(paraUpA[E-1]*paraDownA[1] == 1)
			return -1;
		int s = (paraUpB[E-1]*paraDownA[1] + paraDownB[1])/(1-paraUpA[E-1]*paraDownA[1]);
		int[] P = new int[E];
		P[1] = s;
		for(int i=2;i<E;i++)
			P[i] = 2*P[i-1] - P[i-2] -2;
		int res = -1;
		for(int i=0,j=0,k=0;k<E;i++,j++,k++){
			if(i == N)
				i = i%N;
			if(j == M)
				j = j%M;
			if(i == goalX && j == goalY){
				res = P[k];
				break;
			}
		}
		return res;
	}
}
