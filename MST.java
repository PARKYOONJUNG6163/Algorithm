package HW09;

import java.util.ArrayList;
import java.util.List;

public class MST {
	static int INF = Integer.MAX_VALUE; // ���Ѵ� ��
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Vertex> heap = new ArrayList<Vertex>();
		int[][] w = {{0,4,INF,INF,INF,INF,INF,8,INF},{4,0,8,INF,INF,INF,INF,11,INF},{INF,8,0,7,INF,4,INF,INF,2},
				{INF,INF,7,0,9,14,INF,INF,INF},{INF,INF,INF,9,0,10,INF,INF,INF},{INF,INF,4,14,10,0,2,INF,INF},
				{INF,INF,INF,INF,INF,2,0,1,6},{8,11,INF,INF,INF,INF,1,0,7},{INF,INF,2,INF,INF,INF,6,7,0}};
		
		
		System.out.println("w(MST) = "+Prim(heap,w));
	}
	
	public static int Prim(List<Vertex> heap,int[][] w) {
		int total_weight = 0; // ����ġ�� �� �� 
		int[] key = new int[w.length]; // Ȯ���� ����ġ�� ���� ����
		Vertex[] PI = new Vertex[w.length]; // v vertex�� �θ� �ش��ϴ� vertex
		
		insert(heap, new Vertex("a",0,0)); //ť�� ù���� vertex �߰�
		for(int i=1;i<w.length;i++) { // ť�� ������ vertex�� �߰�
			insert(heap, new Vertex(String.valueOf((char)('a'+i)),INF,i));
		}
		
		key[0] = 0; // ���� vertex = 0
		for(int i=1;i<key.length;i++) { // �� �� vertext = ����
			key[i] = INF;
		}
		
		while(!heap.isEmpty()) { // ť�� ������� ������
			Vertex u = extract_min(heap); // ���� ���� �� ������ vertex ����
			if(PI[u.index] == null) { // a�� ���ۿ��Ҷ� null���̹Ƿ� ���� ��� �������
				System.out.println("w( ,"+u.vertex+") = "+ key[u.index]);
			}else {
				System.out.println("w("+ PI[u.index].vertex+","+u.vertex+") = "+ key[u.index]);
			}
			
			for(int i=0;i<heap.size();i++) { // ť ������ ��ŭ
				Vertex v = heap.get(i);
				if(w[u.index][v.index] != INF && w[u.index][v.index] < key[v.index]) {
					// u���� v������ �Ÿ��� ���Ѵ� ���� �ƴϸ鼭 u�� �Ÿ��� u���� v������ �Ÿ��� ���� v�� �Ÿ����� �۴ٸ� ����
					key[v.index] = w[u.index][v.index];
					v.distance =  w[u.index][v.index];
					PI[v.index] = u;
				}
			}
			buildMinHeap(heap);
		}
		
		System.out.println();
		for(int i=0;i<key.length;i++) { // ����ġ�� �� �� ���
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
			heap.set(smallest, temp); // index�ڸ� ���� smallest�ڸ� �� ��ȯ
			minHeapify(heap,smallest);
		}
	}
	
	public static void buildMinHeap(List<Vertex> heap) {
		for(int index=heap.size()/2-1;index>=0;index--) {
			minHeapify(heap,index);
		}
	}
	
	public static void insert(List<Vertex> heap,Vertex v) {
		heap.add(v); // �����Ϸ���vertex�� list�� �߰�
		buildMinHeap(heap); // min heap���� ����� �ֱ�
	}
	
	public static Vertex extract_min(List<Vertex> heap) {
		Vertex root = heap.get(0);
		heap.set(0, heap.get(heap.size()-1)); // ��Ʈ�ڸ��� ������ ���� �ֱ�
		heap.remove(heap.size()-1); // ������ �ϳ� ����
		minHeapify(heap,0); // ��Ʈ ���� �� min heap�����
		return root;
	}
}

class Vertex { 
	String vertex; // �̸�
	int distance; // �Ÿ�
	int index; // �ε���
	
	public Vertex(String vertex,int distance,int index) { // vertex������
		this.index = index;
		this.vertex = vertex;
		this.distance = distance;
	}
}
