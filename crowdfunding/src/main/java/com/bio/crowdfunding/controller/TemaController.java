package com.bio.crowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.bio.crowdfunding.model.Tema;
import com.bio.crowdfunding.repository.TemaRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@ApiOperation(value = "consulta de todos os temas")
	@GetMapping
	public ResponseEntity<List<Tema>> findAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@ApiOperation(value = "consulta um tema através do id")
	@GetMapping("/{id}")
	public ResponseEntity<Tema> findById(@PathVariable long id){
		if(repository.existsById(id)) {
			return ResponseEntity.ok(repository.findById(id).get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
		/* return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build()); */
	} 
	
	@ApiOperation(value = "consulta de temas através de parte de um nome")
	@GetMapping("/nome/{tema}")
	public ResponseEntity<List<Tema>> findByTema(@PathVariable String tema){
		return ResponseEntity.ok(repository.findAllByTemaContainingIgnoreCase(tema));
	}
	
	@ApiOperation(value = "cria um tema")
	@PostMapping
	public ResponseEntity<Tema> post(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@ApiOperation(value = "alteração de um tema")
	@PutMapping
	public ResponseEntity<Tema> put(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	
	@ApiOperation(value = "exclui um tema")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
