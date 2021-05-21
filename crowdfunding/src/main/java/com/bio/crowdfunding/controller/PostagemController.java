package com.bio.crowdfunding.controller;

import java.util.List;
import java.util.Optional;

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
import com.bio.crowdfunding.service.PostagemService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/postagem")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@Autowired
	private PostagemService service;

	@ApiOperation(value = "consulta de todas as postagens")
	@GetMapping
	public ResponseEntity<List<Postagem>> findAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@ApiOperation(value = "consulta uma postagem através do id")
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> findById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "consulta de postagens através de parte de um título")
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@ApiOperation(value = "faz uma postagem")
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem Postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(Postagem));
	}

	// SERÁ NECESSÁRIO COLOCAR DUAS FORMAS DE ALTERAR A POSTAGEM, UMA DO CRIADOR DO
	// POST E OUTRA PARA OS OUTROS USUÁRIOS.
	@ApiOperation(value = "alteração de uma postagem")
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem Postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(Postagem));
	}

	@ApiOperation(value = "doação para a postagem")
	@PutMapping("/doacao/id/{id}/valor/{valor}")
	public ResponseEntity<Postagem> doacaoPostagem(@PathVariable(value = "id") long id, @PathVariable(value = "valor") double doacao) {
		Optional<Postagem> postagem = service.doacao(id, doacao);
		try {
			if(postagem.get().getId() < 0) {
				postagem.get().setId(-1 * postagem.get().getId());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(postagem.get());
			}
			
			else {
				return ResponseEntity.ok(postagem.get());
			}
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@ApiOperation(value = "exclui uma postagem")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
