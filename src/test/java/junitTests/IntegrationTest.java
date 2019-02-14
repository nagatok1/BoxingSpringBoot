package junitTests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.apache.catalina.mapper.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.benleadbeater.database.boxing.SpringDatabaseBootApp.MySpringBootDatabaseAppApplication;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.model.BoxerModel;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.model.ChampionModel;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.repository.BoxerRepository;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.repository.ChampionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MySpringBootDatabaseAppApplication.class })
@AutoConfigureMockMvc
public class IntegrationTest {
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mvc;

	@Autowired
	private BoxerRepository myrepo;

	@Autowired
	private ChampionRepository myrepo2;

	@Before
	public void clearDB() {
		myrepo.deleteAll();
		myrepo2.deleteAll();

	}

	@Test
	public void findingAndRetrievingBoxerFromDatabase() throws Exception {
		myrepo.save(new BoxerModel("Dale Salford", "Active", 29, "09/09/1990", "Mexican", "Lightweight", "orthodox",
				"los Angeles, USA", "los Angeles, USA"));
		mvc.perform(get("/api/boxers/allboxers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("Dale Salford")));
	}

	@Test
	public void addABoxerToDatabaseTest() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/api/boxers/createboxer").contentType(MediaType.APPLICATION_JSON).content(
						"{\"name\" : \"Robert Dawson\",\"status\" : \"inactive\", \"age\" : 45 , \"dob\" : \"05/06/1978\", \"nationality\" : \"british\", \"weight\" : \"Bantamweight\", \"stance\" : \"orthodox\", \"location\" : \"New York, USA\", \"birthplace\" : \"New York, USA\"}"))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Robert Dawson")));
	}

	@Test
	public void DeleteBoxerFromDatabaseTest() throws Exception {
		myrepo.save(new BoxerModel("Dale Salford", "Active", 29, "09/09/1990", "Mexican", "Lightweight", "orthodox",
				"los Angeles, USA", "los Angeles, USA"));
		mvc.perform(MockMvcRequestBuilders.delete("/api/boxers/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void UpdateBoxerInDatabaseTest() throws Exception {

		BoxerModel boxer = new BoxerModel("Frank Bruno", "Active", 29, "09/09/1990", "Mexican", "Lightweight",
				"orthodox", "los Angeles, USA", "los Angeles, USA");
		BoxerModel boxer2 = new BoxerModel("Burger Johnson", "Active", 29, "09/09/1990", "Mexican", "Lightweight",
				"orthodox", "los Angeles, USA", "los Angeles, USA");

		myrepo.save(boxer);
		mvc.perform(MockMvcRequestBuilders.put("/api/boxers/" + boxer.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(boxer2))).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Burger Johnson")));

	}

	@Test
	public void FindBoxerInDatabaseByIdTest() throws Exception {
		BoxerModel boxer = new BoxerModel("Frank Bruno", "Active", 29, "09/09/1990", "Mexican", "Lightweight",
				"orthodox", "los Angeles, USA", "los Angeles, USA");
		myrepo.save(boxer);

		mvc.perform(get("/api/boxers/" + boxer.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Frank Bruno")));

	}

	@Test
	public void addAChampionToDatabaseTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/champions/createchampion").contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"boxer\" : \"Robert Dawson\",\"weightclass\" : \"Heavyweight\", \"title\" : \"World Test Title\"}"))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.boxer", is("Robert Dawson")));
	}

	@Test
	public void findingAndRetrievingChampionFromDatabase() throws Exception {
		myrepo2.save(new ChampionModel("Dale Salford", "Middleweight", "World Test Title"));
		mvc.perform(get("/api/champions/allchampions").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].boxer", is("Dale Salford")));
	}

	@Test
	public void findingAndRetrievingChampionFromDatabasebyTitle() throws Exception {
		ChampionModel champ = new ChampionModel("Jordan Jo", "Middleweight", "World Test Title");
		myrepo2.save(champ);
		mvc.perform(get("/api/champions/" + champ.getTitleid()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.boxer", is("Jordan Jo")));
	}

	@Test
	public void DeleteChampionFromDatabaseTest() throws Exception {
		ChampionModel champ = new ChampionModel("Jordan Jo", "Middleweight", "World Test Title");
		myrepo2.save(champ);
		mvc.perform(MockMvcRequestBuilders.delete("/api/champions/" + champ.getTitleid())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void UpdateChampioninDatabaseTest() throws Exception {

		ChampionModel champ = new ChampionModel("Jordan Jo", "Middleweight", "World Test Title");
		ChampionModel champ2 = new ChampionModel("Gundar Gunson", "Middleweight", "World Test Title");

		myrepo2.save(champ);
		mvc.perform(MockMvcRequestBuilders.put("/api/champions/" + champ.getTitleid())
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(champ2)))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.boxer", is("Gundar Gunson")));

	}
}
