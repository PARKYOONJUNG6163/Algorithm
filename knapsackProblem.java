package HW11;
// 201602001 박윤정
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class knapsackProblem {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> value = new ArrayList<Integer>(); // value값들 저장하는 배열
		ArrayList<Integer> weight = new ArrayList<Integer>(); // weight값들 저장하는 배열
		int n=0; // 아이템의 개수
		try { // 파일 읽어와서 배열에 넣기
			FileReader fr = new FileReader("C://Users//user//Desktop//data11.txt"); // 파일을 읽어올 경로 설정
			String line = "";
			BufferedReader br = new BufferedReader(fr); // 버퍼 생성자 객체 br
			while ((line = br.readLine()) != null) { // 파일 내용이 비어있을 때까지
				String[] s = line.split(",");
				value.add(Integer.parseInt(s[0])-1, Integer.parseInt(s[1]));
				weight.add(Integer.parseInt(s[0])-1, Integer.parseInt(s[2]));
				n++;
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 예외상황 처리
		Scanner sc = new Scanner(System.in);
		System.out.println("배낭의 사이즈를 입력하세요 (0-50) : ");
		int size = sc.nextInt(); // 입력받은 배낭의 사이즈
		if(size>=0 && size <= 50) { // 0에서 50의 범위내에서 
			knapsack(n,size,weight,value);	
		}else {
			System.out.println("size는 0~50의 범위 내에서 존재해야 합니다.");
		}
	}

	public static void knapsack(int n,int size,ArrayList<Integer> weight,ArrayList<Integer> value) {
		OPT[][] max = new OPT[n+1][size+1]; // n+1크기, w+1크기
		
		for(int i=0;i<n+1;i++) { // 초기화
			for(int j=0;j<size+1;j++) {
				max[i][j] = new OPT();
			}
		}
		
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<size+1;j++) {
				if(weight.get(i-1) > j) { // i-1번쨰 물건의 무게가 커서 배낭에 넣을 수 없음
					max[i][j].total = max[i-1][j].total;
					max[i][j].set = max[i-1][j].set;
				}else // 물건을 넣었을 때와 넣지 않았을 때 가격 중 최대
					max[i][j].total = Math.max(max[i-1][j].total,value.get(i-1)+max[i-1][j-weight.get(i-1)].total);
						// value는 현재가치 + 배낭의 크기에서 현재 가치의 크기를 뺀 크기일때 max값
					if(max[i][j].total == max[i-1][j].total) { // 물건을 넣지 않았을 때가 최대
						max[i][j].set = max[i-1][j].set;
					}else { // 물건을 넣었을 때가 최대
						max[i][j].set = new int[max[i-1][j-weight.get(i-1)].set.length+1]; // 배열 크기 설정
						System.arraycopy(max[i-1][j-weight.get(i-1)].set,0, max[i][j].set, 0, max[i-1][j-weight.get(i-1)].set.length);
						max[i][j].set[max[i-1][j-weight.get(i-1)].set.length] = i;
					}
			}
		} 
		
		for(int i=0;i<n+1;i++) { // max 테이블 출력
			for(int j=0;j<size+1;j++) {
				System.out.print("\t" + max[i][j].total);
			}
			System.out.println();
		}
		
		System.out.println("max : " + max[n][size].total); // 최대 가치 출력
		System.out.print("item: "); // 가방안의 item 출력
		for(int i=0;i<max[n][size].set.length;i++) {
			System.out.print(max[n][size].set[i]+" ");
		}
	}
}

class OPT{ // 최적의 전체 가치와 그에 대한 set
	int total;
	int[] set;
	
	public OPT() {
		total = 0;
		set = new int[0];
	}
}
