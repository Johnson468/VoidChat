package Utilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.NewsArticle;

public interface NewsManager {
	
	public static ArrayList<NewsArticle> getArticles() {
		ArrayList<NewsArticle> articles = new ArrayList<NewsArticle>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = ConnectionManager.makeConnection();
		ArrayList<String> comments = new ArrayList<String>();
		Date dateAdded;
		int id;
		String message;
		
		try {
		    stmt = conn.createStatement();
		    //Query all articles
		    rs = stmt.executeQuery("SELECT id FROM news_articles;");
		    if (!rs.next()) {
		    	return null;
		    }
		    // Go through the results and add articles to the collection
		    while(rs.next()) {
		    	id=rs.getInt("id");
		    	comments.addAll(getComments(id));
		    	dateAdded = rs.getDate("date_added");
		    	message = rs.getString("message");
		    	articles.add(new NewsArticle(message, comments, dateAdded, id));
		    	comments.removeAll(getComments(id));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return articles;
	}
	/**
	 * Shouldn't be used anywhere but in getArticles
	 * @param id
	 * @return
	 */
	public static ArrayList<String> getComments(int id) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = ConnectionManager.makeConnection();
		String comment;
		ArrayList<String> comments = new ArrayList<String>();
		try {
		    stmt = conn.createStatement();
		    //Query all rooms in the database
		    rs = stmt.executeQuery("SELECT id FROM news_comments WHERE atricle_id = " + String.valueOf(id) + ";");
		    if (rs.next() == false) {
		    	return null;
		    }
		    // Go through the results and see if the session has the chatrooms or not
		    while(rs.next()) {
		    	comment=rs.getString("article_id");
		    	comments.add(comment);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return comments;
	}
}
