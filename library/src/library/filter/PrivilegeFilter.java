package library.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.model.PermissionUrl;
import library.model.User;
import library.util.ConstantUtil;

public class PrivilegeFilter implements Filter {

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String uri = request.getRequestURI();
		String[] posts = { ".css", ".js", ".jpg", ".bmp", "jpeg" };
		for (String post : posts) {
			if (uri == null || uri.endsWith(post)) {
				chain.doFilter(request, response);
				System.out.println("�߱�Ȩ��");
				return;
			}
		}
		String pre = "front/";
		if (uri.contains(".action")) {
			uri = uri.substring(0, uri.indexOf(".action") + 1);
		}
		int index = uri.lastIndexOf("/admin/");
		if (index != -1) {
			uri = uri.substring(index + 1);
			pre = "admin/";
		}
		index = uri.lastIndexOf("/front/");
		if (index != -1) {
			uri = uri.substring(index + 1);
			pre = "front/";
		}
		System.out.println(uri);
		for (String u : ConstantUtil.COMMON_URLS) {// �����¼��·��
			System.out.println(uri + "   " + u);
			if (uri.startsWith(u)) {
				chain.doFilter(arg0, arg1);
				return;
			}
		}
		User user = (User) request.getSession(true).getAttribute("user");
		if (user == null) {// û��½ ��ת����¼����
			response.sendRedirect(pre + "loginInput_User");
			System.out.println("û�е�¼");
			return;
		} else {
			List<PermissionUrl> permissionUrls = user.getPermissionGroup()
					.getPermissionUrls();
			for (PermissionUrl permissionUrl : permissionUrls) {
				System.out.println(permissionUrl.getAction());
				if (uri.startsWith(permissionUrl.getAction())) {
					chain.doFilter(request, response);
					System.out.println("�߱�Ȩ��");
					return;
				}
			}
			response.sendRedirect("admin/error");
			System.out.println("��");
			return;
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {

	}
}
