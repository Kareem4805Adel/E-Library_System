public class Reviews {
    private int ReaderId;
    private String comment;
    private int score;

    public Reviews(int readerId, String comment, int score) {
        ReaderId = readerId;
        this.comment = comment;
        this.score = score;
    }

    public Reviews(){}

    public int getReaderId() {
        return ReaderId;
    }

    public void setReaderId(int readerId) {
        ReaderId = readerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
