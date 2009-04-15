package cosco.vsagent.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cosco.vsagent.app.Constants;
import cosco.vsagent.model.Course;
import cosco.vsagent.model.Exam;
import cosco.vsagent.model.Grade;
import cosco.vsagent.model.IUser;
import cosco.vsagent.model.SchoolClass;
import cosco.vsagent.model.Student;
import cosco.vsagent.model.StudentScore;
import cosco.vsagent.model.Teacher;


public class AbstractdbOperate implements DbOperate {

	public IUser getUser(String userId) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery("select * from iuser where userId ='"+userId+"'");
			if (rs.next()){
				IUser user = createUserFromRs(rs);
				user.setId(new Long(rs.getLong("id")));
				user.setUserId(userId);
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setLatOnLine(rs.getDate("latestOnline"));
				return user;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return null;
	}
	
	private IUser createUserFromRs(ResultSet rs) throws SQLException{
		String type = rs.getString("type");
		
		if(type.equalsIgnoreCase(Constants.IUSER_TEACHER_TYPE)){
			Teacher o = new Teacher();
			//System.out.println("老师");
			Long iuser_id = new Long(rs.getLong("id"));
			o.setCourses(getCourses(iuser_id));
			return o;
		}else if(type.equalsIgnoreCase(Constants.IUSER_STUDENT_TYPE)){
			
			Student o = new Student();
			//System.out.println("学生 ");
			Long schoolclass_id = new Long(rs.getLong("schoolclass_id"));
			o.setSchoolclass(getSchoolclass(schoolclass_id));
			return o;
		}
		return null;
	}


	
	void close(ResultSet rs){
		if (rs != null){
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			rs = null;
		}
	}
	
	void close(Statement sm){
		if (sm != null){
			try{sm.close();}catch(SQLException e){e.printStackTrace();}
			sm = null;
		}
	}
	
	void close (Connection con){
		
	}

