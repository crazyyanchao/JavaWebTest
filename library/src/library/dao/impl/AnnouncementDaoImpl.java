package library.dao.impl;

import java.util.HashMap;
import java.util.Map;

import library.dao.AnnouncementDao;
import library.model.Announcement;
import library.util.PageInfo;

import org.springframework.stereotype.Repository;

@Repository("announcementDao")
public class AnnouncementDaoImpl extends BaseDaoImpl<Announcement> implements
		AnnouncementDao {

	@Override
	public PageInfo<Announcement> queryFirst() {
		String condition = "from Announcement clazz order by clazz.announcementId desc";
		return queryAll(0, null, condition, null, 1, 0);
	}

	@Override
	public PageInfo<Announcement> queryAll(int page) {
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("announcementId", "desc");
		return queryAll(page, orderby);
	}
}