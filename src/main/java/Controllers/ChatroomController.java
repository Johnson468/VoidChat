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
import Models.User;
import Utilities.ChatroomManager;
import Utilities.Crypto;
import Utilities.MessageManager;
import Utilities.Users;

@Controller
public class ChatroomController {
ChatroomManager crm = new ChatroomManager();
MessageManager mm = new MessageManager();
   //Need to add a function to increment the number of people in the room when someone joins, and the inverse when someone leaves.
    @RequestMapping("/byId")
    public String Chat(@RequestParam(name="roomId", required=true, defaultValue="-1")int roomId, Model model, HttpSession session) {
    	//crm.updateRooms();
    	Chatroom chatroom = crm.getRoomById(roomId);
    	String userName = Users.generateUsername();
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
    	User user = new User(userName);
        model.addAttribute("chatroom", chatroom);
        session.setAttribute("chatroom", chatroom);
        model.addAttribute("message",new Message());
        session.setAttribute("user", user);
        model.addAttribute("user", user);
        model.addAttribute("messages",mm.getMessages(chatroom.getRoomId()));
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
    	String username = Users.generateUsername();
    	User user = new User(username);
    	model.addAttribute("chatroom",newRoom);
    	model.addAttribute("message", new Message());
    	model.addAttribute("user",user);
    	session.removeAttribute("roomManager");
    	session.setAttribute("user", user);
    	//session.setAttribute("roomManager", crm);
    	session.setAttribute("chatroom", newRoom);
    	return "chatroom";
    }
    @RequestMapping("/leave")
    public String leave(Model model, HttpSession session, Chatroom cr) {
    	Chatroom myRoom = (Chatroom) session.getAttribute("chatroom");
    	myRoom = crm.getRoomById(myRoom.getRoomId());
    	crm.removeMember(myRoom.getRoomId());
    	return "home";
    }
    @RequestMapping("/sendmessage")
    public String send(Model model, HttpSession session, Message message, Chatroom cr) {
    	Chatroom myRoom = (Chatroom) session.getAttribute("chatroom");
    	User user = (User) session.getAttribute("user");
    	String messageContents = message.getContents();
    	
    	
    	crm.sendMessage(myRoom.getRoomId(),message.getContents(), user.getUserName());
    	model.addAttribute("user", user);
    	model.addAttribute("chatroom",myRoom);
    	model.addAttribute("messages",mm.getMessages(myRoom.getRoomId()));
    	return "chatroom";
    }
}