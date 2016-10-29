package library.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarker {
	private static final String BASE_PATH = "d:/";

	public static void main(String[] args) throws IOException, TemplateException {
		// createJavaFiles("yyy", "User", "user", "www.baidu.com");
		Map<String, String> map = new HashMap<String, String>();
		/*
		 * map.put("Contest", "showAll_Contest"); map.put("Exam", "showAll_Exam"); map.put("Problem", "showAll_Problem"); map.put("ProblemType",
		 * "showAll_ProblemType"); map.put("Reply", "showAll_Reply"); map.put("Result", "showAll_Result"); map.put("ResultType",
		 * "showAll_ResultType"); map.put("Topic", "showAll_Topic");
		 */

		map.put("RecommandBook", "null");
		createJavaFiles("library", map);
	}

	public static void createJavaFiles(String pack, Map<String, String> map) throws IOException, TemplateException {
		Set<String> set = map.keySet();
		for (String key : set) {
			createJavaFiles(pack, key, ("" + key.charAt(0)).toLowerCase() + key.substring(1), map.get(key));
		}
	}

	public static void createJavaFiles(String pack, String className, String variable, String action) throws IOException, TemplateException {
		createAction(pack, className, variable, action);
		createService(pack, className, variable, action);
		createServiceImpl(pack, className, variable, action);
		createDao(pack, className, variable, action);
		createDaoImpl(pack, className, variable, action);
	}

	private static void createFile(String pack, String className, String variable, String action, String ftl, String javaName) throws IOException,
			TemplateException {
		// 创建Freemarker配置实例
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("templates"));

		// 创建数据模型
		Map<String, String> root = new HashMap<String, String>();
		root.put("package", pack);
		root.put("variable", variable);
		root.put("className", className);
		root.put("action", action);
		// 加载模板
		Template template = configuration.getTemplate(ftl);

		// 显示生成数据
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(javaName))));
		template.process(root, writer);
		writer.flush();
		writer.close();
	}

	public static void createAction(String pack, String className, String variable, String action) throws IOException, TemplateException {
		String directory = BASE_PATH + pack.replace(".", "/") + "/action/";
		System.out.println(directory);
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(pack, className, variable, action, "action.ftl", directory + className + "Action.java");
	}

	public static void createService(String pack, String className, String variable, String action) throws IOException, TemplateException {
		String directory = BASE_PATH + pack.replace(".", "/") + "/service/";
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(pack, className, variable, action, "service.ftl", directory + className + "Service.java");
	}

	public static void createServiceImpl(String pack, String className, String variable, String action) throws IOException, TemplateException {
		String directory = BASE_PATH + pack.replace(".", "/") + "/service/impl/";
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(pack, className, variable, action, "serviceImpl.ftl", directory + className + "ServiceImpl.java");
	}

	public static void createDao(String pack, String className, String variable, String action) throws IOException, TemplateException {
		String directory = BASE_PATH + pack.replace(".", "/") + "/dao/";
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(pack, className, variable, action, "dao.ftl", directory + className + "Dao.java");
	}

	public static void createDaoImpl(String pack, String className, String variable, String action) throws IOException, TemplateException {
		String directory = BASE_PATH + pack.replace(".", "/") + "/dao/impl/";
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(pack, className, variable, action, "daoImpl.ftl", directory + className + "DaoImpl.java");
	}

}
