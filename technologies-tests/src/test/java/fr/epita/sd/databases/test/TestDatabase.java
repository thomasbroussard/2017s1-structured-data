/**
 * 
 */
package fr.epita.sd.databases.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * @author tbrou
 *
 */
public class TestDatabase {

	
	@Test
	public void firstDbTest() throws SQLException{
		//first : connect
		String connectionString = "jdbc:derby:memory:testDB;create=true";
		Connection connection = DriverManager.getConnection(connectionString, "user", "test");
		
		String tableCreationString = "CREATE TABLE STUDENTS"
				+ "(STUDENT_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT STUDENT_PK PRIMARY KEY, "
				+ "FIRST_NAME VARCHAR(35), "
				+ "LAST_NAME VARCHAR(50), "
				+ "BIRTHDATE DATE)";
		PreparedStatement pstmt = connection.prepareStatement(tableCreationString);
		
		pstmt.execute();
		pstmt.close();
		
		
		String insertionString = "INSERT INTO STUDENTS (FIRST_NAME,LAST_NAME,BIRTHDATE) VALUES ('Thomas','BROUSSARD','1986-04-09')";
		
		PreparedStatement insertionStatement = connection.prepareStatement(insertionString);
		insertionStatement.execute();
		insertionStatement.close();
		String selectString = "SELECT * FROM STUDENTS";
		PreparedStatement selectStatement = connection.prepareStatement(selectString);
		
		ResultSet rs = selectStatement.executeQuery();
		
		while(rs.next()){
			System.out.println(rs.getString("FIRST_NAME"));
			System.out.println(rs.getString("LAST_NAME"));
			System.out.println(rs.getDate("BIRTHDATE"));
		}
		rs.close();
		selectStatement.close();
		connection.close();
	
	}
	
}
