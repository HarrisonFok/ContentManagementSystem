/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.Comment;
import java.util.List;

/**
 *
 * @author Joshua Martel
 */
public interface DaoComment {
    
    
    public Comment addCommment(Comment comment);
    public Comment getComment(int commentID);
    public List<Comment> getAllComments();
    public boolean updateComment(Comment comment);
    public boolean removeComment(int commentID);
    public List<Comment> getCommentsByUser(int userID);
    public List<Comment> getCommentsByBlog(int blogID);
    
}
