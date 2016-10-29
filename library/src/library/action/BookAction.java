package library.action;

import javax.annotation.Resource;

import library.model.Book;
import library.query.LibraryQuery;
import library.service.BookService;
import library.util.ConstantUtil;
import library.util.PageInfo;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
	private static final long serialVersionUID = -8843498941254135021L;
	private Book book;
	private BookService bookService;
	private LibraryQuery query;
	private PageInfo<Book> pageInfo;

	public String add() {
		bookService.add(book);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Book");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		return SUCCESS;
	}

	public String update() {
		bookService.update(book);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Book");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		Book obj = bookService.queryById(book.getBookId());
		ActionContext.getContext().put("book", obj);
		return SUCCESS;
	}

	public String show() {
		Book obj = bookService.queryById(book.getBookId());
		ActionContext.getContext().put("book", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<Book> pageInfo = bookService.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String delete() {
		bookService.delete(book.getBookId());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Book");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String showJson() {
		book = bookService.queryById(book.getBookId());
		return SUCCESS;
	}

	public String showAllJson() {
		pageInfo = bookService.queryAll(query);
		return SUCCESS;
	}

	public PageInfo<Book> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Book> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@JSON(serialize = false)
	public BookService getBookService() {
		return bookService;
	}

	@Resource
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

}
