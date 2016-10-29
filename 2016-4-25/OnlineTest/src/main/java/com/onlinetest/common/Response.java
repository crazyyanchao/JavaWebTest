package com.onlinetest.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;


/**
 * @author 丁 鹏
 *
 */
public class Response {

	private static Logger log = Logger.getLogger(Response.class);

	public String resCodeKey = "resCode";
	public String resMsgKey = "resMsg";

	private Map<String, Object> data = null;

	private Map<String, Object> body = null;// json中的data部分

	public final static String SUCCESS = "000000";// 成功
	public final static String IS_EXIST = "100101";// 已经存在
	public final static String FAILURE = "100001";// 失败提示

	public final static String PARAMS_IS_EMPTY = "100101";// 参数不能为空
	public final static String PARAMS_CHECH_ERROR = "100102";// 参数校验错误
	public final static String ACTIVITE_FAILURE = "100103";// 激活失败

	public final static String SERVICE_ERROR = "500100";// 服务端系统发生错误
	public final static String SAVE_FAILURE = "500101";// 保存数据失败
	public final static String UPDATE_FAILURE = "500102";// 修改数据失败
	public final static String DB_FAILURE = "500103";// 修改数据失败

	public Response() {
		data = new HashMap<String, Object>(3);
	}

	public static String reLogin() {
		return "{\"resCode\":\"020001\",\"resMsg\":\"需要重新登录\",\"data\":{},\"rows\":[],\"total\":\"0\"}";
	}
	/**
	 * 返回参数为空的错误
	 * 
	 * @param msg
	 * @return
	 */
	public static String paramsIsEmpty(String params) {
		return paramsIsEmptyError("["+params+"]不能为空!");
	}
	public static String paramsIsEmptyError(String msg) {
		return "{\"resCode\":\"" + PARAMS_CHECH_ERROR + "\",\"resMsg\":\"" + msg + "\",\"data\":{}}";
	}

	/**
	 * 返回参数校验失败的错误
	 * 
	 * @param msg
	 * @return
	 */
	public static String paramsCheckError(String msg) {
		return "{\"resCode\":\"" + PARAMS_CHECH_ERROR + "\",\"resMsg\":\"" + msg + "\",\"data\":{}}";
	}

	/**
	 * 返回系统处理出错的异常
	 * @param e
	 * @return
	 */
	public static String systemError(Exception e) {
		log.error(e, e);
		return "{\"resCode\":\"" + Response.SERVICE_ERROR + "\",\"resMsg\":\"系统处理出错!\",\"data\":{},\"rows\":[],\"total\":\"0\"}";
	}

	/**
	 * 返回系统处理出错的异常
	 * @param e
	 * @return
	 */
	public static String systemError(DaoException e) {
		log.error(e, e);
		return "{\"resCode\":\"" + Response.SERVICE_ERROR + "\",\"resMsg\":\"系统处理出错:" + e.getMessage().replaceAll("\"", "") + "\",\"data\":{}}";
	}
	

	/**
	 * 返回一个自定义的错误信息
	 * @param e
	 * @return
	 */
	public static String error(String resCode , String resMsg) {
		return "{\"resCode\":\"" + resCode + "\",\"resMsg\":\"" + resMsg + "\",\"data\":{}}";
	}
	/**
	 * 返回一个操作失败的信息
	 * @param resCode
	 * @param resMsg
	 * @return
	 */
	public static String failuer(  String resMsg) {
		return "{\"resCode\":\"" + FAILURE + "\",\"resMsg\":\"" + resMsg + "\",\"data\":{}}";
	}
	/**
	 * 返回一个执行成功的消息
	 * @param resCode
	 * @param resMsg
	 * @return
	 */
	public static String success() {
		return success("成功");
	}
	/**
	 * 返回一个执行成功的消息
	 * @param resCode
	 * @param resMsg
	 * @return
	 */
	public static String success(String msg) {
		return "{\"resCode\":\"" + SUCCESS + "\",\"resMsg\":\""+msg+"\",\"data\":{}}";
	}

	/**
	 * @deprecated 使用systemError方法替代 捕获异常
	 * 捕获异常
	 * @param e
	 */
	@Deprecated
	public Response catchException(Exception e) {
		log.error(e, e);
		data.put(resCodeKey, Response.SERVICE_ERROR);
		data.put(resMsgKey, "系统处理出错");
		return this;
	}

	/**
	 * 设置返回的错误信息
	 * 
	 * @param resCode
	 * @param resMsg
	 */
	public Response setError(String resCode, String resMsg) {
		data.put(resCodeKey, resCode);
		data.put(resMsgKey, resMsg);

		return this;
	}

