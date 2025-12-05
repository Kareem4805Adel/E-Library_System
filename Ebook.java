import java.util.Date;
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

    public void removeBook(int bookId){}
    public void updateBook(int bookId){}
    public Ebook getBook(int bookId){return this;}
    public void LogVersionHistory(int bookId){}
    public void CreateBook(){}
    public void BorrowBook(int bookId){}
    public void purchaseBook(int bookId){}
}
