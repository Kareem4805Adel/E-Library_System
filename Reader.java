import java.util.List;

public class Reader extends User{
    private double balance;
    private List<Ebook> PurchasedBooks;
    private List<Ebook> BorrowedBooks;
    private List<Integer> BorrowedBooksDuration;

    public Reader(String username, int age, int id, String email, String phoneNumber, String password, String role, double balance, List<Ebook> purchasedBooks, List<Ebook> borrowedBooks, List<Integer> borrowedBooksDuration) {
        super(username, age, id, email, phoneNumber, password, role);
        this.balance = balance;
        PurchasedBooks = purchasedBooks;
        BorrowedBooks = borrowedBooks;
        BorrowedBooksDuration = borrowedBooksDuration;
    }

    public Reader() {

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Ebook> getPurchasedBooks() {
        return PurchasedBooks;
    }

    public void setPurchasedBooks(List<Ebook> purchasedBooks) {
        PurchasedBooks = purchasedBooks;
    }

    public List<Ebook> getBorrowedBooks() {
        return BorrowedBooks;
    }

    public void setBorrowedBooks(List<Ebook> borrowedBooks) {
        BorrowedBooks = borrowedBooks;
    }

    public List<Integer> getBorrowedBooksDuration() {
        return BorrowedBooksDuration;
    }

    public void setBorrowedBooksDuration(List<Integer> borrowedBooksDuration) {
        BorrowedBooksDuration = borrowedBooksDuration;
    }

    public void addToMyPurchasedBooks(Ebook ebook){
        PurchasedBooks.add(ebook);
    }

    public void addToMyBorrowedBooks(Ebook ebook, int duration){
        BorrowedBooks.add(ebook);
        BorrowedBooksDuration.add(duration);
    }

    public void endBorrowing(int bookId){

        for (int i = 0; i < BorrowedBooks.size(); i++){
            if (BorrowedBooks.get(i).getBookId() == bookId){
                BorrowedBooks.remove(i);
                BorrowedBooksDuration.remove(i);
            }
        }

    }

    private Boolean checkBalance(double value){
        return (this.balance >= value);
    }

    public Boolean updateBalance(double value){
        Boolean done = checkBalance(value);
        if(done){
            this.balance += value;
            return true;
        }else{
            return false;
        }
    }

    public Boolean ExtendBorrowing(int extraDuration, double BookPrice, int bookId){

        for (int i = 0; i < BorrowedBooks.size(); i++){

            if (BorrowedBooks.get(i).getBookId() == bookId){

                if (BorrowedBooks.get(i).CalculateTimeLeft(BorrowedBooksDuration.get(i), this) != 0) {
                    Boolean done = updateBalance(BookPrice * 0.25 * extraDuration);
                    if (done) {
                        BorrowedBooksDuration.set(i, (extraDuration + BorrowedBooksDuration.get(i))); // old duration + extra duration = new duration;
                    }
                    return true;
                }
            }
        }

        return false;
    }

}
