package cosco.vsagent.model;
import java.util.Date;
public class Exam {
	private Long id;
	private String name;
	private Teacher teacher;
	private Course course;
	private SchoolClass schoolClass;
	private Date date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
