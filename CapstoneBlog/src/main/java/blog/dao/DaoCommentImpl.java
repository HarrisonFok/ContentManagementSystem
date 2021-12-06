/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
        return null;
    }
    
    @Override
    public Comment getComment(int commentID){
        return null;
    }
    
    @Override
    public boolean updateComment(Comment comment){
        return false;
        
    }
    
    @Override
    public boolean removeComment(int commentID){
        return false;
        
    }
    
    @Override
    public List<Comment> getCommentsByUser(int userID){
        return null;
    }
    
    @Override
    public List<Comment> getCommentsByBlog(int blogID){
        return null;
    }
    
}
