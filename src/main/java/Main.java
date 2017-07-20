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
				"localhost", "javadb", "jdbc", "pas123");

		DatabaseUsersDAO usersDAO = new DatabaseUsersDAO(databaseServer);

		List<User> users = usersDAO.get();

		for(User user : users) {
			System.out.println(user.toString());
		}

		usersDAO.close();
	}
}