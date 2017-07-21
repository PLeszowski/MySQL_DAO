import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main
{
	public static void main( String[] args )
	{
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DatabaseServer databaseServer = new DatabaseServer(
				"localhost", "pizza_maker", "patryk", "pas123");

		PizzaDAO usersDAO = new PizzaDAO(databaseServer);

		List<Pizza> pizzas = usersDAO.get();

		for(Pizza pizza : pizzas) {
			System.out.println(pizza.toString());
		}

		usersDAO.close();
	}
}