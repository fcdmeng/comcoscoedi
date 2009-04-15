package cosco.vsagent.db;

import java.util.List;
import java.util.Set;

import cosco.vsagent.model.Course;
import cosco.vsagent.model.Exam;
import cosco.vsagent.model.Grade;
import cosco.vsagent.model.IUser;
import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.model.StudentScore;


public interface DbOperate{
	IUser getUser(String userId);
	Set<Course> getCourses(Long iuser_id);
	List<Course> getCourses();
	Course getCourses(String name);
	SchoolClass getSchoolclass(Long id);
	List<IUser> getUsers(QueryInfo qi);
	boolean removeUser(IUser user);
	List<SchoolClass> getAllSchoolClass();
	boolean inserUser(IUser user);
	
	boolean modifyUser(IUser user);
	
	List<Exam> getExam(Course course, SchoolClass schoolClass);
	List<StudentScore> getStudentScore(Exam exam);
	
	List<Course> getCourse (QueryInfo qi);
	
	boolean removeCourse(Course course);
	boolean removeGrade(Grade grade);
	boolean removeSchoolClass(SchoolClass schoolClass);
	
	List<Grade> getGrade (QueryInfo qi);
	List<SchoolClass> getSchoolClass (QueryInfo qi);
	
	Grade getGrade(int id);
	boolean insertCourse(Course course);
}
