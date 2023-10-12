
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

    public static void AddPost(Post p) {
        postMap.put(p.getID(), p);
    }

    public static void removePost(int ID) {
        if (postMap.containsKey(ID)) {
            postMap.remove(ID);
            System.out.println("The post has been deleted from the collection!");
        } else {
            System.out.println("Sorry the post does not exist in the collection!");
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

    public static void showTopNLikes(int num) {
        List<Post> sortedPosts = new ArrayList<>(postMap.values());
        sortedPosts.sort((p1, p2) -> p2.getLikes() - p1.getLikes());

        if (num > sortedPosts.size()) {
            num = sortedPosts.size();
            System.out.println("Only " + sortedPosts.size() + " posts exist in the collection. Showing all of them.");
        }

        System.out.println("The top-" + num + " posts with the most likes are:");
        for (int i = 0; i < num; i++) {
            System.out.print((i + 1) + ") ");
            System.out.print(sortedPosts.get(i).getID() + " | ");
            System.out.print(sortedPosts.get(i).getContent() + " | ");
            System.out.println(sortedPosts.get(i).getLikes());
        }
    }

    public static void showTopNShares(int num) {
        //Sort by shares
        List<Post> sortedPosts = new ArrayList<>(postMap.values());
        sortedPosts.sort((p1, p2) -> p2.getShares() - p1.getShares());

        if (num > sortedPosts.size()) {
            num = sortedPosts.size();
            System.out.println("Only " + sortedPosts.size() + " posts exist in the collection. Showing all of them.");
        }

        System.out.println("The top-" + num + " posts with the most shares are:");
        for (int i = 0; i < num; i++) {
            System.out.print((i + 1) + ") ");
            System.out.print(sortedPosts.get(i).getID() + " | ");
            System.out.print(sortedPosts.get(i).getContent() + " | ");
            System.out.println(sortedPosts.get(i).getShares());
        }
    }
}
