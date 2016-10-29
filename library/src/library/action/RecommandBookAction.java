package library.action;

import javax.annotation.Resource;
import org.apache.struts2.json.annotations.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import library.model.RecommandBook;
import library.query.LibraryQuery;
import library.service.RecommandBookService;
import library.util.ConstantUtil;
import library.util.PageInfo;

public class RecommandBookAction extends ActionSupport {
	private static final long serialVersionUID = 4298429399657386444L;
	private RecommandBook recommandBook;
	private RecommandBookService recommandBookService;
	private LibraryQuery query;
	private PageInfo<RecommandBook> pageInfo;
	
	public String add() {
		recommandBookService.add(recommandBook);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,"showAll_RecommandBook");
		return ConstantUtil.REDIRECT_ACTION;
	}
	
	public String addInput() {
		return SUCCESS;
	}

	public String update() {
		recommandBookService.update(recommandBook);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,"showAll_RecommandBook");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		RecommandBook obj = recommandBookService.queryById(recommandBook.getRecommandBookId());
		ActionContext.getContext().put("recommandBook", obj);
		return SUCCESS;
	}
	
	public String show() {
		RecommandBook obj = recommandBookService.queryById(recommandBook.getRecommandBookId());
		ActionContext.getContext().put("recommandBook", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<RecommandBook> pageInfo = recommandBookService.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String delete() {
		recommandBookService.delete(recommandBook.getRecommandBookId());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,"showAll_RecommandBook");
		return ConstantUtil.REDIRECT_ACTION;
	}
	
	public String showAllJson() {
		pageInfo = recommandBookService.queryAll(query);
		return SUCCESS;
	}

	public RecommandBook getRecommandBook() {
		return recommandBook;
	}

	public void setRecommandBook(RecommandBook recommandBook) {
		this.recommandBook = recommandBook;
	}

	@JSON(serialize = false)
	public RecommandBookService getRecommandBookService() {
		return recommandBookService;
	}

	@Resource
	public void setRecommandBookService(RecommandBookService recommandBookService) {
		this.recommandBookService = recommandBookService;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

	public PageInfo<RecommandBook> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<RecommandBook> pageInfo) {
		this.pageInfo = pageInfo;
	}

}