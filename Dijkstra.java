package HW08;
// 201602001 박윤정
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dijkstra {
	static int INF = Integer.MAX_VALUE; // 무한대 값
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] w = {{0,10,3,INF,INF},{INF,0,1,2,INF},{INF,4,0,8,2},
		{INF,INF,INF,0,7},{INF,INF,INF,9,0}}; // N*N배열 w생성 후 각 path의 길이 저장
		
		System.out.println("Dijkstra's algorithm으로 계산한 결과는 다음과 같습니다.");
		System.out.println();
		Dijkstra(w);
	}
	
	public static void Dijkstra(int[][] w) {
		Vertex[] S = new Vertex[w.length]; // 노드들의 짧은 거리가 확정된 값 (처음은 공집합)
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(); // 우선순위 큐 생성
		
		Q.offer(new Vertex("A",0,0)); // 시작 원소는 0으로
		for(int i=1;i<w.length;i++) { // 모두 무한대 값으로 설정
			Q.offer(new Vertex(String.valueOf((char)('A'+i)),INF,i));
		}
		
		int index = 0;
		while(!Q.isEmpty()) { // 큐가 비어있지 않다면
			System.out.println("----------------------------------------------------------------------------");
			Vertex u = Q.poll(); // 큐에서 root추출
			S[index] = u; // S배열에 u값을 넣는다 최단 거리이므로
			
			System.out.println("S["+index+"]  :  d["+u.vertex+"] = "+u.distance);
			System.out.println("----------------------------------------------------------------------------");
			
			Iterator<Vertex> it = Q.iterator(); // 반복자를 통하여 큐 원소 돌기
			for(int i=0;i<Q.size();i++) {
				Vertex v = it.next(); // 큐에서 하나씩 가져옴
				System.out.print("Q["+i+"]  :  d["+v.vertex+"] = "+v.distance);
				if(w[u.index][v.index] != INF && u.distance+w[u.index][v.index] < v.distance) {
					// u에서 v까지의 거리가 무한대 값이 아니면서 u의 거리와 u에서 v까지의 거리의 합이 v의 거리보다 작다면 갱신
					v.distance = u.distance + w[u.index][v.index];
					System.out.print(" ->  d["+v.vertex+"] = "+v.distance);
				}
				System.out.println();
			}
			
			if (!Q.isEmpty()) { // 큐에서 원소들 재정렬
				Vertex temp = Q.poll(); // 맨 위 원소를 temp에 담앗다가
				Q.offer(temp); // 다시 넣기
			}
			System.out.println();
			index++;
		}
	}
}


class Vertex implements Comparable<Vertex>{ //우선순위 큐 이므로 comparable을 사용하여 크기 비교
	String vertex; // 이름
	int distance; // 거리
	int index; // 인덱스
	
	public Vertex(String vertex,int distance,int index) { // vertex생성자
		this.index = index;
		this.vertex = vertex;
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex v) {
		if(distance < v.distance)
			return -1;
		else if(distance > v.distance)
			return 1;
		else 
			return 0;
	}
}
