/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dto;

import java.util.Objects;

/**
 *
 * @author Joshua Martel
 */
public class Comment {
    
    private int commentID;
    private String userComment;
    private int blogID;
    private int userID;
    
    public Comment(){
        
    }

    public Comment(int commentID, String userComment, int blogID, int userID) {
        this.commentID = commentID;
        this.userComment = userComment;
        this.blogID = blogID;
        this.userID = userID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }
    
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.commentID;
        hash = 37 * hash + Objects.hashCode(this.userComment);
        hash = 37 * hash + this.blogID;
        hash = 37 * hash + this.userID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.commentID != other.commentID) {
            return false;
        }
        if (this.blogID != other.blogID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (!Objects.equals(this.userComment, other.userComment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentID=" + commentID + ", userComment=" + userComment + ", blogID=" + blogID + ", userID=" + userID + '}';
    }

    
    
    
}
