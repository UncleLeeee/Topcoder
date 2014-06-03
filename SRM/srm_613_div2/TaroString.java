package srm_613_div2;

public class TaroString {
	public String getAnswer(String S){
		for(char x = 'A';x<='Z';x++){
			if(x == 'A' || x == 'C' || x == 'T')
				continue;
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<S.length();i++){
				if(S.charAt(i) !=x)
					sb.append(S.charAt(i));
			}
			S = sb.toString();
		}
		if(S.equals("CAT"))
			return "Possible";
		return "Impossible";
	}
}
