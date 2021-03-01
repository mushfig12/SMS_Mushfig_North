package jpa.service;

import java.util.List;




import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;


public class ServiceTest {
	 
	@Test
	void testValidateStudent() {
		StudentService studentService = new StudentService();
	boolean isValidActual= studentService.validateStudent("c9@bing.com", "FnVklVgC6r6");
	Assertions.assertTrue(isValidActual);
	}
	
	@ParameterizedTest
	@CsvSource({"0","1", "2", "3", "4", "5", "6", "7", "8", "9"})
	 void  testGetAllStudents(int a) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SMSBoston");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT s FROM Student s");
		     Object studentListExpected = query.getResultList().get(a);
		  StudentService studentService = new StudentService();
		     Student studentListActual = studentService.getAllStudents().get(a);
		  Assertions.assertEquals(studentListExpected, studentListActual);
	}
	@ParameterizedTest
	@CsvSource({"0","1", "2", "3", "4", "5", "6", "7", "8", "9"})
	void  testGetAllCourses(int a) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SMSBoston");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT c FROM Course c");
		Object courseListExpected = query.getResultList().get(a);
		CourseService courseService = new CourseService();
		Course courseListActual = courseService.getAllCourses().get(a);
		 Assertions.assertEquals(courseListExpected, courseListActual);
	
	}

}
