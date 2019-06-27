package HW12;

import java.util.ArrayList;
import java.util.Scanner;

class DOT{
	int row;
	int cal;
	ArrayList<DOT> prev;
	int cost;
	
	public DOT(int row,int cal,ArrayList<DOT> prev,int cost) {
		this.row = row;
		this.cal = cal;
		this.prev = prev;
		this.cost=cost;
	}
}

public class sequenceAlignment {
	static int INF = Integer.MAX_VALUE; // 무한대 값
	static ArrayList<String> str_Result = new ArrayList<String>(); // 최적 조합 결과 담는 배열
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String x = sc.next();  // actgagttaa
		String y = sc.next(); // acagaagta
		sequence_Alignment(x,y);
	}

	public static void sequence_Alignment(String x,String y) {
		DOT[][] result = new DOT[y.length()+1][x.length()+1];
		int lx = x.length(); // x의 길이
		int ly = y.length(); // y의 길이
		int q = -2; // mismatch
		
		for(int i=0;i<=ly;i++) { // INF로 초기화 아직 담기지 않은 곳을 구분하기 위해
			for(int j=0;j<=lx;j++) {
				result[i][j] = new DOT(i,j,new ArrayList<DOT>(),INF);
			}
		}
		
		for(int i=0;i<=lx;i++) { // 공백
			result[0][i].cost = i * q;
		}
		for(int j=0;j<=ly;j++) { // 공백
			result[j][0].cost = j * q;
		}
		
		for(int i=1;i<=ly;i++) {
			for(int j=1;j<=lx;j++) {
				result[i][j].cost = Math.max(Math.max(result[i][j-1].cost+q,result[i-1][j-1].cost+plll(x,y,i,j)),result[i-1][j].cost+q);
				if(result[i][j].cost == result[i][j-1].cost+q) { // prev담기 중복이 있을 경우를 위해 if문을 따로따로 놓았음
					result[i][j].prev.add(result[i][j-1]);
				}
				if(result[i][j].cost == result[i-1][j-1].cost+plll(x,y,i,j)) {
					result[i][j].prev.add(result[i-1][j-1]);
				}
				if(result[i][j].cost == result[i-1][j].cost+q) {
					result[i][j].prev.add(result[i-1][j]);
				}
				
				System.out.println("**************************************************** 현재 테이블 ***********************************************************");
				for(int a=0;a<=ly;a++) { // max 테이블 출력
					for(int b=0;b<=lx;b++) {
						if(result[a][b].cost == INF) {
							System.out.print("\t" + "-");
						}else {
							System.out.print("\t" + result[a][b].cost);							
						}
					}
					System.out.println();
				}
				System.out.println();
			}
		}	
		
		String str = "";
		printResult(x,y,lx,ly,result,str); // 최적 조합 결과 만드는 함수
		
		for(int i=0;i<str_Result.size();i++) { // 점수와 결과 출력
			System.out.println("점수 :" + result[ly][lx].cost);
			for(int j=0;j<str_Result.get(i).length();j++) {
				System.out.print("\t" + x.charAt(j));		
			}
			System.out.println();
			for(int j=str_Result.get(i).length()-1;j>=0;j--) {
				System.out.print("\t" + str_Result.get(i).charAt(j));		
			}
			System.out.println();
		}
		
	}
	
	public static void printResult(String x,String y,int lx,int ly,DOT[][] result,String str) {
		if(result[ly][lx].prev.size() == 0) {
			str_Result.add(str);
			str = "";
			return;
		}
		if(result[ly][lx].prev.size() == 1) { // 중복 x	
			DOT temp = result[ly][lx].prev.get(0);
			if(ly ==temp.row || lx == temp.cal) {
				str += "-";
			}else {
				str += y.charAt(ly-1);
			}
			ly = temp.row;
			lx = temp.cal;
			printResult(x,y,lx,ly,result,str);
		}else { // 중복인 경우
			overLap(x,y,lx,ly,result,str);
		}
	}
	
	public static void overLap(String x,String y,int lx,int ly,DOT[][] result,String str) {
		for(int i=0;i<result[ly][lx].prev.size();i++) {
			if(ly ==result[ly][lx].prev.get(i).row || lx == result[ly][lx].prev.get(i).cal) {
				str += "-";
			}else {
				str += y.charAt(ly-1);
			}
			printResult(x,y,result[ly][lx].prev.get(i).cal,result[ly][lx].prev.get(i).row,result,str);
			str = str.substring(0, str.length()-1);
		}
	}
	
	public static int plll(String x, String y,int i,int j) { // 글자가 맞는지 틀린지 비교
		if(x.charAt(j-1) == y.charAt(i-1)) {
			return +1;
		}else {
			return -1;
		}
	}
	
	
}
