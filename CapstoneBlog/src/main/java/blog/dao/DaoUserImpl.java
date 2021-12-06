/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joshua Martel
 */
@Repository
public class DaoUserImpl implements DaoUser{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public User addUser(User user){
        return null;
    }
    
    @Override
    public User getUser(int userID){
        return null;
    }
    
    @Override
    public boolean updateUser(User user){
        return false;
        
    }
    
    @Override
    public boolean removeUser(int userID){
        return false;
        
    }
    
    @Override
    public List<User> getAllUsers(){
        return null;
    }
    
}
