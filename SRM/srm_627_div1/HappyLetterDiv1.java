package srm_627_div1;
public class HappyLetterDiv1 {
	public String getHappyLetters(String letters){
		int[] cnt = new int[26];
		int sum = 0;
		for(int i=0;i<letters.length();i++){
			char curr = letters.charAt(i);
			cnt[curr-'a'] += 1;
			sum += 1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<26;i++){
			if(cnt[i] == 0)
				continue;
			int otherSum = sum - cnt[i];
			int otherMax = 0;
			for(int ii=0;ii<26;ii++){
				if(ii!=i&&cnt[ii]>otherMax)
					otherMax = cnt[ii];
			}
			int others = otherSum - otherMax;
			int need = otherMax>others?(otherMax-others):otherSum%2;
			if(cnt[i]>need)
				sb.append((char)('a'+i));
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		HappyLetterDiv1 h = new HappyLetterDiv1();
		String test = "ddabccadb";
		System.out.println(h.getHappyLetters(test));
	}
}
