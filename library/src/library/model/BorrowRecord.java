package library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BorrowRecord implements Serializable {
	private static final long serialVersionUID = -9128722099616977967L;
	private int borrowRecordId;
	private Date borrowTime;
	private Date returnTime;
	private Book book;
	private User borrowUser;
	private User lendAdmin; // 借出操作人
	private User returnAdmin;// 归还操作人
	private int renewCount;// 续借次数

	@Id
	@GeneratedValue
	public int getBorrowRecordId() {
		return borrowRecordId;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	@ManyToOne
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setBorrowRecordId(int borrowRecordId) {
		this.borrowRecordId = borrowRecordId;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public User getBorrowUser() {
		return borrowUser;
	}

	public void setBorrowUser(User borrowUser) {
		this.borrowUser = borrowUser;
	}

	public User getLendAdmin() {
		return lendAdmin;
	}

	public void setLendAdmin(User lendAdmin) {
		this.lendAdmin = lendAdmin;
	}

	public User getReturnAdmin() {
		return returnAdmin;
	}

	public void setReturnAdmin(User returnAdmin) {
		this.returnAdmin = returnAdmin;
	}

	public int getRenewCount() {
		return renewCount;
	}

	public void setRenewCount(int renewCount) {
		this.renewCount = renewCount;
	}

}
