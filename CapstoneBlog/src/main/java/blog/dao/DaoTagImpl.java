/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joshua Martel
 */@Repository
public class DaoTagImpl implements DaoTag{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Tag addTag(Tag tag){
        return null;
    }
    
    @Override
    public Tag getTag(int tagID){
        return null;
    }
    
    @Override
    public boolean updateTag(Tag tag){
        return false;
        
    }
    
    @Override
    public boolean removeTag(int tagID){
        return false;
        
    }
    
    @Override
    public List<Tag> getAllTags(){
       return null; 
    }
    
}
