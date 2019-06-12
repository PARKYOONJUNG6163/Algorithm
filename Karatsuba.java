package HW07;

import java.util.Scanner;
public class Karatsuba {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String num1 = sc.next();
		String num2 = sc.next();
		long result = Karatsuba(num1,num2);
		System.out.println(result);
	}
	
	public static long Karatsuba(String num1,String num2) {
		int Threshold = 3; // 3으로 지정
		int N = Math.max(num1.length(), num2.length());
		if(num1.length()<=Threshold || num2.length()<=Threshold) {
			return Long.parseLong(num1) * Long.parseLong(num2);
		} // 자릿수가 Threshold보다 작은 경우 일반 곱셈연산으로 결과 계산
		
		N = (N / 2) + (N % 2);
		int num1_midIndex = num1.length()/2; 
		int num2_midIndex = num2.length()/2;
		String x1 = num1.substring(0,num1_midIndex);
		String x2 = num1.substring(num1_midIndex,num1.length());
		String y1 = num2.substring(0,num2_midIndex);
		String y2 = num2.substring(num2_midIndex,num2.length());
		
		long z1 = Karatsuba(x1,y1);
		long z2 = Karatsuba(x2,y2);
		long z3 = Karatsuba(String.valueOf(Long.parseLong(x2)+Long.parseLong(x1)),String.valueOf(Long.parseLong(y2)+Long.parseLong(y1)))-z1-z2;
		
		long result = (long) (z1 * Math.pow(10,2*N) + z3 * Math.pow(10,N)+ z2);
		return result;
	}
}
