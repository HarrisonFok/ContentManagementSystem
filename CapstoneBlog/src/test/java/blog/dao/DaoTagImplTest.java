/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.TestApplicationConfiguration;
import blog.dto.Tag;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
public class DaoTagImplTest {
    
    @Autowired
    public DaoTagImpl daoTag;
    
    public DaoTagImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Tag> tags = daoTag.getAllTags();
        for(Tag tag: tags){
            daoTag.removeTag(tag.getTagID());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addTag, getTag, updateTag , and removeTag methods, of class DaoTagImpl.
     */
    @Test
    public void testAddGetRemoveUpdateTag() {
        Tag newHashTag = new Tag();
        newHashTag.setHashTag("world");
        //add tag to database
        newHashTag = daoTag.addTag(newHashTag);
        //get tag
        Tag fromDao = daoTag.getTag(newHashTag.getTagID());
        //check if operation worked
        assertEquals(newHashTag,fromDao);
        
        newHashTag.setHashTag("stars");
        //update hashtag
        daoTag.updateTag(newHashTag);
        
        assertNotEquals(newHashTag,fromDao);
        
        //remove hashtag
        daoTag.removeTag(newHashTag.getTagID());
        
        List<Tag> tags = daoTag.getAllTags();
        
        assertEquals(0, tags.size());
    }

    
}
