/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.User;
import java.util.List;

/**
 *
 * @author Joshua Martel
 */
public interface DaoUser {
    
    public User addUser(User user);
    public User getUser(int user);
    public boolean updateUser(User user);
    public boolean removeUser(int user);
    public List<User> getAllUsers();
    
}
