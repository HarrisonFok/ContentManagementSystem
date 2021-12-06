/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.Blog;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joshua Martel
 */
@Repository
public class DaoBlogImpl implements DaoBlog{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Blog addBlog(Blog newBlog){
        final String sql = "INSERT INTO Blog(blogID, title, content, userID, "
                + "visible, datePosted, dateExpires, likes, dislikes) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update((Connection conn) -> {
            PreparedStatement pState = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pState.setString(1, newBlog.getTitle());
            pState.setString(2, newBlog.getContent());
            pState.setInt(3, newBlog.getUserID());
            pState.setBoolean(4, newBlog.isVisible());
            pState.setDate(5, Date.valueOf(newBlog.getDatePosted()));
            pState.setDate(6, Date.valueOf(newBlog.getDateExpires()));
            pState.setInt(7, newBlog.getLikes());
            pState.setInt(8, newBlog.getDislikes());
            
            return pState;
        }, key);
        newBlog.setBlogID(key.getKey().intValue());
        return newBlog;
    }
    
    //Get blog by id
    @Override
    public Blog getBlog(int blogID){
        final String GET_BLOG = "SELECT * FOM Blogs WHERE blogID = ?";
        return jdbc.queryForObject(GET_BLOG, new BlogMapper(), blogID);
    }
    
    //update a blog useing id
//    blogID, title, content, userID, "
//                + "visible, datePosted, dateExpires, likes, dislikes
    @Override
    public boolean updateBlog(Blog blog){
        final String sql = "UPDATE Blogs SET"
                + "title = ?,"
                + "content = ?,"
                + "userID = ?,"
                + "visible = ?,"
                + "datePosted = ?,"
                + "dateExpires = ?,"
                + "likes = ?,"
                + "dislikes = ?,"
                + "WHERE blogID = ?";
        return jdbc.update(sql,
                blog.getTitle(),
                blog.getContent(),
                blog.getUserID(),
                blog.isVisible(),
                blog.getDatePosted(),
                blog.getDateExpires(),
                blog.getLikes(),
                blog.getDislikes()) > 0;
    }
    
    @Override
    public boolean removeBlog(int blogID){
        final String DELETE_BLOG = "DELETE FROM Blogs WHERE blogID = ?";
        return jdbc.update(DELETE_BLOG, blogID) > 0;
    }
    
    @Override
    public List<Blog> getAllBlogs(){
        final String GET_ALL_BLOGS = "SELECT * FROM Blogs";
        return jdbc.query(GET_ALL_BLOGS, new BlogMapper());
    }
    
    @Override
    public List<Blog> getBlogsByUser(int userID){
        final String GET_ALL_BLOGS_BY_USER = "SELECT * FROM Blogs WHERE userID = ?";
        return jdbc.query(GET_ALL_BLOGS_BY_USER, new BlogMapper(), userID);
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
            newBlog.setDatePosted(rs.getDate("datePosted").toLocalDate());
            newBlog.setDateExpires(rs.getDate("dateExpires").toLocalDate());
            newBlog.setLikes(rs.getInt("likes"));
            newBlog.setDislikes(rs.getInt("dislikes"));
            return newBlog;
        }
        
    }
    
}
