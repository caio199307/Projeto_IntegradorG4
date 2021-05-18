package com.bio.crowdfunding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bio.crowdfunding.model.Postagem;
import com.bio.crowdfunding.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
    private PostagemRepository repository;

    public Optional<Postagem> doacao(long id, double doacao) {

        Optional<Postagem> postagem = repository.findById(id);

        if (postagem.isPresent()) {

            if (postagem.get().getValor_doado() + doacao <= postagem.get().getMeta()) {

                double doacaoSoma = postagem.get().getValor_doado() + doacao;

                postagem.get().setValor_doado(doacaoSoma);

                return Optional.of(repository.save(postagem.get()));

            }
            
            else {
            	postagem.get().setId(-1);
            	return Optional.of(postagem.get());
            }

          

        }

        return null;
    }
}
