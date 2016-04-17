package generation;

import java.util.Set;

public class BeanGenerator {

	public static void main(String[] args) {
		 Reflections reflections = new Reflections("my.project.prefix");

		 Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
	}
}
