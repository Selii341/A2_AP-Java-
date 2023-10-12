

import java.util.Date;

public class Post {
    private int ID;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private Date date;

    public Post(int ID, String content, String author, int likes, int shares, Date date) {
        this.ID = ID;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        this.date = date;
    }

    public int getID() {
        return ID;
    }
    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

    public int getShares() {
        return shares;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\n" + "Content: " + content + "\n" + "Author: " + author + "\n" + "Likes: " + likes + "\n" + "Shares: " + shares + "\n";
    }
}
