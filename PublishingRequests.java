public class PublishingRequests {
    private Publisher publisher;
    private Ebook book;
    private Librarian assignedLibrarian;
    private boolean requestStatus;

    public PublishingRequests(Publisher publisher, Ebook book, Librarian assignedLibrarian, boolean requestStatus) {
        this.publisher = publisher;
        this.book = book;
        this.assignedLibrarian = assignedLibrarian;
        this.requestStatus = requestStatus;
    }

    public PublishingRequests(){}

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Ebook getBook() {
        return book;
    }

    public void setBook(Ebook book) {
        this.book = book;
    }

    public Librarian getAssignedLibrarian() {
        return assignedLibrarian;
    }

    public void setAssignedLibrarian(Librarian assignedLibrarian) {
        this.assignedLibrarian = assignedLibrarian;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }
}
