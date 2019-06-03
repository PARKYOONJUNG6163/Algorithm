package HW03;
//201602001 박윤정
import java.util.*;
import java.io.*;
public class MaxPriorityQueue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<MaxHeap> heap = new ArrayList<MaxHeap>();
		try { // 파일 읽어와서 배열에 넣기
			int i=0;
			FileReader fr = new FileReader("C://Users//user//Desktop//data03.txt"); // 파일을 읽어올 경로 설정
			String line = "";
			BufferedReader br = new BufferedReader(fr); // 버퍼 생성자 객체 br
			while ((line = br.readLine()) != null) { // 파일 내용이 비어있을 때까지
				String[] str = line.split(", "); // " "을 기준으로 읽어온 line을 나눠준다
				MaxHeap mh = new MaxHeap(Integer.parseInt(str[0]),str[1]);
				heap.add(mh);
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 예외상황 처리
		buildMaxHeap(heap);
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("*** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다. ***");
		System.out.println("[index] key, value");
		System.out.println("-------------------");
		for(int i=0;i<heap.size();i++) {
			System.out.println("["+i+"] "+heap.get(i).key+", "+heap.get(i).value);
		}
		System.out.println("-------------------");
		System.out.println("1.작업추가		2.최대값		3.최대 우선순위 작업 처리\n4.원소 키 값 증가	5.작업제거		6.종료");	
		System.out.println("-------------------");
		System.out.print("명령 번호 입력 : ");
		int menu = sc.nextInt();
		switch(menu) {
			case 1:
				System.out.println("추가할 key와 value값을 입력해주세요.");
				System.out.print("key값 : ");
				int add_key = sc.nextInt();
				sc.nextLine();
				System.out.print("value값 : ");
				String add_value = sc.next();
				MaxHeap mh = new MaxHeap(add_key,add_value);
				insert(heap,mh);
				System.out.println("삽입을 성공하였습니다.");
				break;
			case 2:
				System.out.println("최대 값은 "+max(heap).key+", "+max(heap).value);
				break;
			case 3:
				MaxHeap ex = extract_max(heap);
				System.out.println("최대 우선순위인 "+ex.key+", "+ex.value+" 처리를 완료하였습니다.");
				break;
			case 4:
				System.out.print("증가시킬 key값을 입력해주세요 : ");
				int in_key = sc.nextInt();
				System.out.print("현재 key값을 어떤 key값으로 증가시킬까요? : ");
				int in = sc.nextInt();
				if(in > in_key) { // 증가시키려는 값이 현재 키값도다 커야 함
					for(int i=0;i<heap.size();i++) {
						if(heap.get(i).key == in_key) {
							increase_key(heap,heap.get(i),in);
							System.out.println("key값을 증가하는데에 성공하였습니다.");
							break;
						}
					}
				}else
					System.out.println("증가시키려는 key값이 현재 key값보다 커야합니다.");
				break;
			case 5:
				System.out.print("삭제하려는 key를 입력하세요 : ");
				int delete = sc.nextInt();
				for(int i=0;i<heap.size();i++) {
					if(heap.get(i).key == delete) {
						h_delete(heap,heap.get(i));
						System.out.println("삭제를 성공하였습니다.");
						break;
					}
				}
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				break;
			default :
				System.out.println("잘못된 번호 입력입니다. 다시 입력해주세요.");
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
			heap.set(largest, temp); // index자리 값과 largest자리 값 교환
			maxHeapify(heap,largest);
		}
	}
	
	public static void buildMaxHeap(List<MaxHeap> heap) {
		for(int index=heap.size()/2-1;index>=0;index--) {
			maxHeapify(heap,index);
		}
	}
	
	public static void insert(List<MaxHeap> heap,MaxHeap mh) {
		heap.add(mh); // 삽입하려는 max heap을 list에 추가
		buildMaxHeap(heap); // max heap으로 만들어 주기
	}
	
	public static MaxHeap max(List<MaxHeap> heap) {
		return heap.get(0);
	}
	
	public static MaxHeap extract_max(List<MaxHeap> heap) {
		MaxHeap root = heap.get(0);
		heap.set(0, heap.get(heap.size()-1)); // 루트자리에 마지막 원소 넣기
		heap.remove(heap.size()-1); // 사이즈 하나 감소
		maxHeapify(heap,0); // 루트 추출 후 max heap만들기
		return root;
	}
	
	public static void increase_key(List<MaxHeap> heap,MaxHeap mh,int k) {
		mh.key = k; // 현재 key값을 k값으로
		maxHeapify(heap,0); // max heap 만들기
	}

	public static MaxHeap h_delete(List<MaxHeap> heap,MaxHeap mh) {
		int delete_index = heap.indexOf(mh);
		heap.set(delete_index, heap.get(heap.size()-1)); // 삭제할 인덱스 자리에 마지막 원소 넣기
		heap.remove(heap.size()-1); // 마지막 원소 삭제
		buildMaxHeap(heap); // 삭제 후 max heap으로 만들어줌
		return mh;
	}

}
