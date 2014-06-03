package srm_612_div2;

import java.util.HashSet;
import java.util.Set;

public class EmoticonsDiv2 {
	class States{
		public int smiles;
		public int clipborad;
		public States(int smiles, int clipboard) {
			this.smiles = smiles;
			this.clipborad = clipboard;
		}
		
		@Override
		public int hashCode() {
			return this.smiles*31+this.clipborad*11;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof States){
				States temp = (States)obj;
				if(temp.clipborad == this.clipborad && temp.smiles == this.smiles) 
					return true;
			}
			return false;
		}
	}
	public int printSmiles(int smiles){
		Set<States> list = new HashSet<States>();
		int steps = 0;
		boolean isFind = false;
		list.add(new States(1,0));
		while(true){
			Set<States> temp = new HashSet<States>();
			for(States i:list){
				if(i.smiles == smiles){
					isFind = true;
					break;
				}
				if(i.clipborad>0 && (i.smiles + i.clipborad) <= smiles){
					temp.add(new States(i.smiles+i.clipborad,i.clipborad));
				}
				temp.add(new States(i.smiles,i.smiles));
			}
			if(isFind) break;
			steps++;
			list = temp;
		}
		return steps;
	}
	
	public static void main(String[] args) {
		EmoticonsDiv2 e = new EmoticonsDiv2();
		int res = e.printSmiles(101);
		System.out.println(res);
	}
}
