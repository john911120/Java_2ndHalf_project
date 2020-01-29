package www.javaTelecom.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
	  	전체프로그램에서 필요한 클래스와 메소드를 준비한다.
	  	작성일 : 0128
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
 	사용자의 선택에 따라서 해당 기능을 하는 메소드를 넣었다.
 	작성일 : 0128
	 */
	public static void main(String[]args) throws IOException { 
		Scanner sc = new Scanner(System.in);
		String select = "";
		System.out.println(" \n### 연락처 관리 프로그램입니다. ###\n");
		
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
				System.out.println("\n 번호를 잘못 입력했으니 다시 입력하세요.\n");
			}
		}
	}
	// 사용자가 무엇을 선택 할 것인가를 선택하기 위한 메뉴를 출력한다.
		static void print_menu() {
			System.out.println("\n");
			System.out.println("1. 연락처 출력\n");
			System.out.println("2. 연락처 등록\n");
			System.out.println("3. 연락처 삭제\n");
			System.out.println("4. 프로그램 종료\n");
		}
		
		/*
		사용자가 1번을 선택하면 실행되는 코드들이다.
		- 연락처 파일에서 기존에 입력된 내용을 읽어서 출력하는 기능
		- 처음에 fname이라는 파일이 없다면 빈 파일을 만든다.
		- 기존의 연락처를 모두 읽어서 출력시킨다.
		- for문을 사용해서 i를 1씩 증가 시키는 무한 루프를 만들어서
		- 파일을 읽을 수 없거나 i가 1이면 실 파일에는 내용이 없거나
		- 연락처 파일에 전화번호가 존재하지 않는다는 문장을 출력한다.
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
				System.out.println("\n ** 연락처 파일에 전화번호가 하나도 없다. **\n");
			
			br.close();
	
			}
	}
	static void Add() throws IOException {
		Scanner sc = new Scanner(System.in);
		address adr = new address("", "", "");
		
		String wstr = "";
		
		// 파일을 추가 모드로 여는 기능
		BufferedWriter bw = new BufferedWriter(new FileWriter(fname, true));
		
		System.out.println("이름을 입력하세요 : ");
		adr.name = sc.nextLine();
		System.out.println("나이를 입력하세요 : ");
		adr.age = sc.nextLine();
		System.out.println("전화번호를 입력하세요 : ");
		adr.phone = sc.nextLine();
		
		// 입력된 3개의 값을 하나의 문자열로 만든다.
		wstr = adr.name + "\t" + adr.age + "\t" + adr.phone;
		
		// 파일에 문자열을 쓰기
		bw.write(wstr);
		bw.newLine();
		bw.close();
	}
	static void Remove() throws IOException {
		Scanner sc = new Scanner(System.in);
		// 연락처 파일의 내용 전체를 저장하기 위해 문자열 배열을 만든다.
		// 최대 연락처 개수를 지정할 수 있다. 여기서는 50개만 설정한다.
		String[] read_str = new String[50];
		String str = "";
		int del_line, i, count = 0;
		
		BufferedReader br = new BufferedReader(new FileReader(fname));
		
		// 연락처 파일이 없다면 되돌아가는 기능
		if(!br.ready()) {
			System.out.println("\n 연락처 파일이 읎어요 \n");
			return;
		}
		
		System.out.println("\n 삭제할 행 번호는? \n");
		del_line = sc.nextInt();
		
		// 파일에 있는 동안에 수행하는 작업개수 여기서는 최대 50개
		for(i = 0; i < 50; i++) {
			if((str = br.readLine()) == null)
				break;
			// 삭제를 하려는 행이 아니라면 추가시키는 작업
			if(i + 1 != del_line) {
				read_str[count] = str;
				count ++;
			} else 
				System.out.printf("%d 행이 삭제 되었습니다. \n", del_line);
			}
		br.close();
		
		// 파일을 쓰기 모드로 열고 새로운 내용을 쓴다.
		BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
		
		for(i = 0; i < count; i++) {
			bw.write(read_str[i]);
			bw.newLine();
		}
		bw.close();
	}
}
