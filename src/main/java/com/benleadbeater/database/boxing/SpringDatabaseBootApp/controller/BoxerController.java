package com.benleadbeater.database.boxing.SpringDatabaseBootApp.controller;

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

import com.benleadbeater.database.boxing.SpringDatabaseBootApp.exception.ResourceNotFoundException;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.model.BoxerModel;
import com.benleadbeater.database.boxing.SpringDatabaseBootApp.repository.BoxerRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BoxerController {

	@Autowired
	BoxerRepository myRepository;

	// Method to create a person
	@PostMapping("/boxers/createboxer")
	public BoxerModel createBoxer(@Valid @RequestBody BoxerModel mSDM) {
		return myRepository.save(mSDM);
	}

	// Method to get a person
	@GetMapping("/boxers/{boxerid}")
	public BoxerModel getBoxerbyID(@PathVariable(value = "boxerid") Long boxerid) {
		return myRepository.findById(boxerid)
				.orElseThrow(() -> new ResourceNotFoundException("boxingSpringBoot", "boxerid", boxerid));
	}

	// Method to get all people
	@GetMapping("/boxers/allboxers")
	public List<BoxerModel> getAllBoxers() {
		return myRepository.findAll();
	}

	// @GetMapping("/film/category/{category}")
	// public List<mySpringBootDataModel> getFilmCategory(@PathVariable(value =
	// "category") String filmCategory) {
	// return myRepository.findByCategory(filmCategory);
	// }

	// Method to update a person
	@PutMapping("/boxers/{id}")
	public BoxerModel updateBoxer(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BoxerModel BoxerDetails) {

		BoxerModel mSDM = myRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Boxer", "id", id));

		mSDM.setName(BoxerDetails.getName());
		
		mSDM.setStatus(BoxerDetails.getStatus());
		mSDM.setAge(BoxerDetails.getAge());
		mSDM.setDob(BoxerDetails.getDob());
		mSDM.setNationality(BoxerDetails.getNationality());
		mSDM.setWeight(BoxerDetails.getWeight());
		mSDM.setStance(BoxerDetails.getStance());
		mSDM.setLocation(BoxerDetails.getLocation());
		mSDM.setBirthplace(BoxerDetails.getBirthplace());

		BoxerModel updateData = myRepository.save(mSDM);
		return updateData;
	}

	// Method to remove a person
	@DeleteMapping("/boxers/{id}")
	public ResponseEntity<?> deleteBoxer(@PathVariable(value = "id") Long id) {
		BoxerModel mSDM = myRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Boxer", "id", id));

		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}

}
