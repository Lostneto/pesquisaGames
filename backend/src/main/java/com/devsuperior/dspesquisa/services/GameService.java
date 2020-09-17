package com.devsuperior.dspesquisa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.GameDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.repositories.GameRepository;

//ESSA CAMADA TEM DEPENDENCIA PARA O REPOSITORIES
//ESSA CLASSE BUSCA DO OBJETO DTO GAME
//A CAMADA DE SERVIÇO INTERMEDIA O CONTROLADOR(RESOURCES) E O REPOSITORY
@Service
public class GameService {
	
	//injeta uma dependência da classe game da camada de repositories
	@Autowired
	private GameRepository repository;
	
	//metodo irá buscar uma lista de game e transformar em game DTO
	//anotação para abrir a transação, passando readonly para ter baixo lock
	@Transactional(readOnly = true)
	public List<GameDTO> findAll(){
		List<Game> list = repository.findAll();
		return list.stream().map(x -> new GameDTO(x)).collect(Collectors.toList());
	}

}
