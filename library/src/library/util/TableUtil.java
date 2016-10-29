package library.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TableUtil {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure("beans.xml");
		new SchemaExport(configuration).create(true, true);
	}
}
