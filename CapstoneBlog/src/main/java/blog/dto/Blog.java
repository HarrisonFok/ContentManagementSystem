/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Joshua Martel
 */
public class Blog {
    
    private int blogID;
    private String title;
    private String content;
    private int userID;
    private boolean visible;
    private LocalDate datePosted;
    private LocalDate dateExpires;
    private int likes;
    private int dislikes;
    
    public Blog(){
        
    }

    public Blog(int blogID, String title, String content, int userID, boolean visible, LocalDate datePosted, LocalDate dateExpires, int likes, int dislikes) {
        this.blogID = blogID;
        this.title = title;
        this.content = content;
        this.userID = userID;
        this.visible = visible;
        this.datePosted = datePosted;
        this.dateExpires = dateExpires;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public LocalDate getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(LocalDate dateExpires) {
        this.dateExpires = dateExpires;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.blogID;
        hash = 61 * hash + Objects.hashCode(this.title);
        hash = 61 * hash + Objects.hashCode(this.content);
        hash = 61 * hash + this.userID;
        hash = 61 * hash + (this.visible ? 1 : 0);
        hash = 61 * hash + Objects.hashCode(this.datePosted);
        hash = 61 * hash + Objects.hashCode(this.dateExpires);
        hash = 61 * hash + this.likes;
        hash = 61 * hash + this.dislikes;
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
        final Blog other = (Blog) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogID=" + blogID + ", title=" + title + ", content=" + content + ", userID=" + userID + ", visible=" + visible + ", datePosted=" + datePosted + ", dateExpires=" + dateExpires + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }

    
    
}
