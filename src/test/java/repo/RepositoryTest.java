//package repo;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.benleadbeater.database.hello.mySpringDatabaseBootApp.MySpringBootDatabaseAppApplication;
//import com.benleadbeater.database.hello.mySpringDatabaseBootApp.model.mySpringBootDataModel;
//import com.benleadbeater.database.hello.mySpringDatabaseBootApp.repository.MySpringBootRepository;
//
//@RunWith(SpringRunner.class)
////@SpringBootTest(classes = {MySpringBootDatabaseAppApplication.class})
//@ContextConfiguration(classes= {MySpringBootDatabaseAppApplication.class})
//@DataJpaTest
//public class RepositoryTest {
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private MySpringBootRepository MyRepo;
//	
//	@Test
//	public void retrievebyIdTest() {
//		mySpringBootDataModel model1 = new mySpringBootDataModel("Bobby", "Space", 50);
//		entityManager.persist(model1);
//		entityManager.flush();
//		assertTrue(MyRepo.findById(model1.getId()).isPresent()); 
//	}
//
//}