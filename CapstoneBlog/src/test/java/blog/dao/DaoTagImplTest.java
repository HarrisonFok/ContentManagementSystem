package blog.dao;


import blog.TestApplicationConfiguration;
import blog.dto.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Tag tag = new Tag();
        tag.setHashTag("test");
        Tag tag2 = new Tag();
        tag2.setHashTag("test2");
        tagDao.addTag(tag);
        tagDao.addTag(tag2);

        List<Tag> actual = tagDao.getAllTags();
        List<Tag> expected = new ArrayList<>();
        expected.add(tag);
        expected.add(tag2);
        assertEquals(expected, actual);

    }

    @Test
    void testRemoveTag() {
        Tag tag = new Tag();
        tag.setHashTag("test");
        Tag tag2 = new Tag();
        tag2.setHashTag("test2");
        tagDao.addTag(tag);
        tagDao.addTag(tag2);
        tagDao.removeTag(tag2.getTagID());
        List<Tag> actual = tagDao.getAllTags();

        List<Tag> expected = new ArrayList<>();
        expected.add(tag);
        assertEquals(expected, actual);

    }

    @Test
    void testUpdateTag() {
        Tag tag = new Tag();
        tag.setHashTag("test");
        Tag tag2 = new Tag();
        tag2.setHashTag("test2");
        tagDao.addTag(tag);
        tagDao.addTag(tag2);
        tag2.setHashTag("test2Update");
        tagDao.updateTag(tag2);
        Tag actual = tagDao.getTag(tag2.getTagID());
        assertEquals(tag2, actual);
    }


}
