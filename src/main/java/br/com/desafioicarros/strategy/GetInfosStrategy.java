package br.com.desafioicarros.strategy;

import br.com.desafioicarros.domain.MRData;
import br.com.desafioicarros.domain.Race;
import br.com.desafioicarros.domain.RaceTable;
import br.com.desafioicarros.exception.RestTemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetInfosStrategy extends AbstractStrategy {

    @Override
    public MRData getSeasonInfos (Integer seasonYear) {
        MRData mrData = new MRData();
        List<Race> raceList = raceRepository.findAllBySeasonOrderByRound(seasonYear);

        try {
            if (CollectionUtils.isEmpty(raceList) || raceList.get(0).getTotalRaces() == 0) {
                mrData = f1Client.getSeasonInfos(seasonYear);
                completeRaceList(mrData, raceList);
                return mrData;
            }

            mrData.setRaceTable(new RaceTable(seasonYear, raceList));
            return mrData;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RestTemplateException(e.getMessage());
        }
    }

    @Override
    public MRData saveSeasonInfos (MRData mrData) {
        List<Race> raceList = mrData.getRaceTable().getRaceList();
        raceRepository.saveAll(raceList);
        return mrData;
    }

    @Override
    public MRData getResultInfos (Integer seasonYear, Integer round) {
        MRData mrData;
        Race race = raceRepository.findBySeasonAndRound(seasonYear, round);

        try {
            if (Objects.isNull(race)) {
                mrData = f1Client.getResultInfos(seasonYear, round);
                race = mrData.getRaceTable().getRaceList().get(0);
                raceRepository.save(race);
                return mrData;
            }
            else if (CollectionUtils.isEmpty(race.getResults())) {
                mrData = f1Client.getResultInfos(seasonYear, round);
                race.setResults(mrData.getRaceTable().getRaceList().get(0).getResults());
                raceRepository.save(race);
                return mrData;
            }

            return MRData.builder()
                    .raceTable(new RaceTable(seasonYear, Collections.singletonList(race)))
                    .build();
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RestTemplateException(e.getMessage());
        }
    }
}
