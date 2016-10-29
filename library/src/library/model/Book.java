package library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = -2273196650378585596L;
	private int bookId;
	private String bookName;
	private Date publishTime;
	private String press;// 出版社
	private String author;
	private BookType bookType;
	private Date exportTime;
	private Date importTime;
	private int count;
	private boolean hasCard;// 是否拥有光盘
	private boolean isOnline;// 是否上架
	private int borrowedCount;// 被借阅次数

	@Id
	@GeneratedValue
	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	@ManyToOne
	public BookType getBookType() {
		return bookType;
	}

	public int getCount() {
		return count;
	}

	public String getAuthor() {
		return author;
	}

	public Date getExportTime() {
		return exportTime;
	}

	public Date getImportTime() {
		return importTime;
	}

	public String getPress() {
		return press;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public boolean isHasCard() {
		return hasCard;
	}

	public void setHasCard(boolean hasCard) {
		this.hasCard = hasCard;
	}

	public int getBorrowedCount() {
		return borrowedCount;
	}

	public void setBorrowedCount(int borrowedCount) {
		this.borrowedCount = borrowedCount;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

}
