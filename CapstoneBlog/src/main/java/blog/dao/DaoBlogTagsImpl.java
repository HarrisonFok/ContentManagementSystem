/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.Blog;
import blog.dto.BlogTags;
import blog.dto.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joshua Martel
 */
@Repository
public class DaoBlogTagsImpl implements DaoBlogTags{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public BlogTags addTag(BlogTags bTag){
        final String sql ="INSERT INTO BlogTags (blogID, tagID) VALUES (?,?)";
        jdbc.update(sql, bTag.getBlogID(),bTag.getTagID());
        
        return bTag;
    }
    
    @Override
    public boolean removeTagFromBlog(BlogTags bTag){
        throw new UnsupportedOperationException();

    }
    
    @Override
    public List<Blog> getAllBlogsWithTag(int tagID){
        throw new UnsupportedOperationException();
    }
    
    @Override
    public List<Tag> getAllTagsForBlog(int blogID){
        throw new UnsupportedOperationException();
        
    }
    
    private static final class BlogTagsMapper implements RowMapper<BlogTags> {

        @Override
        public BlogTags mapRow(ResultSet rs, int rowNum) throws SQLException {
            BlogTags newBlogTag = new BlogTags();
            newBlogTag.setBlogID(rs.getInt("blogID"));
            newBlogTag.setTagID(rs.getInt("tagID"));
            return newBlogTag;
        }
        
    }
    
}
