package library.util;

public class ConstantUtil {

	public static final int COUNT_PER_PAGE = 50;
	public static final int WIDTH_PER_PAGE = 50;
	public static final String REDIRECT_ACTION = "redirectAction";

	public static final String[] URLS;
	public static final String[] COMMON_URLS;
	static {
		URLS = new String[] { "add_User", "addInput_User", "update_User",
				"updateInput_User", "delete_User", "show_User", "showAll_User",
				"login_User", "loginInput_User", "logout_User",

				"add_PermissionGroup", "addInput_PermissionGroup",
				"update_PermissionGroup", "updateInput_PermissionGroup",
				"delete_PermissionGroup", "show_PermissionGroup",
				"showAll_PermissionGroup",

				"add_PermissionUrl", "addInput_PermissionUrl",
				"update_PermissionUrl", "updateInput_PermissionUrl",
				"delete_PermissionUrl", "show_PermissionUrl",
				"showAll_PermissionUrl",

		};

		COMMON_URLS = new String[] { "front/add_User", "front/addInput_User",
				"front/update_User", "front/updateInput_User",
				"front/show_User", "front/showAll_User", "front/login_User",
				"front/loginInput_User", "front/logout_User",
				"admin/login_User","admin/loginInput_User","admin/error","admin/logout_User"
		/*		"admin/add_User", "admin/addInput_User", "admin/update_User",
				"admin/updateInput_User", "admin/updatePassword_User",
				"admin/updatePasswordInput_User", "admin/login_User",
				"admin/loginInput_User", "admin/logout_User",*/

		};
	}
}
