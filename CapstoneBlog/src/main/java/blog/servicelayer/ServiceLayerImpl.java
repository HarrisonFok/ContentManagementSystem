/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.servicelayer;

import blog.dao.DaoBlog;
import blog.dao.DaoBlogImpl;
import blog.dao.DaoBlogTags;
import blog.dao.DaoBlogTagsImpl;
import blog.dao.DaoComment;
import blog.dao.DaoCommentImpl;
import blog.dao.DaoTag;
import blog.dao.DaoTagImpl;
import blog.dao.DaoUser;
import blog.dao.DaoUserImpl;
import blog.dto.Blog;
import blog.dto.BlogTags;
import blog.dto.Comment;
import blog.dto.Tag;
import blog.dto.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joshua Martel
 */
@Service
@ComponentScan(basePackageClasses = DaoBlogImpl.class)
@ComponentScan(basePackageClasses = DaoBlogTagsImpl.class)
@ComponentScan(basePackageClasses = DaoCommentImpl.class)
@ComponentScan(basePackageClasses = DaoTagImpl.class)
@ComponentScan(basePackageClasses = DaoUserImpl.class)
public class ServiceLayerImpl implements ServiceLayer{
    
    @Autowired
    DaoBlog daoBlog;
    
    @Autowired
    DaoBlogTags daoBlogTags;
    
    @Autowired
    DaoComment daoComment;
    
    @Autowired
    DaoTag daoTag;
    
    @Autowired
    DaoUser daoUser;

    public ServiceLayerImpl() {
    }

    public ServiceLayerImpl(DaoBlog daoBlog, DaoBlogTags daoBlogTags, DaoComment daoComment, DaoTag daoTag, DaoUser daoUser) {
        this.daoBlog = daoBlog;
        this.daoBlogTags = daoBlogTags;
        this.daoComment = daoComment;
        this.daoTag = daoTag;
        this.daoUser = daoUser;
    }

    //====Blog Pass-Through Methods====
    //--noel
    @Override
    public Blog addBlog(Blog blog){
        return daoBlog.addBlog(blog);
    }
    @Override
    public Blog getBlog(int blogID){
        return daoBlog.getBlog(blogID);
    }
    @Override
    public boolean updateBlog(Blog blog){
        return daoBlog.updateBlog(blog);
        
    }
    @Override
    public boolean removeBlog(int blogID){
        return daoBlog.removeBlog(blogID);
        
    }
    @Override
    public List<Blog> getAllBlogs(){
        return daoBlog.getAllBlogs();
    }
    @Override
    public List<Blog> getBlogsByUser(int userID){
        return daoBlog.getBlogsByUser(userID);
    }
    
    //====BlogTags Pass-Through Methods====
    
    @Override
    public BlogTags addTag(BlogTags bTag){
        return daoBlogTags.addTag(bTag);
    }
    @Override
    public boolean removeTagFromBlog(BlogTags bTag){
        return daoBlogTags.removeTagFromBlog(bTag);
        
    }
    //--noel
    @Override
    public List<Blog> getAllBlogsWithTag(int tagID){
        return daoBlogTags.getAllBlogsWithTag(tagID);
    }
    @Override
    public List<Tag> getAllTagsForBlog(int blogID){
        return daoBlogTags.getAllTagsForBlog(blogID);
    }
    
    //====Comment Pass-Through Methods====
    
    @Override
    public Comment addCommment(Comment comment){
        return daoComment.addCommment(comment);
    }
    @Override
    public Comment getComment(int commentID){
        return daoComment.getComment(commentID);
    }
    @Override
    public boolean updateComment(Comment comment){
        return daoComment.updateComment(comment);
        
    }
    @Override
    public boolean removeComment(int commentID){
       return daoComment.removeComment(commentID);
    }
    @Override
    public List<Comment> getCommentsByUser(int userID){
        return daoComment.getCommentsByUser(userID);
    }
    @Override
    public List<Comment> getCommentsByBlog(int blogID){
       return daoComment.getCommentsByBlog(blogID);
    }
    
    //====Tag Pass-Through Methods====
    
    @Override
    public Tag addTag(Tag tag){
       return daoTag.addTag(tag);
    }
    @Override
    public Tag getTag(int tagID){
        return daoTag.getTag(tagID);
    }
    @Override
    public boolean updateTag(Tag tag){
        return daoTag.updateTag(tag);
    }
    @Override
    public boolean removeTag(int tagID){
        return daoTag.removeTag(tagID);
    }
    @Override
    public List<Tag> getAllTags(){
        return daoTag.getAllTags();
    }
    
    //====User Pass-Through Methods====
    
    @Override
    public User addUser(User user){
        return daoUser.addUser(user);
    }
    @Override
    public User getUser(int user){
        return daoUser.getUser(user);
    }
    @Override
    public boolean updateUser(User user){
        return daoUser.updateUser(user);
    }
    @Override
    public boolean removeUser(int user){
        return daoUser.removeUser(user);
    }
    @Override
    public List<User> getAllUsers(){
        return daoUser.getAllUsers();
    }
    
    //=====Business Logic Methods=====
    
    //--noel
    @Override
    public List<Blog> getBlogsByVisibility(boolean visible){
        List<Blog> blogs = daoBlog.getAllBlogs();
        List<Blog> blogsByVisibility = new ArrayList<>();
        for (Blog blog : blogs){
            if(blog.isVisible()==visible){
                blogsByVisibility.add(blog);
            }
        }
        return blogsByVisibility;
    }
    
    @Override
    public boolean validUserIDForBlog(int userID1, int userID2){
//        Blog blog = getBlog(blogID);
        if(userID1 == userID2){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAccessPrivilegeAdmin(User user){
        if(user.getUserRole().equalsIgnoreCase("admin")){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean checkAccessPrivilegeAssistant(User user){
        if(user.getUserRole().equalsIgnoreCase("assistant")){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isVisible(Blog blog){
        if(blog.isVisible()){
            return true;
        }
        return false;
    }
    
}
