package springcore.homework;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "springcore.homework")
public class ConfigBeam {

	@Bean
	public List<Subject> subjects(){
		return List.of(new Subject("Book1")
				,new Subject("Book2"),
				new Subject("Book3"));
	}
	@Bean
	public String[] color() {
		String[] colors = {"Black","Green","White" };
		return colors;
	}
}
