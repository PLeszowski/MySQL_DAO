import java.util.List;

/**
 * Created by RENT on 2017-07-19.
 */
public interface DAO<T> {

	List<T> get();
	void add(T item);
	void update(T item);
	void delete(T item);
}
