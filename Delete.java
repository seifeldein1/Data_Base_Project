import java.sql.*;

public class Delete {
    /**
     *
     * @param connection
     * @param studentId
     * @throws SQLException
     */
    public void deleteStudent(Connection connection, int studentId) throws SQLException {
        String query = "DELETE FROM student WHERE Student_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, studentId);
        int rowsAffected = statement.executeUpdate();
        System.out.println(rowsAffected + " record(s) deleted successfully.");
    }

    /**
     *
     * @param connection
     * @param adminId
     * @throws SQLException
     */
    public void deleteAdmin(Connection connection, int adminId) throws SQLException {
        String query = "DELETE FROM admin WHERE Admin_ID = ?";
         PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, adminId);
        int rowsAffected = statement.executeUpdate();
        System.out.println(rowsAffected + " record(s) deleted successfully.");
    }

    /**
     * 
     * @param connection
     * @param bookId
     * @throws SQLException
     */
    public void deleteBook(Connection connection, int bookId) throws SQLException{
        String query = "DELETE FROM book WHERE ISBN = ?";
         PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, bookId);
        int rowsAffected = statement.executeUpdate();
        System.out.println(rowsAffected + " record(s) deleted successfully.");
    }
}
