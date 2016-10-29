package library.action;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import library.model.Comment;
import library.query.LibraryQuery;
import library.service.CommentService;
import library.util.ConstantUtil;
import library.util.PageInfo;

public class CommentAction extends ActionSupport {
	private static final long serialVersionUID = 8760386314829538369L;
	private Comment comment;
	private CommentService commentService;
	private LibraryQuery query;
	private PageInfo<Comment> pageInfo;

	public String add() {
		commentService.add(comment);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Comment");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		return INPUT;
	}

	public String update() {
		commentService.update(comment);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Comment");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		Comment obj = commentService.queryById(comment.getCommentId());
		ActionContext.getContext().put("comment", obj);
		return SUCCESS;
	}

	public String show() {
		Comment obj = commentService.queryById(comment.getCommentId());
		ActionContext.getContext().put("comment", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<Comment> pageInfo = commentService.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String delete() {
		commentService.delete(comment.getCommentId());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Comment");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addJson() {
		commentService.add(comment);
		query.setKey("bookId");
		String value = String.valueOf(comment.getBook().getBookId());
		query.getProperties().put("", value);
		pageInfo = commentService.queryAll(query);
		for (Comment comment : pageInfo.getDatas()) {
			System.out.println(comment.getContent() + "     "
					+ comment.getBook().getBookName());
		}
		return SUCCESS;
	}

	public String showAllJson() {
		pageInfo = commentService.queryAll(query);
		return SUCCESS;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@JSON(serialize = false)
	public CommentService getCommentService() {
		return commentService;
	}

	@Resource
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public PageInfo<Comment> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Comment> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

}
