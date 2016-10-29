package library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Announcement implements Serializable{
	private static final long serialVersionUID = -3454176055377146983L;
	private int announcementId;
	private String content;
	private Date time;

	@Id
	@GeneratedValue
	public int getAnnouncementId() {
		return announcementId;
	}

	public String getContent() {
		return content;
	}

	public Date getTime() {
		return time;
	}

	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
