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

public class MergeSort {
	static int mergeCount=0; // merge함수가 사용된 횟수
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
		MergeSort(numlist, 0, numlist.size()-1);
	//	long end = System.currentTimeMillis(); 
	//	System.out.println("MergeSort 실행 시간 : " + (end - start) / 1000.0 + "초");
		
		try { // 배열에 있는 내용을 파일로 생성
			int i = 0;
			FileWriter fw = new FileWriter("C://Users//user//Desktop//[알고리즘]01_주차_201602001_merge.txt"); // 파일을 저장할 경로 설정
			BufferedWriter bw = new BufferedWriter(fw); 
			while(i<numlist.size()) {
				bw.write(Integer.toString(numlist.get(i++)));	
				if(i != numlist.size()) bw.write(","); // 마지막 원소 뒤에는 쉼표 안붙게 처리
			}
			bw.newLine();
			bw.write("merge() 사용 횟수 : "+ mergeCount); // merge함수 사용 횟수 출력
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 예외상황 처리
	}
	
	public static void MergeSort(List<Integer> numlist, int p,int r) {
		if(p >= r) return; // 재귀함수 종료 조건
		int q = (p+r)/2; // 중간 값
		MergeSort(numlist,p,q); // 두개로 나눈 앞부분을 크기가 1일 때까지 계속 나눠준다
		MergeSort(numlist,q+1,r); // 두개로 나눈 뒷부분을 크기가 1일 때까지 계속 나눠준다
		merge(numlist,p,q,r); // 정복과 결합하기
	}
	
	public static void merge(List<Integer> numlist, int p, int q, int r) {
		mergeCount++;
		int[] result = new int[numlist.size()]; // 정렬하기 위한 임시 배열 생성
		int mid = q+1; // mid는 중간값 + 1
		int start = p; // 나중에 복사할 때 p값이 필요하므로 start라는 변수를 p대신 사용
		int i=p; // result배열의 index
		while(start<=q && mid<=r) { // 두개로 나눴을 때 start는 중간보다 작거나 같고 mid는 마지막보다 작거나 같아야 함 
			if(numlist.get(start)<numlist.get(mid)) { // 두 부분으로 나눴을 때 각각의 첫번째 원소 비교해서 mid가 크면
				result[i++] = numlist.get(start); // start값을 복사
				start++;
			}else { // start가 크거나 같으면
				result[i++] = numlist.get(mid); // mid값 복사
				mid++;
			}
		}
		// 두개로 나눴을 때 한 부분의 원소 비교가 다 끝나면 끝난게 아니고 나머지 원소도 배열에 옮겨줘야 하므로
		while(start<=q) { // start가 중간보다 작거나 작으면 
			result[i++] = numlist.get(start); // start값을 복사
			start++;
		}
		
		while(mid<=r) { // mid가 마지막보다 작거나 같으면
			result[i++] = numlist.get(mid); // mid값을 복사
			mid++;
		}
		
		for(int k=p;k<=r;k++) { // 임시 배열에 정렬된 값들을 다시 numlist로 복사
			numlist.set(k, result[k]);			
		}
	}
	

}
