package ${package}.action;

import javax.annotation.Resource;
import org.apache.struts2.json.annotations.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ${package}.model.${className};
import ${package}.service.${className}Service;
import ${package}.util.ConstantUtil;
import ${package}.util.PageInfo;

public class ${className}Action extends ActionSupport {
	private ${className} ${variable};
	private ${className}Service ${variable}Service;
	private LibraryQuery query;
	private PageInfo<${className}> pageInfo;
	
	public String add() {
		${variable}Service.add(${variable});
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,"showAll_${className}");
		return ConstantUtil.REDIRECT_ACTION;
	}
	
	public String addInput() {
		return SUCCESS;
	}

	public String update() {
		${variable}Service.update(${variable});
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,"showAll_${className}");
		return ConstantUtil.REDIRECT_ACTION;
	}

	public String updateInput() {
		${className} obj = ${variable}Service.queryById(${variable}.get${className}Id());
		ActionContext.getContext().put("${variable}", obj);
		return SUCCESS;
	}
	
	public String show() {
		${className} obj = ${variable}Service.queryById(${variable}.get${className}Id());
		ActionContext.getContext().put("${variable}", obj);
		return SUCCESS;
	}

	public String showAll() {
		PageInfo<${className}> pageInfo = ${variable}Service.queryAll(query);
		ActionContext.getContext().put("pageInfo", pageInfo);
		return SUCCESS;
	}

	public String delete() {
		${variable}Service.delete(${variable}.get${className}Id());
		ActionContext.getContext().put(ConstantUtil.REDIRECT_ACTION,"showAll_${className}");
		return ConstantUtil.REDIRECT_ACTION;
	}
	
	public String addJson() {
		${variable}Service.add(${variable});
		pageInfo = ${variable}Service.queryAll(query);
		return SUCCESS;
	}
	
	public String updateJson() {
		${variable}Service.update(${variable});
		pageInfo = ${variable}Service.queryAll(query);
		return SUCCESS;
	}
	
	public String showJson() {
		${variable} = ${variable}Service.queryById(${variable}.get${className}Id());
		return SUCCESS;
	}
	
	public String showAllJson() {
		pageInfo = ${variable}Service.queryAll(query);
		return SUCCESS;
	}

	public ${className} get${className}() {
		return ${variable};
	}

	public void set${className}(${className} ${variable}) {
		this.${variable} = ${variable};
	}

	@JSON(serialize = false)
	public ${className}Service get${className}Service() {
		return ${variable}Service;
	}

	@Resource
	public void set${className}Service(${className}Service ${variable}Service) {
		this.${variable}Service = ${variable}Service;
	}

	public LibraryQuery getQuery() {
		return query;
	}

	public void setQuery(LibraryQuery query) {
		this.query = query;
	}

	public PageInfo<${className}> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<${className}> pageInfo) {
		this.pageInfo = pageInfo;
	}

}