package com.bio.crowdfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bio.crowdfunding.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>{
	
}
