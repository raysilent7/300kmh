package br.com.desafioicarros.repository;

import br.com.desafioicarros.domain.Season;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeasonRepository extends MongoRepository<Season, String> {
}
