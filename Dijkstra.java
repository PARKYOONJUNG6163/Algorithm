package HW08;
// 201602001 ������
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dijkstra {
	static int INF = Integer.MAX_VALUE; // ���Ѵ� ��
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] w = {{0,10,3,INF,INF},{INF,0,1,2,INF},{INF,4,0,8,2},
		{INF,INF,INF,0,7},{INF,INF,INF,9,0}}; // N*N�迭 w���� �� �� path�� ���� ����
		
		System.out.println("Dijkstra's algorithm���� ����� ����� ������ �����ϴ�.");
		System.out.println();
		Dijkstra(w);
	}
	
	public static void Dijkstra(int[][] w) {
		Vertex[] S = new Vertex[w.length]; // ������ ª�� �Ÿ��� Ȯ���� �� (ó���� ������)
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(); // �켱���� ť ����
		
		Q.offer(new Vertex("A",0,0)); // ���� ���Ҵ� 0����
		for(int i=1;i<w.length;i++) { // ��� ���Ѵ� ������ ����
			Q.offer(new Vertex(String.valueOf((char)('A'+i)),INF,i));
		}
		
		int index = 0;
		while(!Q.isEmpty()) { // ť�� ������� �ʴٸ�
			System.out.println("----------------------------------------------------------------------------");
			Vertex u = Q.poll(); // ť���� root����
			S[index] = u; // S�迭�� u���� �ִ´� �ִ� �Ÿ��̹Ƿ�
			
			System.out.println("S["+index+"]  :  d["+u.vertex+"] = "+u.distance);
			System.out.println("----------------------------------------------------------------------------");
			
			Iterator<Vertex> it = Q.iterator(); // �ݺ��ڸ� ���Ͽ� ť ���� ����
			for(int i=0;i<Q.size();i++) {
				Vertex v = it.next(); // ť���� �ϳ��� ������
				System.out.print("Q["+i+"]  :  d["+v.vertex+"] = "+v.distance);
				if(w[u.index][v.index] != INF && u.distance+w[u.index][v.index] < v.distance) {
					// u���� v������ �Ÿ��� ���Ѵ� ���� �ƴϸ鼭 u�� �Ÿ��� u���� v������ �Ÿ��� ���� v�� �Ÿ����� �۴ٸ� ����
					v.distance = u.distance + w[u.index][v.index];
					System.out.print(" ->  d["+v.vertex+"] = "+v.distance);
				}
				System.out.println();
			}
			
			if (!Q.isEmpty()) { // ť���� ���ҵ� ������
				Vertex temp = Q.poll(); // �� �� ���Ҹ� temp�� ��Ѵٰ�
				Q.offer(temp); // �ٽ� �ֱ�
			}
			System.out.println();
			index++;
		}
	}
}


class Vertex implements Comparable<Vertex>{ //�켱���� ť �̹Ƿ� comparable�� ����Ͽ� ũ�� ��
	String vertex; // �̸�
	int distance; // �Ÿ�
	int index; // �ε���
	
	public Vertex(String vertex,int distance,int index) { // vertex������
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
