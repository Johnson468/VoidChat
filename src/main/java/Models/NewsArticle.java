package Models;

import java.util.ArrayList;
import java.util.Date;

public class NewsArticle {
	String message;
	ArrayList<String> comments;
	Date addedDate;
	int articleId;
	public NewsArticle(String message, ArrayList<String> comments, Date addedDate, int articleId) {
		super();
		this.message = message;
		this.comments = comments;
		this.addedDate = addedDate;
		this.articleId = articleId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public String getArticle() {
		return message;
	}
	public void setArticle(String article) {
		this.message = article;
	}
	public ArrayList<String> getComments() {
		return comments;
	}
	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}
	
}
