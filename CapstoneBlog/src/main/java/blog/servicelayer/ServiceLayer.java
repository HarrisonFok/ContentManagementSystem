/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.servicelayer;

import blog.dto.Blog;
import blog.dto.BlogTags;
import blog.dto.Comment;
import blog.dto.Tag;
import blog.dto.User;
import java.util.List;

/**
 *
 * @author Joshua Martel
 */
public interface ServiceLayer {
    
    //====Blog Pass-Through Methods====
    
    public Blog addBlog(Blog blog);
    public Blog getBlog(int blogID);
    public boolean updateBlog(Blog blog);
    public boolean removeBlog(int blogID);
    public List<Blog> getAllBlogs();
    public List<Blog> getBlogsByUser(int userID);
    
    //====BlogTags Pass-Through Methods====
    
    public BlogTags addTag(BlogTags bTag);
    public boolean removeTagFromBlog(BlogTags bTag);
    public List<Blog> getAllBlogsWithTag(int tagID);
    public List<Tag> getAllTagsForBlog(int blogID);
    
    //====Comment Pass-Through Methods====
    
    public Comment addCommment(Comment comment);
    public Comment getComment(int commentID);
    public boolean updateComment(Comment comment);
    public boolean removeComment(int commentID);
    public List<Comment> getCommentsByUser(int userID);
    public List<Comment> getCommentsByBlog(int blogID);
    
    //====Tag Pass-Through Methods====
    
    public Tag addTag(Tag tag);
    public Tag getTag(int tagID);
    public boolean updateTag(Tag tag);
    public boolean removeTag(int tagID);
    public List<Tag> getAllTags();
    
    //====User Pass-Through Methods====
    
    public User addUser(User user);
    public User getUser(int userID);
    public boolean updateUser(User user);
    public boolean removeUser(int userID);
    public List<User> getAllUsers();
    
    
    //====Business Logic Methods====
    
    public List<Blog> getBlogsByVisibility(boolean visible);
    public boolean validUserIDForBlog(int userID, int blogID);
    public boolean checkAccessPrivilegeAdmin(User user);
    public boolean checkAccessPrivilegeAssistant(User user);
    public boolean isVisible(Blog blog);
}
