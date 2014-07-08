package srm_613_div1;

import java.util.HashMap;

public class RandomGCD {
	final long MOD = 1000000007;
	/**
	 * 
	 * @Title:        powUnderMOD 
	 * @Description:  Calculate POWER(n,k) under a max value:MOD.
	 * @param:        @param n
	 * @param:        @param k
	 * @param:        @return    
	 * @return:       long    
	 * @throws 
	 */
	public long powUnderMOD(long n, long k){
		long r = 1;
		while(k>0){
			if((k&1)==1)
				r = (r*n)%MOD;
			n = (n*n)%MOD;
			k = k>>1;
		}
		return r;
	}
	
	class Pair{
		public int low;
		public int high;
		public Pair(int low, int high) {
			this.low = low;
			this.high = high;
		}
		@Override
		public int hashCode() {
			return low*31+high;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Pair){
				Pair p = (Pair)obj;
				if(low == p.low && high == p.high)
					return true;
			}
			return false;
		}
	}

	HashMap<Pair, Integer> dpMap = new HashMap<Pair, Integer>();
	public int countTuples(int N, int K, int low, int high){
		if(K>1)
			return countTuples(N, 1, (int)Math.ceil((double)low/(double)K), (int)Math.floor((double)high/(double)K));
		if(low>high)
			return 0;
		Pair curr = new Pair(low,high);
		if(dpMap.containsKey(curr))
			return dpMap.get(curr);
		long res = powUnderMOD(high-low+1, N);
		for(int i=2;i<=high;i++){
			if(i>(high-low+1)&&i<low)
				i=low;
			res = (res - countTuples(N, i, low, high) + MOD)%MOD;
		}
		dpMap.put(curr, (int)res);
		return dpMap.get(curr);
	}
}
