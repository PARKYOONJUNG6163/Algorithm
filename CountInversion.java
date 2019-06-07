package HW07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CountInversion {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numlist = new ArrayList<Integer>(); 
		try { // 파일 읽어와서 배열에 넣기
			FileReader fr = new FileReader("C://Users//user//Desktop//data07.txt"); // 파일을 읽어올 경로 설정
			String line = "";
			BufferedReader br = new BufferedReader(fr); // 버퍼 생성자 객체 br
			while ((line = br.readLine()) != null) { // 파일 내용이 비어있을 때까지
				StringTokenizer st = new StringTokenizer(line, ","); // ","을 기준으로 읽어온 line을 나눠준다
				while (st.hasMoreTokens()) { // token이 더 이상 없을 까지
					numlist.add(Integer.parseInt(st.nextToken())); // 리스트에 추가
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 예외상황 처리
		int inversion_Count = MergeSort(numlist,0,numlist.size()-1);
		System.out.println("Inversion의 개수 : " + inversion_Count);
	}
	
	public static int MergeSort(List<Integer> numlist, int p,int r) {
		if(p >= r) return 0; // 재귀함수 종료 조건
		int q = (p+r)/2; // 중간 값
		int left_inversion_Count = MergeSort(numlist,p,q); // 두개로 나눈 앞부분을 크기가 1일 때까지 계속 나눠준다
		int right_inversion_Count  =  MergeSort(numlist,q+1,r); // 두개로 나눈 뒷부분을 크기가 1일 때까지 계속 나눠준다
		int inversion_Count = merge(numlist,p,q,r); // 정복과 결합하기
		
		return inversion_Count + left_inversion_Count + right_inversion_Count;
	}
	
	public static int merge(List<Integer> numlist, int p, int q, int r) {
		int[] result = new int[numlist.size()]; // 정렬하기 위한 임시 배열 생성
		int inversion_Count = 0;
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
				inversion_Count += (q + 1 - start);
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
		
		return inversion_Count;
	}
}
