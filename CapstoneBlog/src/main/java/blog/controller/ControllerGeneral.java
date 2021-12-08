package blog.controller;

import blog.dto.Comment;
import blog.dto.User;
import blog.servicelayer.ServiceLayerImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Harrison Fok
 */
@RestController
@RequestMapping("/capstone/general")
@ComponentScan(basePackageClasses = ServiceLayerImpl.class)
public class ControllerGeneral {
    
    @Autowired
    private ServiceLayerImpl service;

    public ControllerGeneral(ServiceLayerImpl service) {
        this.service = service;
    }
    
    //===== Comments Related Methods =====
    
    @PostMapping("/comment/new")
    public Comment addComment(@RequestBody Comment comment){
        Comment newComment = new Comment();
        newComment.setUserComment(comment.getUserComment());
        newComment.setBlogID(comment.getBlogID());
        newComment.setUserID(comment.getUserID());
        return service.addCommment(newComment);
    }
    
    @GetMapping("/comment")
    public Comment getComment(int commentID){
        return service.getComment(commentID);
    }
    
    @PutMapping("/comment/update")
    public ResponseEntity<Object> updateComment(@RequestBody Comment comment){
        service.updateComment(comment);
        return ResponseHandler.generateResponse("Successfully updated user!", HttpStatus.CREATED, comment);
    }
    
    @DeleteMapping("/comment/delete")
    public ResponseEntity<Object> removeComment(int commentID){
        if (service.removeComment(commentID)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/comment/byUser")
    public List<Comment> getCommentsByUser(int userID){
        return service.getCommentsByUser(userID);
    }
    
    @GetMapping("/comment/byBlog")
    public List<Comment> getCommentsByBlog(int blogID){
        return service.getCommentsByBlog(blogID);
    }
    
    //===== Likes Related Methods =====
}
