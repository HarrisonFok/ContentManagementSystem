/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.TestApplicationConfiguration;
import blog.dto.Blog;
import blog.dto.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author Joshua Martel
 */
//@ComponentScan({"blog.*", "blog.*"})
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DaoBlogImplTest {
    
    @Autowired
    private DaoBlogImpl daoBlog;
    @Autowired
    private DaoUserImpl daoUser;
    
    public DaoBlogImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
       
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
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
     * Test of addBlog method, of class DaoBlogImpl.
     */
    @Test
    public void testAddGetBlog() {
        //Add user who wrote blog
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        Blog newBlog = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        newBlog = daoBlog.addBlog(newBlog);
        
        Blog fromDao = daoBlog.getBlog(newBlog.getBlogID());
        
        assertEquals(newBlog,fromDao);
        
    }

    /**
     * Test of updateBlog method, of class DaoBlogImpl.
     */
    @Test
    public void testUpdateBlog() {
        //Add user who wrote blog
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        Blog newBlog = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        newBlog = daoBlog.addBlog(newBlog);
        
        Blog fromDao = daoBlog.getBlog(newBlog.getBlogID());
//        newBlog.setBlogID(fromDao.getBlogID());
        
        assertEquals(newBlog,fromDao);
        
//        System.out.println(newBlog);
        newBlog.setContent("Ben Kenobi");
        assertTrue(daoBlog.updateBlog(newBlog));
        System.out.println(newBlog);

         System.out.println(fromDao);
        assertNotEquals(newBlog,fromDao);
    }

    /**
     * Test of removeBlog method, of class DaoBlogImpl.
     */
    @Test
    public void testRemoveBlog() {
        //Add user who wrote blog
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        Blog newBlog = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        daoBlog.addBlog(newBlog);
        Blog fromDao = daoBlog.getBlog(newBlog.getBlogID());
        
        assertEquals(newBlog,fromDao);
        
        daoBlog.removeBlog(newBlog.getBlogID());
        
        try{
            fromDao = daoBlog.getBlog(newBlog.getBlogID());
            assertNull(fromDao);
        }catch(EmptyResultDataAccessException e){
        }
        
        
   
    }

    /**
     * Test of getAllBlogs method, of class DaoBlogImpl.
     */
    @Test
    public void testGetAllBlogs() {
        //Add user who wrote blog
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        Blog blog1 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 35, 5);
        Blog blog2 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 45, 5);
        Blog blog3 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 55, 5);
        
        //Add blogs
        blog1 = daoBlog.addBlog(blog1);
        blog2 = daoBlog.addBlog(blog2);
        blog3 = daoBlog.addBlog(blog3);
        
        List<Blog> blogs = daoBlog.getAllBlogs();
        
        assertEquals(blogs.size(),3);
        assertTrue(blogs.contains(blog1));
        assertTrue(blogs.contains(blog2));
        assertTrue(blogs.contains(blog3));

    }

    /**
     * Test of getBlogsByUser method, of class DaoBlogImpl.
     */
    @Test
    public void testGetBlogsByUser() {
        //Add user who wrote blog
        User user1 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        User user2 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        
        user1 = daoUser.addUser(user1);
        user2 = daoUser.addUser(user2);
        
        Blog blog1 = new Blog(1, "Money",
                "Money money money.", 
                user1.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 35, 5);
        Blog blog2 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user1.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 45, 5);
        Blog blog3 = new Blog(1, "attack of the peppers",
                "once upon a time peppers attacked. Then everyone died. The End", 
                user2.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 55, 5);
        
        //Add blogs
        blog1 = daoBlog.addBlog(blog1);
        blog2 = daoBlog.addBlog(blog2);
        blog3 = daoBlog.addBlog(blog3);
        
        List<Blog> user1Blogs = daoBlog.getBlogsByUser(user1.getUserID());
        List<Blog> user2Blogs = daoBlog.getBlogsByUser(user2.getUserID());
        
        assertEquals(user1Blogs.size(),2);
        assertEquals(user2Blogs.size(),1);
        
        assertTrue(user1Blogs.contains(blog1));
        assertTrue(user1Blogs.contains(blog2));
        assertTrue(user2Blogs.contains(blog3));
        
        assertFalse(user1Blogs.contains(blog3));
        assertFalse(user2Blogs.contains(blog2));
        assertFalse(user2Blogs.contains(blog1));
    }
    
}
