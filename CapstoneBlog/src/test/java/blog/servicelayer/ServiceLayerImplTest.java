/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.servicelayer;

import blog.TestApplicationConfiguration;
import blog.dao.DaoBlogImpl;
import blog.dao.DaoUserImpl;
import blog.dto.Blog;
import blog.dto.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Joshua Martel
 */
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceLayerImplTest {
    
    @Autowired
    private DaoBlogImpl daoBlog;
    @Autowired
    private DaoUserImpl daoUser;
    
    @Autowired
    private ServiceLayerImpl service;
    
    public ServiceLayerImplTest() {
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
     * Test of getBlogsByVisibility method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetBlogsByVisibility() {
        //Add user who wrote blog
        User user1 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        User user2 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        //Add users
        user1 = daoUser.addUser(user1);
        user2 = daoUser.addUser(user2);
        
        //Create some visible and invisible blogs
        Blog blog1 = new Blog(1, "Money",
                "Money money money.", 
                user1.getUserID(),
                false, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 35, 5);
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
        
        //get blogs by visiblity
        List<Blog> visible = service.getBlogsByVisibility(true);
        List<Blog> invisible = service.getBlogsByVisibility(false);
        
        assertEquals(visible.size(), 2);
        assertEquals(invisible.size(), 1);
        
        assertTrue(visible.contains(blog2));
        assertTrue(visible.contains(blog3));
        assertFalse(visible.contains(blog1));
        
        assertFalse(invisible.contains(blog2));
        assertFalse(invisible.contains(blog3));
        assertTrue(invisible.contains(blog1));
        
        
    }

    /**
     * Test of validUserIDForBlog method, of class ServiceLayerImpl.
     */
    @Test
    public void testValidUserIDForBlog() {
        //Add user who wrote blog
        User user1 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        User user2 = new User(1 ,"Vader", "Darth", "dontunderstame", 
                "thepowerofthedarkside", "Assistant");
        
        //Add users
        user1 = daoUser.addUser(user1);
        user2 = daoUser.addUser(user2);
        
        //Create some visible and invisible blogs
        Blog blog1 = new Blog(1, "Money",
                "Money money money.", 
                user1.getUserID(),
                false, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 35, 5);
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
        
        
        //Check if user's id matches that on the blog
        assertTrue(service.validUserIDForBlog(blog1.getUserID(), user1.getUserID()));
        assertTrue(service.validUserIDForBlog(blog2.getUserID(), user1.getUserID()));
        assertFalse(service.validUserIDForBlog(blog3.getUserID(), user1.getUserID()));
        
        assertFalse(service.validUserIDForBlog(blog1.getUserID(), user2.getUserID()));
        assertFalse(service.validUserIDForBlog(blog2.getUserID(), user2.getUserID()));
        assertTrue(service.validUserIDForBlog(blog3.getUserID(), user2.getUserID()));

    }

    /**
     * Test of checkAccessPrivilegeAdmin method, of class ServiceLayerImpl.
     */
    @Test
    public void testCheckAccessPrivilegeAdmin() {
       //admin user
        User user1 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        //is not an admin
        User user2 = new User(1 ,"Vader", "Darth", "dontunderstame", 
                "thepowerofthedarkside", "Assistant");
        
        //Add users
        user1 = daoUser.addUser(user1);
        user2 = daoUser.addUser(user2); 
        
        assertTrue(service.checkAccessPrivilegeAdmin(user1));
        assertFalse(service.checkAccessPrivilegeAdmin(user2));
    }

    /**
     * Test of checkAccessPrivilegeAssistant method, of class ServiceLayerImpl.
     */
    @Test
    public void testCheckAccessPrivilegeAssistant() {
        //admin user
        User user1 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        //is not an admin
        User user2 = new User(1 ,"Vader", "Darth", "dontunderstame", 
                "thepowerofthedarkside", "Assistant");
        
        //Add users
        user1 = daoUser.addUser(user1);
        user2 = daoUser.addUser(user2); 
        
        assertTrue(service.checkAccessPrivilegeAssistant(user2));
        assertFalse(service.checkAccessPrivilegeAssistant(user1));
    }

    /**
     * Test of isVisible method, of class ServiceLayerImpl.
     */
    @Test
    public void testIsVisible() {
        //add users
        User user1 = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        User user2 = new User(1 ,"Vader", "Darth", "dontunderstame", 
                "thepowerofthedarkside", "Assistant");
        
        //Add users
        user1 = daoUser.addUser(user1);
        user2 = daoUser.addUser(user2);
        
        //Create some visible and invisible blogs
        Blog blog1 = new Blog(1, "Money",
                "Money money money.", 
                user1.getUserID(),
                false, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 35, 5);
        Blog blog2 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user1.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 45, 5);
        Blog blog3 = new Blog(1, "attack of the peppers",
                "once upon a time peppers attacked. Then everyone died. The End", 
                user2.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 55, 5);
    
    assertFalse(service.isVisible(blog1));
    assertTrue(service.isVisible(blog2));
    assertTrue(service.isVisible(blog3));
    
    }
    
}
