package HW05;
//201602001 박윤정
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClosestPair {
	static DOT point1 = null; // 최소 point좌표
	static DOT point2 = null; // 최소 point좌표
	static double minNum = Double.MAX_VALUE; // 최소 좌표를 구별해주기 위한 최소값
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<DOT> numlist = new ArrayList<DOT>();
		try { // 파일 읽어와서 배열에 넣기
			FileReader fr = new FileReader("C://Users//user//Desktop//data05.txt"); // 파일을 읽어올 경로 설정
			String line = "";
			int i = 0;
			BufferedReader br = new BufferedReader(fr); // 버퍼 생성자 객체 br
			while ((line = br.readLine()) != null) { // 파일 내용이 비어있을 때까지
				String[] s = line.split(",");
				DOT dot = new DOT(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
				numlist.add(dot); // 리스트에 추가
			}
			MergeSort(numlist, 0, numlist.size() - 1, "x"); // x축을 기준으로 mergeSort를 이용하여 정렬
			double min = closestMin(numlist, 0, numlist.size() - 1);
			System.out.println("가장 짧은 거리는 : " + min);
			System.out.println("closest Pair 는 (" + point1.x +" , "+point1.y+") "+"(" + point2.x +" , "+point2.y+")");
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 예외상황 처리
	}

	public static double closestCal(List<DOT> numlist, int i, int j) { // 최단거리를 계산하는 함수
		double min = Double.MAX_VALUE;
		double distance;
		for (int a = i; a <= j; a++) {
			for (int b = a + 1; b <= j; b++) {
				distance = (double) Math.sqrt(Math.pow(numlist.get(b).x - numlist.get(a).x, 2)
						+ Math.pow(numlist.get(b).y - numlist.get(a).y, 2)); // 두 점사이의 거리 구하기
				if(min > distance) {
					min = distance;
					if(minNum > distance) {  // 최단거리의 좌표 구하는 부분
						minNum = distance;
						point1 = numlist.get(a);
						point2 = numlist.get(b);						
					}
				}
			}
		}
		return min;
	}
	
	public static double closestMin(List<DOT> numlist,int i,int j) {	
		int mid = (i+j) / 2; // 중간 인덱스
		int n = j-i+1;
		if(n <= 3) { // 개수가 3개 이하인 경우
			return closestCal(numlist,i,j);
		}
		
		DOT midDot = numlist.get(mid);
		
		double Left_min_dist = closestMin(numlist,i,mid); // 좌측 영역의 포인트 간의 최단 거리
		double Right_min_dist = closestMin(numlist,mid+1,j); // 우측 영역의 포인트 간의 최단 거리

		double d = Math.min(Left_min_dist,Right_min_dist); // 가장 최단 거리
		
		int index=0;
		List<DOT> dlist = new ArrayList<DOT>(); 
		for(int k=i;k<=j;k++) {  // window안에 있는 원소들을 모은다
			if(Math.abs(numlist.get(k).x-midDot.x) < d) {
				dlist.add(index, numlist.get(k));
				index++;
			}
		}
		
		MergeSort(dlist,0,dlist.size()-1,"y"); // y축을 기준으로 mergeSort
		for (int a = 0; a < dlist.size(); a++) {
			for (int b = a + 1; b < dlist.size(); b++) {
				if(Math.abs(dlist.get(b).y - dlist.get(a).y) < d) { // y좌표를 비교하여 가장 최단거리 구하기
					double distance = (double) Math.sqrt(Math.pow(dlist.get(b).x - dlist.get(a).x, 2)
							+ Math.pow(dlist.get(b).y - dlist.get(a).y, 2)); // 두 점사이의 거리 구하기
					if(d > distance) {
						d = distance;
						if(minNum > distance) { // 가장 최단거리의 좌표 구하는 부분
							minNum = distance;
							point1 = dlist.get(a);
							point2 = dlist.get(b);						
						}
					}
				}
			}
		}
		
		return d;
	}
	
	public static void MergeSort(List<DOT> numlist, int p, int r, String axis) {
		if (p >= r)
			return; // 재귀함수 종료 조건
		int q = (p + r) / 2; // 중간 값
		MergeSort(numlist, p, q, axis); // 두개로 나눈 앞부분을 크기가 1일 때까지 계속 나눠준다
		MergeSort(numlist, q + 1, r, axis); // 두개로 나눈 뒷부분을 크기가 1일 때까지 계속 나눠준다
		merge(numlist, p, q, r, axis); // 정복과 결합하기
	}

	public static void merge(List<DOT> numlist, int p, int q, int r, String axis) {
		DOT[] result = new DOT[numlist.size()]; // 정렬하기 위한 임시 배열 생성
		int mid = q + 1; // mid는 중간값 + 1
		int start = p; // 나중에 복사할 때 p값이 필요하므로 start라는 변수를 p대신 사용
		int i = p; // result배열의 index
		if (axis.equals("x")) { // x축 기준 정렬
			while (start <= q && mid <= r) { // 두개로 나눴을 때 start는 중간보다 작거나 같고 mid는 마지막보다 작거나 같아야 함
				if (numlist.get(start).x < numlist.get(mid).x) { // 두 부분으로 나눴을 때 각각의 첫번째 원소 비교해서 mid가 크면
					result[i++] = numlist.get(start); // start값을 복사
					start++;
				} else { // start가 크거나 같으면
					result[i++] = numlist.get(mid); // mid값 복사
					mid++;
				}
			}
		} else { // y축 기준 정렬
			while (start <= q && mid <= r) { // 두개로 나눴을 때 start는 중간보다 작거나 같고 mid는 마지막보다 작거나 같아야 함
				if (numlist.get(start).y < numlist.get(mid).y) { // 두 부분으로 나눴을 때 각각의 첫번째 원소 비교해서 mid가 크면
					result[i++] = numlist.get(start); // start값을 복사
					start++;
				} else { // start가 크거나 같으면
					result[i++] = numlist.get(mid); // mid값 복사
					mid++;
				}
			}
		}
		// 두개로 나눴을 때 한 부분의 원소 비교가 다 끝나면 끝난게 아니고 나머지 원소도 배열에 옮겨줘야 하므로
		while (start <= q) { // start가 중간보다 작거나 작으면
			result[i++] = numlist.get(start); // start값을 복사
			start++;
		}

		while (mid <= r) { // mid가 마지막보다 작거나 같으면
			result[i++] = numlist.get(mid); // mid값을 복사
			mid++;
		}

		for (int k = p; k <= r; k++) { // 임시 배열에 정렬된 값들을 다시 numlist로 복사
			numlist.set(k, result[k]);
		}
	}
}

class DOT {
	double x, y;

	public DOT(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
