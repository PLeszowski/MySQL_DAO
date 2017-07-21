import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-21.
 */
public class PizzaSauceDAO implements DAO<PizzaSauce> {

	public static final String ID_COLUMN = "ID";
	public static final String SAUCE_TYPE_COLUMN = "SAUCE_TYPE";
	public static final String SAUCE_PRICE_COLUMN = "SAUCE_PRICE";

	public static final String GET_ALL_SQL = "select * from pizza_sauce;";

	private DatabaseServer server;

	public PizzaSauceDAO(DatabaseServer server) {
		this.server = server;

		try {
			server.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<PizzaSauce> get() {
		List<PizzaSauce> pizzaCrusts = new ArrayList<>();

		Statement statement = null;
		try {
			statement = server.createStatement();

			ResultSet resultSet = statement.executeQuery(GET_ALL_SQL);
			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID_COLUMN));
				String sauseType = resultSet.getString(resultSet.findColumn(SAUCE_TYPE_COLUMN));
				int sausePrice = resultSet.getInt(resultSet.findColumn(SAUCE_PRICE_COLUMN));

				pizzaCrusts.add(new PizzaSauce(id, sauseType, sausePrice));
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
	public void add(PizzaSauce pizzaSauce) {

		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("insert into pizza_sauce (SAUCE_TYPE, SAUCE_PRICE) values (\"" +
					pizzaSauce.getType() + "\", \"" + pizzaSauce.getPrice() + "\"");


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
	public void update(PizzaSauce pizzaSauce) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("update pizza_sauce set SAUCE_TYPE = \"" + pizzaSauce.getType() +
					"\", SAUCE_PRICE = \"" + pizzaSauce.getPrice() + pizzaSauce.getId());
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
	public void delete(PizzaSauce pizzaSauce) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("delete from pizza_sauce where ID = " + pizzaSauce.getId());
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
