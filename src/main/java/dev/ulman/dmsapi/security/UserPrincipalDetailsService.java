package dev.ulman.dmsapi.security;

import dev.ulman.dmsapi.dao.UserDao;
import dev.ulman.dmsapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserPrincipalDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userDao.getUserByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return  userPrincipal;
    }
}
