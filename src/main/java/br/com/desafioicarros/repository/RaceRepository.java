package br.com.desafioicarros.repository;

import br.com.desafioicarros.domain.Race;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RaceRepository extends MongoRepository<Race, String> {

    List<Race> findAllBySeasonOrderByRound (Integer season);

    Race findBySeasonAndRound (Integer season, Integer round);
}
