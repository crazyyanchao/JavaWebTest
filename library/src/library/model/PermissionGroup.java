package library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class PermissionGroup implements Serializable {
	private static final long serialVersionUID = 6184686980200514135L;
	private int permissionGroupId;
	private String permissionGroupName;
	private String description;
	private List<User> users;
	private List<PermissionUrl> permissionUrls = new ArrayList<PermissionUrl>();

	public void resetPermissionUrls(List<PermissionUrl> urls) {
		permissionUrls.clear();
		permissionUrls.addAll(urls);
	}

	public void clearPermissionUrls() {
		permissionUrls.clear();
	}

	public void addPermissionUrl(PermissionUrl url) {
		permissionUrls.add(url);
	}

	@Id
	@GeneratedValue
	public int getPermissionGroupId() {
		return permissionGroupId;
	}

	public String getPermissionGroupName() {
		return permissionGroupName;
	}

	public String getDescription() {
		return description;
	}

	@OneToMany(mappedBy = "permissionGroup")
	public List<User> getUsers() {
		return users;
	}

	// @ManyToMany(mappedBy = "permissionGroups", fetch = FetchType.EAGER,
	// cascade = { CascadeType.ALL })
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "permission_group_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_url_id") }, name = "permission_group_url")
	public List<PermissionUrl> getPermissionUrls() {
		return permissionUrls;
	}

	public void setPermissionGroupId(int permissionGroupId) {
		this.permissionGroupId = permissionGroupId;
	}

	public void setPermissionGroupName(String permissionGroupName) {
		this.permissionGroupName = permissionGroupName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setPermissionUrls(List<PermissionUrl> permissionUrls) {
		this.permissionUrls = permissionUrls;
	}
}
