package hotel.controller;

import hotel.dao.impl.BillServiceDaoImpl;
import hotel.dao.impl.RequestServiceDaoImpl;
import hotel.entity.Request;
import hotel.entity.User;
import hotel.entity.enums.RoomClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
@SessionAttributes({"user"})
public class NewRequestController {
    @Autowired
    private RequestServiceDaoImpl requestDao;
    @Autowired
    private BillServiceDaoImpl billDao;

    @RequestMapping(value = "/addRequest", method = RequestMethod.POST)
    public ModelAndView addNewRequest (@ModelAttribute User user,
                                       @Param("personquantity") String personquantity,
                                       @Param("roomclass") String roomclass,
                                       @Param("start") Date start,
                                       @Param("end") Date end,
                                       HttpSession session){
        ModelAndView mv = new ModelAndView("addRequest");
        if (user!=null){
            Request req = new Request();
            req.setUser(user);
            req.setRoomClass(RoomClass.valueOf(roomclass));
            req.setPersonQuantity(Integer.parseInt(personquantity));
                req.setStart(start);
                req.setEnd(end);
            req.setProcessed(false);
            requestDao.add(req);
            session.setAttribute("user", user);
            session.setAttribute("requestList", requestDao.findAllByUserAndProcessedIsFalse(user.getId()));
            session.setAttribute("listToPay", billDao.findAllByUserAndPaidIsFalse(user.getId()));
            session.setAttribute("readyList", billDao.findAllByUserAndPaidIsTrue(user.getId()));
            mv.setViewName("userCabinet");
            return mv;
        } else {
            mv.addObject("message", "Something went wrong. This message from trying to add newRequest.");
            mv.setViewName("error");
            return mv;
        }
    }
}
