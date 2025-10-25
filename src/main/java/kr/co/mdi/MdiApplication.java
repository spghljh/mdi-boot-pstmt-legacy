package kr.co.mdi;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

//@SpringBootApplication
//public class MdiApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(MdiApplication.class, args);
//	}
//
//}

//@Override
//protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//  return builder.sources(MdiApplication.class);
//}

@SpringBootApplication
public class MdiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
	    ApplicationContext context = SpringApplication.run(MdiApplication.class, args);

	    String[] beanNames = context.getBeanDefinitionNames();
	    Arrays.sort(beanNames); // 보기 좋게 정렬 (선택사항)

	    System.out.println("------------- Beans by IoC Container-------------");
	    for (int i = 0; i < beanNames.length; i++) {
	        System.out.printf("(%d) %s%n", i + 1, beanNames[i]);
	    }
	    System.out.println("-------------------------------------------------");
	    System.out.printf("총 등록된 Bean 개수: %,d개%n", beanNames.length);
	}

}
