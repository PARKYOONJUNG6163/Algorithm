package HW01;
// 201602001 박윤정
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BinarySearchSort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numlist = new ArrayList<Integer>(); 
		try { // 파일 읽어와서 배열에 넣기
			FileReader fr = new FileReader("C://Users//user//Desktop//data02.txt"); // 파일을 읽어올 경로 설정
			String line = "";
			int i = 0;
			BufferedReader br = new BufferedReader(fr); // 버퍼 생성자 객체 br
			while ((line = br.readLine()) != null) { // 파일 내용이 비어있을 때까지
				StringTokenizer st = new StringTokenizer(line, ","); // " "을 기준으로 읽어온 line을 나눠준다
				while (st.hasMoreTokens()) { // token이 더 이상 없을 까지
					numlist.add(Integer.parseInt(st.nextToken())); // 리스트에 추가
					i++;
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 예외상황 처리
	//	long start = System.currentTimeMillis();
		insertionSort(numlist, numlist.size()); 
	//	long end = System.currentTimeMillis();
	//	System.out.println("BinarySearchSort 실행 시간 : " + (end - start) / 1000.0 + "초");
		
		try { // 배열에 있는 내용을 파일로 생성
			int i = 0;
			FileWriter fw = new FileWriter("C://Users//user//Desktop//[알고리즘]01_주차_201602001_binary_insertion.txt"); // 파일을 저장할 경로 설정
			BufferedWriter bw = new BufferedWriter(fw); 
			while(i<numlist.size()) {
				bw.write(Integer.toString(numlist.get(i++)));	
				if(i != numlist.size()) bw.write(","); // 마지막 원소 뒤에는 쉼표 안붙게 처리
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 예외상황 처리
	}
	
	public static void insertionSort(List<Integer> numlist, int n) {
		int key;
		int index;
		int i;
		for(int j=1;j<n;j++) {
			i = j-1; 
			key = numlist.get(j);
			index = binarySearch(numlist,0,i,key); // 이진 탐색 함수로 자리 찾기
			while(i>=index) { // index자리에 key값 넣기위하여 한칸 씩 뒤로 땡기기
				numlist.set(i+1, numlist.get(i));
				i -= 1;
			}
			numlist.set(index, key); // index자리에 key값 넣기	
			
		}
	}
	
	public static int binarySearch(List<Integer> numlist,int start,int end,int key) {
		if(start >= end) { // 재귀함수의 종료 조건
			if(key >= numlist.get(start)) { // 같거나 찾으려는 값이 크면 
				return start+1;
			}else { // 찾으려는 값이 작으면
				return start;
			}
		}
		int mid = (start+end)/2;
		if(numlist.get(mid) > key) // key가 mid보다 작으면 mid보다 아랫 부분을 재귀적으로
			return binarySearch(numlist,start,mid-1,key);
		else // key가 mid보다 크면 mid보다 윗 부분을 재귀적으로
			return binarySearch(numlist,mid+1,end,key);
	}
}
