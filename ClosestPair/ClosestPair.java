package HW05;
//201602001 ������
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClosestPair {
	static DOT point1 = null; // �ּ� point��ǥ
	static DOT point2 = null; // �ּ� point��ǥ
	static double minNum = Double.MAX_VALUE; // �ּ� ��ǥ�� �������ֱ� ���� �ּҰ�
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<DOT> numlist = new ArrayList<DOT>();
		try { // ���� �о�ͼ� �迭�� �ֱ�
			FileReader fr = new FileReader("C://Users//user//Desktop//data05.txt"); // ������ �о�� ��� ����
			String line = "";
			int i = 0;
			BufferedReader br = new BufferedReader(fr); // ���� ������ ��ü br
			while ((line = br.readLine()) != null) { // ���� ������ ������� ������
				String[] s = line.split(",");
				DOT dot = new DOT(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
				numlist.add(dot); // ����Ʈ�� �߰�
			}
			MergeSort(numlist, 0, numlist.size() - 1, "x"); // x���� �������� mergeSort�� �̿��Ͽ� ����
			double min = closestMin(numlist, 0, numlist.size() - 1);
			System.out.println("���� ª�� �Ÿ��� : " + min);
			System.out.println("closest Pair �� (" + point1.x +" , "+point1.y+") "+"(" + point2.x +" , "+point2.y+")");
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // ���ܻ�Ȳ ó��
	}

	public static double closestCal(List<DOT> numlist, int i, int j) { // �ִܰŸ��� ����ϴ� �Լ�
		double min = Double.MAX_VALUE;
		double distance;
		for (int a = i; a <= j; a++) {
			for (int b = a + 1; b <= j; b++) {
				distance = (double) Math.sqrt(Math.pow(numlist.get(b).x - numlist.get(a).x, 2)
						+ Math.pow(numlist.get(b).y - numlist.get(a).y, 2)); // �� �������� �Ÿ� ���ϱ�
				if(min > distance) {
					min = distance;
					if(minNum > distance) {  // �ִܰŸ��� ��ǥ ���ϴ� �κ�
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
		int mid = (i+j) / 2; // �߰� �ε���
		int n = j-i+1;
		if(n <= 3) { // ������ 3�� ������ ���
			return closestCal(numlist,i,j);
		}
		
		DOT midDot = numlist.get(mid);
		
		double Left_min_dist = closestMin(numlist,i,mid); // ���� ������ ����Ʈ ���� �ִ� �Ÿ�
		double Right_min_dist = closestMin(numlist,mid+1,j); // ���� ������ ����Ʈ ���� �ִ� �Ÿ�

		double d = Math.min(Left_min_dist,Right_min_dist); // ���� �ִ� �Ÿ�
		
		int index=0;
		List<DOT> dlist = new ArrayList<DOT>(); 
		for(int k=i;k<=j;k++) {  // window�ȿ� �ִ� ���ҵ��� ������
			if(Math.abs(numlist.get(k).x-midDot.x) < d) {
				dlist.add(index, numlist.get(k));
				index++;
			}
		}
		
		MergeSort(dlist,0,dlist.size()-1,"y"); // y���� �������� mergeSort
		for (int a = 0; a < dlist.size(); a++) {
			for (int b = a + 1; b < dlist.size(); b++) {
				if(Math.abs(dlist.get(b).y - dlist.get(a).y) < d) { // y��ǥ�� ���Ͽ� ���� �ִܰŸ� ���ϱ�
					double distance = (double) Math.sqrt(Math.pow(dlist.get(b).x - dlist.get(a).x, 2)
							+ Math.pow(dlist.get(b).y - dlist.get(a).y, 2)); // �� �������� �Ÿ� ���ϱ�
					if(d > distance) {
						d = distance;
						if(minNum > distance) { // ���� �ִܰŸ��� ��ǥ ���ϴ� �κ�
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
			return; // ����Լ� ���� ����
		int q = (p + r) / 2; // �߰� ��
		MergeSort(numlist, p, q, axis); // �ΰ��� ���� �պκ��� ũ�Ⱑ 1�� ������ ��� �����ش�
		MergeSort(numlist, q + 1, r, axis); // �ΰ��� ���� �޺κ��� ũ�Ⱑ 1�� ������ ��� �����ش�
		merge(numlist, p, q, r, axis); // ������ �����ϱ�
	}

	public static void merge(List<DOT> numlist, int p, int q, int r, String axis) {
		DOT[] result = new DOT[numlist.size()]; // �����ϱ� ���� �ӽ� �迭 ����
		int mid = q + 1; // mid�� �߰��� + 1
		int start = p; // ���߿� ������ �� p���� �ʿ��ϹǷ� start��� ������ p��� ���
		int i = p; // result�迭�� index
		if (axis.equals("x")) { // x�� ���� ����
			while (start <= q && mid <= r) { // �ΰ��� ������ �� start�� �߰����� �۰ų� ���� mid�� ���������� �۰ų� ���ƾ� ��
				if (numlist.get(start).x < numlist.get(mid).x) { // �� �κ����� ������ �� ������ ù��° ���� ���ؼ� mid�� ũ��
					result[i++] = numlist.get(start); // start���� ����
					start++;
				} else { // start�� ũ�ų� ������
					result[i++] = numlist.get(mid); // mid�� ����
					mid++;
				}
			}
		} else { // y�� ���� ����
			while (start <= q && mid <= r) { // �ΰ��� ������ �� start�� �߰����� �۰ų� ���� mid�� ���������� �۰ų� ���ƾ� ��
				if (numlist.get(start).y < numlist.get(mid).y) { // �� �κ����� ������ �� ������ ù��° ���� ���ؼ� mid�� ũ��
					result[i++] = numlist.get(start); // start���� ����
					start++;
				} else { // start�� ũ�ų� ������
					result[i++] = numlist.get(mid); // mid�� ����
					mid++;
				}
			}
		}
		// �ΰ��� ������ �� �� �κ��� ���� �񱳰� �� ������ ������ �ƴϰ� ������ ���ҵ� �迭�� �Ű���� �ϹǷ�
		while (start <= q) { // start�� �߰����� �۰ų� ������
			result[i++] = numlist.get(start); // start���� ����
			start++;
		}

		while (mid <= r) { // mid�� ���������� �۰ų� ������
			result[i++] = numlist.get(mid); // mid���� ����
			mid++;
		}

		for (int k = p; k <= r; k++) { // �ӽ� �迭�� ���ĵ� ������ �ٽ� numlist�� ����
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
