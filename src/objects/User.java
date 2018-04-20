package objects;

public class User {
	private Integer idu;
	private Boolean isadmin;
	private String login, pwd, fn, ln, email;

	public User(Boolean isadmin, String login, String pwd, String fn,
			String ln, String email) {
		super();
		this.isadmin = isadmin;
		this.login = login;
		this.pwd = pwd;
		this.fn = fn;
		this.ln = ln;
		this.email = email;
	}

	public User(Integer idu, Boolean isadmin, String login, String pwd,
			String fn, String ln, String email) {
		super();
		this.idu = idu;
		this.isadmin = isadmin;
		this.login = login;
		this.pwd = pwd;
		this.fn = fn;
		this.ln = ln;
		this.email = email;
	}

	@Override
	public String toString() {
		return "@" + login;
	}

	public Integer getIdUser() {
		return idu;
	}

	public void setIdUser(Integer idu) {
		this.idu = idu;
	}

	public Boolean getIsAdmin() {
		return isadmin;
	}

	public void setIsAdmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return pwd;
	}

	public String getFisrtName() {
		return fn;
	}

	public void setFirstName(String fn) {
		this.fn = fn;
	}

	public String getLastName() {
		return ln;
	}

	public void setLastName(String ln) {
		this.ln = ln;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
