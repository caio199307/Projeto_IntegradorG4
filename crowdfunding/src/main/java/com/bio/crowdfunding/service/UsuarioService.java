package com.bio.crowdfunding.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bio.crowdfunding.model.UserLogin;
import com.bio.crowdfunding.model.Usuario;
import com.bio.crowdfunding.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		//No projeto do professor é getUsuario()
        if(repository.findByEmail(usuario.getEmail()).isPresent())
            return null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEncoder = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncoder);

        return Optional.of(repository.save(usuario));
    }

    public Optional<UserLogin> logar(Optional<UserLogin> user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      //No projeto do professor é getUsuario()
        Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());

        if(usuario.isPresent()) {
            if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

            	//No projeto do professor é getUsuario()
                String auth = user.get().getEmail() + ":" + user.get().getSenha();
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

                String authHeader = "Basic " + new String(encodedAuth);

                user.get().setToken(authHeader);
                user.get().setNome(usuario.get().getNome());
                user.get().setId(usuario.get().getId());
                user.get().setFoto(usuario.get().getFoto());
				user.get().setTipo(usuario.get().getTipo());
                

                return user;
            }
        }
        return null;
    }
}
