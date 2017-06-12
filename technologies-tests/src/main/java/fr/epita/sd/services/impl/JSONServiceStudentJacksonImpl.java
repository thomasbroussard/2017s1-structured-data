/**
 * 
 */
package fr.epita.sd.services.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.epita.sd.datamodel.Student;
import fr.epita.sd.services.JSONService;

/**
 * @author tbrou
 *
 */
public class JSONServiceStudentJacksonImpl implements JSONService<Student>{

	
	ObjectMapper mapper = new ObjectMapper();
	/* (non-Javadoc)
	 * @see fr.epita.sd.services.JSONService#toJson(java.util.List)
	 */
	@Override
	public String toJson(List<Student> list) {
		StringWriter writer = new StringWriter();
		String result ="";
		try {
			mapper.writeValue(writer, list);
			result = writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see fr.epita.sd.services.JSONService#toJson(java.lang.Object)
	 */
	@Override
	public String toJson(Student instance) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.epita.sd.services.JSONService#fromJson(java.lang.String)
	 */
	@Override
	public Student fromJson(String jsonString) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.epita.sd.services.JSONService#fromJsonAsList(java.lang.String)
	 */
	@Override
	public List<Student> fromJsonAsList(String jsonString) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
