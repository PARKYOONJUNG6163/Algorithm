package HW03;
//201602001 ������
import java.util.*;
import java.io.*;
public class MaxPriorityQueue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<MaxHeap> heap = new ArrayList<MaxHeap>();
		try { // ���� �о�ͼ� �迭�� �ֱ�
			int i=0;
			FileReader fr = new FileReader("C://Users//user//Desktop//data03.txt"); // ������ �о�� ��� ����
			String line = "";
			BufferedReader br = new BufferedReader(fr); // ���� ������ ��ü br
			while ((line = br.readLine()) != null) { // ���� ������ ������� ������
				String[] str = line.split(", "); // " "�� �������� �о�� line�� �����ش�
				MaxHeap mh = new MaxHeap(Integer.parseInt(str[0]),str[1]);
				heap.add(mh);
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // ���ܻ�Ȳ ó��
		buildMaxHeap(heap);
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("*** ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�. ***");
		System.out.println("[index] key, value");
		System.out.println("-------------------");
		for(int i=0;i<heap.size();i++) {
			System.out.println("["+i+"] "+heap.get(i).key+", "+heap.get(i).value);
		}
		System.out.println("-------------------");
		System.out.println("1.�۾��߰�		2.�ִ밪		3.�ִ� �켱���� �۾� ó��\n4.���� Ű �� ����	5.�۾�����		6.����");	
		System.out.println("-------------------");
		System.out.print("��� ��ȣ �Է� : ");
		int menu = sc.nextInt();
		switch(menu) {
			case 1:
				System.out.println("�߰��� key�� value���� �Է����ּ���.");
				System.out.print("key�� : ");
				int add_key = sc.nextInt();
				sc.nextLine();
				System.out.print("value�� : ");
				String add_value = sc.next();
				MaxHeap mh = new MaxHeap(add_key,add_value);
				insert(heap,mh);
				System.out.println("������ �����Ͽ����ϴ�.");
				break;
			case 2:
				System.out.println("�ִ� ���� "+max(heap).key+", "+max(heap).value);
				break;
			case 3:
				MaxHeap ex = extract_max(heap);
				System.out.println("�ִ� �켱������ "+ex.key+", "+ex.value+" ó���� �Ϸ��Ͽ����ϴ�.");
				break;
			case 4:
				System.out.print("������ų key���� �Է����ּ��� : ");
				int in_key = sc.nextInt();
				System.out.print("���� key���� � key������ ������ų���? : ");
				int in = sc.nextInt();
				if(in > in_key) { // ������Ű���� ���� ���� Ű������ Ŀ�� ��
					for(int i=0;i<heap.size();i++) {
						if(heap.get(i).key == in_key) {
							increase_key(heap,heap.get(i),in);
							System.out.println("key���� �����ϴµ��� �����Ͽ����ϴ�.");
							break;
						}
					}
				}else
					System.out.println("������Ű���� key���� ���� key������ Ŀ���մϴ�.");
				break;
			case 5:
				System.out.print("�����Ϸ��� key�� �Է��ϼ��� : ");
				int delete = sc.nextInt();
				for(int i=0;i<heap.size();i++) {
					if(heap.get(i).key == delete) {
						h_delete(heap,heap.get(i));
						System.out.println("������ �����Ͽ����ϴ�.");
						break;
					}
				}
				break;
			case 6:
				System.out.println("���α׷��� �����մϴ�.");
				break;
			default :
				System.out.println("�߸��� ��ȣ �Է��Դϴ�. �ٽ� �Է����ּ���.");
				break;
			}
			if(menu == 6) break;
		}
	}
	
	public static void maxHeapify(List<MaxHeap> heap, int index) {
		int largest = index;
		int left_child = 2*index+1;
		int right_child = 2*index+2;
		if(left_child<heap.size() && heap.get(left_child).key > heap.get(index).key) 
			largest = left_child;
		else
			largest = index;
		if(right_child<heap.size() && heap.get(right_child).key > heap.get(largest).key)
			largest = right_child;
		if(largest != index) {
			MaxHeap temp = heap.get(index);
			heap.set(index, heap.get(largest));
			heap.set(largest, temp); // index�ڸ� ���� largest�ڸ� �� ��ȯ
			maxHeapify(heap,largest);
		}
	}
	
	public static void buildMaxHeap(List<MaxHeap> heap) {
		for(int index=heap.size()/2-1;index>=0;index--) {
			maxHeapify(heap,index);
		}
	}
	
	public static void insert(List<MaxHeap> heap,MaxHeap mh) {
		heap.add(mh); // �����Ϸ��� max heap�� list�� �߰�
		buildMaxHeap(heap); // max heap���� ����� �ֱ�
	}
	
	public static MaxHeap max(List<MaxHeap> heap) {
		return heap.get(0);
	}
	
	public static MaxHeap extract_max(List<MaxHeap> heap) {
		MaxHeap root = heap.get(0);
		heap.set(0, heap.get(heap.size()-1)); // ��Ʈ�ڸ��� ������ ���� �ֱ�
		heap.remove(heap.size()-1); // ������ �ϳ� ����
		maxHeapify(heap,0); // ��Ʈ ���� �� max heap�����
		return root;
	}
	
	public static void increase_key(List<MaxHeap> heap,MaxHeap mh,int k) {
		mh.key = k; // ���� key���� k������
		maxHeapify(heap,0); // max heap �����
	}

	public static MaxHeap h_delete(List<MaxHeap> heap,MaxHeap mh) {
		int delete_index = heap.indexOf(mh);
		heap.set(delete_index, heap.get(heap.size()-1)); // ������ �ε��� �ڸ��� ������ ���� �ֱ�
		heap.remove(heap.size()-1); // ������ ���� ����
		buildMaxHeap(heap); // ���� �� max heap���� �������
		return mh;
	}

}
