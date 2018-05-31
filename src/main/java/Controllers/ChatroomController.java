package Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Models.Chatroom;
import Models.Message;
import Utilities.ChatroomManager;

@Controller
public class ChatroomController {
ChatroomManager crm = new ChatroomManager();
   //Need to add a function to increment the number of people in the room when someone joins, and the inverse when someone leaves.
    @RequestMapping("/byId")
    public String Chat(@RequestParam(name="roomId", required=true, defaultValue="-1")int roomId, Model model, HttpSession session) {
    	//crm.updateRooms();
    	Chatroom chatroom = crm.getRoomById(roomId);
    	//If chatroom doesn't exist
    	if(chatroom == null) {
    		model.addAttribute("sentback",true);
    		model.addAttribute("chatroom",new Chatroom());
    		return "home";
    		//If the chatroom is full
    	} else if (chatroom.getMaxMembers() == chatroom.getCurrentMembers()) {
    		model.addAttribute("chatroom",new Chatroom());
    		model.addAttribute("isFull",true);
    		return "home";
    	}
    	//If the chatroom exists and isn't full
    	crm.addMember(chatroom.getRoomId());
        model.addAttribute("chatroom", chatroom);
        session.setAttribute("chatroom", chatroom);
        model.addAttribute("message",new Message());
        return "chatroom";
    }
    @RequestMapping("/create")
    public String create(Model model) {
    	model.addAttribute(new Chatroom());
    	return "create";
    }
    @RequestMapping("/createRoom")
    public String createRoom(@RequestParam(name="maxMembers", required=true, defaultValue="-1")int maxSize, Model model, HttpSession session) {
    	Chatroom newRoom = crm.createChatroom(maxSize);
    	model.addAttribute("chatroom",newRoom);
    	model.addAttribute("message", new Message());
    	session.removeAttribute("roomManager");
    	//session.setAttribute("roomManager", crm);
    	session.setAttribute("chatroom", newRoom);
    	return "chatroom";
    }
    @RequestMapping("/leave")
    public String leave(Model model, HttpSession session, Chatroom cr) {
    	Chatroom myRoom = (Chatroom) session.getAttribute("chatroom");
    	if (myRoom.getCurrentMembers() == 0) {
    		crm.deleteRoom(myRoom.getRoomId());
    	}
    	return "home";
    }
    @RequestMapping("/sendmessage")
    public String send(Model model, HttpSession session, Message message, Chatroom cr) {
    	Chatroom myRoom = (Chatroom) session.getAttribute("chatroom");
    	crm.sendMessage(myRoom.getRoomId(),message.getContents());
    	model.addAttribute("chatroom",myRoom);
    	return "chatroom";
    }
}