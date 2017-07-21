import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-21.
 */
public class AllergensDAO implements DAO<Allergens> {

	public static final String ID_COLUMN = "ID";
	public static final String ALLERGEN_NAME_COLUMN = "ALLERGEN_NAME";

	public static final String GET_ALL_SQL = "select * from allergens;";

	private DatabaseServer server;

	public AllergensDAO(DatabaseServer server) {
		this.server = server;

		try {
			server.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<Allergens> get() {

		List<Allergens> allergens = new ArrayList<>();

		Statement statement = null;

		try {
			statement = server.createStatement();

			ResultSet resultSet = statement.executeQuery(GET_ALL_SQL);
			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID_COLUMN));
				String allergenName = resultSet.getString(resultSet.findColumn(ALLERGEN_NAME_COLUMN));

				allergens.add(new Allergens(id, allergenName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return allergens;
	}

	@Override
	public void add(Allergens allergen) {

		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("insert into allergens (ALLERGEN_NAME) values (\"" +
					allergen.getName()  + "\"");


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Allergens allergen) {

		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("update allergens set ALLERGEN_NAME = \"" + allergen.getName()  + "\" where ID = " + allergen.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Allergens allergen) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("delete from allergens where ID = " + allergen.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
