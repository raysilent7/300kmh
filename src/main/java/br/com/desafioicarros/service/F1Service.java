package br.com.desafioicarros.service;

import br.com.desafioicarros.domain.MRData;
import br.com.desafioicarros.strategy.AbstractStrategy;
import br.com.desafioicarros.strategy.StrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class F1Service {

    private final StrategyFactory strategyFactory;

    public MRData getSeasonInfos (Integer seasonYear) {
        AbstractStrategy strategy = strategyFactory.get(seasonYear, null);
        MRData mrData = strategy.getSeasonInfos(seasonYear);
        strategy.saveSeasonInfos(mrData);
        return mrData;
    }

    public MRData getResultInfos (Integer seasonYear, Integer round) {
        AbstractStrategy strategy = strategyFactory.get(seasonYear, round);
        MRData mrData = strategy.getResultInfos(seasonYear, round);
        strategy.saveSeasonInfos(mrData);
        return mrData;
    }

    public MRData getSeasonWinner (Integer seasonYear) {
        AbstractStrategy strategy = strategyFactory.get(seasonYear, null);
        MRData mrData = strategy.getResultInfos(seasonYear, null);
        return null;
    }
}
