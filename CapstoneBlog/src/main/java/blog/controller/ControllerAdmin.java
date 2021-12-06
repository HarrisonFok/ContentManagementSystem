/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.controller;

import blog.dto.Blog;
import blog.dto.BlogTags;
import blog.dto.Tag;
import blog.dto.User;
import blog.servicelayer.ServiceLayerImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joshua Martel
 */
@RestController
@RequestMapping("/capstone/admin")
@ComponentScan(basePackageClasses = ServiceLayerImpl.class)
public class ControllerAdmin {
    
    @Autowired
    private ServiceLayerImpl service;

    public ControllerAdmin(ServiceLayerImpl service) {
        this.service = service;
    }
    
    //===== Blog Related Methods =====
    
    @PostMapping("/blog/new")
    public ResponseEntity<Object> addBlog(@RequestBody Blog newBlog){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/blog/get/{blogID}")
    public Blog getBlog(@PathVariable int blogID){
        throw new UnsupportedOperationException();
    }
    
    @PutMapping("/blog/update/{blogID}")
    public ResponseEntity<Object> updateBlog(@PathVariable int blogID,
            @RequestBody Blog blog){
        throw new UnsupportedOperationException();
    }
    
    @DeleteMapping("/blog/delete/{blogID}")
    public ResponseEntity<Object> deleteBlog(@PathVariable int blogID){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/blog/get/blogs/all")
    public List<Blog> getAllBlogs(){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/blog/get/blogs/all/byuser/{userID}")
    public List<Blog> getBlogsByUser(@PathVariable int userID){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/blog/get/blogs/all/byvisibility/{visible}")
    public List<Blog> getBlogsByVisibility(@PathVariable boolean visible){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/blog/get/blogs/all/bytag")
    public List<Blog> getAllBlogsWithTag(int tagID){
        throw new UnsupportedOperationException();
    }
    
    
    //===== Tag Related Methods =====
    
    @DeleteMapping("/tag/delete/blogtag")
    public ResponseEntity<Object> removeTagFromBlog(int blogID, int tagID){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/tag/get/all/tagsforblog")
    public List<Tag> getAllTagsForBlog(int blogID){
        throw new UnsupportedOperationException();
    }
    
    @PutMapping("/tag/add")
    public BlogTags addTag(@RequestBody BlogTags bTag){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/tag/get")
    public Tag getTag(int tagID){
        throw new UnsupportedOperationException();
    }
    
    @PutMapping("/tag/update")
    public ResponseEntity<Object> updateTag(@RequestBody Tag tag){
        throw new UnsupportedOperationException();
    }
    
    @DeleteMapping("/tag/delete")
    public ResponseEntity<Object> deleteTag(int tagID){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/tag/get/all/tags")
    public List<Tag> getAllTags(){
        throw new UnsupportedOperationException();
    }
    
    //===== User Related Methods =====
    
    @PostMapping("/user/add")
    public User addUser(@RequestBody User user){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/user/get")
    public User getUser(int user){
        throw new UnsupportedOperationException();
    }
    
    @PutMapping("/user/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        throw new UnsupportedOperationException();
    }
    
    @DeleteMapping("/user/delete")
    public ResponseEntity<Object> removeUser(int userID){
        throw new UnsupportedOperationException();
    }
    
    @GetMapping("/user/get/all")
    public List<User> getAllUsers(){
        throw new UnsupportedOperationException();
    }
    
}
