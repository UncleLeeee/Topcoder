package srm_625_div2;


public class IncrementingSequence {
	public String canItBeDone(int k, int[] A){
		boolean[] marked = new boolean[51];
		for(int i=0;i<A.length;i++){
			int curr = A[i];
			if(!marked[curr])
				marked[curr] = true;
			else{
				for(int j=curr+1;j<=A.length;j++){
					if(!marked[j]){
						if((j-curr)%k == 0){
							marked[j] = true;
							break;
						}
					}
				}
			}
		}
		for(int i=1;i<=A.length;i++){
			if(!marked[i])
				return "IMPOSSIBLE";
		}
		return "POSSIBLE";
	}
}
