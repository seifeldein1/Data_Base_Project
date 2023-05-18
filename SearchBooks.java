import java.sql.*;

public class SearchBooks {
    private final String connectionString = "jdbc:mysql://localhost:3306/library";
    private final String username = "root";
    private final String password = "Sosaty*2011";
    private String query;
    private Connection connection;
    private PreparedStatement statement;


    public void searchByID(int ID) {
        try {
            query = "SELECT ISBN, Title, Publisher_Name, Publication_Year, Name " +
                    "FROM Book, Author " +
                    "WHERE ISBN = ? and Author.Author_ID = Book.Author_ID"; //The "?" acts as a placeholder for the actual values that will be provided later
            connection = DriverManager.getConnection(connectionString, username, password);
            statement = connection.prepareStatement(query);
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

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByYear(int year){
        try {
            query = "SELECT ISBN, Title, Publisher_Name, Publication_Year, Name " +
                    "FROM Book, Author " +
                    "WHERE Publication_Year = ? and Author.Author_ID = Book.Author_ID";
            connection = DriverManager.getConnection(connectionString, username, password);
            statement = connection.prepareStatement(query);
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
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByTitle(String title){
        try {
            query = "SELECT ISBN, Publisher_Name, Title, Publication_Year, Name " +
                    "FROM Book, Author " +
                    "WHERE Title LIKE ? AND Author.Author_ID = Book.Author_ID";
            connection = DriverManager.getConnection(connectionString, username, password);
            statement = connection.prepareStatement(query);
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
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void searchByPublisherName(String publisherName){
        try{
            query = "SELECT ISBN, Publisher_Name, Title, Publication_Year, Name " +
                    "FROM Book, Author " +
                    "WHERE Publisher_Name LIKE ? AND Author.Author_ID = Book.Author_ID";
            connection = DriverManager.getConnection(connectionString, username, password);
            statement = connection.prepareStatement(query);
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
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void searchByAuthorName(String authorName){
        try{
            query = "SELECT ISBN, Publisher_Name, Title, Publication_Year, Name " +
                    "FROM Book, Author " +
                    "WHERE Name LIKE ? AND Author.Author_ID = Book.Author_ID";
            connection = DriverManager.getConnection(connectionString, username, password);
            statement = connection.prepareStatement(query);
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
        }catch(SQLException e){
            e.printStackTrace();
        }

    }


}
