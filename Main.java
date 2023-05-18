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
        /////////////////////////////////////////////////////////////////////////////
        /**************************** sign up a new user ***************************/
        // by saif eldeen
        // Sign up a new admin user
        System.out.println("\nsign up a new student");
        // User information
        int ID = 1; // Replace with the ID of the user to update
        String userType = "admin"; // Change to "student" if signing up a student
        String name = "John Doe";
        String email = "johndoe@example.com";
        String sign_password = "123";
        String phone = "01111111111";
        SignUpUser signUpUser = new SignUpUser();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (userType.equals("admin")) {
                signUpUser.signUpAdmin(connection,ID, name, email, sign_password);
            } else if (userType.equals("student")) {
                signUpUser.signUpStudent(connection, ID, name, email, sign_password, phone);
            } else {
                System.out.println("Invalid user type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**************************** update user information ***************************/
        // by saif eldeen
        // User information to update
        System.out.println("\nupdate user information");
        int userId = 1; // Replace with the ID of the user to update
        String userType_to_update = "admin"; // Change to "student" if updating a student
        String newName = "saif eldeen";
        String newEmail = "saifeldeen201770@gmail.com";
        String newPassword = "1234";
        String newPhone = "01222222222";
        UpdateUser updateUser = new UpdateUser();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (userType.equals("admin")) {
                updateUser.updateAdminDetails(connection, userId, newName, newEmail, newPassword);
            } else if (userType.equals("student")) {
                updateUser.updateStudentDetails(connection, userId, newName, newEmail, newPassword , newPhone);
            } else {
                System.out.println("Invalid user type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**************************** update 2 parameters of user information ***************************/
        // by saif eldeen
        // User information to update
        System.out.println("\nupdate 2 parameters of user information");
        int userId2 = 1; // Replace with the ID of the user to update
        String userType_to_update2 = "admin"; // Change to "student" if updating a student
        String newName2 = "mariam";
        String newEmail2 = "mariam@gmail.com";
        UpdateUser updateUser2 = new UpdateUser();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (userType.equals("admin")) {
                updateUser.updateAdminDetails(connection, userId, newName, newEmail, null);
            } else if (userType.equals("student")) {
                // NO NEED TO UPDATE ALL THE PARAMETERS :)
                updateUser.updateStudentDetails(connection, userId, newName, newEmail, null , null);
            } else {
                System.out.println("Invalid user type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////////////////////////////
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Select an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Update a book");
            int option = scanner.nextInt();

            if (option == 1) {
                addBook(connection);
            } else if (option == 2) {
                updateBook(connection);
            } else {
                System.out.println("Invalid option selected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }











    }

    public static void addBook(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the book details:");
        System.out.print("ISBN: ");
        int ISBN = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Publisher Name: ");
        String publisherName = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Publication Year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Admin ID: ");
        int adminID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Author ID: ");
        int authorID = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book(ISBN, publisherName, title, publicationYear, adminID, authorID);
        book.addBook(connection);
    }

    public static void updateBook(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the ISBN of the book to update: ");
        int ISBN = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new book details:");
        System.out.print("New Title: ");
        String newTitle = scanner.nextLine();

        System.out.print("New Publication Year: ");
        int newPublicationYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Admin ID: ");
        int newAdminID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Author ID: ");
        int newAuthorID = scanner.nextInt();
        scanner.nextLine();

        Book bookToUpdate = new Book(ISBN, "", "", 0, 0, 0);
        bookToUpdate.updateBookDetails(connection, newTitle, newPublicationYear, newAdminID, newAuthorID);
    }
}









