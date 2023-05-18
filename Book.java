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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ISBN of the book to update: ");
        int ISBN = scanner.nextInt();
        scanner.nextLine();

        String selectQuery = "SELECT * FROM Book WHERE ISBN = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, ISBN);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("Book with ISBN " + ISBN + " does not exist.");
                    return;
                }
                
                String Title = resultSet.getString("Title");
                int PublicationYear = resultSet.getInt("Publication_Year");
                int AdminID = resultSet.getInt("Admin_ID");
                int AuthorID = resultSet.getInt("Author_ID");
                String PublisherName = resultSet.getString("Publisher_Name");

                System.out.println("Enter the updated book details:");

                System.out.print("New Title (leave empty to keep existing title ): ");
                String newTitle = scanner.nextLine();
                if (newTitle.isEmpty()) {
                    newTitle = Title;
                }

                System.out.print("New Publication Year (leave empty to keep existing publication year): ");
                String newPublicationYearInput = scanner.nextLine();
                int newPublicationYear = PublicationYear;
                if (!newPublicationYearInput.isEmpty()) {
                    newPublicationYear = Integer.parseInt(newPublicationYearInput);
                }

                System.out.print("New Admin ID (leave empty to keep existing admin ID):  ");
                String newAdminIDInput = scanner.nextLine();
                int newAdminID = AdminID;
                if (!newAdminIDInput.isEmpty()) {
                    newAdminID = Integer.parseInt(newAdminIDInput);
                }

                System.out.print("New Author ID (leave empty to keep existing author ID): ");
                String newAuthorIDInput = scanner.nextLine();
                int newAuthorID = AuthorID;
                if (!newAuthorIDInput.isEmpty()) {
                    newAuthorID = Integer.parseInt(newAuthorIDInput);
                }

                System.out.print("New Publisher Name (leave empty to keep existing publisher name): ");
                String newPublisherName = scanner.nextLine();
                if (newPublisherName.isEmpty()) {
                    newPublisherName = PublisherName;
                }

                if (!checkAdminIdExists(connection, newAdminID)) {
                    System.out.println("Admin with ID " + newAdminID + " does not exist. Failed to update book details.");
                    return;
                }

                if (!checkAuthorExists(connection, newAuthorID)) {
                    System.out.println("Author with ID " + newAuthorID + " does not exist. Failed to update book details.");
                    return;
                }

                String updateQuery = "UPDATE Book SET Title = ?, Publication_Year = ?, Admin_ID = ?, Author_ID = ?, Publisher_Name = ? WHERE ISBN = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setString(1, newTitle);
                    updateStatement.setInt(2, newPublicationYear);
                    updateStatement.setInt(3, newAdminID);
                    updateStatement.setInt(4, newAuthorID);
                    updateStatement.setString(5, newPublisherName);
                    updateStatement.setInt(6, ISBN);
                    updateStatement.executeUpdate();
                }

                System.out.println("Book details updated successfully.");
            }
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

