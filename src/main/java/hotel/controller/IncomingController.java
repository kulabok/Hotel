package hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kulabok on 12.06.2016.
 */
@Controller
@RequestMapping("/")
public class IncomingController {

    public String execute(){
        return "index";
    }
}
