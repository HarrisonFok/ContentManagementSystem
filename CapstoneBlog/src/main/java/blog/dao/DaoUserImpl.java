/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.dao;

import blog.dto.Blog;
import blog.dto.User;
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
//        System.out.println(user.getUserName());
        final String sql = "INSERT INTO Users (firstName, lastName, userName, userPassword, userRole) "
                + "VALUES (?,?,?,?,?)";
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update((Connection conn) -> {
            PreparedStatement pState = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pState.setString(1, user.getFirstName());
            pState.setString(2, user.getLastName());
            pState.setString(3, user.getUserName());
            pState.setString(4, user.getUserPassword());
            pState.setString(5, user.getUserRole());

            return pState;
        }, key);
        user.setUserID(key.getKey().intValue());
        return user;
    }
    
    //Get user
    @Override
    public User getUser(int userID){
        final String sql = "SELECT * FROM Users WHERE userID = ?";
        return jdbc.queryForObject(sql, new UserMapper(), userID);
    }
    
    
    @Override
    public boolean updateUser(User user){
        final String sql = "UPDATE Users SET "
                + "firstName = ?,"
                + "lastName = ?,"
                + "userName = ?,"
                + "userPassword = ?,"
                + "userRole = ?"
                + " WHERE userID = ?";
        return jdbc.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserRole(),
                user.getUserID()) > 0;
        
    }
    
    @Override
    public boolean removeUser(int userID){
        final String sql = "DELETE FROM Users WHERE userID = ?";
        return jdbc.update(sql, userID) > 0 ;
        
    }
    
    @Override
    public List<User> getAllUsers(){
        final String sql = "SELECT * FROM Users";
        return jdbc.query(sql, new UserMapper());
    }
    
    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserID(rs.getInt("userID"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setUserName(rs.getString("userName"));
            user.setUserPassword(rs.getString("userPassword"));
            user.setUserRole(rs.getString("userRole"));
            
            return user;
        }
        
    }
}
