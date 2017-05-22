/**
 * 
 */
package fr.epita.sd.databases.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author tbrou
 *
 */
public class TestDatabase {

	
	@Test
	public void firstDbTest() throws SQLException, ParserConfigurationException, TransformerException, IOException{
		createDB();

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document document = db.newDocument();
		
		Element students = document.createElement("students");
		document.appendChild(students);
		
		Connection connection = getConnection();
		ResultSet rs = getStudents(connection);
		
		while(rs.next()){
			Element student = document.createElement("student");
			students.appendChild(student);
			student.setAttribute("studentId", String.valueOf(rs.getInt(1)));
			Element firstName = document.createElement("firstName");
			Element lastName = document.createElement("lastName");
			Element date = document.createElement("date");
			firstName.setTextContent(rs.getString(2));
			lastName.setTextContent(rs.getString(3));
			date.setTextContent(rs.getString(4));
			student.appendChild(firstName);
			student.appendChild(lastName);
			student.appendChild(date);
		
		}
		rs.close();
		connection.close();
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(document.getDocumentElement()), new StreamResult(writer));
		String result = writer.toString();
		File file = new File("/tmp/xmltest/identities.xml");
		file.getParentFile().mkdirs();
		if (!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		fw.write(result);
		fw.flush();
		fw.close();
		
		
		
		
		
	}

	/**
	 * @throws SQLException
	 */
	private void createDB() throws SQLException {
		//first : connect
		Connection connection = getConnection();
		
		
		//secondly: create table
		String tableCreationString = "CREATE TABLE STUDENTS"
				+ "(STUDENT_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT STUDENT_PK PRIMARY KEY, "
				+ "FIRST_NAME VARCHAR(35), "
				+ "LAST_NAME VARCHAR(50), "
				+ "BIRTHDATE DATE)";
		PreparedStatement pstmt = connection.prepareStatement(tableCreationString);
		
		pstmt.execute();
		pstmt.close();
		
		//then insert
		String insertionString = "INSERT INTO STUDENTS (FIRST_NAME,LAST_NAME,BIRTHDATE) VALUES ('Thomas','BROUSSARD','1986-04-09')";
		
		PreparedStatement insertionStatement = connection.prepareStatement(insertionString);
		insertionStatement.execute();
		insertionStatement.close();
		
		// eventually search
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

	/**
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		String connectionString = "jdbc:derby:memory:testDB;create=true";
		Connection connection = DriverManager.getConnection(connectionString, "user", "test");
		return connection;
	}
	
	private ResultSet getStudents(Connection connection) throws SQLException{
		String selectString = "SELECT * FROM STUDENTS";
		PreparedStatement selectStatement = connection.prepareStatement(selectString);
		
		ResultSet rs = selectStatement.executeQuery();
		return rs;
	}
	
}
