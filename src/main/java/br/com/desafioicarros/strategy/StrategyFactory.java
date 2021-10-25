package br.com.desafioicarros.strategy;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StrategyFactory {

    private final ListInfosStrategy listInfosStrategy;
    private final GetInfosStrategy getInfosStrategy;
    private final String CURRENT_SEASON = "current";

    public AbstractStrategy get (Integer seasonYear, Integer round) {
        if (Objects.isNull(seasonYear) && Objects.isNull(round)) {
            return listInfosStrategy;
        }
        else {
            return getInfosStrategy;
        }
    }
}
