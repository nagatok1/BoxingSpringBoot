package com.benleadbeater.database.hello.mySpringDatabaseBootApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benleadbeater.database.hello.mySpringDatabaseBootApp.exception.ResourceNotFoundException;
import com.benleadbeater.database.hello.mySpringDatabaseBootApp.model.ChampionModel;
import com.benleadbeater.database.hello.mySpringDatabaseBootApp.repository.ChampionRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChampionController {

	@Autowired
	ChampionRepository myRepository;

	// Method to create a person
	@PostMapping("/champions/createchampion")
	public ChampionModel createChampion(@Valid @RequestBody ChampionModel mSDM) {
		return myRepository.save(mSDM);
	}

	// Method to get a person
	@GetMapping("/champions/{titleid}")
	public ChampionModel getChampionbyTitle(@PathVariable(value = "titleid") Long titleid) {
		return myRepository.findById(titleid)
				.orElseThrow(() -> new ResourceNotFoundException("boxingSpringBoot", "titleid", titleid));
	}

	// Method to get all people
	@GetMapping("/champions/allchampions")
	public List<ChampionModel> getAllChampions() {
		return myRepository.findAll();
	}

	// @GetMapping("/film/category/{category}")
	// public List<mySpringBootDataModel> getFilmCategory(@PathVariable(value =
	// "category") String filmCategory) {
	// return myRepository.findByCategory(filmCategory);
	// }

	// Method to update a person
	@PutMapping("/champions/{titleid}")
	public ChampionModel updateChampion(@PathVariable(value = "titleid") Long titleid,
			@Valid @RequestBody ChampionModel ChampionDetails) {

		ChampionModel mSDM = myRepository.findById(titleid)
				.orElseThrow(() -> new ResourceNotFoundException("Champion", "titleid", titleid));

		mSDM.setBoxer(ChampionDetails.getBoxer());
		mSDM.setWeightclass(ChampionDetails.getWeightclass());
		mSDM.setTitle(ChampionDetails.getTitle());

		ChampionModel updateData = myRepository.save(mSDM);
		return updateData;
	}

	// Method to remove a person
	@DeleteMapping("/champions/{titleid}")
	public ResponseEntity<?> deleteChampion(@PathVariable(value = "titleid") Long titleid) {
		ChampionModel mSDM = myRepository.findById(titleid)
				.orElseThrow(() -> new ResourceNotFoundException("Champion", "titleid", titleid));

		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}

}
