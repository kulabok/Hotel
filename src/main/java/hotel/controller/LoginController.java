package hotel.controller;

import hotel.dao.BillServiceDao;
import hotel.dao.RequestServiceDao;
import hotel.dao.UserServiceDao;
import hotel.dao.impl.BillServiceDaoImpl;
import hotel.dao.impl.RequestServiceDaoImpl;
import hotel.dao.impl.RoomServiceDaoImpl;
import hotel.dao.impl.UserServiceDaoImpl;
import hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by kulabok on 12.06.2016.
 */
@Controller
public class LoginController {
    @Autowired
    private UserServiceDao userDao;
    @Autowired
    private RequestServiceDao requestDao;
    @Autowired
    private BillServiceDao billDao;

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public ModelAndView login(
            @Param("login") String login,
            @Param("password") String password,
            HttpSession session){
        ModelAndView mv = new ModelAndView();
        User user = userDao.exist(login, password);
        if (user!=null && !user.isAdmin()){
            user = userDao.findByLoginAndPassword(login, password);
            session.setAttribute("user", user);
            session.setAttribute("requestList", requestDao.findAllByUserAndProcessedIsFalse(user.getId()));
            session.setAttribute("listToPay", billDao.findAllByUserAndPaidIsFalse(user.getId()));
            session.setAttribute("readyList", billDao.findAllByUserAndPaidIsTrue(user.getId()));
            mv.setViewName("userCabinet");
            return mv;
        } else if (user!=null && user.isAdmin()){
            user = userDao.findByLoginAndPassword(login, password);
            session.setAttribute("user", user);
            session.setAttribute("listToProcess", requestDao.findAllByProcessedIsFalse());
            session.setAttribute("readyList", billDao.findAllByPaidIsTrue());
            mv.setViewName("adminCabinet");
            return mv;
        } else {
            mv.setViewName("error");
            mv.addObject("message", "You have no active profile. Please <a href = \"registration.jsp\">register</a> first.");
            return mv;
        }
    }
}