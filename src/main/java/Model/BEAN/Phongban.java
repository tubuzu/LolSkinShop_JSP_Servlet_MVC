package Model.BEAN;

public class Phongban {
	private String IDPB;
	private String Tenpb;
	private String Mota;
	public Phongban() {
		super();
	}
	public Phongban(String iDPB, String tenpb, String mota) {
		super();
		IDPB = iDPB;
		Tenpb = tenpb;
		Mota = mota;
	}
	public String getIDPB() {
		return IDPB;
	}
	public void setIDPB(String iDPB) {
		IDPB = iDPB;
	}
	public String getTenpb() {
		return Tenpb;
	}
	public void setTenpb(String tenpb) {
		Tenpb = tenpb;
	}
	public String getMota() {
		return Mota;
	}
	public void setMota(String mota) {
		Mota = mota;
	}
	
}
