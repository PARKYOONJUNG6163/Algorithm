package HW11;
// 201602001 ������
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class knapsackProblem {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> value = new ArrayList<Integer>(); // value���� �����ϴ� �迭
		ArrayList<Integer> weight = new ArrayList<Integer>(); // weight���� �����ϴ� �迭
		int n=0; // �������� ����
		try { // ���� �о�ͼ� �迭�� �ֱ�
			FileReader fr = new FileReader("C://Users//user//Desktop//data11.txt"); // ������ �о�� ��� ����
			String line = "";
			BufferedReader br = new BufferedReader(fr); // ���� ������ ��ü br
			while ((line = br.readLine()) != null) { // ���� ������ ������� ������
				String[] s = line.split(",");
				value.add(Integer.parseInt(s[0])-1, Integer.parseInt(s[1]));
				weight.add(Integer.parseInt(s[0])-1, Integer.parseInt(s[2]));
				n++;
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // ���ܻ�Ȳ ó��
		Scanner sc = new Scanner(System.in);
		System.out.println("�賶�� ����� �Է��ϼ��� (0-50) : ");
		int size = sc.nextInt(); // �Է¹��� �賶�� ������
		if(size>=0 && size <= 50) { // 0���� 50�� ���������� 
			knapsack(n,size,weight,value);	
		}else {
			System.out.println("size�� 0~50�� ���� ������ �����ؾ� �մϴ�.");
		}
	}

	public static void knapsack(int n,int size,ArrayList<Integer> weight,ArrayList<Integer> value) {
		OPT[][] max = new OPT[n+1][size+1]; // n+1ũ��, w+1ũ��
		
		for(int i=0;i<n+1;i++) { // �ʱ�ȭ
			for(int j=0;j<size+1;j++) {
				max[i][j] = new OPT();
			}
		}
		
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<size+1;j++) {
				if(weight.get(i-1) > j) { // i-1���� ������ ���԰� Ŀ�� �賶�� ���� �� ����
					max[i][j].total = max[i-1][j].total;
					max[i][j].set = max[i-1][j].set;
				}else // ������ �־��� ���� ���� �ʾ��� �� ���� �� �ִ�
					max[i][j].total = Math.max(max[i-1][j].total,value.get(i-1)+max[i-1][j-weight.get(i-1)].total);
						// value�� ���簡ġ + �賶�� ũ�⿡�� ���� ��ġ�� ũ�⸦ �� ũ���϶� max��
					if(max[i][j].total == max[i-1][j].total) { // ������ ���� �ʾ��� ���� �ִ�
						max[i][j].set = max[i-1][j].set;
					}else { // ������ �־��� ���� �ִ�
						max[i][j].set = new int[max[i-1][j-weight.get(i-1)].set.length+1]; // �迭 ũ�� ����
						System.arraycopy(max[i-1][j-weight.get(i-1)].set,0, max[i][j].set, 0, max[i-1][j-weight.get(i-1)].set.length);
						max[i][j].set[max[i-1][j-weight.get(i-1)].set.length] = i;
					}
			}
		} 
		
		for(int i=0;i<n+1;i++) { // max ���̺� ���
			for(int j=0;j<size+1;j++) {
				System.out.print("\t" + max[i][j].total);
			}
			System.out.println();
		}
		
		System.out.println("max : " + max[n][size].total); // �ִ� ��ġ ���
		System.out.print("item: "); // ������� item ���
		for(int i=0;i<max[n][size].set.length;i++) {
			System.out.print(max[n][size].set[i]+" ");
		}
	}
}

class OPT{ // ������ ��ü ��ġ�� �׿� ���� set
	int total;
	int[] set;
	
	public OPT() {
		total = 0;
		set = new int[0];
	}
}
