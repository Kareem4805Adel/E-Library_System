import java.util.List;

public class Publisher extends User{
    private double balance;
    private List<Ebook> publishedBooks;

    public Publisher(String username, int age, int id, String email, String phoneNumber, String password, int balance, List<Ebook> publishedBooks) {
        super(username, age, id, email, phoneNumber, password, "P");
        this.balance = balance;
        this.publishedBooks = publishedBooks;
    }

    public Publisher() {

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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

    public Boolean publishBookRequest(Ebook ebook){
        try {
            PublishingRequests p = new PublishingRequests(this.getId(), ebook.getBookId(), false);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean getPublicationStatus(int reqId){
        PublishingRequests p = PublishingRequests.getReq(reqId);
        return p.isRequestStatus();
    }

    public void addBalance(double value){
        this.balance += value;
    }

    public void addBookToPublishedlist(Ebook book){
        publishedBooks.add(book);
    }
}
