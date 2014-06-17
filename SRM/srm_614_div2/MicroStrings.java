package srm_614_div2;

public class MicroStrings {
	public String makeMicroString(int A, int D){
		StringBuilder sb = new StringBuilder();
		sb.append(A);
		int now = A;
		for(int i=0;;i++){
			int curr = now - D;
			if(curr>=0)
				sb.append(curr);
			else
				break;
			now = curr;
		}
		return sb.toString();
	}
}
