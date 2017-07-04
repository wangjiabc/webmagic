package Model;

import java.util.Date;

import org.bson.Document;

public class AssortDocument {
	private Document document;
	
    private String title;
	
	private String content;
	
	private Date date;
	
	private String path;
	
	public AssortDocument() {
		// TODO Auto-generated constructor stub		
		document=new Document();
	}
	
	public Document getDocument() {
		return document;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		document.append("title", title);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		document.append("content", content);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		document.append("date", date);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		document.append("path", path);
	}

	
}
