package cosco.vsagent.model;

import java.util.Date;

public class AbstractUser implements IUser {
	private Long id;
	private String userId;
	private String password;
	private String name;
	private Date latestOnline;

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}

	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;

	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;

	}

	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;

	}

	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userId = userId;

	}

	public Date getLatestOnline() {
		// TODO Auto-generated method stub
		return this.latestOnline;
	}

	public void setLatOnLine(Date date) {
		// TODO Auto-generated method stub
		this.latestOnline = date;
		
	}


}
