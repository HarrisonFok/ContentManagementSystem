package blog.dao;


import blog.TestApplicationConfiguration;
import blog.dto.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DaoTagImplTest {

    @Autowired
    DaoTag tagDao;

    @Autowired
    DaoBlog blogDao;

    @Autowired
    DaoBlogTags blogTagsDao;

    @BeforeEach
    void setUp() {
        List<Tag> tags = tagDao.getAllTags();
        for (Tag tag : tags) {
            tagDao.removeTag(tag.getTagID());
        }
    }

    @Test
    void testAddGetTag() {

    }

    @Test
    void testRemoveTag() {

    }

    @Test
    void testUpdateTag() {

    }

    @Test
    void testGetByIDTag() {

    }

}
