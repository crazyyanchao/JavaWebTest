package library.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class LibraryQuery implements Serializable {
	private static final long serialVersionUID = 7677698117739800926L;
	private int page;
	private String key;
	private String column;
	private String value;
	private Map<String, String> properties;
	private String orderby;
	private String sort;
	private Map<String, String> orders;

	public LibraryQuery() {
		page = 0;
		key = "";
		column = "";
		value = "";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		key = key.replace("\"", "").replace("'", "");
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		value = value.replace("\"", "").replace("'", "");
		this.value = value;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Map<String, String> getProperties() {
		System.out.println(properties + "properties");
		if (properties == null) {
			properties = new HashMap<String, String>();
			String[] columns = getColumn().split(",");
			String[] values = getValue().split(",");
			if (columns.length != values.length) {

			}
			for (int i = 0; i < columns.length; i++) {
				properties.put(columns[i], values[i]);
			}
		}
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
