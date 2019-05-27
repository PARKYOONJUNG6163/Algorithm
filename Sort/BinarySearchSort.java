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

public class BinarySearchSort {
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
		insertionSort(numlist, numlist.size()); 
	//	long end = System.currentTimeMillis();
	//	System.out.println("BinarySearchSort ���� �ð� : " + (end - start) / 1000.0 + "��");
		
		try { // �迭�� �ִ� ������ ���Ϸ� ����
			int i = 0;
			FileWriter fw = new FileWriter("C://Users//user//Desktop//[�˰���]01_����_201602001_binary_insertion.txt"); // ������ ������ ��� ����
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
	
	public static void insertionSort(List<Integer> numlist, int n) {
		int key;
		int index;
		int i;
		for(int j=1;j<n;j++) {
			i = j-1; 
			key = numlist.get(j);
			index = binarySearch(numlist,0,i,key); // ���� Ž�� �Լ��� �ڸ� ã��
			while(i>=index) { // index�ڸ��� key�� �ֱ����Ͽ� ��ĭ �� �ڷ� �����
				numlist.set(i+1, numlist.get(i));
				i -= 1;
			}
			numlist.set(index, key); // index�ڸ��� key�� �ֱ�	
			
		}
	}
	
	public static int binarySearch(List<Integer> numlist,int start,int end,int key) {
		if(start >= end) { // ����Լ��� ���� ����
			if(key >= numlist.get(start)) { // ���ų� ã������ ���� ũ�� 
				return start+1;
			}else { // ã������ ���� ������
				return start;
			}
		}
		int mid = (start+end)/2;
		if(numlist.get(mid) > key) // key�� mid���� ������ mid���� �Ʒ� �κ��� ���������
			return binarySearch(numlist,start,mid-1,key);
		else // key�� mid���� ũ�� mid���� �� �κ��� ���������
			return binarySearch(numlist,mid+1,end,key);
	}
}
