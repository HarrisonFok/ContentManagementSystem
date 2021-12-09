/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Joshua Martel
 */@Repository
public class DaoTagImpl implements DaoTag{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Tag addTag(Tag tag){
        final String sql = "INSERT INTO Tags (hashTag) VALUES (?)";
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update((Connection conn) -> {
            PreparedStatement pState = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pState.setString(1, tag.getHashTag());

            return pState;
        }, key);
        tag.setTagID(key.getKey().intValue());
        return tag;
    }
    
    //Get tag
    @Override
    public Tag getTag(int tagID){
        final String sql = "SELECT * FROM Tags WHERE tagID = ?";
        return jdbc.queryForObject(sql, new TagMapper(), tagID);
    }
    
    //Update tag
    @Override
    public boolean updateTag(Tag tag){
       final String sql = "UPDATE Tags SET "
               + "hashTag = ?"
               + "WHERE tagID = ?";
       return jdbc.update(sql, tag.getHashTag(), tag.getTagID()) > 0;
        
    }
    
    @Override
    @Transactional
    public boolean removeTag(int tagID){
        final String DELETE_BLOG_TAG = "DELETE FROM blogTags "
                + "WHERE tagID = ?";
        jdbc.update(DELETE_BLOG_TAG, tagID);

        final String sql = "DELETE FROM Tags WHERE tagID = ?";
        return jdbc.update(sql, tagID) > 0;
        
    }
    
    @Override
    public List<Tag> getAllTags(){
       final String sql = "SELECT * FROM Tags";
       return jdbc.query(sql, new TagMapper());
    }
    
    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag();
            tag.setTagID(rs.getInt("tagID"));
            tag.setHashTag(rs.getString("hashTag"));
            
            return tag;
        }
        
    }
}
