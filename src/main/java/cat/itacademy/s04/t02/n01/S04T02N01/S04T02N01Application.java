package cat.itacademy.s04.t02.n01.S04T02N01;

import cat.itacademy.s04.t02.n01.S04T02N01.config.DatabaseConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class S04T02N01Application {

	public static void main(String[] args) {

		final String bbdd = "CREATE TABLE IF NOT EXISTS fruits " +
				"( id bigint auto_increment, name VARCHAR(50) NOT NULL, " +
				"kg  decimal (5, 2) NOT NULL DEFAULT '0')";

		try (Statement stmt = DatabaseConnectionManager.getConnection().createStatement();){
			// stmt.executeUpdate("DROP TABLE IF EXISTS fruits;");
			stmt.executeUpdate(bbdd);
			System.out.println("BBDD inicialitzada correctament.");
			SpringApplication.run(S04T02N01Application.class, args);
        } catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
