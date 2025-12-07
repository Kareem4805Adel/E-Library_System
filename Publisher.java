import java.util.List;

public class Publisher extends User{
    private int balance;
    private List<Ebook> publishedBooks;

    public Publisher(String username, int age, int id, String email, String phoneNumber, String password, int balance, List<Ebook> publishedBooks) {
        super(username, age, id, email, phoneNumber, password, "P");
        this.balance = balance;
        this.publishedBooks = publishedBooks;
    }

    public Publisher() {

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Ebook> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<Ebook> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    public void setBookPrice(int bookId, double price){
        Ebook b = Ebook.getBook(bookId);
        b.setPrice(price);
    }

    public void publishBookrequest(){
        //requests entity
    }

    public Boolean getPublicationStatus(){
        //request entity
        return false;
    }

    public void addBalance(int value){
        this.balance += value;
    }

    public void addBookToPublishedlist(Ebook book){
        publishedBooks.add(book);
    }
}
