/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.TestApplicationConfiguration;
import blog.dto.Blog;
import blog.dto.BlogTags;
import blog.dto.Tag;
import blog.dto.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
public class DaoBlogTagsImplTest {
    
    @Autowired
    private DaoBlogImpl daoBlog;
    @Autowired
    private DaoUserImpl daoUser;
    @Autowired
    private DaoTagImpl daoTag;
    @Autowired
    private DaoBlogTagsImpl daoBlogTags;
    
    public DaoBlogTagsImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        List<BlogTags> bTags = daoBlogTags.getAllBlogTags();
        for(BlogTags bt :bTags){
            daoBlogTags.removeTagFromBlog(bt);
        }
        
        List<Tag> tags = daoTag.getAllTags();
        for(Tag tag: tags){
            daoTag.removeTag(tag.getTagID());
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
     * Test of addTag method, of class DaoBlogTagsImpl.
     */
    @Test
    public void testEverything() {
        //Add user who wrote blog
        User user = new User(1 ,"Sarah", "Dutkiewicz", "sadukie", 
                "streamOfConsciousness", "Admin");
        user = daoUser.addUser(user);
        
        Blog newBlog1 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        Blog newBlog2 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        Blog newBlog3 = new Blog(1, "Foods Of The World",
                "I love travel and food. My dream is to try all thefoods of the world.", 
                user.getUserID(),
                true, LocalDate.of(2021,12,01),LocalDate.of(2021,12,30), 25, 5);
        
        newBlog1 = daoBlog.addBlog(newBlog1);
        newBlog2 = daoBlog.addBlog(newBlog2);
        newBlog3 = daoBlog.addBlog(newBlog3);
        
        Tag newHashTag = new Tag();
        newHashTag.setHashTag("world");
        //add tag to database
        newHashTag = daoTag.addTag(newHashTag);

 
        
        BlogTags blogTags = daoBlogTags.addTag(new BlogTags(newBlog1.getBlogID(),
                                                            newHashTag.getTagID()));
       //test getting blog tags and getting tagged blogs
       List<Tag> tags = daoBlogTags.getAllTagsForBlog(newBlog1.getBlogID());
       List<Blog> blogs = daoBlogTags.getAllBlogsWithTag(newHashTag.getTagID());
       
       assertEquals(tags.size(),1);
       assertEquals(blogs.size(),1);
       
       assertTrue(tags.contains(newHashTag));
       assertTrue(blogs.contains(newBlog1));
       
       assertFalse(blogs.contains(newBlog2));
       
       //Test removing a tag from a blog
       
       assertTrue(daoBlogTags.removeTagFromBlog(blogTags));
       
       tags = daoBlogTags.getAllTagsForBlog(newBlog1.getBlogID());
       blogs = daoBlogTags.getAllBlogsWithTag(newHashTag.getTagID());
       
       assertEquals(tags.size(),0);
       assertEquals(blogs.size(),0);
       
       assertFalse(tags.contains(newHashTag));
       assertFalse(blogs.contains(newBlog1));
       
    }

   
    
    
}
