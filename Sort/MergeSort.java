package HW01;
// 201602001 ������
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MergeSort {
	static int mergeCount=0; // merge�Լ��� ���� Ƚ��
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numlist = new ArrayList<Integer>(); 
		try { // ���� �о�ͼ� �迭�� �ֱ�
			FileReader fr = new FileReader("C://Users//user//Desktop//data02.txt"); // ������ �о�� ��� ����
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
	//	long start = System.currentTimeMillis();
		MergeSort(numlist, 0, numlist.size()-1);
	//	long end = System.currentTimeMillis(); 
	//	System.out.println("MergeSort ���� �ð� : " + (end - start) / 1000.0 + "��");
		
		try { // �迭�� �ִ� ������ ���Ϸ� ����
			int i = 0;
			FileWriter fw = new FileWriter("C://Users//user//Desktop//[�˰���]01_����_201602001_merge.txt"); // ������ ������ ��� ����
			BufferedWriter bw = new BufferedWriter(fw); 
			while(i<numlist.size()) {
				bw.write(Integer.toString(numlist.get(i++)));	
				if(i != numlist.size()) bw.write(","); // ������ ���� �ڿ��� ��ǥ �Ⱥٰ� ó��
			}
			bw.newLine();
			bw.write("merge() ��� Ƚ�� : "+ mergeCount); // merge�Լ� ��� Ƚ�� ���
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // ���ܻ�Ȳ ó��
	}
	
	public static void MergeSort(List<Integer> numlist, int p,int r) {
		if(p >= r) return; // ����Լ� ���� ����
		int q = (p+r)/2; // �߰� ��
		MergeSort(numlist,p,q); // �ΰ��� ���� �պκ��� ũ�Ⱑ 1�� ������ ��� �����ش�
		MergeSort(numlist,q+1,r); // �ΰ��� ���� �޺κ��� ũ�Ⱑ 1�� ������ ��� �����ش�
		merge(numlist,p,q,r); // ������ �����ϱ�
	}
	
	public static void merge(List<Integer> numlist, int p, int q, int r) {
		mergeCount++;
		int[] result = new int[numlist.size()]; // �����ϱ� ���� �ӽ� �迭 ����
		int mid = q+1; // mid�� �߰��� + 1
		int start = p; // ���߿� ������ �� p���� �ʿ��ϹǷ� start��� ������ p��� ���
		int i=p; // result�迭�� index
		while(start<=q && mid<=r) { // �ΰ��� ������ �� start�� �߰����� �۰ų� ���� mid�� ���������� �۰ų� ���ƾ� �� 
			if(numlist.get(start)<numlist.get(mid)) { // �� �κ����� ������ �� ������ ù��° ���� ���ؼ� mid�� ũ��
				result[i++] = numlist.get(start); // start���� ����
				start++;
			}else { // start�� ũ�ų� ������
				result[i++] = numlist.get(mid); // mid�� ����
				mid++;
			}
		}
		// �ΰ��� ������ �� �� �κ��� ���� �񱳰� �� ������ ������ �ƴϰ� ������ ���ҵ� �迭�� �Ű���� �ϹǷ�
		while(start<=q) { // start�� �߰����� �۰ų� ������ 
			result[i++] = numlist.get(start); // start���� ����
			start++;
		}
		
		while(mid<=r) { // mid�� ���������� �۰ų� ������
			result[i++] = numlist.get(mid); // mid���� ����
			mid++;
		}
		
		for(int k=p;k<=r;k++) { // �ӽ� �迭�� ���ĵ� ������ �ٽ� numlist�� ����
			numlist.set(k, result[k]);			
		}
	}
	

}