	public Set<Course> getCourses(Long iuser_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		

		try{
			con = ConnectManager.getConnection();
			sm =  con.createStatement();
			
			String subSql = "select course_id from iuser_course where iuser_id="+iuser_id;
			String sql = "select * from course where id in("+subSql+")";
			rs = sm.executeQuery(sql);
			Set<Course>set = new HashSet<Course>();
			while(rs.next()){
				Course course = new Course();
				course.setId(new Long(rs.getInt("id")));
				course.setName(rs.getString("name"));
				set.add(course);
			}
			return set;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptySet();
	}

	public SchoolClass getSchoolclass(Long id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.prepareStatement("select * from schoolclass where id="+id);
			rs = sm.executeQuery();
			if(rs.next()){
				SchoolClass schoolClass = new SchoolClass();
				schoolClass.setId(new Long(rs.getInt("id")));
				schoolClass.setName(rs.getString("name"));
				{
					Grade grade = new Grade();
					grade.setId(new Long(rs.getInt("grade_id")));
					schoolClass.setGrade(grade);
				}
				return schoolClass;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public List<IUser> getUsers(QueryInfo qi) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			// 得到总记录数
			rs = sm.executeQuery("select count(id) from iuser");
			rs.next();
			qi.rsCount = rs.getInt(1);
			if (qi.rsCount == 0)// 等于0表示没有记录
				return Collections.emptyList();
			// 算出总页数
			if (qi.rsCount % qi.pageSize == 0)
				qi.pageCount = qi.rsCount / qi.pageSize;
			else
				qi.pageCount = (qi.rsCount / qi.pageSize) + 1;
			// 算出起始位置＝ (当前页号-1)*每页记录数
			int start = (qi.currentPage - 1) * qi.pageSize;
			rs = sm.executeQuery("select * from iuser limit " + start + "," + qi.pageSize);
			List<IUser> list = new ArrayList<IUser>(qi.pageSize);
			while (rs.next()) {
				IUser user = createUserFromRs(rs);
				user.setId(new Long(rs.getLong("id")));
				user.setUserId(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setLatOnLine(rs.getDate("latestOnline"));
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}

	public boolean removeUser(IUser user) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from iuser where id="+user.getId());
			if(user instanceof Teacher)
				sm.addBatch("delete from  iuser_course where iuser_id="+user.getId());
		    sm.executeBatch();
		    con.commit();
		}catch (SQLException e){
			result = false;
			
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
		}finally{
			close(rs);close(sm);close(con);
		}
		return result;
	}

	public List<SchoolClass> getAllSchoolClass() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.prepareStatement("select * from schoolclass");
			rs = sm.executeQuery();
			
			List<SchoolClass> list = new ArrayList<SchoolClass>();
			while(rs.next()){
				SchoolClass schoolClass = new SchoolClass();
				schoolClass.setId(new Long(rs.getInt("id")));
				schoolClass.setName(rs.getString("name"));
				
				Grade grade = new Grade();
				grade.setId(new Long(rs.getInt("grade_id")));
				schoolClass.setGrade(grade);
				list.add(schoolClass);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public List<Course> getCourses(){
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			
			String sql ="select * from course";
			sm = con.prepareStatement(sql);
			rs = sm.executeQuery();
			List<Course> list = new ArrayList<Course>();
			
			while(rs.next()){
				Course course = new Course();
				course.setId(new Long(rs.getInt("id")));
				course.setName(rs.getString("name"));
				list.add(course);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}

	public boolean inserUser(IUser user) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			
			if(getUser(user.getUserId()) != null)
				return false;
			
			String sql = "insert into iuser(type,userid,password,name,schoolclass_id)values(?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			sm.setString(2, user.getUserId());
			sm.setString(3, user.getPassword());
			sm.setString(4, user.getName());
			
			if(user instanceof Student){
				sm.setString(1, Constants.IUSER_STUDENT_TYPE);
				SchoolClass schoolClass = ((Student)user).getSchoolclass();
				if(schoolClass == null)
					sm.setNull(5, java.sql.Types.BIGINT);
				else
					sm.setInt(5, schoolClass.getId().intValue());
				sm.execute();
			}else if(user instanceof Teacher){
				sm.setString(1, Constants.IUSER_TEACHER_TYPE);
				sm.setNull(5, java.sql.Types.BIGINT);
				sm.execute();
				rs = sm.executeQuery("select id from iuser where userid='"+user.getUserId()+"'");
				rs.next();
				
				int iuser_id = rs.getInt(1);
				for(Course course :((Teacher)user).getCourses()){
					sql = "insert into iuser_course values("+iuser_id + "," + course.getId() + ")";
					sm.addBatch(sql);
				}
				sm.executeBatch();
			}
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(con);close(sm);close(rs);
		}
		return false;
	}

	public boolean modifyUser(IUser user) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			String sql ="update iuser set userid=?, password=?, name=?, latestOnline=?" +
					",schoolclass_id=? where id=?";
			sm = con.prepareStatement(sql);
			sm.setString(1, user.getUserId());
			sm.setString(2, user.getPassword());
			sm.setString(3, user.getName());
			Date latestOnline = user.getLatestOnline();
			if(latestOnline != null){
				long dataValue = latestOnline.getTime();
				sm.setDate(4, new java.sql.Date(dataValue));
			}else{
				sm.setDate(4, null);
			}
				
			
			sm.setInt(6, user.getId().intValue());
			
			if(user instanceof Student){
				SchoolClass schoolClass=((Student)user).getSchoolclass();
				
				if (schoolClass == null)
					sm.setNull(5, java.sql.Types.BIGINT);
				else
					sm.setInt(5, schoolClass.getId().intValue());
				
			}else{
				sm.setNull(5, java.sql.Types.BIGINT);
			}
			
			sm.executeUpdate();
			
			if(user instanceof Teacher){
				con.setAutoCommit(false);
				sm.setNull(5, java.sql.Types.BIGINT);
				sm.addBatch("delete from iuser_course where iuser_id="+user.getId());
				Set<Course> set =((Teacher)user).getCourses();
				for(Course course : set)
					sm.addBatch("insert into iuser_course values("+user.getId()+","+course.getId()+")");
				sm.executeBatch();
				con.commit();
			}
		}catch(SQLException e){
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
			return false;
		}finally{
			close(con);close(sm);close(rs);
		}
		return true;
	}

