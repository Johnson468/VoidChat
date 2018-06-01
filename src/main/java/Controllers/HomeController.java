package Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Models.Chatroom;
import Utilities.ChatroomManager;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
    	model.addAttribute("sentBack",false);
    	model.addAttribute(new Chatroom());
    	ChatroomManager crm = new ChatroomManager();
    	//crm.updateRooms();
    	session.setAttribute("roomManager", crm);
        return "home";
    }
    @PostMapping("/")
    public String postHome(Model model, HttpSession session) {
    	model.addAttribute("sentBack",false);
    	model.addAttribute(new Chatroom());
    	ChatroomManager crm = new ChatroomManager();
    	//crm.updateRooms();
    	session.setAttribute("roomManager", crm);
        return "home";
    }
    
}