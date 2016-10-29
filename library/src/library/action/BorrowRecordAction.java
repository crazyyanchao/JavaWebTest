package library.action;

import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import library.model.BorrowRecord;
import library.query.LibraryQuery;
import library.service.BorrowRecordService;
import library.util.ConstantUtil;
import library.util.PageInfo;

public class BorrowRecordAction extends ActionSupport {
	private static final long serialVersionUID = 2663206148047450887L;
	private BorrowRecord borrowRecord;
	private BorrowRecordService borrowRecordService;
	private LibraryQuery query;

	public String add() {
		borrowRecordService.add(borrowRecord);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_BorrowRecord");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		return SUCCESS;
	}

	public String update() {
		borrowRecordService.update(borrowRecord);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_BorrowRecord");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		BorrowRecord obj = borrowRecordService.queryById(borrowRecord
				.getBorrowRecordId());
		ActionContext.getContext().put("borrowRecord", obj);
		return SUCCESS;
	}

	public String show() {
		BorrowRecord obj = borrowRecordService.queryById(borrowRecord
				.getBorrowRecordId());
		ActionContext.getContext().put("borrowRecord", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<BorrowRecord> pageInfo = borrowRecordService.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String delete() {
		borrowRecordService.delete(borrowRecord.getBorrowRecordId());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_BorrowRecord");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public BorrowRecord getBorrowRecord() {
		return borrowRecord;
	}

	public void setBorrowRecord(BorrowRecord borrowRecord) {
		this.borrowRecord = borrowRecord;
	}

	public BorrowRecordService getBorrowRecordService() {
		return borrowRecordService;
	}

	@Resource
	public void setBorrowRecordService(BorrowRecordService borrowRecordService) {
		this.borrowRecordService = borrowRecordService;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

}
