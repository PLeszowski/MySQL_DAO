/**
 * Created by RENT on 2017-07-21.
 */
public class Allergens {

	private int id;
	private String name;

	public Allergens(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
