package library.dao;

import library.model.Announcement;
import library.util.PageInfo;

public interface AnnouncementDao extends BaseDao<Announcement>{

	public PageInfo<Announcement> queryFirst();
}
