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

import com.bio.crowdfunding.model.Postagem;
import com.bio.crowdfunding.repository.PostagemRepository;


@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/postagem")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> findAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> findById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> findByTitulo(@PathVariable String titulo){
      return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
   }
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem Postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(Postagem));
	}
	//SERÁ NECESSÁRIO COLOCAR DUAS FORMAS DE ALTERAR A POSTAGEM, UMA DO CRIADOR DO POST E OUTRA PARA OS OUTROS USUÁRIOS.
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem Postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(Postagem));
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}