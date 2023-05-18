import java.sql.*;
import java.util.Scanner;
public class Book {
    public void addBook(Connection connection) throws SQLException {
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
        if (!checkAdminIdExists(connection, adminID)) {
            System.out.println("Admin does not exist. Failed to add the book: " );
            return;
        }

        if (!checkPublisherExists(connection, publisherName)) {
            System.out.println("Publisher does not exist. Failed to add the book: " );
            return;
        }

        if (!checkAuthorExists(connection, authorID)) {
            System.out.println("Author does not exist. Failed to add the book: " );
            return;
        }

        String query = "INSERT INTO Book (ISBN, Publisher_Name, Title, Publication_Year, Admin_ID, Author_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ISBN);
            statement.setString(2, publisherName);
            statement.setString(3, title);
            statement.setInt(4, publicationYear);
            statement.setInt(5, adminID);
            statement.setInt(6, authorID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully");
            } else {
                System.out.println("Failed to add the book");
            }
        }
    }

   public void updateBookDetails(Connection connection) throws SQLException {
       System.out.println("Enter the book details:");
       System.out.print("ISBN: ");
       Scanner scanner = new Scanner(System.in);
       int ISBN = scanner.nextInt();
       scanner.nextLine();
       System.out.print("Publisher Name: ");
       String new_publisherName = scanner.nextLine();
       System.out.print("Title: ");
       String new_Title = scanner.nextLine();
       System.out.print("Publication Year: ");
       int new_PublicationYear = scanner.nextInt();
       scanner.nextLine();
       System.out.print("Admin ID: ");
       int new_AdminID = scanner.nextInt();
       scanner.nextLine();
       System.out.print("Author ID: ");
       int new_AuthorID = scanner.nextInt();
       scanner.nextLine();
        if (!checkAdminIdExists(connection, new_AdminID)) {
            System.out.println("Admin does not exist. Failed to update book details: ISBN " );
            return;
        }

        if (!checkAuthorExists(connection, new_AuthorID)) {
            System.out.println("Author does not exist. Failed to update book details: ISBN " );
            return;
        }

        String query = "UPDATE Book SET Title = ?, Publication_Year = ?, Admin_ID = ?, Author_ID = ?,Publisher_Name = ? WHERE ISBN = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, new_Title);
            statement.setInt(2, new_PublicationYear);
            statement.setInt(3, new_AdminID);
            statement.setInt(4, new_AuthorID);
            statement.setString(5, new_publisherName);
            statement.setInt(6, ISBN);
            statement.executeUpdate();
        }
    }

    private static boolean checkAdminIdExists(Connection connection, int adminId) throws SQLException {
        String selectQuery = "SELECT Admin_ID FROM Admin WHERE Admin_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    private boolean checkPublisherExists(Connection connection, String publisher_Name) throws SQLException {
        String selectQuery = "SELECT Name FROM Publisher WHERE Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, publisher_Name);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private boolean checkAuthorExists(Connection connection, int author_ID) throws SQLException {
        String selectQuery = "SELECT Author_ID FROM Author WHERE Author_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, author_ID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

