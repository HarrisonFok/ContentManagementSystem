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
public class DaoUserImplTest {
    
    @Autowired
    private DaoBlogImpl daoBlog;
    @Autowired
    private DaoUserImpl daoUser;
    @Autowired
    private DaoCommentImpl daoCom;
    
    public DaoUserImplTest() {
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
     * Test of addUser, getUser, updateUser, 
     * and removeUser method, of class DaoUserImpl.
     */
    @Test
    public void testAddGetUpdateRemoveUser() {
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        User fromDao = daoUser.getUser(user.getUserID());
        
        assertEquals(user, fromDao);
        
        //change first name
        user.setFirstName("Popadropolus");
        daoUser.updateUser(user);
        
        assertNotEquals(user, fromDao);
        
        //remove user
        daoUser.removeUser(user.getUserID());
        
        List<User> users = daoUser.getAllUsers();
        assertEquals(0, users.size());
    }


    
    
}
