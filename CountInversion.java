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
		try { // ���� �о�ͼ� �迭�� �ֱ�
			FileReader fr = new FileReader("C://Users//user//Desktop//data07.txt"); // ������ �о�� ��� ����
			String line = "";
			BufferedReader br = new BufferedReader(fr); // ���� ������ ��ü br
			while ((line = br.readLine()) != null) { // ���� ������ ������� ������
				StringTokenizer st = new StringTokenizer(line, ","); // ","�� �������� �о�� line�� �����ش�
				while (st.hasMoreTokens()) { // token�� �� �̻� ���� ����
					numlist.add(Integer.parseInt(st.nextToken())); // ����Ʈ�� �߰�
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // ���ܻ�Ȳ ó��
		int inversion_Count = MergeSort(numlist,0,numlist.size()-1);
		System.out.println("Inversion�� ���� : " + inversion_Count);
	}
	
	public static int MergeSort(List<Integer> numlist, int p,int r) {
		if(p >= r) return 0; // ����Լ� ���� ����
		int q = (p+r)/2; // �߰� ��
		int left_inversion_Count = MergeSort(numlist,p,q); // �ΰ��� ���� �պκ��� ũ�Ⱑ 1�� ������ ��� �����ش�
		int right_inversion_Count  =  MergeSort(numlist,q+1,r); // �ΰ��� ���� �޺κ��� ũ�Ⱑ 1�� ������ ��� �����ش�
		int inversion_Count = merge(numlist,p,q,r); // ������ �����ϱ�
		
		return inversion_Count + left_inversion_Count + right_inversion_Count;
	}
	
	public static int merge(List<Integer> numlist, int p, int q, int r) {
		int[] result = new int[numlist.size()]; // �����ϱ� ���� �ӽ� �迭 ����
		int inversion_Count = 0;
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
				inversion_Count += (q + 1 - start);
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
		
		return inversion_Count;
	}
}
