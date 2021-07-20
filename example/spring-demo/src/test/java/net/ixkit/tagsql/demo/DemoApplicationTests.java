package net.ixkit.tagsql.demo;

import net.ixkit.land.runtime.Tracer;
import net.ixkit.tagsql.demo.DemoApplication;
import net.ixkit.tagsql.demo.model.User;
import net.ixkit.tagsql.demo.service.DemoService;
import net.ixkit.tagsql.springboot.config.Options;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
	@Autowired
	private Options options ;

	@Autowired
	DemoService service;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testListByProvider() {
		Tracer.debug(this,"options",options);
		List<User> list = service.listByProvider("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
		Tracer.debug(this,"listByProvider Users?",list);
	}

	@Test
	public void listByProviderSubFolder() {
		List<User> list = service.listByProviderSubFolder("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
		Tracer.debug(this,"listByProvider Users?",list);
	}

	@Test
	public void testListByProviderInResources() {
		List<User> list = service.listByProviderFileInResources("firstName","%","beginDate","1888-1-1", "endDate","2222-12-30", "score",0);
		Tracer.debug(this,"listByProviderFileInResources Users?",list);
	}
}
