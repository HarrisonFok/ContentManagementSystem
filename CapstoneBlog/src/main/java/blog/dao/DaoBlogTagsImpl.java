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
       // Remove a BlogTag in the BlogTag table
        final String sql = "DELETE FROM BlogTags WHERE (blogID = ? AND tagID = ?)";
        return jdbc.update(sql, bTag.getBlogID(),bTag.getTagID()) > 0;

    }
    
    @Override
    public List<Blog> getAllBlogsWithTag(int tagID){
        final String sql = "SELECT * FROM blogs INNER JOIN blogtags ON blogtags.blogid = blogs.blogid WHERE tagID = ?";
        return jdbc.query(sql, new BlogMapper(), tagID);
    }
    
    @Override
    public List<Tag> getAllTagsForBlog(int blogID){
        final String sql = "SELECT tagID FROM BlogTags WHERE blogID = ?";
        
        return jdbc.query(sql, new TagMapper(), blogID);
        
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
    
    private static final class BlogMapper implements RowMapper<Blog> {

        @Override
        public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
            Blog newBlog = new Blog();
            newBlog.setBlogID(rs.getInt("blogID"));
            newBlog.setTitle(rs.getString("title"));
            newBlog.setContent(rs.getString("content"));
            newBlog.setUserID(rs.getInt("userID"));
            newBlog.setVisible(rs.getBoolean("visible"));
            newBlog.setDatePosted(rs.getDate("datePost").toLocalDate());
            newBlog.setDateExpires(rs.getDate("dateExpires").toLocalDate());
            newBlog.setLikes(rs.getInt("likes"));
            newBlog.setDislikes(rs.getInt("dislikes"));
            return newBlog;
        }
        
    }
    
    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag();
            tag.setTagID(rs.getInt("tagID"));
            tag.setHashTag(rs.getString("hashTag"));
            
            return tag;
        }
        
    }
    
}
