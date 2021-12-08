/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.Comment;
import java.sql.Connection;
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
public class DaoCommentImpl implements DaoComment{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Comment addCommment(Comment comment){
        final String sql = "INSERT INTO Comments (userID, blogID, userComment)"
                + " VALUES(?,?,?)";
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update((Connection conn) -> {
            PreparedStatement pState = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pState.setInt(1, comment.getUserID());
            pState.setInt(2, comment.getBlogID());
            pState.setString(3, comment.getUserComment());
            
            
            return pState;
        }, key);
        comment.setCommentID(key.getKey().intValue());
        return comment;
    }
    
    //Get a comment 
    @Override
    public Comment getComment(int commentID){
        final String sql = "SELECT * FROM Comments WHERE commentID = ?";
        return jdbc.queryForObject(sql, new CommentMapper(), commentID);
    }
    
    @Override
    public boolean updateComment(Comment comment){
        final String sql = "UPDATE Comments SET "
                + "userID = ?,"
                + "blogID = ?,"
                + "userComment = ?"
                + "WHERE commentID = ?";
        return jdbc.update(sql, 
                comment.getUserID(), 
                comment.getBlogID(), 
                comment.getUserComment(),
                comment.getCommentID()) > 0;
        
    }
    
    @Override
    public boolean removeComment(int commentID){
        final String sql = "DELETE FROM Comments WHERE commentID = ?";
        return jdbc.update(sql,commentID) > 0;
        
    }
    
    @Override
    public List<Comment> getCommentsByUser(int userID){
        final String sql = "SELECT * FROM Comments WHERE userID = ?";
        return jdbc.query(sql, new CommentMapper(), userID);
    }
    
    @Override
    public List<Comment> getCommentsByBlog(int blogID){
        final String sql = "SELECT * FROM Comments WHERE blogID = ?";
        return jdbc.query(sql, new CommentMapper(), blogID);
    }
    
    private static final class CommentMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment com = new Comment();
            com.setCommentID(rs.getInt("commentID"));
            com.setBlogID(rs.getInt("blogID"));
            com.setUserID(rs.getInt("userID"));
            com.setUserComment(rs.getString("userComment"));

            return com;
        }
        
    }
    
}
