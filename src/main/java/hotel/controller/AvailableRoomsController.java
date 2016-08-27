package hotel.controller;

import hotel.dao.BillServiceDao;
import hotel.dao.RequestServiceDao;
import hotel.dao.RoomServiceDao;
import hotel.entity.Room;
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
import java.util.List;

@Controller
@SessionAttributes("user")
public class AvailableRoomsController {
    @Autowired
    private RequestServiceDao requestDao;
    @Autowired
    private BillServiceDao billDao;
    @Autowired
    private RoomServiceDao roomDao;

    @RequestMapping( value = "/findAvailableRooms", method = RequestMethod.POST)
    public ModelAndView findAvailableRooms(@ModelAttribute User user,
                                           @Param("roomclass") String roomclass,
                                           @Param("available") String available,
                                           HttpSession session){
        ModelAndView mv = new ModelAndView();
        List<Room> listAvailable =  roomDao.findAllByRoomclassAndAvailable(RoomClass.valueOf(roomclass), Integer.parseInt(available));

        session.setAttribute("user", user);
        session.setAttribute("listAvailable", listAvailable);
        session.setAttribute("listToProcess", requestDao.findAllByProcessedIsFalse());
        session.setAttribute("readyList", billDao.findAllByPaidIsTrue());
        mv.setViewName("adminCabinet");
        return mv;
    }
}
