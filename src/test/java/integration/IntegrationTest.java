//package integration;
//
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.benleadbeater.database.hello.mySpringDatabaseBootApp.MySpringBootDatabaseAppApplication;
//import com.benleadbeater.database.hello.mySpringDatabaseBootApp.model.mySpringBootDataModel;
//import com.benleadbeater.database.hello.mySpringDatabaseBootApp.repository.MySpringBootRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MySpringBootDatabaseAppApplication.class})
//@AutoConfigureMockMvc
//public class IntegrationTest {
//		
//		@Autowired
//		private MockMvc mvc;
//		
//		@Autowired
//		private MySpringBootRepository repository;
//		
//		@Before
//		public void clearDB() {
//			repository.deleteAll();
//		}
//		
//		@Test
//		public void findingAndRetrievingPersonFromDatabase()
//		throws Exception {
//			repository.save(new mySpringBootDataModel("Dale","Salford", 2));
//			mvc.perform(get("/api/person")                                                          
//			.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(content()
//			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("$[0].name", is("Dale")));
//		}
//		
//		@Test
//		public void addAPersonToDatabaseTest() throws Exception {
//			mvc.perform(MockMvcRequestBuilders.post("/api/person")
//					.contentType(MediaType.APPLICATION_JSON)
//					.content("{\"name\" : \"Robert\",\"address\" : \"Atlantis9\", \"age\" : 200}"))
//			.andExpect(status()
//					.isOk())
//					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//					.andExpect(jsonPath("$.name", is("Robert")));
//		}
//		
//}
//

