package jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

	@Override
	public List<Student> getAllStudents() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SMSBoston");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT s FROM Student s");
		 
		return query.getResultList();
	}

	@Override
	public Student getStudentByEmail(String sEmail) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SMSBoston");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT s FROM Student s WHERE s.sEmail = :email");
		query.setParameter("email", sEmail);
		Student student = (Student) query.getSingleResult();
		if (student == null) {
			throw new AssertionError("Student does not exist.");
		}
		return student;
	}

	@Override
	public boolean validateStudent(String sEmail, String sPassword) {
		try {
			Student student = getStudentByEmail(sEmail);
			if (student != null && student.getsPass().equals(sPassword)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new AssertionError("No records on this");
			
		}
	}

	@Override
	public void registerStudentToCourse(String sEmail, int cId) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SMSBoston");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createQuery("SELECT s FROM Student s WHERE s.sEmail = :email");
		query.setParameter("email", sEmail);
		Student student = (Student) query.getSingleResult();
		Query query2 = manager.createQuery("SELECT c FROM Course c WHERE c.cId = :id");
		query2.setParameter("id", cId);
		try {
			Course course = (Course) query2.getSingleResult();
			if (student.getsCourses().contains(course)) {
				throw new AssertionError(" Already registered to this course.");
			}
			student.addNewCoursesToStudent(course);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new AssertionError("Course does not exist");
		}
		
	}

	@Override
	public List<Course> getStudentCourses(String sEmail) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SMSBoston");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT s FROM Student s WHERE s.sEmail = :email");
		query.setParameter("email", sEmail);
		Student student = (Student) query.getSingleResult();
		return student.getsCourses();
	}

}
