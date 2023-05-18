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
        if (!checkAdminExists(connection, userId)) {
            System.out.println("Admin user does not exist. Update failed.");
            return;
        }

        String updateQuery = "UPDATE Admin SET";
        boolean hasUpdates = false;

        if (newName != null) {
            updateQuery += " Name = ?,";
            hasUpdates = true;
        }
        if (newEmail != null) {
            updateQuery += " Email = ?,";
            hasUpdates = true;
        }
        if (newPassword != null) {
            updateQuery += " Password = ?,";
            hasUpdates = true;
        }
        if(!hasUpdates) {
            System.out.println("No updates were provided. Update failed.");
            return;
        }
        //// Removing the trailing comma ////
        updateQuery = updateQuery.substring(0, updateQuery.length() - 1);

        updateQuery += " WHERE Admin_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            int parameterIndex = 1;
            if (newName != null) {
                statement.setString(parameterIndex++, newName);
            }
            if (newEmail != null) {
                statement.setString(parameterIndex++, newEmail);
            }
            if (newPassword != null) {
                statement.setString(parameterIndex++, newPassword);
            }

            statement.setInt(parameterIndex, userId);
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
    public static void updateStudentDetails(Connection connection, int userId, String newName, String newEmail, String newPassword ,String newPhone) throws SQLException {
        // Check if student user exists
        if (!checkStudentExists(connection, userId)) {
            System.out.println("Student user does not exist. Update failed.");
            return;
        }

        String updateQuery = "UPDATE Student SET";
        boolean hasUpdates = false;

        if (newName != null) {
            String[] names = newName.split(" ");
            String firstName = names[0];
            String lastName = names[1];
            updateQuery += " Fname = ?, Lname = ?,";
            hasUpdates = true;
        }
        if (newEmail != null) {
            updateQuery += " Email = ?,";
            hasUpdates = true;
        }
        if (newPassword != null) {
            updateQuery += " Password = ?,";
            hasUpdates = true;
        }
        if (newPhone != null) {
            updateQuery += " Phone = ?,";
            hasUpdates = true;
        }
        if(!hasUpdates) {
            System.out.println("No updates were provided. Update failed.");
            return;
        }

        /// Remove the trailing comma ///
        updateQuery = updateQuery.substring(0, updateQuery.length() - 1);

        updateQuery += " WHERE Student_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            int parameterIndex = 1;

            if (newName != null) {
                String[] names = newName.split(" ");
                String firstName = names[0];
                String lastName = names[1];
                statement.setString(parameterIndex++, firstName);
                statement.setString(parameterIndex++, lastName);
            }
            if (newEmail != null) {
                statement.setString(parameterIndex++, newEmail);
            }
            if (newPassword != null) {
                statement.setString(parameterIndex++, newPassword);
            }
            if (newPhone != null) {
                statement.setString(parameterIndex++, newPhone);
            }

            statement.setInt(parameterIndex, userId);
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
