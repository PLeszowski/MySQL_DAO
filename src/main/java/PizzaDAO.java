import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAO implements DAO<Pizza> {

	public static final String ID_COLUMN = "ID";
	public static final String PIZZA_NAME_COLUMN = "PIZZA_NAME";
	public static final String PIZZA_CRUST_COLUMN = "PIZZA_CRUST_TYPE";
	public static final String PIZZA_SAUCE_COLUMN = "PIZZA_SAUCE_TYPE";

	public static final String GET_ALL_SQL = "select * from pizza;";

	private DatabaseServer server;

	public PizzaDAO(DatabaseServer server) {
		this.server = server;

		try {
			server.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		server.close();
	}

	@Override
	public List<Pizza> get() {
		List<Pizza> pizzas = new ArrayList<>();

		Statement statement = null;
		try {
			statement = server.createStatement();

			ResultSet resultSet = statement.executeQuery(GET_ALL_SQL);
			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID_COLUMN));
				String pizzaName = resultSet.getString(resultSet.findColumn(PIZZA_NAME_COLUMN));
				String crustType = resultSet.getString(resultSet.findColumn(PIZZA_CRUST_COLUMN));
				String sauceType = resultSet.getString(resultSet.findColumn(PIZZA_SAUCE_COLUMN));

				pizzas.add(new Pizza(id, pizzaName, crustType, sauceType));
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
		return pizzas;
	}

	@Override
	public void add(Pizza pizza) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("insert into pizza (PIZZA_NAME, PIZZA_CRUST_TYPE, PIZZA_SAUCE_TYPE) values (\"" +
					pizza.getPizzaName() + "\", \"" + pizza.getCrustType() + "\", \"" + pizza.getSauceType() + "\"");


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
	public void update(Pizza pizza) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("update pizza set PIZZA_NAME = \"" + pizza.getPizzaName() +
					"\", PIZZA_CRUST_TYPE = \"" + pizza.getCrustType() + "\", PIZZA_SAUCE_TYPE = \"" + pizza.getSauceType() + "\" where ID = " + pizza.getId());
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
	public void delete(Pizza pizza) {
		Statement statement = null;
		try {
			statement = server.createStatement();

			statement.executeUpdate("delete from pizza where ID = " + pizza.getId());
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