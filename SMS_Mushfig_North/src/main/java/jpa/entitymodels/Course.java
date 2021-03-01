package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	
	@Id
//  @GeneratedValue(strategy= GenerationType.IDENTITY) - with this my project is not running, therefore I commented it.
	@Column(name="id")
	@OrderBy
	private int cId;
	
	@Column(name="name")
	private String cName;
	
	@Column(name="instructor")
	private String cInstructorName;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(int cId, String cName, String cInstructorName) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cId;
		result = prime * result + ((cInstructorName == null) ? 0 : cInstructorName.hashCode());
		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (cId != other.cId)
			return false;
		if (cInstructorName == null) {
			if (other.cInstructorName != null)
				return false;
		} else if (!cInstructorName.equals(other.cInstructorName))
			return false;
		if (cName == null) {
			if (other.cName != null)
				return false;
		} else if (!cName.equals(other.cName))
			return false;
		return true;
	}
	
	

}
