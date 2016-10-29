package library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BookType  implements Serializable{
	private static final long serialVersionUID = -405902853671143184L;
	private int bookTypeId;
	private String bookTypeName;
	private BookType parent;
	private List<BookType> children;

	@Id
	@GeneratedValue
	public int getBookTypeId() {
		return bookTypeId;
	}

	public String getBookTypeName() {
		return bookTypeName;
	}

	@ManyToOne
	public BookType getParent() {
		return parent;
	}

	@OneToMany(mappedBy = "parent")
	public List<BookType> getChildren() {
		return children;
	}

	public void setBookTypeId(int bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	public void setParent(BookType parent) {
		this.parent = parent;
	}

	public void setChildren(List<BookType> children) {
		this.children = children;
	}
}
