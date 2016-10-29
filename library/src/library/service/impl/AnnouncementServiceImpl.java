package library.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import library.dao.AnnouncementDao;
import library.model.Announcement;
import library.query.LibraryQuery;
import library.service.AnnouncementService;
import library.util.PageInfo;

import org.springframework.stereotype.Service;

@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {
	private AnnouncementDao announcementDao;

	@Override
	public void update(Announcement announcement) {
		announcement.setTime(new Date());
		announcement.setTime(new Date());
		announcementDao.update(announcement);
	}

	@Override
	public void add(Announcement announcement) {
		announcement.setTime(new Date());
		announcementDao.save(announcement);
	}

	@Override
	public void delete(Announcement announcement) {
		announcementDao.delete(announcement);
	}

	@Override
	public void delete(int id) {
		announcementDao.delete(id);
	}

	@Override
	public Announcement queryById(int id) {
		return announcementDao.queryById(id);
	}

	@Override
	public List<Announcement> queryAll() {
		return announcementDao.queryAll();
	}

	public AnnouncementDao getAnnouncementDao() {
		return announcementDao;
	}

	@Resource
	public void setAnnouncementDao(AnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}

	@Override
	public PageInfo<Announcement> queryAll(LibraryQuery query) {
		query = query == null ? new LibraryQuery() : query;
		int page = query.getPage();
		PageInfo<Announcement> pageInfo = new PageInfo<Announcement>(0, 0, 0);
		switch (query.getKey()) {
		case "new":// ÐÂ¹«¸æ
			pageInfo = announcementDao.queryFirst();
			System.out.println("new");
			break;
		default:
			pageInfo = announcementDao.queryAll(page);
			System.out.println("default");
			break;
		}
		for (Announcement announcement : pageInfo.getDatas()) {
			System.out.println(announcement.getContent());
		}
		return pageInfo;
	}
}
