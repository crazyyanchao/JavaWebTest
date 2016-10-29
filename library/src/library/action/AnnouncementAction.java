package library.action;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import library.model.Announcement;
import library.query.LibraryQuery;
import library.service.AnnouncementService;
import library.util.ConstantUtil;
import library.util.PageInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AnnouncementAction extends ActionSupport {
	private static final long serialVersionUID = 6648660639797584320L;
	private Announcement announcement;
	private AnnouncementService announcementService;
	private LibraryQuery query;
	private PageInfo<Announcement> pageInfo;

	public String add() {
		announcementService.add(announcement);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Announcement");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String addInput() {
		return SUCCESS;
	}

	public String update() {
		announcementService.update(announcement);
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Announcement");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		announcement = announcementService.queryById(announcement
				.getAnnouncementId());
		ActionContext.getContext().put("announcement", announcement);
		return SUCCESS;
	}

	public String show() {
		Announcement obj = announcementService.queryById(announcement
				.getAnnouncementId());
		ActionContext.getContext().put("announcement", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<Announcement> pageInfo = announcementService.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String delete() {
		announcementService.delete(announcement.getAnnouncementId());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,
				"showAll_Announcement");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String showAllJson() {
		pageInfo = announcementService.queryAll(query);
		return SUCCESS;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@JSON(serialize = false)
	public AnnouncementService getAnnouncementService() {
		return announcementService;
	}

	@Resource
	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	public PageInfo<Announcement> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Announcement> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

}
