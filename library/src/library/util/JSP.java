package library.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JSP {
	private static final String BASE_PATH = "d:/";

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("User", "null");
		createJavaFiles("library", map);
	}

	public static void createJavaFiles(String pack, Map<String, String> map) {
		Set<String> set = map.keySet();
		for (String key : set) {
			createJavaFiles(key,
					("" + key.charAt(0)).toLowerCase() + key.substring(1));
		}
	}

	public static void createJavaFiles(String className, String variable) {
		// createShowJSP("library.model.Comment", variable);
		createAddInputJSP("library.model.Comment", variable);
	}

	public static void createShowJSP(String className, String variable) {
		int index = className.lastIndexOf(".");
		String name = className.substring(index + 1);
		String directory = BASE_PATH + "jsp/" + name;
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(className, variable, "show.ftl", directory + "/show.jsp");
	}

	private static void createShowAllJSP(String className, String variable) {
		int index = className.lastIndexOf(".");
		String name = className.substring(index + 1);
		String directory = BASE_PATH + "jsp/" + name;
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(className, variable, "showAll.ftl", directory + "/show.jsp");
	}

	private static void createAddInputJSP(String className, String variable) {
		int index = className.lastIndexOf(".");
		String name = className.substring(index + 1);
		String directory = BASE_PATH + "jsp/" + name;
		File d = new File(directory);
		if (!d.exists())
			d.mkdirs();
		createFile(className, variable, "addInput.ftl", directory
				+ "/addInput.jsp");
	}

	private static void createFile(String className, String variable,
			String ftl, String fileName) {
		try {
			// 创建Freemarker配置实例
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(
					"jsptemplates"));

			// 创建数据模型
			Map<String, Object> root = new HashMap<String, Object>();
			List<String> fields = new ArrayList<String>();
			Field[] f = Class.forName(className).getDeclaredFields();
			for (Field ff : f) {
				String name = ff.getName();
				if (!"serialVersionUID".equals(name))
					fields.add(name);
				System.out.println(name);
			}
			int index = className.lastIndexOf(".");
			String name = className.substring(index + 1);
			root.put("className", name);
			root.put("variable", variable);
			root.put("fields", fields);
			// 加载模板
			Template template = configuration.getTemplate(ftl);

			// 显示生成数据
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(fileName))));
			template.process(root, writer);
			writer.flush();
			writer.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
