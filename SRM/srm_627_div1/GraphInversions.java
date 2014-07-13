package srm_627_div1;

import java.util.ArrayList;

public class GraphInversions {
	class BinaryIndexedTree {
		private int[] treeArray;
		private int N;
		
		private int lowbit(int x){
			return x&(-x);
		}
		
		public BinaryIndexedTree(int N) {
			this.N = N;
			this.treeArray = new int[N+1];
		}
		
		public int sum(int x){
			x = x-1;
			int res = 0;
			while(x>0){
				res += treeArray[x];
				x -= lowbit(x);
			}
			return res;
		}
		
		public void add(int x, int val){
			while(x<=N){
				treeArray[x] += val;
				x += lowbit(x);
			}
		}
	}
	class Graph{
		public int N;
		public ArrayList<Integer>[] adjList;
		
		public Graph(int N) {
			this.N = N;
			this.adjList = new ArrayList[N];
			for(int i=0;i<N;i++)
				this.adjList[i] = new ArrayList<Integer>();
		}
		
		public void addEdge(int w, int v){
			this.adjList[w].add(v);
			this.adjList[v].add(w);
		}
		
		public ArrayList<Integer> adj(int w){
			return this.adjList[w];
		}
	}
	
	class DFS{
		public Graph g;
		public int k;
		public boolean[] marked;
		public BinaryIndexedTree bit;
		public int[] V;
		public int min = Integer.MAX_VALUE;
		
		public DFS(Graph g, BinaryIndexedTree bit, int[] V, int k) {
			this.g = g;
			this.k = k;
			this.V = V;
			this.bit = bit;
			this.marked = new boolean[g.N];
		}
		
		public int getMin(int s){
			dfs(s,1,0);
			return min;
		}
		
		public void dfs(int curr, int d, int res){
			int r = bit.sum(V[curr]) + res;
			bit.add(V[curr], 1);
			if(d == k){
				min = Math.min(min, r);
			}else{
				marked[curr] = true;
				for(Integer i:g.adj(curr)){
					if(marked[i])
						continue;
					dfs(i,d+1,r);
				}
			}
			marked[curr] = false;
			bit.add(V[curr], -1);
		}
	}
	
	public int getMinimumInversions(int[] A, int[] B, int[] V, int K){
		int N = V.length;
		Graph g = new Graph(N);
		BinaryIndexedTree bit = new BinaryIndexedTree(1000);
		for(int i=0;i<N;i++)
			g.addEdge(A[i], B[i]);
		DFS dfs = new DFS(g,bit,V,K);
		int min = Integer.MAX_VALUE;
		for(int i=0;i<N;i++){
			min = Math.min(dfs.getMin(i),min);
		}
		if(min == Integer.MAX_VALUE)
			min = -1;
		return min;
	}

	public static void main(String[] args) {
		GraphInversions gi = new GraphInversions();
		int[] A = {0,1,2};
		int[] B = {1,2,0};
		int[] V = {40,50,60};
		int K = 3;
		int res = gi.getMinimumInversions(A, B, V, K);
		System.out.println(res);
	}
}
