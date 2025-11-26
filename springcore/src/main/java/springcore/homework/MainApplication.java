package springcore.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBeam.class);
		context.registerShutdownHook();
		Student student = context.getBean(Student.class);
		student.Display();
		student.DisplaySubjects();

	}

}
