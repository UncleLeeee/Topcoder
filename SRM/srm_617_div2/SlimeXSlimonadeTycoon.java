package srm_617_div2;

import java.util.Queue;
import java.util.LinkedList;
public class SlimeXSlimonadeTycoon{
	class Slime{
		public int size;
		public int days;
		public Slime(int s, int d){
			this.size = s;
			this.days = d;
		}
	}
	public int sell(int[] morning, int[] customers, int stale_limit){
		Queue<Slime> queue = new LinkedList<Slime>();
		int len = morning.length;
		int remain = 0;
		int sell = 0;
		for(int i=0;i<len;i++){
			queue.offer(new Slime(morning[i],i));
			remain += morning[i];
			int needs = customers[i];
			while(!queue.isEmpty()&&needs>0){
				Slime temp = queue.peek();
				if(i-temp.days>=stale_limit)
					queue.poll();
				else{
					if(temp.size<=needs){
						queue.poll();
						needs -= temp.size;
						sell += temp.size;
					}else{
						temp.size -= needs;
						sell += needs;
						needs = 0;
					}
				}
			}
		}
		return sell;
	}
}
