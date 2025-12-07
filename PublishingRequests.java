import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PublishingRequests {
    private int requestId;
    private int publisherId;
    private int bookId;
    private boolean requestStatus;
    private static int counter = 1;

    public PublishingRequests(int publisher, int book, boolean requestStatus) {
        requestId = counter;
        this.publisherId= publisher;
        this.bookId = book;
        this.requestStatus = requestStatus;
        counter++;

        String sb = requestId + publisher + "," + book + ",false";
        try (FileWriter writer = new FileWriter("PendingPublishingRequests.CSV", true)) {
            writer.write(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PublishingRequests(){}

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisher(int publisher) {
        this.publisherId = publisher;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBook(int book) {
        this.bookId = book;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public static PublishingRequests getReq(int reqId){

        try (BufferedReader br = new BufferedReader(new FileReader("PendingPublishingRequests.CSV"))) {

            String line;
            br.readLine();
            // skip header
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if(reqId==(Integer.parseInt(data[0]))){
                    PublishingRequests p = new PublishingRequests();
                    p.setRequestId(reqId);
                    p.setPublisher(Integer.parseInt(data[1]));
                    p.setBook(Integer.parseInt(data[2]));
                    p.setRequestStatus(Boolean.parseBoolean(data[3]));
                    return p;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void RemoveReq(int reqId){
        List<PublishingRequests> Reqs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("PendingPublishingRequests.CSV"))) {

            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                PublishingRequests u = new PublishingRequests();
                u.setRequestId(Integer.parseInt(data[0]));
                u.setPublisher(Integer.parseInt(data[1]));
                u.setBook(Integer.parseInt(data[2]));
                u.setRequestStatus(Boolean.parseBoolean(data[3]));
                Reqs.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < Reqs.size(); i++) {
            if (Reqs.get(i).getRequestId() == this.requestId) {
                Reqs.remove(i);
                break;
            }

        }

        try (FileWriter writer = new FileWriter("Accounts.CSV")) {

            writer.write("BookId,genre,PublishingDate,PublisherId,description,Title,Author\n");

            for (PublishingRequests u : Reqs) {
                writer.write(u.getRequestId() + "," +
                                u.getPublisherId() + "," +
                                u.getBookId() + "," +
                                u.isRequestStatus()+"\n"
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void changeStore(){
        RemoveReq(this.requestId);
        String sb = requestId + this.publisherId + "," + this.bookId + ",false";
        try (FileWriter writer = new FileWriter("ProcessedPublishingRequests.CSV", true)) {
            writer.write(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
