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
import org.springframework.dao.EmptyResultDataAccessException;
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
    // -- noel
    
    
    //works
    @PostMapping("/blog/new")
    public ResponseEntity<Object> addBlog(@RequestBody Blog newBlog){
        newBlog = service.addBlog(newBlog);
        return   ResponseHandler.generateResponse("Successfully added blog!", HttpStatus.OK, newBlog);
    }
    
    //works
    @GetMapping("/blog/get/{blogID}")
    public Blog getBlog(@PathVariable int blogID){
        return service.getBlog(blogID);
    }
    
    //works
    @PutMapping("/blog/update/{blogID}")
    public ResponseEntity<Object> updateBlog(@PathVariable int blogID,
            @RequestBody Blog blog){
            blog.setBlogID(blogID);
            boolean blogUpdated = service.updateBlog(blog);
            if(blogUpdated){
                return   ResponseHandler.generateResponse("Successfully edited blog!", HttpStatus.OK, blog);
            }
            else{
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        
    }
    
    //works
    @DeleteMapping("/blog/delete/{blogID}")
    public ResponseEntity<Object> deleteBlog(@PathVariable int blogID){
        boolean blogRemoved = service.removeBlog(blogID);
        if(blogRemoved){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    //works
    @GetMapping("/blog/get/blogs/all")
    public List<Blog> getAllBlogs(){
        return service.getAllBlogs();
    }
    
    //works
    @GetMapping("/blog/get/blogs/all/byuser/{userID}")
    public List<Blog> getBlogsByUser(@PathVariable int userID){
        return service.getBlogsByUser(userID);
    }
    
    //works
    @GetMapping("/blog/get/blogs/all/byvisibility/{visible}")
    public List<Blog> getBlogsByVisibility(@PathVariable boolean visible){
        return service.getBlogsByVisibility(visible);
    }
    
    //works
    @GetMapping("/blog/get/blogs/all/bytag/{tagID}")
    public List<Blog> getAllBlogsWithTag(@PathVariable int tagID){
        return service.getAllBlogsWithTag(tagID);
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
    
    @PostMapping("/tag/get/all/tagsforblog")
    public List<Tag> getAllTagsForBlog(int blogID){
        return service.getAllTagsForBlog(blogID);
    }
    
    @PostMapping("/tag/add/blogTag")
    public ResponseEntity<Object> addTag(@RequestBody BlogTags bTag){
        try{
                if(service.getAllTags().contains(service.getTag(bTag.getTagID()))
                    && service.getAllBlogs().contains(service.getBlog(bTag.getBlogID())))   {
                    service.addTag(bTag);
                    return ResponseHandler.generateResponse("Successfully added tag to blog!", HttpStatus.OK, bTag); 
            }   
        }catch(EmptyResultDataAccessException e){
            return ResponseHandler.generateResponse(
                                "Error: tag or blog is not in system",
                                HttpStatus.MULTI_STATUS, null);
        }
        
        return ResponseHandler.generateResponse(
                    "Error: blog already has tag",
                    HttpStatus.MULTI_STATUS, null);
    }
    

    @PostMapping("/tag/add/tag")
    public Tag addHashTag(@RequestBody Tag tag){
        return service.addTag(tag);
    }

    
    @PostMapping("/tag/get/{tagID}")
    public Tag getTag(@PathVariable int tagID){
        return service.getTag(tagID);
    }
    
    @PutMapping("/tag/update")
    public ResponseEntity<Object> updateTag(@RequestBody Tag tag){
        if(!service.getAllTags().contains(service.getTag(tag.getTagID()))) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.updateTag(tag)) {
            return ResponseHandler.generateResponse("Successfully updated tag!", HttpStatus.OK, tag);
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
    public User getUser(int userID){
        return service.getUser(userID);
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
