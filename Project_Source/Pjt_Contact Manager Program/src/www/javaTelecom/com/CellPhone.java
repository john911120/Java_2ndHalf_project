package www.javaTelecom.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
	  	��ü���α׷����� �ʿ��� Ŭ������ �޼ҵ带 �غ��Ѵ�.
	  	�ۼ��� : 0128
	 */

public class CellPhone {
	static String fname = "C:\\temp\\address.txt";
	
	static class address{
		String name;
		String age;
		String phone;
		
		address(String s1, String s2, String s3){
			this.name = s1;
			this.age = s2;
			this.phone = s3;
		}
	}
	/*
 	������� ���ÿ� ���� �ش� ����� �ϴ� �޼ҵ带 �־���.
 	�ۼ��� : 0128
	 */
	public static void main(String[]args) throws IOException { 
		Scanner sc = new Scanner(System.in);
		String select = "";
		System.out.println(" \n### ����ó ���� ���α׷��Դϴ�. ###\n");
		
		while(select != "4") {
			print_menu();
			select = sc.next();
			
			switch (select) {
			case "1":
				View();
				break;
			case "2":
				Add();
				break;
			case "3":
				Remove();
				break;
			case "4":
				return;
			default:
				System.out.println("\n ��ȣ�� �߸� �Է������� �ٽ� �Է��ϼ���.\n");
			}
		}
	}
	// ����ڰ� ������ ���� �� ���ΰ��� �����ϱ� ���� �޴��� ����Ѵ�.
		static void print_menu() {
			System.out.println("\n");
			System.out.println("1. ����ó ���\n");
			System.out.println("2. ����ó ���\n");
			System.out.println("3. ����ó ����\n");
			System.out.println("4. ���α׷� ����\n");
		}
		
		/*
		����ڰ� 1���� �����ϸ� ����Ǵ� �ڵ���̴�.
		- ����ó ���Ͽ��� ������ �Էµ� ������ �о ����ϴ� ���
		- ó���� fname�̶�� ������ ���ٸ� �� ������ �����.
		- ������ ����ó�� ��� �о ��½�Ų��.
		- for���� ����ؼ� i�� 1�� ���� ��Ű�� ���� ������ ����
		- ������ ���� �� ���ų� i�� 1�̸� �� ���Ͽ��� ������ ���ų�
		- ����ó ���Ͽ� ��ȭ��ȣ�� �������� �ʴ´ٴ� ������ ����Ѵ�.
	 */
	static void View() throws IOException {
		String str = "";
		
		File f = new File(fname);
		if(!f.exists()) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
			bw.close();
		}
		BufferedReader br = new BufferedReader(new FileReader(fname));
		int i;
		
		for(i = 1;; i++){
			if(!br.ready())
				break;
		else {
			str = br.readLine();
			System.out.printf("%2d: %s\n", i , str);
		}
		}
			if(i == 1) {
				System.out.println("\n ** ����ó ���Ͽ� ��ȭ��ȣ�� �ϳ��� ����. **\n");
			
			br.close();
	
			}
	}
	static void Add() throws IOException {
		Scanner sc = new Scanner(System.in);
		address adr = new address("", "", "");
		
		String wstr = "";
		
		// ������ �߰� ���� ���� ���
		BufferedWriter bw = new BufferedWriter(new FileWriter(fname, true));
		
		System.out.println("�̸��� �Է��ϼ��� : ");
		adr.name = sc.nextLine();
		System.out.println("���̸� �Է��ϼ��� : ");
		adr.age = sc.nextLine();
		System.out.println("��ȭ��ȣ�� �Է��ϼ��� : ");
		adr.phone = sc.nextLine();
		
		// �Էµ� 3���� ���� �ϳ��� ���ڿ��� �����.
		wstr = adr.name + "\t" + adr.age + "\t" + adr.phone;
		
		// ���Ͽ� ���ڿ��� ����
		bw.write(wstr);
		bw.newLine();
		bw.close();
	}
	static void Remove() throws IOException {
		Scanner sc = new Scanner(System.in);
		// ����ó ������ ���� ��ü�� �����ϱ� ���� ���ڿ� �迭�� �����.
		// �ִ� ����ó ������ ������ �� �ִ�. ���⼭�� 50���� �����Ѵ�.
		String[] read_str = new String[50];
		String str = "";
		int del_line, i, count = 0;
		
		BufferedReader br = new BufferedReader(new FileReader(fname));
		
		// ����ó ������ ���ٸ� �ǵ��ư��� ���
		if(!br.ready()) {
			System.out.println("\n ����ó ������ ����� \n");
			return;
		}
		
		System.out.println("\n ������ �� ��ȣ��? \n");
		del_line = sc.nextInt();
		
		// ���Ͽ� �ִ� ���ȿ� �����ϴ� �۾����� ���⼭�� �ִ� 50��
		for(i = 0; i < 50; i++) {
			if((str = br.readLine()) == null)
				break;
			// ������ �Ϸ��� ���� �ƴ϶�� �߰���Ű�� �۾�
			if(i + 1 != del_line) {
				read_str[count] = str;
				count ++;
			} else 
				System.out.printf("%d ���� ���� �Ǿ����ϴ�. \n", del_line);
			}
		br.close();
		
		// ������ ���� ���� ���� ���ο� ������ ����.
		BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
		
		for(i = 0; i < count; i++) {
			bw.write(read_str[i]);
			bw.newLine();
		}
		bw.close();
	}
}
