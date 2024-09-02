package com.connection.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {

		initDb();
		dbRun();
	}

	static private void initDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("잘연결됨");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	static private void dbRun() {
		try { // 아래 코드들을 이 try{ 블럭 안에 안쓰면 에러남.
				// *db명* *id* *암호*
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			java.sql.Statement st = con.createStatement(); // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체. Statement하나당 한개의
															// ResultSet 객체만을 열 수있다.
			// 위에 까지는 매번 뻔하게 작성해주는 실행문
			// 이 아래부터는 보낼 sql 문 종류에 따라 차이가 생김
			ResultSet result = st.executeQuery("select * from tottenham_squad where p_number=10"); // 전송 할 sql 문
//			ResultSet re = st.executeQuery("select * from board where b_reply_ori=20");
//			System.out.println(re);
			while (result.next()) { // 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
				String name = result.getString("p_name"); // p_name 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서)
				System.out.println(name);

			}
		} catch (SQLException e) { // java try-catch 문. p.14장 참고. 일단 그냥 복붙.
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
		}
	}

}
