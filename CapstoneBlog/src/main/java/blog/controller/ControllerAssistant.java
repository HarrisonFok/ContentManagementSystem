/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.controller;

import blog.dto.Blog;
import blog.dto.User;
import blog.servicelayer.ServiceLayerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/capstone/assistant")
@ComponentScan(basePackageClasses = ServiceLayerImpl.class)
public class ControllerAssistant {
    
    @Autowired
    private ServiceLayerImpl service;

    public ControllerAssistant(ServiceLayerImpl service) {
        this.service = service;
    }
    
     @GetMapping("/get/blog")
    public Blog getBlog(int blogID){
        return service.getBlog(blogID);
    }
    
    @PostMapping("/add/blog")
    public ResponseEntity<Object> newBlog(@RequestBody Blog newBlog){
        if(service.isVisible(newBlog)){
            return ResponseHandler.generateResponse(
                    "Error: blog is visiblity is true. You do not have "
                            + "access privileges to create a visible blog",
                    HttpStatus.MULTI_STATUS, null);
        }
        return ResponseHandler.generateResponse("Successfully added blog!", HttpStatus.OK, newBlog);
    }
    
    @PutMapping("/edit/blog")
    public ResponseEntity<Object> getEditBlog(Blog blog, int userID){
        
        User user = service.getUser(userID);
        Blog originalBlog = service.getBlog(blog.getBlogID());
        
        User blogUser = service.getUser(blog.getUserID());
        
        if(service.checkAccessPrivilegeAssistant(user) &&
                service.checkAccessPrivilegeAssistant(blogUser)){
            return ResponseHandler.generateResponse(
                    "Error: user does not have access to blog",
                    HttpStatus.MULTI_STATUS, null);
        }
        
        if(originalBlog.isVisible()){
            return ResponseHandler.generateResponse(
                    "Error: blog is visible. Only admin can now edit blog",
                    HttpStatus.MULTI_STATUS, null);
        }
        service.updateBlog(blog);
        return ResponseHandler.generateResponse("Successfully edited blog!", HttpStatus.OK, blog);

    }
    
   
    
    
}
