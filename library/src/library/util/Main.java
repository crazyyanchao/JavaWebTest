package library.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		try {
			String path = Main.class.getResource("/").getPath();
			System.out.println(path);
			List<String> fields = new ArrayList<String>();
			Field[] f = Class.forName("library.model.Comment").getDeclaredFields();
			System.out.println(f.length);
			for (Field ff : f) {
				String name = ff.getName();
				fields.add(name);
				System.out.println(name);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
