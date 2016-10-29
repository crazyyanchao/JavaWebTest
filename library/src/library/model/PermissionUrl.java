package library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class PermissionUrl implements Serializable {
	private static final long serialVersionUID = -405601920208533379L;
	private int permissionUrlId;//
	private String action; // ¿ØÖÆaction
	private String description;// ×÷ÓÃÃèÊö
	private List<PermissionGroup> permissionGroups = new ArrayList<PermissionGroup>();

	public void addPermissionGroup(PermissionGroup group) {
		permissionGroups.add(group);
	}

	@Id
	@GeneratedValue
	public int getPermissionUrlId() {
		return permissionUrlId;
	}

	public String getAction() {
		return action;
	}

	public String getDescription() {
		return description;
	}

	//@ManyToMany
	@ManyToMany(mappedBy = "permissionUrls", cascade = { CascadeType.ALL })
	public List<PermissionGroup> getPermissionGroups() {
		return permissionGroups;
	}

	public void setPermissionUrlId(int permissionUrlId) {
		this.permissionUrlId = permissionUrlId;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPermissionGroups(List<PermissionGroup> permissionGroups) {
		this.permissionGroups = permissionGroups;
	}
}
