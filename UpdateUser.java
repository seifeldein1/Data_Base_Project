import java.sql.*;

/**
 * Update user details
 * @author saif eldeen 20210536
 */
public class UpdateUser {
    public UpdateUser() {}

    /**
     * Update an admin user
     * @param connection
     * @param userId
     * @param newName
     * @param newEmail
     * @param newPassword
     * @throws SQLException
     */
    public static void updateAdminDetails(Connection connection, int userId, String newName, String newEmail, String newPassword) throws SQLException {
        // Check if admin user exists
        if (!checkAdminExists(connection, userId)) {
            System.out.println("Admin user does not exist. Update failed.");
            return;
        }

        String updateQuery = "UPDATE Admin SET Name = ?, Email = ?, Password = ? WHERE Admin_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newPassword);
            statement.setInt(4, userId);
            statement.executeUpdate();
        }
        System.out.println("Admin user details updated successfully.");
    }

    /**
     * Update a student user
     * @param connection
     * @param userId
     * @param newName
     * @param newEmail
     * @param newPassword
     * @throws SQLException
     */
    public static void updateStudentDetails(Connection connection, int userId, String newName, String newEmail, String newPassword) throws SQLException {
        // Check if student user exists
        if (!checkStudentExists(connection, userId)) {
            System.out.println("Student user does not exist. Update failed.");
            return;
        }

        String updateQuery = "UPDATE Student SET Fname = ?, Lname = ?, Email = ?, Password = ? WHERE Student_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            // Split the new name into first name and last name
            String[] names = newName.split(" ");
            String firstName = names[0];
            String lastName = names[1];

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, newEmail);
            statement.setString(4, newPassword);
            statement.setInt(5, userId);
            statement.executeUpdate();
        }
        System.out.println("Student user details updated successfully.");
    }

    /**
     * Check if admin user exists
     * @param connection
     * @param userId
     * @return boolean
     * @throws SQLException
     */
    private static boolean checkAdminExists(Connection connection, int userId) throws SQLException {
        String selectQuery = "SELECT Admin_ID FROM Admin WHERE Admin_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    /**
     * Check if student user exists
     * @param connection
     * @param userId
     * @return boolean
     * @throws SQLException
     */
    private static boolean checkStudentExists(Connection connection, int userId) throws SQLException {
        String selectQuery = "SELECT Student_ID FROM Student WHERE Student_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
