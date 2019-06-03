package HW04;
//201602001 ������
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BubbleSort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numlist = new ArrayList<Integer>(); 
		try { // ���� �о�ͼ� �迭�� �ֱ�
			FileReader fr = new FileReader("C://Users//user//Desktop//data04.txt"); // ������ �о�� ��� ����
			String line = "";
			int i = 0;
			BufferedReader br = new BufferedReader(fr); // ���� ������ ��ü br
			while ((line = br.readLine()) != null) { // ���� ������ ������� ������
				StringTokenizer st = new StringTokenizer(line, ","); // " "�� �������� �о�� line�� �����ش�
				while (st.hasMoreTokens()) { // token�� �� �̻� ���� ����
					numlist.add(Integer.parseInt(st.nextToken())); // ����Ʈ�� �߰�
					i++;
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // ���ܻ�Ȳ ó��
		BubbleSort(numlist);	
		try { // �迭�� �ִ� ������ ���Ϸ� ����
			int i = 0;
			FileWriter fw = new FileWriter("C://Users//user//Desktop//data04_Sort_Bub.txt"); // ������ ������ ��� ����
			BufferedWriter bw = new BufferedWriter(fw); 
			while(i<numlist.size()) {
				bw.write(Integer.toString(numlist.get(i++)));	
				if(i != numlist.size()) bw.write(","); // ������ ���� �ڿ��� ��ǥ �Ⱥٰ� ó��
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // ���ܻ�Ȳ ó��
	}

	private static void BubbleSort(List<Integer> numlist) {
			for(int i = numlist.size()-1;i>=0;i--) { 
				for(int j=0;j<i;j++) {
					if(numlist.get(j) > numlist.get(j+1)) {
						int temp = numlist.get(j); // swap
						numlist.set(j,numlist.get(j+1));
						numlist.set(j+1,temp);
					}
				}
			}
		}
}