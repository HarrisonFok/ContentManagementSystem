/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.TestApplicationConfiguration;
import blog.dto.Blog;
import blog.dto.Comment;
import blog.dto.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Joshua Martel
 */
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DaoCommentImplTest {
    
    @Autowired
    private DaoBlogImpl daoBlog;
    @Autowired
    private DaoUserImpl daoUser;
    @Autowired
    private DaoCommentImpl daoCom;
    
    public DaoCommentImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        List<Comment> coms = daoCom.getAllComments();
        for(Comment com: coms){
            daoCom.removeComment(com.getCommentID());
        }
        
        List<Blog> blogs = daoBlog.getAllBlogs();
         for(Blog blog: blogs){
             daoBlog.removeBlog(blog.getBlogID());
         }
         
         List<User> users = daoUser.getAllUsers();
         for(User user: users){
             daoUser.removeUser(user.getUserID());
         }
         
         
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addCommment method, of class DaoCommentImpl.
     */
    @Test
    public void testAddRemoveGetCommment() {
        //Add user who wrote blog
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        //Add Blog
        Blog newBlog = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        newBlog = daoBlog.addBlog(newBlog);
        System.out.println(newBlog);
        //now create comment
        Comment newComment = new Comment(1, "Me to! I've always dreamed of that :)",
                newBlog.getBlogID(), user.getUserID());
        
        //add comment
        newComment = daoCom.addCommment(newComment);
        //get comment
        Comment fromDao = daoCom.getComment(newComment.getCommentID());
        //now test if successful
        assertEquals(newComment, fromDao);
        
        //remove comment
        daoCom.removeComment(newComment.getCommentID());
        
        //get list of comments to avoid exceptions
        List<Comment> coms = daoCom.getAllComments();
        
        //check if removal was successful
        assertEquals(coms.size(), 0);
        assertFalse(coms.contains(newComment));
        
    }

    /**
     * Test of updateComment method, of class DaoCommentImpl.
     */
    @Test
    public void testUpdateComment(){
        //Add user who wrote blog
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        //Add Blog
        Blog newBlog = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        newBlog = daoBlog.addBlog(newBlog);
        System.out.println(newBlog);
        //now create comment
        Comment newComment = new Comment(1, "Me to! I've always dreamed of that :)",
                newBlog.getBlogID(), user.getUserID());
        
        //add comment
        newComment = daoCom.addCommment(newComment);
        
        //Get a copy of newComment
        Comment fromDao = daoCom.getComment(newComment.getCommentID());
        
        //Check if they are the same
        assertEquals(newComment,fromDao);
        
        //Now update
        newComment.setUserComment("Now, wittness the firepower of this fully armed, and operational battle station");
        
        assertNotEquals(newComment,fromDao);
    }

    
    /**
     * Test of getCommentsByUser and getCommentsByBlog methods, of class DaoCommentImpl.
     */
    @Test
    public void testGetCommentsByUserAndByBlog() {
        //Add user who wrote blog
        User user1 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        User user2 = new User(1 ,"Dave", "Freeman", "DFree", "halflife",
                            "Assistant");
        User user3 = new User(1 ,"Elizabeth", "Thatcher", "LizyGirl123", "happyThoughts",
                            "Regular");
        user1 = daoUser.addUser(user1);
        user2 = daoUser.addUser(user2);
        
        //Add Blog
        Blog newBlog1 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user1.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        Blog newBlog2 = new Blog(1, "Death Star",
                "now, wittness the firepower of this fully armed, and operational battle station", 
                user1.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        newBlog1 = daoBlog.addBlog(newBlog1);
        newBlog2 = daoBlog.addBlog(newBlog2);

        //now create comments
        Comment newComment1 = new Comment(1, "Me to! I've always dreamed of that :)",
                newBlog1.getBlogID(), user1.getUserID());
        Comment newComment2 = new Comment(1, "That station's operational!",
                newBlog2.getBlogID(), user2.getUserID());
        
        //add comment
        newComment1 = daoCom.addCommment(newComment1);
        newComment2 = daoCom.addCommment(newComment2);
        
        //get comments based on userID
        List<Comment> user1Comments = daoCom.getCommentsByUser(user1.getUserID());
        List<Comment> user2Comments = daoCom.getCommentsByUser(user2.getUserID());
        
        assertEquals(user1Comments.size(), 1);
        assertEquals(user2Comments.size(), 1);
        
        assertTrue(user1Comments.contains(newComment1));
        assertTrue(user2Comments.contains(newComment2));
        
        //Get comments base on blogID
        List<Comment> blog1Comments = daoCom.getCommentsByBlog(newBlog1.getBlogID());
        List<Comment> blog2Comments = daoCom.getCommentsByBlog(newBlog2.getBlogID());
        
        assertEquals(blog1Comments.size(), 1);
        assertEquals(blog2Comments.size(), 1);
        
        assertTrue(blog1Comments.contains(newComment1));
        assertTrue(blog2Comments.contains(newComment2));
        
    }

    
}
