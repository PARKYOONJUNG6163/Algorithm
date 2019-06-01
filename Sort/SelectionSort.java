package HW04;
//201602001 박윤정
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SelectionSort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numlist = new ArrayList<Integer>(); 
		try { // 파일 읽어와서 배열에 넣기
			FileReader fr = new FileReader("C://Users//user//Desktop//data04.txt"); // 파일을 읽어올 경로 설정
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
		SelectionSort(numlist);	
		try { // 배열에 있는 내용을 파일로 생성
			int i = 0;
			FileWriter fw = new FileWriter("C://Users//user//Desktop//data04_Sort_Sel.txt"); // 파일을 저장할 경로 설정
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

	private static void SelectionSort(List<Integer> numlist) {
		int minIndex;
		for(int i=0;i<numlist.size()-1;i++) { // 마지막 원소는 자동 정렬되므로	
			minIndex = i;
			for(int j=i+1;j<numlist.size();j++) 
				if(numlist.get(minIndex) > numlist.get(j)) minIndex = j; // min자리값이 크면 min값을 바꿔줌
			int temp = numlist.get(minIndex); // swap
			numlist.set(minIndex,numlist.get(i));
			numlist.set(i,temp);
		}
		}
}
