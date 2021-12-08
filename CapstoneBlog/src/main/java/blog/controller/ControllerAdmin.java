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
import org.springframework.http.HttpStatus;
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
        if(!service.getAllTagsForBlog(blogID).contains(service.getTag(tagID))) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.removeTagFromBlog(new BlogTags(blogID, tagID))) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @GetMapping("/tag/get/all/tagsforblog")
    public List<Tag> getAllTagsForBlog(int blogID){
        return service.getAllTagsForBlog(blogID);
    }
    
    @PostMapping("/tag/add/blogTag")
    public BlogTags addTag(@RequestBody BlogTags bTag){
        if(service.getAllTags().contains(service.getTag(bTag.getTagID()))
                && service.getAllBlogs().contains(service.getBlog(bTag.getBlogID())))   {
            return service.addTag(bTag);
        }
        return null;
    }

    @PostMapping("/tag/add/tag")
    public Tag addHashTag(@RequestBody Tag tag){
        return service.addTag(tag);
    }

    
    @GetMapping("/tag/get")
    public Tag getTag(int tagID){
        return service.getTag(tagID);
    }
    
    @PutMapping("/tag/update")
    public ResponseEntity<Object> updateTag(@RequestBody Tag tag){
        if(!service.getAllTags().contains(tag)) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.updateTag(tag)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @DeleteMapping("/tag/delete")
    public ResponseEntity<Object> deleteTag(int tagID){
        if(!service.getAllTags().contains(service.getTag(tagID))) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.removeTag(tagID)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @GetMapping("/tag/get/all")
    public List<Tag> getAllTags(){
        return service.getAllTags();
    }
    
    //===== User Related Methods =====
    
    @PostMapping("/user/add")
    public User addUser(@RequestBody User user){
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        newUser.setUserPassword(user.getUserPassword());
        newUser.setUserRole(user.getUserRole());
        return service.addUser(newUser);
    }
    
    @GetMapping("/user/get")
    public User getUser(int user){
        return service.getUser(user);
    }
    
    @PutMapping("/user/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        service.updateUser(user);
        return ResponseHandler.generateResponse("Successfully updated user!", HttpStatus.CREATED, user);
    }
    
    @DeleteMapping("/user/delete")
    public ResponseEntity<Object> removeUser(int userID){
        if (service.removeUser(userID)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/user/get/all")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }
    
}
