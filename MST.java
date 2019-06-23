package HW09;

import java.util.ArrayList;
import java.util.List;

public class MST {
	static int INF = Integer.MAX_VALUE; // 무한대 값
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Vertex> heap = new ArrayList<Vertex>();
		int[][] w = {{0,4,INF,INF,INF,INF,INF,8,INF},{4,0,8,INF,INF,INF,INF,11,INF},{INF,8,0,7,INF,4,INF,INF,2},
				{INF,INF,7,0,9,14,INF,INF,INF},{INF,INF,INF,9,0,10,INF,INF,INF},{INF,INF,4,14,10,0,2,INF,INF},
				{INF,INF,INF,INF,INF,2,0,1,6},{8,11,INF,INF,INF,INF,1,0,7},{INF,INF,2,INF,INF,INF,6,7,0}};
		
		
		System.out.println("w(MST) = "+Prim(heap,w));
	}
	
	public static int Prim(List<Vertex> heap,int[][] w) {
		int total_weight = 0; // 가중치의 총 합 
		int[] key = new int[w.length]; // 확정된 가중치의 값을 저장
		Vertex[] PI = new Vertex[w.length]; // v vertex의 부모에 해당하는 vertex
		
		insert(heap, new Vertex("a",0,0)); //큐에 첫번재 vertex 추가
		for(int i=1;i<w.length;i++) { // 큐에 나머지 vertex들 추가
			insert(heap, new Vertex(String.valueOf((char)('a'+i)),INF,i));
		}
		
		key[0] = 0; // 시작 vertex = 0
		for(int i=1;i<key.length;i++) { // 그 외 vertext = 무한
			key[i] = INF;
		}
		
		while(!heap.isEmpty()) { // 큐가 비어있지 않으면
			Vertex u = extract_min(heap); // 가장 작은 값 가지는 vertex 추출
			if(PI[u.index] == null) { // a는 시작원소라서 null값이므로 따로 경우 만들어줌
				System.out.println("w( ,"+u.vertex+") = "+ key[u.index]);
			}else {
				System.out.println("w("+ PI[u.index].vertex+","+u.vertex+") = "+ key[u.index]);
			}
			
			for(int i=0;i<heap.size();i++) { // 큐 사이즈 만큼
				Vertex v = heap.get(i);
				if(w[u.index][v.index] != INF && w[u.index][v.index] < key[v.index]) {
					// u에서 v까지의 거리가 무한대 값이 아니면서 u의 거리와 u에서 v까지의 거리의 합이 v의 거리보다 작다면 갱신
					key[v.index] = w[u.index][v.index];
					v.distance =  w[u.index][v.index];
					PI[v.index] = u;
				}
			}
			buildMinHeap(heap);
		}
		
		System.out.println();
		for(int i=0;i<key.length;i++) { // 가중치의 총 합 계산
			total_weight += key[i];
		}
		
		return total_weight;
	}
	
	public static void minHeapify(List<Vertex> heap, int index) {
		int smallest = index;
		int left_child = 2*index+1;
		int right_child = 2*index+2;
		if(left_child<heap.size() && heap.get(left_child).distance < heap.get(index).distance) 
			smallest = left_child;
		else
			smallest = index;
		if(right_child<heap.size() && heap.get(right_child).distance < heap.get(smallest).distance)
			smallest = right_child;
		if(smallest != index) {
			Vertex temp = heap.get(index);
			heap.set(index, heap.get(smallest));
			heap.set(smallest, temp); // index자리 값과 smallest자리 값 교환
			minHeapify(heap,smallest);
		}
	}
	
	public static void buildMinHeap(List<Vertex> heap) {
		for(int index=heap.size()/2-1;index>=0;index--) {
			minHeapify(heap,index);
		}
	}
	
	public static void insert(List<Vertex> heap,Vertex v) {
		heap.add(v); // 삽입하려는vertex를 list에 추가
		buildMinHeap(heap); // min heap으로 만들어 주기
	}
	
	public static Vertex extract_min(List<Vertex> heap) {
		Vertex root = heap.get(0);
		heap.set(0, heap.get(heap.size()-1)); // 루트자리에 마지막 원소 넣기
		heap.remove(heap.size()-1); // 사이즈 하나 감소
		minHeapify(heap,0); // 루트 추출 후 min heap만들기
		return root;
	}
}

class Vertex { 
	String vertex; // 이름
	int distance; // 거리
	int index; // 인덱스
	
	public Vertex(String vertex,int distance,int index) { // vertex생성자
		this.index = index;
		this.vertex = vertex;
		this.distance = distance;
	}
}
