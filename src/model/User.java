package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{

	private String userid;
	private String userpasswd;
	private String username;
	private String useremail;
	private String useremailhash;
	private int useremailcheck;
	private String userphone;
	private String useraddress;
	private int userscore;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpasswd() {
		return userpasswd;
	}
	public void setUserpasswd(String userpasswd) {
		this.userpasswd = userpasswd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUseremailhash() {
		return useremailhash;
	}
	public void setUseremailhash(String useremailhash) {
		this.useremailhash = useremailhash;
	}
	public int getUseremailcheck() {
		return useremailcheck;
	}
	public void setUseremailcheck(int useremailcheck) {
		this.useremailcheck = useremailcheck;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public int getUserscore() {
		return userscore;
	}
	public void setUserscore(int userscore) {
		this.userscore = userscore;
	}
	
	
}
