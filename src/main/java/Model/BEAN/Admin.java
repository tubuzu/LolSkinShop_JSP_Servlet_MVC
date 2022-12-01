package Model.BEAN;

public class Admin {
	private String Id;
	private String Username;
	private String Password;
	
	public Admin() {
		super();
	}

	public Admin(String id, String username, String password) {
		super();
		Id = id;
		Username = username;
		Password = password;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
}
