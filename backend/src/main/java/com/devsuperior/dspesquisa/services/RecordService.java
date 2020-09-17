package com.devsuperior.dspesquisa.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.repositories.GameRepository;
import com.devsuperior.dspesquisa.repositories.RecordRepository;

//ESSA CAMADA TEM DEPENDENCIA PARA O REPOSITORIES
//ESSA CLASSE BUSCA DO OBJETO DTO GAME
//A CAMADA DE SERVIÇO INTERMEDIA O CONTROLADOR(RESOURCES) E O REPOSITORY
@Service
public class RecordService {
	
	//injeta uma dependência da classe game da camada de repositories
	@Autowired
	private RecordRepository repository;
	@Autowired
	private GameRepository gameRepository;
	
	//metodo para salvar um novo voto; 
	//necessário um instanciar todos os objetos para inserir todas as informações do voto
	//
	@Transactional
	public RecordDTO insert(RecordInsertDTO dto) {
		Record entity = new Record();
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now());		
		//getOne = vai apenas instanciar o objeto Game monitorado;
		//correlaciona com o id para setar um novo game		
		Game game = gameRepository.getOne(dto.getGameId());
		entity.setGame(game);
		//aqui ele salvará no bd
		entity = repository.save(entity);
		return new RecordDTO(entity);
		
	}

	@Transactional
	public Page<RecordDTO> findByMoments(Instant minDate, Instant maxDate, PageRequest pageRequest) {
		return repository.findByMoments(minDate, maxDate, pageRequest).map(x -> new RecordDTO(x));
	}
}
