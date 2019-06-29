package HW13;
// 201602001 박윤정
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixMultiplication {
	static int INF = Integer.MAX_VALUE; // 무한대 값
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		ArrayList<Integer> p = new ArrayList<Integer>();
		boolean first = true;
		while(true) {
			String input = sc.nextLine();
			String[] str = input.split(" ");
			if(Integer.parseInt(str[0]) == 0 || Integer.parseInt(str[1]) == 0) break;
			if(first) {
				p.add(Integer.parseInt(str[0]));
				first = false;
			}
			p.add(Integer.parseInt(str[1]));
		}
		for(int i=0;i<p.size()-1;i++) {
			System.out.println("A("+(i+1)+") = " + p.get(i)+ " x " + p.get(i+1));
		}
		Matrix_Chain_Order(p);
	}
	
	public static void Matrix_Chain_Order(ArrayList<Integer> p) { // 괄호 사용하여 최소 곱셈 횟수 가지는 행렬 곱 순서
		int n = p.size()-1;
		int[][] m = new int[n][n]; 
		int[][] s = new int[n][n]; 
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				m[i][j] = -1;
				s[i][j] = -1;
			}
		}
		for(int i=0;i<n;i++) {
			m[i][i] = 0; 
		}
		for(int l=1;l<n;l++) {
			for(int i=0;i<n-l;i++) {
				int j = i+l;
				m[i][j] = INF;
				for(int k=i;k<j;k++) {
					int q = m[i][k] + m[k+1][j] + (p.get(i) * p.get(k+1) * p.get(j+1)); 
					if(q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		
		System.out.println("---------------------테이블 출력----------------------");
		for(int i=0;i<n;i++) { // m테이블 출력
			for(int j=0;j<n;j++) {
				System.out.print(m[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("---------------------테이블 출력----------------------");
		for(int i=0;i<n;i++) { // s테이블 출력
			for(int j=0;j<n;j++) {
				System.out.print(s[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Optimal solution : "+m[0][5]);
		System.out.print("Optimal parens : ");
		Print_Optimal_Parens(s,0,5);
	}
	
	public static void Print_Optimal_Parens(int[][] s,int i,int j) {
		if(i==j) {
			System.out.print("A"+(i+1));
		}else {
			System.out.print("(");
			Print_Optimal_Parens(s,i,s[i][j]);
			Print_Optimal_Parens(s,s[i][j]+1,j);
			System.out.print(")");
		}
	}
}
