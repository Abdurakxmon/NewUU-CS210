package homework;

import java.sql.*;
import java.util.Scanner;

public class AuthorsBrowser {
    public static void main(String[] args) {
        System.out.println("All authors:");

        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Authors")
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("AuthorID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");

                System.out.println("ID: " + id + " | " + firstName + " " + lastName);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter last-name prefix to search: ");
        String prefix = scanner.nextLine();

        String sql = "SELECT * FROM Authors WHERE LastName LIKE ?";

        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, prefix + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean found = false;

                while (resultSet.next()) {
                    found = true;

                    int id = resultSet.getInt("AuthorID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    System.out.println("ID: " + id + " | " + firstName + " " + lastName);
                }

                if (!found) {
                    System.out.println("No results found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}