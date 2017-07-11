package Model;

import java.util.Date;

import org.bson.Document;

public class AssortDocument {
	private Document document;
	
    private String title;
	
    private String type;
    
    private String source;
    
	private String content;
	
	private String date;
	
	private Date insertDate;
	
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
		document.append("insertDate", insertDate);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
		document.append("source", source);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		document.append("type", type);
	}

	
}
