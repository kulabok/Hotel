package hotel.controller;

import hotel.dao.BillServiceDao;
import hotel.dao.RequestServiceDao;
import hotel.dao.impl.BillServiceDaoImpl;
import hotel.dao.impl.RequestServiceDaoImpl;
import hotel.dao.impl.RoomServiceDaoImpl;
import hotel.entity.Bill;
import hotel.entity.Request;
import hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"user"})
public class PayController {
    @Autowired
    private RequestServiceDao requestDao;
    @Autowired
    private BillServiceDao billDao;


    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ModelAndView pay(@ModelAttribute User user,
                            @Param("bill") Bill bill,
                            HttpSession session
                            ){
        ModelAndView mv = new ModelAndView();
        if (billDao.exist(bill.getId())){
            bill = billDao.findById(bill.getId());
            billDao.setPaidTrue(bill);

            session.setAttribute("user", user);
            session.setAttribute("requestList", requestDao.findAllByUserAndProcessedIsFalse(user.getId()));
            //TODO when processing one from list all data is lost in the next page. :(
            session.setAttribute("listToPay", billDao.findAllByUserAndPaidIsFalse(user.getId()));
            session.setAttribute("readyList", billDao.findAllByUserAndPaidIsTrue(user.getId()));
            mv.setViewName("userCabinet");
        return mv;
        } else {
            mv.addObject("message", "You have a mistake while trying to pay your request.");
            mv.setViewName("error");
            return mv;
        }
    }
}
