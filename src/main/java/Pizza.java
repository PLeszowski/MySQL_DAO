/**
 * Created by RENT on 2017-07-19.
 */
public class Pizza {
	private int id;
	private String name;
	private String crustType;
	private String sauceType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPizzaName() {
		return name;
	}

	public void setPizzaName(String name) {
		this.name = name;
	}

	public String getCrustType() {
		return crustType;
	}

	public void setCrustType(String crustType) {
		this.crustType = crustType;
	}

	public String getSauceType() {
		return sauceType;
	}

	public void setSauceType(String sauceType) {
		this.sauceType = sauceType;
	}

	public Pizza(int id, String name, String crustType, String sauceType) {
		super();
		this.id = id;
		this.name = name;
		this.crustType = crustType;
		this.sauceType = sauceType;
	}

	@Override
	public String toString() {
		return id + " " + name;
	}
}