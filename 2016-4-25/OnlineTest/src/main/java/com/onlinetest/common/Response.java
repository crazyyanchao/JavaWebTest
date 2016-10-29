package com.onlinetest.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;


/**
 * @author �� ��
 *
 */
public class Response {

	private static Logger log = Logger.getLogger(Response.class);

	public String resCodeKey = "resCode";
	public String resMsgKey = "resMsg";

	private Map<String, Object> data = null;

	private Map<String, Object> body = null;// json�е�data����

	public final static String SUCCESS = "000000";// �ɹ�
	public final static String IS_EXIST = "100101";// �Ѿ�����
	public final static String FAILURE = "100001";// ʧ����ʾ

	public final static String PARAMS_IS_EMPTY = "100101";// ��������Ϊ��
	public final static String PARAMS_CHECH_ERROR = "100102";// ����У�����
	public final static String ACTIVITE_FAILURE = "100103";// ����ʧ��

	public final static String SERVICE_ERROR = "500100";// �����ϵͳ��������
	public final static String SAVE_FAILURE = "500101";// ��������ʧ��
	public final static String UPDATE_FAILURE = "500102";// �޸�����ʧ��
	public final static String DB_FAILURE = "500103";// �޸�����ʧ��

	public Response() {
		data = new HashMap<String, Object>(3);
	}

	public static String reLogin() {
		return "{\"resCode\":\"020001\",\"resMsg\":\"��Ҫ���µ�¼\",\"data\":{},\"rows\":[],\"total\":\"0\"}";
	}
	/**
	 * ���ز���Ϊ�յĴ���
	 * 
	 * @param msg
	 * @return
	 */
	public static String paramsIsEmpty(String params) {
		return paramsIsEmptyError("["+params+"]����Ϊ��!");
	}
	public static String paramsIsEmptyError(String msg) {
		return "{\"resCode\":\"" + PARAMS_CHECH_ERROR + "\",\"resMsg\":\"" + msg + "\",\"data\":{}}";
	}

	/**
	 * ���ز���У��ʧ�ܵĴ���
	 * 
	 * @param msg
	 * @return
	 */
	public static String paramsCheckError(String msg) {
		return "{\"resCode\":\"" + PARAMS_CHECH_ERROR + "\",\"resMsg\":\"" + msg + "\",\"data\":{}}";
	}

	/**
	 * ����ϵͳ���������쳣
	 * @param e
	 * @return
	 */
	public static String systemError(Exception e) {
		log.error(e, e);
		return "{\"resCode\":\"" + Response.SERVICE_ERROR + "\",\"resMsg\":\"ϵͳ�������!\",\"data\":{},\"rows\":[],\"total\":\"0\"}";
	}

	/**
	 * ����ϵͳ���������쳣
	 * @param e
	 * @return
	 */
	public static String systemError(DaoException e) {
		log.error(e, e);
		return "{\"resCode\":\"" + Response.SERVICE_ERROR + "\",\"resMsg\":\"ϵͳ�������:" + e.getMessage().replaceAll("\"", "") + "\",\"data\":{}}";
	}
	

	/**
	 * ����һ���Զ���Ĵ�����Ϣ
	 * @param e
	 * @return
	 */
	public static String error(String resCode , String resMsg) {
		return "{\"resCode\":\"" + resCode + "\",\"resMsg\":\"" + resMsg + "\",\"data\":{}}";
	}
	/**
	 * ����һ������ʧ�ܵ���Ϣ
	 * @param resCode
	 * @param resMsg
	 * @return
	 */
	public static String failuer(  String resMsg) {
		return "{\"resCode\":\"" + FAILURE + "\",\"resMsg\":\"" + resMsg + "\",\"data\":{}}";
	}
	/**
	 * ����һ��ִ�гɹ�����Ϣ
	 * @param resCode
	 * @param resMsg
	 * @return
	 */
	public static String success() {
		return success("�ɹ�");
	}
	/**
	 * ����һ��ִ�гɹ�����Ϣ
	 * @param resCode
	 * @param resMsg
	 * @return
	 */
	public static String success(String msg) {
		return "{\"resCode\":\"" + SUCCESS + "\",\"resMsg\":\""+msg+"\",\"data\":{}}";
	}

	/**
	 * @deprecated ʹ��systemError������� �����쳣
	 * �����쳣
	 * @param e
	 */
	@Deprecated
	public Response catchException(Exception e) {
		log.error(e, e);
		data.put(resCodeKey, Response.SERVICE_ERROR);
		data.put(resMsgKey, "ϵͳ�������");
		return this;
	}

	/**
	 * ���÷��صĴ�����Ϣ
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
	 * ���һ��String���͵ķ�������
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
	 * ���һ��List��ķ�������
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
	 * ���Map<String, Object>���͵�����
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
		pd.put("currPage", String.valueOf(quickPager.getCurrPage()));// ��ǰҳ��
		pd.put("totalPages", String.valueOf(quickPager.getTotalPages()));// ��ҳ��
		pd.put("totalRows", String.valueOf(quickPager.getTotalRows()));// �ܼ�¼��
		pd.put("pageSize", String.valueOf(quickPager.getMaxLine()));// ÿҳ������
		pd.put("data", (quickPager.getData() == null ? new ArrayList<Object>(0) : quickPager.getData()));
		this.addMap(key, pd);
		return this;
	}
	public Response addQuickPager( QuickPager<?> quickPager) {
		
		Map<String,Object> pd = new HashMap<String, Object>(5);
		pd.put("currPage", String.valueOf(quickPager.getCurrPage()));// ��ǰҳ��
		pd.put("totalPages", String.valueOf(quickPager.getTotalPages()));// ��ҳ��
		pd.put("totalRows", String.valueOf(quickPager.getTotalRows()));// �ܼ�¼��
		pd.put("pageSize", String.valueOf(quickPager.getMaxLine()));// ÿҳ������
		pd.put("list", (quickPager.getData() == null ? new ArrayList<Object>(0) : quickPager.getData()));
		this.addMap(pd);
		return this;
	}

	public String toJson() {
		if (!this.data.containsKey(resCodeKey)) {
			this.data.put(resCodeKey, Response.SUCCESS);
		}
		if (!this.data.containsKey(resMsgKey)) {
			this.data.put(resMsgKey, "�ɹ�");
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
			return "{\"data\":{},\"resCode\":\"500010\",\"resMsg\":\"webservice�����JSONת������\"}";
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
			this.data.put(resMsgKey, "�ɹ�");
		}
		this.data.put("total", quickPager.getTotalRows());// ��ǰҳ��
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
