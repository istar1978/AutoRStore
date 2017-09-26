package com.zhaozhy.autorstore.save;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

public class GetDataTest  extends TestCase{
	public void testGetStaffer(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/drugstore","root","root");
			Statement st=conn.createStatement();
			//String sql="select * from staffer";
			String sql="insert into staffer(name,password,position,level,dep_id,create_date,stat) values('钟永','123','sales','0','00000001','2010-01-01','0')";
			
			st.execute(sql);
			/*ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}*/
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