	public Response setMessage(String resMsg) {
		data.put(resMsgKey, resMsg);
		return this;
	}

	/**
	 * 添加一个String类型的返回数据
	 * 
	 * @param key
	 * @param value
	 */
	public Response addString(String key, String value) {
		if (this.body == null) {
			this.body = new HashMap<String, Object>(1);

		}

		this.body.put(key, value);

		return this;
	}

	/**
	 * 添加一个List类的返回数据
	 * 
	 * @param key
	 * @param list
	 */
	public Response addList(String key, List<?> list) {
		try {
			if (this.body == null) {
				this.body = new HashMap<String, Object>(1);

			}
			this.body.put(key, list);
			this.body.put(key + "Size", (list == null) ? "0" : list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}


	/**
	 * 添加Map<String, Object>类型的数据
	 * 
	 * @param key
	 * @param map
	 * @return Map<String, Object>
	 */
	public Response addMap(String key, Map<String, ?> map) {

		if (this.body == null) {
			this.body = new HashMap<String, Object>(1);
		}

		this.body.put(key, map);

		return this;
	}

	public Response addMap(Map<String, ?> map) {
		if (this.body == null) {
			this.body = new HashMap<String, Object>(1);
		}
		if (map != null) {
			this.body.putAll(map);
		}
		return this;
	}

	public Response addObject(String name, Object object) {
		if (this.body == null) {
			this.body = new HashMap<String, Object>(1);
		}
		this.body.put(name, object);
		return this;
	}

	public Response addQuickPager(String key , QuickPager<?> quickPager) {
		
		Map<String,Object> pd = new HashMap<String, Object>(5);
		pd.put("currPage", String.valueOf(quickPager.getCurrPage()));// 当前页码
		pd.put("totalPages", String.valueOf(quickPager.getTotalPages()));// 总页数
		pd.put("totalRows", String.valueOf(quickPager.getTotalRows()));// 总记录数
		pd.put("pageSize", String.valueOf(quickPager.getMaxLine()));// 每页的条数
		pd.put("data", (quickPager.getData() == null ? new ArrayList<Object>(0) : quickPager.getData()));
		this.addMap(key, pd);
		return this;
	}
	public Response addQuickPager( QuickPager<?> quickPager) {
		
		Map<String,Object> pd = new HashMap<String, Object>(5);
		pd.put("currPage", String.valueOf(quickPager.getCurrPage()));// 当前页码
		pd.put("totalPages", String.valueOf(quickPager.getTotalPages()));// 总页数
		pd.put("totalRows", String.valueOf(quickPager.getTotalRows()));// 总记录数
		pd.put("pageSize", String.valueOf(quickPager.getMaxLine()));// 每页的条数
		pd.put("list", (quickPager.getData() == null ? new ArrayList<Object>(0) : quickPager.getData()));
		this.addMap(pd);
		return this;
	}

	public String toJson() {
		if (!this.data.containsKey(resCodeKey)) {
			this.data.put(resCodeKey, Response.SUCCESS);
		}
		if (!this.data.containsKey(resMsgKey)) {
			this.data.put(resMsgKey, "成功");
		}
		if (this.body == null || this.body.isEmpty()) {
			this.data.put("data", new Object());
		} else {
			this.data.put("data", this.body);
		}
		try {
			return JSONObject.fromObject(this.data).toString();
		} catch (Exception e) {
			log.error(e, e);
			return "{\"data\":{},\"resCode\":\"500010\",\"resMsg\":\"webservice服务端JSON转换出错\"}";
		}
	}
	
	public String toEasyUiDatagridJson( QuickPager<?> quickPager) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>(2);
		}
		if (!this.data.containsKey(resCodeKey)) {
			this.data.put(resCodeKey, Response.SUCCESS);
		}
		if (!this.data.containsKey(resMsgKey)) {
			this.data.put(resMsgKey, "成功");
		}
		this.data.put("total", quickPager.getTotalRows());// 当前页码
		this.data.put("rows", (quickPager.getData() == null ? new ArrayList<Object>(0) : quickPager.getData()));
		try {
			return JSONObject.fromObject(this.data).toString();
		} catch (Exception e) {
			log.error(e, e);
			return "{\"total\":\"0\",\"rows\":[]}";
		}
	}
	
	public String toListJson (List<?> list) {
		try {
			return JSONArray.fromObject(list).toString();
		} catch (Exception e) {
			log.error(e, e);
			return "{\"total\":\"0\",\"rows\":[]}";
		}
	}

	
	@Override
	public String toString() {
		return this.toJson();
	}

}
