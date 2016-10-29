package library.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RecommandBook {
	private int recommandBookId;
	private String bookName;
	private String bookAuthor;
	private String reason;
	private String press;
	private String publishTime;
	private Date recommandTime;
	private User recomandUser;

	@Id
	@GeneratedValue
	public int getRecommandBookId() {
		return recommandBookId;
	}

	public void setRecommandBookId(int recommandBookId) {
		this.recommandBookId = recommandBookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Date getRecommandTime() {
		return recommandTime;
	}

	public void setRecommandTime(Date recommandTime) {
		this.recommandTime = recommandTime;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getRecomandUser() {
		return recomandUser;
	}

	public void setRecomandUser(User recomandUser) {
		this.recomandUser = recomandUser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
