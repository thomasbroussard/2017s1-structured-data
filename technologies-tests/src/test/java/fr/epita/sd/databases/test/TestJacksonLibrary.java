/**
 * 
 */
package fr.epita.sd.databases.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.epita.sd.datamodel.Student;

/**
 * @author tbrou
 *
 */
public class TestJacksonLibrary {

	@Test
	public void firstTest() throws JsonGenerationException, JsonMappingException, IOException{
		Student student = new Student("Thomas", "Broussard", "tbr@tbr.com",new Date());
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.writeValue(new File("target/studentTest.json"), student);
		
		List<Student> list = new ArrayList<>();
		list.add(student);
		mapper.writeValue(new File("target/studentTestList.json"), list);
	}
	
}
