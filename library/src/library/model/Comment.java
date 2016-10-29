package library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -918987044087690444L;
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private int commentId;
	private String content;
	private Date commentTime;
	private Book book;
	private int level;//1∫√∆¿  2÷–∆¿  3≤Ó∆¿
	@Id
	@GeneratedValue
	public int getCommentId() {
		return commentId;
	}

	public String getContent() {
		return content;
	}

	@ManyToOne
	public Book getBook() {
		return book;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}
