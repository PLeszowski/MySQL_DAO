import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-21.
 */
public class PizzaCrustDAO implements DAO<PizzaCrust> {

	public static final String ID_COLUMN = "ID";
	public static final String CRUST_TYPE_COLUMN = "CRUST_TYPE";
	public static final String CRUST_PRICE_COLUMN = "CRUST_PRICE";

	public static final String GET_ALL_SQL = "select * from pizza_crust;";

	private DatabaseServer server;

	public PizzaCrustDAO(DatabaseServer server) {
		this.server = server;

		try {
			server.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<PizzaCrust> get() {
		List<PizzaCrust> pizzaCrusts = new ArrayList<>();

		Statement statement = null;
		try {
			statement = server.createStatement();

			ResultSet resultSet = statement.executeQuery(GET_ALL_SQL);
			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID_COLUMN));
				String crustType = resultSet.getString(resultSet.findColumn(CRUST_TYPE_COLUMN));
				int crustPrice = resultSet.getInt(resultSet.findColumn(CRUST_PRICE_COLUMN));

				pizzaCrusts.add(new PizzaCrust(id, crustType, crustPrice));
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
		return pizzaCrusts;
	}

	@Override
	public void add(PizzaCrust pizzaCrust) {

		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("insert into pizza_crust (CRUST_TYPE, CRUST_PRICE) values (\"" +
					pizzaCrust.getType() + "\", \"" + pizzaCrust.getPrice() + "\"");


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
	public void update(PizzaCrust pizzaCrust) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("update pizza_crust set CRUST_TYPE = \"" + pizzaCrust.getType() +
					"\", CRUST_PRICE = \"" + pizzaCrust.getPrice() + pizzaCrust.getId());
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
	public void delete(PizzaCrust pizzaCrust) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("delete from pizza_crust where ID = " + pizzaCrust.getId());
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
