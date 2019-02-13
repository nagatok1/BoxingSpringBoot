package com.benleadbeater.database.boxing.SpringDatabaseBootApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.benleadbeater.database.boxing.SpringDatabaseBootApp.model.BoxerModel;

@Repository
public interface BoxerRepository extends JpaRepository<BoxerModel,Long> {
	
//List<mySpringBootDataModel> findByCategory(String filmCategory);

}
