package srm_617_div2;

import java.util.BitSet;

public class MyVeryLongCake {
	public int cut(int n){
		int[] primes = new int[31624];
		int counter = 0;
		int temp = n;
		for(int i=2;i<=Math.ceil(Math.sqrt(n))+1;i++){
			if(temp%i == 0){
				primes[counter++] = i;
				while(temp%i==0){
					temp = temp/i;
				}
			}
		}
		if(temp>1)
			primes[counter++] = temp;
		int r = n;
		for(int i=0;i<counter;i++){
			r -= r/primes[i];
		}
		return n-r;
	}
	
	public static void main(String[] args) {
		MyVeryLongCake m = new MyVeryLongCake();
		System.out.println(m.cut(577007436));
	}
}
