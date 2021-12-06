/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.Tag;
import java.util.List;

/**
 *
 * @author Joshua Martel
 */
public interface DaoTag {
    
    public Tag addTag(Tag tag);
    public Tag getTag(int tagID);
    public boolean updateTag(Tag tag);
    public boolean removeTag(int tagID);
    public List<Tag> getAllTags();
    
}
