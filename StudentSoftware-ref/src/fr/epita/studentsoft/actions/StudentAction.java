package fr.epita.studentsoft.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.sd.datamodel.Message;
import fr.epita.sd.datamodel.Student;
import fr.epita.sd.services.JSONService;
import fr.epita.sd.services.impl.AbstractJSONServiceImpl;
import fr.epita.sd.services.impl.JSONServiceMessageJacksonImpl;
import fr.epita.sd.services.impl.JSONServiceStudentJacksonImpl;
import fr.epita.sd.services.impl.StudentJDBCDAO;

/**
 * Servlet implementation class StudentAction
 */
@WebServlet("/studentaction")
public class StudentAction extends HttpServlet {
	
	private static final StudentJDBCDAO dao = new StudentJDBCDAO();
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StudentAction() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletInputStream inputStream = request.getInputStream();
		String jsonString = "";
		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNext()){
			jsonString += scanner.nextLine();
		}
		
		JSONService<Student> jsonService = new JSONServiceStudentJacksonImpl();
		Student student = jsonService.fromJson(jsonString);
		
		try {
			dao.insert(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONService<Message> jsonMessageService = new JSONServiceMessageJacksonImpl();
		
		JSONService<Message> jsonMessageService2 = new AbstractJSONServiceImpl<Message>() {

			@Override
			public Class<Message> getObjectClass() {
				// TODO Auto-generated method stub
				return Message.class;
			}
			
			
		};
		
		
	//	AbstractJSONServiceImpl<Message> jsonMessageService2 = new AbstractJSONServiceImpl<Message>(() ->  Message.class);
		
		String responseAsJson = jsonMessageService.toJson(new Message("success", "the student has been inserted correctly"));
		
		response.setStatus(200);
		response.getWriter().println(responseAsJson);
		response.flushBuffer();
		
		
		
		
		
	}

}
