package srm_612_div1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

public class SpecialCells {
	class MaximumFlow {
		class FlowEdge{
			public int v;
			public int w;
			public int flow;
			public int capacity;
			public int cost;
			
			public FlowEdge(int v, int w, int f, int c) {
				this.v = v;
				this.w = w;
				this.flow = f;
				this.capacity = c;
				this.cost = 0;
			}
			
			public FlowEdge(int v, int w, int f, int c, int co) {
				this.v = v;
				this.w = w;
				this.flow = f;
				this.capacity = c;
				this.cost = co;
			}
			
			public int getCost(int to){
				if(to == w)
					return cost;
				else
					return -cost;
			}
			
			public int other(int x){
				if(x == v) return w;
				else if(x == w) return v;
				else throw new RuntimeException("Inconsistent Edge!");
			}
			public int residualTo(int x){
				if(x == v)
					return flow;
				else
					return capacity - flow;
			}
			public void addResidualTo(int x, int delta){
				if(x == v)
					flow -= delta;
				else 
					flow += delta;
			}
			
			@Override
			public String toString() {
				return " from:"+v+" to:"+w+" c:"+cost+" f:"+flow+" cp:"+capacity;
			}
		}
		
		public int N;
		public int M;
		public int S;
		public int T;
		public ArrayList<FlowEdge>[] adjList;
		public FlowEdge[] edgeTo;
		
		/**
		 * 
		 * @param N
		 */
		public MaximumFlow(int N){
			this.N = N;
			this.M = 0;
			this.adjList = new ArrayList[N];
			for(int i=0;i<N;i++)
				this.adjList[i] = new ArrayList<FlowEdge>();
			this.edgeTo = new FlowEdge[N];
			this.costTo = new int[N];
		}
		
		/**
		 * 
		 * @Title:        addEdge 
		 * @Description:  Add an edge to the graph. 
		 * @param:        @param from
		 * @param:        @param to
		 * @param:        @param capacity
		 * @param:        @param cost    
		 * @return:       void    
		 * @throws 
		 */
		public void addEdge(int from, int to, int capacity, int cost){
			FlowEdge curr = new FlowEdge(from, to ,0 , capacity, cost);
			this.adjList[from].add(curr);
			this.adjList[to].add(curr);
			M += 1;
		}
		
		public int maxFlows;
		
		public int minCosts;
		public int[] costTo;
		/**
		 * 
		 * @Title:        hasResidualMinCostPath 
		 * @Description:  Find the min cost path from residual network. 
		 * @param:        @return    
		 * @return:       boolean    
		 * @throws 
		 */
		public boolean hasResidualMinCostPath(){
			boolean[] marked = new boolean[N];
			Arrays.fill(edgeTo, null);
			Arrays.fill(costTo, Integer.MAX_VALUE);
			costTo[S] = 0;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(S);
			while(!q.isEmpty()){
				int curr = q.poll();
				marked[curr] = false;
				for(FlowEdge e:adjList[curr]){
					int other = e.other(curr);
					if(e.residualTo(other)>0&&(costTo[other]>costTo[curr]+e.getCost(other))){
						costTo[other] = costTo[curr]+e.getCost(other);
						edgeTo[other] = e;
						if(marked[other])
							continue;
						marked[other] = true;
						q.offer(other);
					}
				}
			}
			if(costTo[T]<Integer.MAX_VALUE)
				return true;
			return false;
		}
		/**
		 * 
		 * @Title:        MinCostMaxFlow 
		 * @Description:  Run the algorithm to calculate min cost maximum flow.  
		 * @param:        @param S
		 * @param:        @param T    
		 * @return:       void    
		 * @throws 
		 */
		public void MinCostMaxFlow(int S, int T){
			this.S = S;
			this.T = T;
			maxFlows = 0;
			minCosts = 0;
			while(hasResidualMinCostPath()){
				int bottle = Integer.MAX_VALUE;
				for(int p=T;p!=S;p=edgeTo[p].other(p)){
					FlowEdge curr = edgeTo[p];
					int f = curr.residualTo(p);
					if(f<bottle)
						bottle = f;
				}
				maxFlows += bottle;
				minCosts += bottle*costTo[T];
				for(int p=T;p!=S;p=edgeTo[p].other(p)){
					FlowEdge curr = edgeTo[p];
					curr.addResidualTo(p, bottle);
				}
			}
		}
	}
	
	class Pair{
		public int x;
		public int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			return x*101+y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Pair){
				Pair o = (Pair)obj;
				if(x == o.x && y == o.y)
					return true;
			}
			return false;
		}
	}

	public int guess(int[] x, int[] y){
		int len = x.length;
		Map<Integer, Integer> xMap = new HashMap<Integer, Integer>(50);
		Map<Integer, Integer> yMap = new HashMap<Integer, Integer>(5);
		Set<Pair> set = new HashSet<Pair>(50);
		for(int i=0;i<len;i++){
			Pair p = new Pair(x[i],y[i]);
			set.add(p);
			if(xMap.containsKey(x[i]))
				xMap.put(x[i], xMap.get(x[i])+1);
			else
				xMap.put(x[i], 1);
			if(yMap.containsKey(y[i]))
				yMap.put(y[i], yMap.get(y[i])+1);
			else
				yMap.put(y[i], 1);
		}
		int N = 2+xMap.size()+yMap.size();
		MaximumFlow mf = new MaximumFlow(N);
		List<Entry<Integer, Integer>> xArray = new ArrayList<Entry<Integer,Integer>>(xMap.entrySet());
		List<Entry<Integer, Integer>> yArray = new ArrayList<Entry<Integer,Integer>>(yMap.entrySet());
		for(int i=0;i<xArray.size();i++)
			mf.addEdge(0, i+1, xArray.get(i).getValue(), 0);
		for(int i=0;i<yArray.size();i++)
			mf.addEdge(i+1+xArray.size(), N-1, yArray.get(i).getValue(), 0);
		for(int i=0;i<xArray.size();i++){
			for(int j=0;j<yArray.size();j++){
				int from = i+1;
				int to = j+1+xArray.size();
				Pair temp = new Pair(xArray.get(i).getKey(),yArray.get(j).getKey());
				if(set.contains(temp))
					mf.addEdge(from, to, 1, 1);
				else
					mf.addEdge(from, to, 1, 0);
			}
		}
		mf.MinCostMaxFlow(0, N-1);
		return mf.minCosts;
	}
	
	public static void main(String[] args) {
		SpecialCells s = new SpecialCells();
		int[][] test = {{53675, 17458, 73568, 17458, 60597, 53675, 47434, 53675, 69884, 69884, 47434, 60597, 60597, 69884, 25801, 73568, 69884, 17458, 17458, 69884, 25801}, 
				{4698, 4698, 49765, 88535, 4698, 49765, 4698, 88535, 12133, 4698, 88535, 336, 12133, 336, 49765, 66068, 88535, 85491, 66068, 85491, 85491}};
		int res = s.guess(test[0], test[1]);
		System.out.println(res);
	}
}
