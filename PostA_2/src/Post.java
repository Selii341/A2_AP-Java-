
/*
 * The Post class models individual social media posts.
 * It encapsulates essential post data, including an ID, content, author, likes, shares, and date.
 * This class is responsible for managing post information and can be used for displaying post data.
 * It is a key element of the application's data model.
 */
import java.util.Date;

public class Post {
    private int ID;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private Date date;
    
    /**
     * Constructs a Post object with the provided data.
     *
     * @param ID      The unique identifier of the post.
     * @param content The content of the post.
     * @param author  The author of the post.
     * @param likes   The number of likes on the post.
     * @param shares  The number of shares of the post.
     * @param date    The date and time when the post was created.
     */
    public Post(int ID, String content, String author, int likes, int shares, Date date) {
        this.ID = ID;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        this.date = date;
    }
    
 // Getter methods for accessing the attributes
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
    
    /**
     * Returns a string representation of the Post object.
     *
     * @return A string containing post information, including ID, content, author, likes, and shares.
     */
    @Override
    public String toString() {
        return "ID: " + ID + "\n" + "Content: " + content + "\n" + "Author: " + author + "\n" + "Likes: " + likes + "\n" + "Shares: " + shares + "\n";
    }
}
