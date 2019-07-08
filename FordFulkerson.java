package HW14;
// 201602001 박윤정
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
	static int INF = 987654321;
	static int V = 6; // Vertex 수
	static int[][] flow = new int[V][V];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] capacity = {{0,9,9,0,0,0},{0,0,10,8,0,0},{0,0,0,1,3,0},{0,0,0,0,0,10},{0,0,0,8,0,7},{0,0,0,0,0,0}};
		System.out.println("전체의 최대 용량 : " + networkFlow(capacity,0,V-1));
	}
	// {{0,12,0,3,0,0},{0,0,10,0,0,0},{0,0,0,0,3,15},{0,11,0,0,5,0},{0,0,0,0,0,17},{0,0,0,0,0,0}};
	// {{0,9,9,0,0,0},{0,0,10,8,0,0},{0,0,0,1,3,0},{0,0,0,0,0,10},{0,0,0,8,0,7},{0,0,0,0,0,0}};
	// {{0,3,4,0,5,0,0,0},{0,0,0,3,0,0,1,0},{0,0,0,2,0,0,0,0},{0,0,0,0,0,0,0,5},{0,0,0,0,0,7,0,0},{0,0,0,1,0,0,0,4},{0,0,0,0,0,0,0,3},{0,0,0,0,0,0,0,0}};
	
	public static int networkFlow(int[][] capacity,int source,int sink) {
		int totalFlow = 0;
		while(true) {
			int[] parent = new int[V];
			for(int i=0;i<V;i++) { // parent배열 초기화
				parent[i] = -1;
			}
			Queue<Integer> q = new LinkedList<>();
			parent[source] = source;
			q.add(source);
			while(!q.isEmpty() && parent[sink] == -1) {
				int here = q.peek();
				q.remove();
				for(int there =0;there<V;there++) {
					if(capacity[here][there] - flow[here][there] > 0 && parent[there] == -1) {
						q.add(there);
						parent[there] = here;
					}
				}
			}
			if(parent[sink] == -1) break;
			int amount = INF;
			for(int p = sink; p!=source;p=parent[p]) { // amount의 값 정해주기
				amount = Math.min(capacity[parent[p]][p] - flow[parent[p]][p], amount);
			}
			
			for(int p=sink;p!=source;p=parent[p]) { //flow배열에 현재 유량 값 넣기
				flow[parent[p]][p] += amount;
				flow[p][parent[p]] -= amount;
			}
			// 경로 출력 부분
			ArrayList<Integer> result = new ArrayList<>();
			for(int i=V-1;i!=0;i=parent[i]) {
				result.add(i);
			}
			result.add(0);
			System.out.print("경로 : ");
			for(int i=result.size()-1;i>0;i--) {
				System.out.print(result.get(i)+"->");
			}
			System.out.println(result.get(0));
			
			System.out.println("최대 용량 : "+amount);
			totalFlow += amount;
			System.out.println();
		}
		System.out.println();
		return totalFlow;
	}
}
