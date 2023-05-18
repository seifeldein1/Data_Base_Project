import java.sql.*;

public class browseBooks {
    String connectionString = "jdbc:mysql://localhost:3306/library";
    String username = "root";
    String password = "Sosaty*2011";
    String query = "SELECT ISBN, Title, Publisher_Name, Publication_Year, Name " +
            "FROM Book, Author " +
            "WHERE Book.Author_ID = Author.Author_ID";

    public void BrowseBooks() {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            System.out.println("------------------------BROWSING BOOKS------------------------");
            while (result.next()) { // The next method moves the cursor to the next row, and because it returns false when there are no more rows in the ResultSet object
                int isbn = result.getInt("ISBN");
                String title = result.getString("Title");
                String PublisherName = result.getString("Publisher_Name");
                int publicationYear = result.getInt("Publication_Year");
                String AuthorName = result.getString("Name");

                System.out.println("Book ISBN: " + isbn);
                System.out.println("Book Title: " + title);
                System.out.println("Author Name: " + AuthorName);
                System.out.println("Publisher Name: " + PublisherName);
                System.out.println("Publication Year: " + publicationYear);
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
