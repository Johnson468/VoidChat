package Models;

public class Message {

	String contents;
	String sender;
	
	public String getSender() {
		return sender;
	}

	public Message(String contents, String sender) {
		super();
		this.contents = contents;
		this.sender = sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Message() {
		super();
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
