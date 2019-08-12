package dev.ulman.dms.security;

import dev.ulman.dms.dao.UserDao;
import dev.ulman.dms.dao.UserDaoImp;
import dev.ulman.dms.model.User;
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
