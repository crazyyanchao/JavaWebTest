package library.action;

import javax.annotation.Resource;

import library.model.BookType;
import library.query.LibraryQuery;
import library.service.BookTypeService;
import library.util.ConstantUtil;
import library.util.PageInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BookTypeAction extends ActionSupport {
	private static final long serialVersionUID = -5244535016185067641L;
	private BookType bookType;
	private BookTypeService bookTypeService;
	private LibraryQuery query;

	public String add() {
		bookTypeService.add(bookType);
		int parentId = bookType.getParent() == null ? 0 : bookType.getParent()
				.getBookTypeId();
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_BookType?query=parentId&value=" + parentId);
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		System.out.println("input");
		return SUCCESS;
	}

	public String update() {
		bookTypeService.update(bookType);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_BookType");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		BookType obj = bookTypeService.queryById(bookType.getBookTypeId());
		ActionContext.getContext().put("bookType", obj);
		return INPUT;
	}

	public String show() {
		BookType obj = bookTypeService.queryById(bookType.getBookTypeId());
		ActionContext.getContext().put("bookType", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<BookType> pageInfo = bookTypeService.queryAll(query);
		int parentId = 0;
		if (query != null && query.getKey().equals("parentId")) {
			String value = query.getProperties().get("parentId");
			parentId = value == null ? 0 : Integer.parseInt(value);
			ActionContext.getContext().put("parentId", parentId);
		}
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String delete() {
		bookTypeService.delete(bookType.getBookTypeId());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_BookType");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public BookTypeService getBookTypeService() {
		return bookTypeService;
	}

	@Resource
	public void setBookTypeService(BookTypeService bookTypeService) {
		this.bookTypeService = bookTypeService;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

}
