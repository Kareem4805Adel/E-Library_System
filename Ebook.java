import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//hi
public class Ebook {
    private int BookId;
    private String genre;
    private Date PublishingDate;
    private int PublisherId;
    private String description;
    private String Title;
    private String Author;


    public Ebook(){}

    public Ebook(int bookId, String genre, Date publishingDate, int publisherId, String description, String title, String author) {
        BookId = bookId;
        this.genre = genre;
        PublishingDate = publishingDate;
        PublisherId = publisherId;
        this.description = description;
        Title = title;
        Author = author;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPublishingDate() {
        return PublishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        PublishingDate = publishingDate;
    }

    public int getPublisherId() {
        return PublisherId;
    }

    public void setPublisherId(int publisherId) {
        PublisherId = publisherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void removeBook(int bookId) {
        List<Ebook> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Book.CSV"))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Ebook u = new Ebook();
                u.setBookId(Integer.parseInt(data[0]));
                System.out.println(data[0]);
                u.setGenre(data[1]);
                u.setPublisherId(Integer.parseInt(data[2]));
                u.setDescription(data[3]);
                u.setTitle(data[4]);
                u.setAuthor(data[5]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == this.BookId) {
                books.remove(i);
                break;
            }

        }
        try (FileWriter writer = new FileWriter("Accounts.CSV")) {

            writer.write("BookId,genre,PublishingDate,PublisherId,description,Title,Author\n");

            for (Ebook u : books) {
                writer.write(
                        u.getBookId() + "," +
                                u.getGenre() + "," +
                                u.getPublishingDate()+ "," +
                                u.getPublisherId() + "," +
                                u.getDescription() + "," +
                                u.getTitle()+","+
                                u.getAuthor()+ "\n"
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateBook(int bookId,String genre,String description){
        List<Ebook> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Book.CSV"))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Ebook u = new Ebook();
                u.setBookId(Integer.parseInt(data[0]));
                System.out.println(data[0]);
                u.setGenre(data[1]);
                u.setPublisherId(Integer.parseInt(data[2]));
                u.setDescription(data[3]);
                u.setTitle(data[4]);
                u.setAuthor(data[5]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == this.BookId) {
                books.get(i).setGenre(genre);
                books.get(i).setDescription(description);

                break;
            }

        }
        try (FileWriter writer = new FileWriter("Accounts.CSV")) {

            writer.write("BookId,genre,PublishingDate,PublisherId,description,Title,Author\n");

            for (Ebook u : books) {
                writer.write(
                        u.getBookId() + "," +
                                u.getGenre() + "," +
                                u.getPublishingDate()+ "," +
                                u.getPublisherId() + "," +
                                u.getDescription() + "," +
                                u.getTitle()+","+
                                u.getAuthor()+ "\n"
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Ebook getBook(int bookId){return this;}
    public void LogVersionHistory(int bookId){}
    public void CreateBook(){}
    public void BorrowBook(int bookId){}
    public void purchaseBook(int bookId){}
}
