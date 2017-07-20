import java.util.List;

/**
 * Created by RENT on 2017-07-19.
 */
public interface UsersDAO {

	List<User> get();
	void add(User user);
	void update(User user);
	void delete(User user);
}
