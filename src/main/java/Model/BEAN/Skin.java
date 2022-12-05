package Model.BEAN;

public class Skin {
	private String Id;
	private String Name;
	private String Img;
	private int Price;
	private int Tier;
	private String Champion;
	
	public Skin() {
		super();
	}

	public Skin(String id, String name, String img, int price, int tier, String champion) {
		super();
		Id = id;
		Name = name;
		Img = img;
		Price = price;
		Tier = tier;
		Champion = champion;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public String getChampion() {
		return Champion;
	}

	public void setChampion(String champion) {
		Champion = champion;
	}
	
	public int getPrice() {
		return Price;
	}
	
	public void setPrice(int price) {
		Price = price;
	}
	
	public int getTier() {
		return Tier;
	}
	
	public void setTier(int tier) {
		Tier = tier;
	}
}
