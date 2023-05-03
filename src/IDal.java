import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;

public interface IDal {
    void initializeDB();

    void forEach(Consumer<?> action);

    List<HashMap<String, Object>> getAll(String tableName);

    HashMap<String, Object> getOne(String tableName, String id);

    void createOne(String tableName, TreeMap<String, ?> information);

    void modifyOne(String tableName, String id, TreeMap<String, ?> modifications);

    void suppressOne(String tableName, String id);
}
