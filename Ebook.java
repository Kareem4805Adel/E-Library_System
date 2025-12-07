import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.abs;

public class Ebook {
    private int BookId;
    private String genre;
    private LocalDate PublishingDate;
    private int PublisherId;
    private String description;
    private String Title;
    private String Author;
    private double price;
    private boolean status;
    private List<Reviews> reviews;

    public Ebook(){}

    //--------------------ONLY FOR ACTUAL CREATION--------------------------------
    //IN CASE OF THE NEED OF A TEMPORARY EBOOK USE THE NO-ARGS THEN THE SETTERS
    //TO PREVENT CREATION OF MULTIPLE UNNECESSARY LOGS
    public Ebook(int bookId, String genre, LocalDate publishingDate, int publisherId, String description, String title, String author) {
        BookId = bookId;
        this.genre = genre;
        PublishingDate = publishingDate;
        PublisherId = publisherId;
        this.description = description;
        Title = title;
        Author = author;
        this.price = 0.0;
        status = false;

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sb = "[" + (today.format(dateFormatter)) + ("] Action: Book Created\n");

        File dir = new File("BooksLogs");
        String filename = "Book" + bookId;
        File file = new File(dir, filename);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addBookToCSV(this);
    }
    //-------------------------END OF DANGER--------------------------------------


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

    public LocalDate getPublishingDate() {
        return PublishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                u.setGenre(data[1]);
                u.setPublisherId(Integer.parseInt(data[2]));
                u.setDescription(data[3]);
                u.setTitle(data[4]);
                u.setAuthor(data[5]);
                books.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == this.BookId) {

                books.remove(i);

                String filename = "Book" + this.BookId;
                try (FileWriter writer = new FileWriter(filename)) {
                    LocalDate today = LocalDate.now();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    String sb = "["
                            +(today.format(dateFormatter))
                            +("] Action: BOOK DELETED\n");

                    writer.write(sb);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
                u.setGenre(data[1]);
                u.setPublisherId(Integer.parseInt(data[2]));
                u.setDescription(data[3]);
                u.setTitle(data[4]);
                u.setAuthor(data[5]);
                books.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == this.BookId) {
                books.get(i).setGenre(genre);
                books.get(i).setDescription(description);
                books.get(i).LogVersionHistory(genre, description);
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

    public static Ebook getBook(int bookId){

        try (BufferedReader br = new BufferedReader(new FileReader("Book.CSV"))) {

        String line;
        br.readLine();
        // skip header
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if(bookId==(Integer.parseInt(data[0]))){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(data[2], formatter);

                Ebook u = new Ebook();

                u.setBookId(Integer.parseInt(data[0]));
                u.setGenre(data[1]);
                u.setPublisherId(Integer.parseInt(data[2]));
                u.setDescription(data[3]);
                u.setTitle(data[4]);
                u.setAuthor(data[5]);
                return u;
            }

        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

    public void LogVersionHistory(String NewGenre,String NewDescription){

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String sb = "["
                +(today.format(dateFormatter))
                +("] Action: Update")
                +(" | New Genre: ")
                +(NewGenre)
                +(" | New Description: ")
                +(NewDescription + "\n");

        String filename = "Book" + this.BookId;
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Boolean BorrowBook(Reader Reader, int duration){
        Boolean done = Reader.updateBalance((this.price * 0.25 ) * duration);
        if(done){
            Reader.addToMyBorrowedBooks(this, duration);
            return true;
        }

        return false;
    }

    public int CalculateTimeLeft(int duration, Reader reader){
        int Cday = LocalDate.now().getDayOfYear();
        int daysleft = abs(duration - Cday);
        if (daysleft == 0){
            reader.endBorrowing(this.getBookId());
        }
        return  daysleft;
    }

    public Boolean purchaseBook(Reader reader){
        Boolean done = reader.updateBalance(this.price);
        if(done){
            reader.addToMyPurchasedBooks(this);
            return true;
        }

        return false;
    }

    public void addBookToCSV(Ebook b){
        List<Ebook> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Book.CSV"))) {

            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Ebook u = new Ebook();
                u.setBookId(Integer.parseInt(data[0]));
                u.setGenre(data[1]);
                u.setPublisherId(Integer.parseInt(data[2]));
                u.setDescription(data[3]);
                u.setTitle(data[4]);
                u.setAuthor(data[5]);
                books.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        books.add(b);

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

}