	public List<Exam> getExam(Course course, SchoolClass schoolClass) {
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select a.*,");
			sb.append("b.name schoolclass_name,b.grade_id schoolclass_grade_id,");
			sb.append("c.name course_name,");
			sb.append("d.name iuser_name,d.userid iuser_userid,d.password iuser_password,d.latestonline iuser_latestonline ");
			sb.append("from exam a left join schoolclass b on a.schoolclass_id = b.id ");
			sb.append("left join course c on a.course_id = c.id ");
			sb.append("left join iuser d on a.iuser_id = d.id ");
			
			if(course !=null && schoolClass != null){
				sb.append("where a.course_id= "+course.getId() +" and a.schoolclass_id = "+ schoolClass.getId());
			}else if( course == null && schoolClass != null){
				sb.append("where a.schoolclass_id="+schoolClass.getId());
			}else if( course != null && schoolClass == null){
				sb.append("where a.course_id="+course.getId());	
			}
			//System.out.println(sb.toString());
			sm = con.prepareStatement(sb.toString());
			rs = sm.executeQuery();
			
			List<Exam> list = new ArrayList<Exam>();
			while(rs.next()){
				Exam exam = new Exam();
				exam.setId(new Long(rs.getInt("id")));
				exam.setName(rs.getString("name"));
				exam.setDate(rs.getDate("date"));
				{
					SchoolClass o = new SchoolClass();
					o.setId(new Long(rs.getInt("schoolclass_id")));
					o.setName(rs.getString("schoolclass_name"));
					
					Grade o2 = new Grade();
					o2.setId(new Long(rs.getInt("schoolclass_grade_id")));
					o.setGrade(o2);
					exam.setSchoolClass(o);
					
				}
				{
					Course o = new Course();
					o.setId(new Long(rs.getInt("course_id")));
					o.setName(rs.getString("course_name"));
					exam.setCourse(o);
				}
				{
					Teacher o = new Teacher();
					o.setId(new Long(rs.getInt("iuser_id")));
					o.setUserId(rs.getString("iuser_userid"));
					o.setPassword(rs.getString("iuser_password"));
					o.setName(rs.getString("iuser_name"));
					o.setLatOnLine(rs.getDate("iuser_latestonline"));
					exam.setTeacher(o);
				}
				list.add(exam);
			}
			return list;
		}catch (SQLException e){e.printStackTrace();}
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	public List<StudentScore> getStudentScore(Exam exam) {
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select a.*,b.userid,b.password,b.name,b.latestonline,b.schoolclass_id ");
			sb.append("from studentscore a left join iuser b on a.iuser_id = b.id ");
			sb.append("where exam_id="+exam.getId());
						
//			System.out.println(sb.toString());
			sm = con.prepareStatement(sb.toString());
			rs = sm.executeQuery();

			List<StudentScore> list = new ArrayList<StudentScore>();
			while(rs.next()){
				StudentScore studentScore = new StudentScore();
				studentScore.setId(new Long(rs.getInt("id")));
				studentScore.setExam(exam);
				studentScore.setScore(rs.getFloat("score"));
				{
					Student stu = new Student();
					stu.setId(new Long(rs.getLong("iuser_id")));
					stu.setPassword(rs.getString("password"));
					stu.setName(rs.getString("name"));
					stu.setLatOnLine(rs.getDate("latestOnline"));
					
					{
						SchoolClass schoolClass = new SchoolClass();
						schoolClass.setId(new Long(rs.getInt("schoolclass_id")));
						stu.setSchoolclass(schoolClass);
						
					}
					studentScore.setStudent(stu);
				}
				list.add(studentScore);
				}
			return list;
		}catch(SQLException e){e.printStackTrace();}
		finally{
			close(rs);close(sm);close(con);
		}
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}
	
	public List<Course> getCourse(QueryInfo qi) {
		Connection con = null;
		ResultSet rs = null;
		Statement sm = null;
		
		// TODO Auto-generated method stub
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery("select * from Course");
			List<Course> list = new ArrayList<Course>();
			while(rs.next()){
				Course course = new Course();
				course.setId(new Long(rs.getInt("id")));
				course.setName(rs.getString("name"));
				list.add(course);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}

	public boolean removeCourse(Course course){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			con.setAutoCommit(false);
			sm.addBatch("delete from course where id="+course.getId());
			sm.executeBatch();
			con.commit();
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}finally{
			close(con);close(sm);close(rs);
		}
		return result;
	}

	public boolean removeGrade(Grade grade) {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement sm = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			con.setAutoCommit(false);
			sm.addBatch("delete from grade where id ="+grade.getId());
			sm.executeBatch();
			con.commit();
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}finally{
			close(con);close(sm);close(rs);
		}
		
		return result;
	}

	public boolean removeSchoolClass(SchoolClass schoolClass) {
		// TODO Auto-generated method stub
		boolean result = true;
		Connection con = null;
		ResultSet rs = null;
		Statement sm = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			con.setAutoCommit(false);
			sm.addBatch("delete from schoolclass where id="+schoolClass.getId() +" And grade_id="+ schoolClass.getGrade().getId() );
			sm.executeBatch();
			con.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(con);close(rs);close(sm);
		}
		return result;
	}
	
	//读取班级列表
	public List<Grade> getGrade(QueryInfo qi) {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement sm = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery("select * from grade");
			List<Grade> list = new ArrayList<Grade>();
			while(rs.next()){
				Grade grade = new Grade();
				grade.setId(new Long(rs.getInt("id")));
				grade.setName(rs.getString("name"));
				list.add(grade);
			}
			return list;
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}

	public List<SchoolClass> getSchoolClass(QueryInfo qi) {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement sm = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery("select * from schoolclass ");
			List<SchoolClass> list = new ArrayList<SchoolClass>();
			while (rs.next()){
				SchoolClass schoolClass = new SchoolClass();
				
				schoolClass.setId(new Long(rs.getInt("id")));
				schoolClass.setName(rs.getString("name"));
				
				Grade grade = getGrade(rs.getInt("grade_id"));
				if (grade != null){
					schoolClass.setGrade(grade);
				}else{
//					schoolClass.setGrade(new Grade());
//					System.out.println("<><><><><>");
				}
				

				list.add(schoolClass);
			}
			return list;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);close(sm);close(con);
		}
		return Collections.emptyList();
	}
	
	public Grade getGrade(int id) {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement sm = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery("select * from grade where id=" + id);
			List<Grade> list = new ArrayList<Grade>();
			if(rs.next()){
				Grade grade = new Grade();
				grade.setId(new Long(rs.getInt("id")));
				grade.setName(rs.getString("name"));
				
				return grade;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertCourse(Course course) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			//校验课程名是否已经存在
			if(getCourses(course.getName())  != null)
				return false;
			
			sm = con.prepareStatement("insert into course(name)values(?);");
			sm.setString(1, course.getName());
//			sm.addBatch();
			sm.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
			try{
				con.rollback();
			}catch(Exception e2){
				e2.printStackTrace();
			}
			return false;
		}finally{
			close(con);close(sm);close(rs);
		}
		return true;
	}

	public Course getCourses(String name) {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery("select * from course where name ='"+name+"'");
			if (rs.next()){
				Course course = new Course();
				course.setId(new Long(rs.getInt("id")));
				
				course.setName(rs.getString("name"));
				return course;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return null;
	}

}
