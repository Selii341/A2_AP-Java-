/*
 * This manages a collection of posts by storing them in a HashMap and 
 * includes methods to interact with this collection
 * which is a part of Model 
 * */

import javafx.scene.control.Alert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostsCollection {
    private static HashMap<Integer, Post> postMap;

    public static void initPosts(HashMap<Integer,Post> loadFiles) {
        postMap = loadFiles;
    }

    public static HashMap<Integer, Post> getPostMap() {
        return postMap;
    }

    public static void addPost(Post p) {
        postMap.put(p.getID(), p);
    }

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

    public static void getPost(int ID) {
        if (postMap.containsKey(ID)) {
            System.out.println("Retrieved Post:");
            System.out.println(postMap.get(ID));
        } else {
            System.out.println("Sorry the post does not exist in the collection!");
        }
    }

    public static List<Post> showTopNLikes() {
        List<Post> sortedPosts = new ArrayList<>(postMap.values());
        sortedPosts.sort((p1, p2) -> p2.getLikes() - p1.getLikes());
        return sortedPosts;
    }

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
