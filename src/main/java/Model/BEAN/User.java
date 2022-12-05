package Model.BEAN;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String Id;
	private String Username;
	private String Password;
	private int Balance;
	
	public User() {
		super();
	}

	public User(String id, String username, String password) {
		super();
		Id = id;
		Username = username;
		Password = password;
		Balance = 0;
	}
	
	public User(String id, String username, String password, int balance) {
		super();
		Id = id;
		Username = username;
		Password = password;
		Balance = balance;
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
	
	public int getBalance() {
		return Balance;
	}

	public void setBalance(int balance) {
		Balance = balance;
	}
}
