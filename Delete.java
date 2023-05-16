import java.sql.*;

public class Delete {
    String url = "jdbc:mysql://localhost:3306/library";
    String username = "root";
    String password = "2468mariam";
    public void deleteStudent(int studentId){
        String query = "DELETE FROM student WHERE Student_ID = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " record(s) deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteAdmin(int adminId){
        String query = "DELETE FROM admin WHERE Admin_ID = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " record(s) deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteBook(int bookId){
        String query = "DELETE FROM book WHERE ISBN = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " record(s) deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
