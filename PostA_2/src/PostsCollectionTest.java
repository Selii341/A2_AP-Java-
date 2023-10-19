import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class PostsCollectionTest {

    @Before
    public void beforeAddPost() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void addPost() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:MM");
        Date d = sdf.parse("15/11/2022 23:30");
        Post p = new Post(1, "test1", "test1", 1, 1, d);
        PostsCollection.addPost(p);
        //test add successfully
        assertTrue(PostsCollection.getPostMap().size() == 6);
        //test add repeatedly and no change
        PostsCollection.addPost(p);
        assertTrue(PostsCollection.getPostMap().size() == 6);
    }

    @Before
    public void beforeRemovePost() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void removePost() {
        PostsCollection.removePost(10);
      //test remove successfully
        assertTrue(PostsCollection.getPostMap().size() == 4);
      //test remove failed
        PostsCollection.removePost(1);
        assertTrue(PostsCollection.getPostMap().size() == 4);
    } 

    @Before
    public void beforeGetPost() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void getPost() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
      //test retrieve failed
        PostsCollection.getPost(1);
        assertEquals("Sorry the post does not exist in the collection!\n", outContent.toString());

        outContent.reset();
        //test retrieve successfully,\n from post.tostring \n from system.out.println
        PostsCollection.getPost(10);
        assertEquals("Retrieved post:\nID: 10\nContent: Check out this epic film.\nAuthor: A567VF\nLikes: 1000\nShares: 1587\nDate:Sun Jan 01 14:00:00 AEDT 2023\n", outContent.toString());
    }

    @Before
    public void beforeShowTopNLikes() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void showTopNLikes() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //test within the size of postmap
        PostsCollection.showTopNLikes();
        assertEquals(PostsCollection.showTopNLikes().size() == 4, outContent.toString());


     
        //test over the size of postmap
        PostsCollection.showTopNLikes();
        assertEquals(PostsCollection.showTopNLikes().size() == 4, outContent.toString());
    }

    @Before
    public void beforeGetSharesMap() {
        PostsCollection.initPosts(CenterController.loadFiles());
    }

    @Test
    public void getSharesMap() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //test within the size of postmap
        PostsCollection.getSharesMap();
        assertEquals(PostsCollection.getSharesMap().size() == 3, outContent.toString());

        //test over the size of postmap
        PostsCollection.getSharesMap();
        assertEquals(PostsCollection.getSharesMap().size() == 3, outContent.toString());
        
        PostsCollection.getSharesMap();
        assertEquals(PostsCollection.getSharesMap().set(0, 2),outContent.toString());
        assertEquals(PostsCollection.getSharesMap().set(1, 0),outContent.toString());
        assertEquals(PostsCollection.getSharesMap().set(2, 3),outContent.toString());
        


    }
}