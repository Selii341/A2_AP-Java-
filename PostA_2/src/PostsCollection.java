
/*
 * The PostsCollection class manages a collection of social media posts using a HashMap data structure.
 * It includes methods to interact with the post collection, such as adding, removing, and retrieving posts.
 * This class is an essential part of the Model in the MVC pattern, handling post data storage and retrieval.
 * It also provides functionality to save all posts to a CSV file.
 * It links to the Post class for post data storage and retrieval.
 */

import javafx.scene.control.Alert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostsCollection {
    private static HashMap<Integer, Post> postMap;

    /**
     * Initializes the collection of posts with data loaded from files.
     *
     * @param loadFiles A HashMap containing posts loaded from files.
     */
    public static void initPosts(HashMap<Integer,Post> loadFiles) {
        postMap = loadFiles;
    }

    /**
     * Returns the HashMap containing the posts in the collection.
     *
     * @return A HashMap of posts with unique IDs.
     */
    public static HashMap<Integer, Post> getPostMap() {
        return postMap;
    }

    /**
     * Adds a new post to the collection.
     *
     * @param p The Post object to be added to the collection.
     */
    public static void addPost(Post p) {
        postMap.put(p.getID(), p);
    }
    
    /**
     * Removes a post from the collection by its unique ID.
     *
     * @param ID The unique ID of the post to be removed.
     */

    public static void removePost(int ID) {
        if (postMap.containsKey(ID)) {
            postMap.remove(ID);
            System.out.println("The post has been deleted from the collection!");
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "The post is not existed");
            a.setTitle("Warning");
            a.setHeaderText("ID error");
            a.show();
        }
    }
    
    /**
     * Retrieves and prints the details of a post from the collection by its unique ID.
     *
     * @param ID The unique ID of the post to be retrieved and displayed.
     */

    public static void getPost(int ID) {
        if (postMap.containsKey(ID)) {
            System.out.println("Retrieved Post:");
            System.out.println(postMap.get(ID));
        } else {
            System.out.println("Sorry the post does not exist in the collection!");
        }
    }
    
    /**
     * Retrieves a list of posts sorted by the number of likes in descending order.
     *
     * @return A list of posts sorted by the number of likes.
     */
    public static List<Post> showTopNLikes() {
        List<Post> sortedPosts = new ArrayList<>(postMap.values());
        sortedPosts.sort((p1, p2) -> p2.getLikes() - p1.getLikes());
        return sortedPosts;
    }

    /**
     * Calculates the distribution of shares among posts and returns an ArrayList of counts for different share ranges.
     *
     * @return An ArrayList representing the distribution of shares.
     */
    public static ArrayList<Integer> getSharesMap() {
        //Sort by shares
        ArrayList<Integer> result = new ArrayList<Integer>(3);
        result.add(0);
        result.add(0);
        result.add(0);
        for (Post p : postMap.values()) {
            if(p.getShares() >= 0 && p.getShares() <= 99) {
                result.set(0, result.get(0) + 1);
            } else if(p.getShares() >= 100 && p.getShares() <= 999) {
                result.set(1, result.get(1) + 1);
            } else {
                result.set(2, result.get(2) + 1);
            }
        }
        return result;
    }

   // Saves all posts in the collection to a CSV file.
     
    public static void saveAllPostToFiles() {
        File file = new File("posts.csv");
        BufferedWriter out = null;
        try {

            SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY HH:MM");

            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, false)));
            out.write("ID,content,author,likes,shares,date-time\n");
            for(Post p : postMap.values()) {
                String content = p.getID() + "," + p.getContent() + "," + p.getAuthor()
                        + "," + p.getLikes() + "," + p.getShares() + "," + dateFormat.format(p.getDate()) + "\n";
                out.write(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
