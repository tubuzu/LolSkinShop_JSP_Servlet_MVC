package Model.BEAN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
	private String Id;
	private String SkinId;
	private String UserId;
	private LocalDateTime CreatedAt;
	
	public Transaction() {
		super();
	}

	public Transaction(String id, String skinId, String userId, String createdAt) {
		super();
		Id = id;
		SkinId = skinId;
		UserId = userId;
		System.out.println(createdAt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		CreatedAt = dateTime;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getSkinId() {
		return SkinId;
	}

	public void setSkinId(String skinId) {
		SkinId = skinId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}
	
	public LocalDateTime getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(String createdAt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(createdAt, formatter);
		CreatedAt = dateTime;
	}
}
