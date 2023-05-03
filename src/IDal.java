import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;

public interface IDal {

    TreeMap<String, String> test = new TreeMap<String, String>();

    void initializeDB();

    void forEach(Consumer<?> action);

    List<HashMap<String, Object>> getAll(String tableName);

    HashMap<String, Object> getOne(String tableName, int id);

    void createOne(String tableName, TreeMap<String, ?> information);

    void modifyOne(String tableName, int id, TreeMap<String, ?> modifications);

    void suppressOne(String tableName, int id);
}
