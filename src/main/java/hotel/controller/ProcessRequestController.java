package hotel.controller;

import hotel.dao.BillServiceDao;
import hotel.dao.RequestServiceDao;
import hotel.dao.RoomServiceDao;
import hotel.entity.Bill;
import hotel.entity.Room;
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
@SessionAttributes("user")
public class ProcessRequestController {
    @Autowired
    private RequestServiceDao requestDao;
    @Autowired
    private BillServiceDao billDao;
    @Autowired
    private RoomServiceDao roomDao;

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public ModelAndView process(@ModelAttribute User user,
                                @Param("number") String number,
                                @Param("id") String id,
                                HttpSession session){
        ModelAndView mv = new ModelAndView();

        if (roomDao.exist(Integer.parseInt(number))) {
            requestDao.updateProcessedToTrue(Integer.parseInt(id));
            Bill bill = new Bill();
            bill.setRequest(requestDao.findById(Integer.parseInt(id)));
            Room room = roomDao.findByNumber(Integer.parseInt(number));
            room.setAvailable(room.getAvailable() - bill.getRequest().getPersonQuantity());
            roomDao.edit(room);
            bill.setRoom(room);
            int dayDiff = (int) (bill.getRequest().getEnd().getTime() - bill.getRequest().getStart().getTime()) / (1000*60*60*24);
            bill.setCost(bill.getRequest().getPersonQuantity() * bill.getRoom().getCostPerPerson() * dayDiff);
            billDao.add(bill);

            session.setAttribute("user", user);
            session.setAttribute("listToProcess", requestDao.findAllByProcessedIsFalse());
            session.setAttribute("readyList", billDao.findAllByPaidIsTrue());
            mv.setViewName("adminCabinet");
            return mv;
        } else{
            mv.addObject("message", "You have entered room number which is not exist.");
            mv.setViewName("error");
            return mv;
        }
    }
}
