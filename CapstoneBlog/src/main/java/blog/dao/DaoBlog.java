/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.Blog;
import java.util.List;

/**
 *
 * @author Joshua Martel
 */
public interface DaoBlog {
    
    public Blog addBlog(Blog blog);
    public Blog getBlog(int blogID);
    public boolean updateBlog(Blog blog);
    public boolean removeBlog(int blogID);
    public List<Blog> getAllBlogs();
    public List<Blog> getBlogsByUser(int userID);
    public boolean addLike(int blogID);
    public boolean addDislike(int blogID);
    
}
