package com.jvictorweb.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jvictorweb.workshopmongo.domain.Post;
import com.jvictorweb.workshopmongo.domain.User;
import com.jvictorweb.workshopmongo.dto.AuthorDTO;
import com.jvictorweb.workshopmongo.dto.CommentDTO;
import com.jvictorweb.workshopmongo.repositories.PostRepository;
import com.jvictorweb.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob ));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("25/04/2019"), "Bom dia", "Vou viajar para Curitiba. Abraços!", new AuthorDTO(maria));
		Post post3 = new Post(null, sdf.parse("25/04/2019"), "Arrumei um emprego", "Fui selecionado para o emprego, estou mega feliz!", new AuthorDTO(maria));
		Post post4 = new Post(null, sdf.parse("25/04/2019"), "Boa noite", "Hoje o dia foi produtivo!!", new AuthorDTO(alex));
		Post post5 = new Post(null, sdf.parse("25/04/2019"), "Sextou", "Ufa, finalmente final de semana", new AuthorDTO(alex));
		Post post6 = new Post(null, sdf.parse("25/04/2019"), "Segundou", "aff, acabou o final de semana, bora trabalhar!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex)); 
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
	
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5, post6));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
