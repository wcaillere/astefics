import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class DalMySQL implements IDal {

    Connection cnx = new ConnectionFactory().getConnection();

    @Override
    public void initializeDB() {

        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();
        } catch (SQLException e) {
            System.out.println("Echec création statement");
            System.out.println(e.getMessage());
            System.exit(15);
        }

        String rqt = "SET FOREIGN_KEY_CHECKS = 0;" +
                "TRUNCATE TABLE categories;" +
                "TRUNCATE TABLE entries;" +
                "TRUNCATE TABLE formations;" +
                "TRUNCATE TABLE levels;" +
                "TRUNCATE TABLE students;" +
                "TRUNCATE TABLE teachers;" +
                "SET FOREIGN_KEY_CHECKS = 1";

        try {
            stmt.executeUpdate(rqt);
            System.out.println("\nInitialisation de la base réussie !");
        } catch (SQLException e) {
            System.out.println("Echec création statement");
            System.out.println(e.getMessage());
            System.exit(15);
        }
    }

    @Override
    public void forEach(Consumer<?> action) {

    }

    @Override
    public List<HashMap<String, Object>> getAll(String tableName) {

        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();
        } catch (SQLException e) {
            System.out.println("Echec création statement");
            System.out.println(e.getMessage());
            System.exit(15);
        }

        ResultSet rs = null;

        try {
            rs = stmt.executeQuery("SELECT * FROM " + tableName);
        } catch (SQLException e) {
            System.out.println("Echec création du ResultSet");
            System.out.println(e.getMessage());
            System.exit(25);
        }

        List<HashMap<String, Object>> results = new ArrayList<>();

        try {
            while (rs.next()) {
                HashMap<String, Object> result = new HashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    result.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("Echec lors du parcours du ResultSet");
            System.out.println(e.getMessage());
            System.exit(30);
        }


        return results;
    }

    @Override
    public HashMap<String, Object> getOne(String tableName, String id) {

        Statement stmt = null;

        try {
            stmt = cnx.createStatement();
        } catch (SQLException e) {
            System.out.println("Echec création statement");
            System.out.println(e.getMessage());
            System.exit(15);
        }

        ResultSet rs = null;

        try {
            rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id=" + id);
        } catch (SQLException e) {
            System.out.println("Echec création du ResultSet");
            System.out.println(e.getMessage());
            System.exit(25);
        }

        HashMap<String, Object> item = new HashMap<>();
        try {
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    item.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
            }
        } catch (SQLException e) {
            System.out.println("Echec lors du parcours du ResultSet");
            System.out.println(e.getMessage());
            System.exit(30);
        }

        return item;
    }

    @Override
    public void createOne(String tableName, TreeMap<String, ?> information) {

    }

    @Override
    public void modifyOne(String tableName, String id, TreeMap<String, ?> modifications) {

    }

    @Override
    public void suppressOne(String tableName, String id) {
        String rqt = "DELETE FROM " + tableName + " WHERE id=" + id;
        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id FROM " + tableName + " WHERE id=" + id);

            if (rs.next()) {
                stmt = this.cnx.createStatement();
                stmt.executeUpdate(rqt);

                System.out.println("Suppression réussie !");
            } else {
                System.out.println("Suppression annulée : aucun élément ne possède l'id donné");
            }

        } catch (SQLException e) {
            System.out.println("Échec de la suppression");
            System.out.println(e.getMessage());
            System.exit(35);
        }
    }
}
