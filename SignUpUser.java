import java.sql.*;

/**
 * Sign up a new user
 * @author saif eldeen 20210536
 */
public class SignUpUser {
    public SignUpUser() {}

    /**
     * Sign up a new admin user
     * @param connection
     * @param adminId
     * @param name
     * @param email
     * @param password
     * @throws SQLException
     */
    public static void signUpAdmin(Connection connection, int adminId, String name, String email, String password) throws SQLException {
        if (checkAdminIdExists(connection, adminId)) {
            System.out.println("Admin ID already exists. Sign up failed.");
            return;
        }

        String insertQuery = "INSERT INTO Admin (Admin_ID, Name, Email, Password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, adminId);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.executeUpdate();
        }
        System.out.println("Admin user signed up successfully.");
    }

    /**
     * Sign up a new student user
     * @param connection
     * @param studentId
     * @param name
     * @param email
     * @param password
     * @throws SQLException
     */
    public static void signUpStudent(Connection connection, int studentId, String name, String email, String password) throws SQLException {
        if (checkStudentIdExists(connection, studentId)) {
            System.out.println("Student ID already exists. Sign up failed.");
            return;
        }

        String insertQuery = "INSERT INTO Student (Student_ID, Fname, Lname, Email, Password) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            String[] names = name.split(" ");
            String firstName = names[0];
            String lastName = names[1];

            statement.setInt(1, studentId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.executeUpdate();
        }
        System.out.println("Student user signed up successfully.");
    }

    /**
     * Check if admin ID already exists
     * @param connection
     * @param adminId
     * @return boolean
     * @throws SQLException
     */
    private static boolean checkAdminIdExists(Connection connection, int adminId) throws SQLException {
        String selectQuery = "SELECT Admin_ID FROM Admin WHERE Admin_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    /**
     * Check if student ID already exists
     * @param connection
     * @param studentId
     * @return boolean
     * @throws SQLException
     */
    private static boolean checkStudentIdExists(Connection connection, int studentId) throws SQLException {
        String selectQuery = "SELECT Student_ID FROM Student WHERE Student_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

