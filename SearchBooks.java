import java.sql.*;

/**
 * SearchBooks class is for searching book(s) that satisfy a certain criteria
 */
public class SearchBooks {

    /**
     * Search for a book with its ISBN
     * @param connection the database connection
     * @param ID the book ISBN to search for
     * @throws SQLException if a database access error occurs
     */
    public void searchByID(Connection connection, int ID) throws SQLException{
        String query = "SELECT ISBN, Title, Publisher_Name, Publication_Year, Name " +
                "FROM Book, Author " +
                "WHERE ISBN = ? and Author.Author_ID = Book.Author_ID"; //The "?" acts as a placeholder for the actual values that will be provided later
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ID); // the first found "?" corresponds to index 1
        ResultSet result = statement.executeQuery();

        int counter = 0; // To count the number of rows retrieved, if zero then there is no book satisfy the given criteria.
        while (result.next()) { // The next method moves the cursor to the next row, and because it returns false when there are no more rows in the ResultSet object
            if (counter == 0){ // To print only once before the first row
                System.out.println("------------------BOOK SATISFIED THE GIVEN ISBN------------------");
            }
            counter++;

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
        if (counter == 0){
            System.out.println("NO BOOKS SATISFIED THE GIVEN ISBN");
        }
    }

    /**
     * Search for books published in a specific year
     * @param connection the database connection
     * @param year the publication year to search for books in
     * @throws SQLException if a database access error occurs
     */
    public void searchByYear(Connection connection, int year) throws SQLException {
        String query = "SELECT ISBN, Title, Publisher_Name, Publication_Year, Name " +
                "FROM Book, Author " +
                "WHERE Publication_Year = ? and Author.Author_ID = Book.Author_ID";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, year);
        ResultSet result = statement.executeQuery();

        int counter = 0; // To count the number of rows retrieved, if zero then there is no book satisfy the given criteria.
        while (result.next()) { // The next method moves the cursor to the next row, and because it returns false when there are no more rows in the ResultSet object
            if (counter == 0){ // To print only once before the first row
                System.out.println("------------------BOOKS SATISFIED THE GIVEN PUBLICATION YEAR------------------");
            }
            counter++;

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
        if (counter == 0){
            System.out.println("NO BOOKS SATISFIED THE GIVEN PUBLICATION YEAR");
        }
    }

    /**
     * Search for a book with its title
     * @param connection the database connection
     * @param title the book title to search for
     * @throws SQLException if a database access error occurs
     */
    public void searchByTitle(Connection connection, String title) throws SQLException {
        String query = "SELECT ISBN, Publisher_Name, Title, Publication_Year, Name " +
                "FROM Book, Author " +
                "WHERE Title LIKE ? AND Author.Author_ID = Book.Author_ID";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, title);
        ResultSet result = statement.executeQuery();

        int counter = 0;
        while(result.next()){
            if (counter == 0){ // To print only once before the first row
                System.out.println("------------------BOOKS SATISFIED THE GIVEN TITLE------------------");
            }
            counter++;
            int isbn = result.getInt("ISBN");
            String publisherName = result.getString("Publisher_Name");
            String Title = result.getString("Title");
            int publicationYear = result.getInt("Publication_Year");
            String author = result.getString("Name");

            System.out.println("Book ISBN: " + isbn);
            System.out.println("Book Title: " + Title);
            System.out.println("Author Name: " + author);
            System.out.println("Publisher Name: " + publisherName);
            System.out.println("Publication Year: " + publicationYear);
            System.out.println("--------------------------------");
        }
        if (counter == 0){
            System.out.println("NO BOOKS SATISFIED THE GIVEN TITLE");
        }
    }

    /**
     * Search for books published by a specific publisher
     * @param connection the database connection
     * @param publisherName the publisher name to search for books with
     * @throws SQLException if a database access error occurs
     */
    public void searchByPublisherName(Connection connection, String publisherName) throws SQLException{
        String query = "SELECT ISBN, Publisher_Name, Title, Publication_Year, Name " +
                "FROM Book, Author " +
                "WHERE Publisher_Name LIKE ? AND Author.Author_ID = Book.Author_ID";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, publisherName);
        ResultSet result = statement.executeQuery();

        int counter = 0; // To count the number of rows retrieved, if zero then there is no book satisfy the given criteria.
        while (result.next()) { // The next method moves the cursor to the next row, and because it returns false when there are no more rows in the ResultSet object
            if (counter == 0){ // To print only once before the first row
                System.out.println("------------------BOOKS SATISFIED THE GIVEN PUBLISHER NAME------------------");
            }
            counter++;

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
        if (counter == 0){
            System.out.println("NO BOOKS SATISFIED THE GIVEN PUBLISHER NAME");
        }
    }


    /**
     * Search for books written by a specific author
     * @param connection the database connection
     * @param authorName the author name to search for books with
     * @throws SQLException if a database access error occurs
     */
    public void searchByAuthorName(Connection connection, String authorName) throws SQLException{
        String query = "SELECT ISBN, Publisher_Name, Title, Publication_Year, Name " +
                "FROM Book, Author " +
                "WHERE Name LIKE ? AND Author.Author_ID = Book.Author_ID";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, authorName);
        ResultSet result = statement.executeQuery();

        int counter = 0; // To count the number of rows retrieved, if zero then there is no book satisfy the given criteria.
        while (result.next()) { // The next method moves the cursor to the next row, and because it returns false when there are no more rows in the ResultSet object
            if (counter == 0){ // To print only once before the first row
                System.out.println("------------------BOOKS SATISFIED THE GIVEN AUTHOR NAME------------------");
            }
            counter++;

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
        if (counter == 0){
            System.out.println("NO BOOKS SATISFIED THE GIVEN AUTHOR NAME");
        }
    }

}
