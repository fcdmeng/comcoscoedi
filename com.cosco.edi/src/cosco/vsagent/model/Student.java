package cosco.vsagent.model;

import java.util.Date;

public class Student extends AbstractUser {
	private SchoolClass schoolclass;

	public SchoolClass getSchoolclass() {
		return schoolclass;
	}

	public void setSchoolclass(SchoolClass schoolclass) {
		this.schoolclass = schoolclass;
	}

}
