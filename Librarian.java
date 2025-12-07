import java.time.LocalDate;

public class Librarian extends User{

    public Librarian(String username, int age, int id, String email, String phoneNumber, String password, String role) {
        super(username, age, id, email, phoneNumber, password, role);
    }

    public Librarian() {
    }

    public Boolean processReq(int reqId, Boolean choice){
        PublishingRequests p = PublishingRequests.getReq(reqId);
        if (p != null) {
            p.setRequestStatus(choice);
            p.changeStore();
            return true;
        }
        return false;
    }

    public void addBook(int bookId, String genre, LocalDate publishingDate, int publisherId, String description, String title, String author){
        new Ebook(bookId, genre, publishingDate, publisherId, description, title, author);
    }

    public void removeBook(Ebook ebook){
        ebook.removeBook(ebook.getBookId());
    }

    public void updateBook(Ebook ebook, String genre, String description){
        ebook.updateBook(ebook.getBookId(), genre, description);
    }
}
