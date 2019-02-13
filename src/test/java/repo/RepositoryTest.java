package repo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.benleadbeater.database.boxing.SpringDatabaseBootApp.MySpringBootDatabaseAppApplication;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.model.BoxerModel;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.repository.BoxerRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MySpringBootDatabaseAppApplication.class})
@ContextConfiguration(classes= {MySpringBootDatabaseAppApplication.class})
@DataJpaTest
public class RepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BoxerRepository MyRepo;
	
	@Test
	public void retrievebyIdTest() {
		BoxerModel model1 = new BoxerModel("Dale Salford","Active", 29, "09/09/1990", "Mexican", "Lightweight", "orthodox", "los Angeles, USA", "los Angeles, USA");
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(MyRepo.findById(model1.getId()).isPresent());
	}

}