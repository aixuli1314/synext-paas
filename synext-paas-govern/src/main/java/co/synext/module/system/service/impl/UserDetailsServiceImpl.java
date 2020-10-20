package co.synext.module.system.service.impl;

import co.synext.common.utils.SpringContextHolder;
import co.synext.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author xu.ran
 * @date 2020/4/21 19:54
 * @description: TODO
 */
@Service(value = "UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    //@Autowired //SpringContextHolder.getBean(UserServiceImpl.class)
    //private IUserService userService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SpringContextHolder.getBean(IUserService.class).login(username);
    }

}
