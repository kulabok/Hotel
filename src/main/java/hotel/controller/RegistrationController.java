package hotel.controller;

import hotel.dao.impl.RequestServiceDaoImpl;
import hotel.dao.impl.UserServiceDaoImpl;
import hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kulabok on 12.06.2016.
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserServiceDaoImpl userDao;

    @Autowired
    private RequestServiceDaoImpl requestDao;

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public ModelAndView register (@Param("fullname") String fullname,
                                  @Param("login") String login,
                                  @Param("password") String password){
        ModelAndView mv = new ModelAndView();
        User u = userDao.exist(login, password);
        if (u==null) {
            User user = new User();
            user.setFullName(fullname);
            user.setLogin(login);
            user.setPassword(password);
            user.setAdmin(false);
            if (userDao.add(user)) {
                mv.addObject("user", user);
                mv.setViewName("userCabinet");
                return mv;
            } else {
                mv.setViewName("error");
                mv.addObject("message", "Something went wrong. You haven't been registered.");
                return mv;
            }
        } else {
            mv.setViewName("error");
            mv.addObject("message", "Something went wrong. You have already been registered. Try to login");
            return mv;
        }
    }

    /*@RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String register (
            @Param("fullname") String fullName,
            @Param("login") String login,
            @Param("password") String password){
        User user = new User();
        user.setFullName(fullName);
        user.setLogin(login);
        user.setPassword(password);
        user.setAdmin(false);
        if (userDao.add(user)!=null){
            return "userCabinet";
        } else {
            return "error";
        }
    }*/
}
