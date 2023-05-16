import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "2468mariam";
        System.out.println("This Query is to delete the students who borrowed the book whose title 'The Great Gatsby'");
        String query = """
                DELETE FROM student
                WHERE Student_ID IN (
                \tSELECT Borrower_ID
                \tFROM loan
                \tWHERE Book_ID = (
                \t\tSELECT ISBN
                \t\tFROM book
                \t\tWHERE Title = 'The Great Gatsby'
                \t)
                );""";
        System.out.println(query);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " record(s) deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nDelete all loan records where the corresponding books have been published after the year 2000");
        String query2 = """
                DELETE FROM Loan
                WHERE Book_ID IN (
                  SELECT ISBN
                  FROM Book
                  WHERE Publication_Year > 2000
                );""";
        System.out.println(query2);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query2)) {
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " record(s) deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}