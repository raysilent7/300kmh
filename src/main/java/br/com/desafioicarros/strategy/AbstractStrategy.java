package br.com.desafioicarros.strategy;

import br.com.desafioicarros.client.F1Client;
import br.com.desafioicarros.domain.Driver;
import br.com.desafioicarros.domain.MRData;
import br.com.desafioicarros.domain.Race;
import br.com.desafioicarros.domain.Result;
import br.com.desafioicarros.domain.Season;
import br.com.desafioicarros.domain.SeasonTable;
import br.com.desafioicarros.domain.Winner;
import br.com.desafioicarros.domain.WinnerTable;
import br.com.desafioicarros.exception.RestTemplateException;
import br.com.desafioicarros.repository.RaceRepository;
import br.com.desafioicarros.repository.SeasonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public abstract class AbstractStrategy {

    @Autowired
    protected SeasonRepository seasonRepository;

    @Autowired
    protected RaceRepository raceRepository;

    @Autowired
    protected F1Client f1Client;

    public MRData getSeasonInfos (Integer seasonYear) {
        MRData mrData = new MRData();
        List<Season> seasonsList = seasonRepository.findAll();
        mrData.setSeasonTable(new SeasonTable(seasonsList));

        try {
            if (CollectionUtils.isEmpty(seasonsList)) {
                return f1Client.listSeasonTable();
            }

            return mrData;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RestTemplateException(e.getMessage());
        }
    }

    public MRData saveSeasonInfos (MRData mrData) {
        seasonRepository.saveAll(mrData.getSeasonTable().getSeasons());
        return mrData;
    }

    public MRData getResultInfos (Integer seasonYear, Integer round) {
        MRData mrData = new MRData();
        List<Race> raceList = raceRepository.findAllBySeasonOrderByRound(seasonYear);

        try {
            if (CollectionUtils.isEmpty(raceList) || raceList.size() < raceList.get(0).getTotalRaces()) {
                mrData = f1Client.getSeasonInfos(seasonYear);
                completeRaceList(mrData, raceList);
                mrData.getRaceTable().getRaceList().forEach(race -> {
                    MRData tempMrData = f1Client.getResultInfos(race.getSeason(), race.getRound());
                    race.setResults(tempMrData.getRaceTable().getRaceList().get(0).getResults());
                });
                raceRepository.saveAll(mrData.getRaceTable().getRaceList());

                return mrData;
            }
            return mrData;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RestTemplateException(e.getMessage());
        }
    }

    protected void completeRaceList (MRData mrData, List<Race> raceListFromMongo) {
        raceListFromMongo.addAll(mrData.getRaceTable().getRaceList().stream()
                .filter(race -> raceListFromMongo.stream().noneMatch(race1 -> race.getSeason().equals(race1.getSeason()) && race.getRound().equals(race1.getRound())))
                .collect(Collectors.toList()));

        raceListFromMongo.forEach(race -> race.setTotalRaces(mrData.getTotal()));

        mrData.getRaceTable().setRaceList(raceListFromMongo);
    }

    private MRData calculateWinner (MRData mrData) {
        List<Winner> winners = new ArrayList<>();
        List<Driver> drivers = mrData.getRaceTable().getRaceList().stream()
                .map(race -> race.getResults().stream().map(Result::getDriver).collect(Collectors.toList()))
                .findAny()
                .get();

        drivers.stream()
                .map(driver -> {

        });

        return MRData.builder()
                .winnerTable(WinnerTable.builder()
                        .winners(winners)
                        .build())
                .build();
    }
}
