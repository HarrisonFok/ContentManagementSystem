/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dto;

/**
 *
 * @author Joshua Martel
 */
public class BlogTags {
    
    private int blogID;
    private int tagID;
    
    public BlogTags(){
        
    }

    public BlogTags(int blogID, int tagID) {
        this.blogID = blogID;
        this.tagID = tagID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.blogID;
        hash = 29 * hash + this.tagID;
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
        final BlogTags other = (BlogTags) obj;
        if (this.blogID != other.blogID) {
            return false;
        }
        if (this.tagID != other.tagID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlogTags{" + "blogID=" + blogID + ", tagID=" + tagID + '}';
    }
    
    
}
