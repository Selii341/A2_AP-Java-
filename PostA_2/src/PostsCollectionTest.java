import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PostsCollectionTest {

    @Before
    public void beforeAddPost() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void addPost() {
        PostsCollection.initPosts(new HashMap<Integer,Post>()); // Initialize an empty collection
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date d = null;
        try {
            d = sdf.parse("15/11/2022 23:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Post p = new Post(1, "test1", "test1", 1, 1, d);

        PostsCollection.addPost(p);
        // Verify the collection size increases after adding a post
        assertTrue(PostsCollection.getPostMap().size() == 1);
        
        // Add the same post again, it should not increase the size
        PostsCollection.addPost(p);
        assertTrue(PostsCollection.getPostMap().size() == 1);
    }


    @Before
    public void beforeRemovePost() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void removePost() {
    	
    	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	  Date d = null;
          try {
              d = sdf.parse("15/11/2022 23:30");
          } catch (ParseException e) {
              e.printStackTrace();
          }
          Post p = new Post(1, "test1", "test1", 1, 1, d);
          
          PostsCollection.addPost(p); // Add a post to ensure it exists


          try {
        	  // Verify that a post is removed, and the size decreases
              PostsCollection.removePost(1);
              assertEquals(6, PostsCollection.getPostMap().size());
          } catch (Exception e) {
              e.printStackTrace();
              fail("Removing a post failed: " + e.getMessage());
          }

          try {
     
              PostsCollection.removePost(2);
              assertEquals(5, PostsCollection.getPostMap().size());
          } catch (Exception e) {
              e.printStackTrace();
           	  // Attempt to remove a non-existent post, it should not affect the size
              fail("Removing a non-existent post should not throw an exception");
          }
      
    }

    @Before
    public void beforeGetPost() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void getPost() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date d = null;
        try {
            d = sdf.parse("16/10/2023 20:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Post p = new Post(2, "2", "2", 2, 2, d);
        PostsCollection.addPost(p); // Add a post to ensure it exists

        // Verify that an existing post is retrieved and printed
        PostsCollection.getPost(2);

        // Use assertions to verify the content
        String output = outContent.toString();
        assertEquals("Retrieved Post:"+ "\n" +"ID: " + p.getID()+ "\n" + "Content: " +p.getContent()+ "\n" + "Author: " + p.getAuthor()+ "\n" + "Likes: " +p.getLikes()+ "\n" + "Shares: " +p.getShares()+ "\n"+ "\n", outContent.toString());
       
        outContent.reset();

        // Attempt to retrieve a non-existent post, it should print an error message
        PostsCollection.getPost(1000);

        // Use assertions to verify the error message
        output = outContent.toString();
        assertEquals("Sorry the post does not exist in the collection!\n", outContent.toString());

        
    }

    @Before
    public void beforeShowTopNLikes() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void showTopNLikes() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date d = null;
        try {
            d = sdf.parse("15/11/2022 23:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Post p1 = new Post(1, "test1", "test1", 10, 1, d);
        Post p2 = new Post(2, "test2", "test2", 5, 1, d);
        Post p3 = new Post(3, "test3", "test3", 15, 1, d);
        PostsCollection.addPost(p1);
        PostsCollection.addPost(p2);
        PostsCollection.addPost(p3);

        // Verify that the list of posts sorted by likes is correct
        assertEquals(8, PostsCollection.showTopNLikes().size());
        assertEquals(382, PostsCollection.showTopNLikes().get(0).getID());
        assertEquals(37221, PostsCollection.showTopNLikes().get(2).getID());
    }

    @Before
    public void beforeGetSharesMap() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void getSharesMap() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date d = null;
        try {
            d = sdf.parse("15/11/2022 23:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Post p1 = new Post(1, "test1", "test1", 1, 25, d);
        Post p2 = new Post(2, "test2", "test2", 1, 500, d);
        Post p3 = new Post(3, "test3", "test3", 1, 1000, d);
        PostsCollection.addPost(p1);
        PostsCollection.addPost(p2);
        PostsCollection.addPost(p3);

        // Verify the distribution of shares
        assertEquals(3, PostsCollection.getSharesMap().size());
        assertEquals(Integer.valueOf(3), PostsCollection.getSharesMap().get(0));
        assertEquals(Integer.valueOf(1), PostsCollection.getSharesMap().get(1));
        assertEquals(Integer.valueOf(4), PostsCollection.getSharesMap().get(2));


    }
}