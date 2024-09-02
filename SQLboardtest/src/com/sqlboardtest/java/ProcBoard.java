package com.sqlboardtest.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProcBoard {
	Connection con = null;
	Statement st = null;
	ResultSet result = null;
	Scanner sc = new Scanner(System.in);

	void run() {
		dbInit();
		loop_xx: while (true) {
			System.out.println(
					"----------------------------------------------------------------------------------------");
			System.out.println("1.글 리스트/ 2.글 읽기/ 3. 글 쓰기/ 4. 글 삭제/ 5.글 수정/ 0.관리자/ e. 프로그램 종료");
			System.out.println(
					"----------------------------------------------------------------------------------------");
			System.out.println("명령 입력:");
			String choose = sc.next();

			switch (choose) {
			case "1":
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("글 리스트 ");
				System.out.println("-------------------------------------------------------------------------");
				try {
					result = st.executeQuery("select * from board3");
					while (result.next()) {
						String no = result.getString("b_no");
						String title = result.getString("b_title");
						String writer = result.getString("b_id");
						String date = result.getString("b_datetime");
						System.out.println("글번호: " + no + "  제목:" + title + "  작성자:" + writer + "  작성일:" + date);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "2":
				System.out.println("읽을 글 선택: ");
				int readcon = sc.nextInt();
				String y = String.format("select b_text from board3 where b_no=%d;", readcon);
				try {
					result = st.executeQuery(y);
					while (result.next()) {
						String content = result.getNString("b_text");
						System.out.println("---------------------------");
						System.out.println("선택한 글의 내용");
						System.out.println("---------------------------");
						System.out.println(content);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
//				
				break;
			case "3":
				System.out.println("작성할 글 제목 입력: ");
				String title = sc.next();
				System.out.println("작성할 글 내용: ");
				String content = sc.next();
				System.out.println("작성자 입력: ");
				String writer = sc.next();

				String x = String.format(
						"insert into board3 (b_title,b_id,b_datetime,b_text,b_hit) values ('%s','%s',now(),'%s',0)",
						title, writer, content);

				dbExecuteUpdate(x);

				break;
			case "4":
				System.out.println("삭제할 글 번호 입력: ");
				int deletenum = sc.nextInt();
				String d = String.format("delete from board3 where b_no =%d", deletenum);
				dbExecuteUpdate(d);
				break;

			case "5":
				break;
			case "0":
				break;
			case "e":
				break loop_xx;
			default:
				break;
			}

		}

	}

	private void dbInit() {
		try {
			// (1/n) 디비 접속 정보 넣어서 접속하기
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			// (2/n) Statement 객체 얻어오기.
			st = con.createStatement(); // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다. Statement하나당 한개의 ResultSet 객체만을 열
										// 수있다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dbExecuteQuery(String query) {
		try {
			result = st.executeQuery(query);
			while (result.next()) { // 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
				String name = result.getString("p_name"); // p_name 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서)
				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void dbExecuteUpdate(String query) {
		try {
			// (3/n) Statement 객체의 executeUpdate함수에 sql문 실어서 디비에서 실행되게 하기
			int resultCount = st.executeUpdate(query); // 이거 하는 순간 디비에 sql(쿼리) 날아감. (디비에 반영됨)
			System.out.println("처리된 행 수:" + resultCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
