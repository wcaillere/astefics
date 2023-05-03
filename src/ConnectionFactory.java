import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {

        Connection cnx = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Echec chargement pilote");
            System.out.println(e);
            System.exit(5);
        }

        // créer une connexion
        String connection_string = "jdbc:mysql://localhost/exo_formations?user=root&password=&noAccessToProcedureBodies=true&useSSL=false&allowMultiQueries=true";

        try {
            cnx = DriverManager.getConnection(connection_string);
        } catch (SQLException e) {
            System.out.println("Échec création connexion");
            System.out.println(e);
            System.exit(10);
        }

        return cnx;
    }

}
