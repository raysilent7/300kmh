package br.com.desafioicarros.controller;

import br.com.desafioicarros.domain.MRData;
import br.com.desafioicarros.service.F1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class F1Controller {

    private final F1Service f1Service;

    @GetMapping(value = "/seasons", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MRData> listAvailableSeasons (@RequestParam (required = false) Integer season) {

        return ResponseEntity.ok(f1Service.getSeasonInfos(season));
    }

    @GetMapping(value = "/results", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MRData> listResults (@RequestParam Integer season,
                                               @RequestParam Integer round) {

        return ResponseEntity.ok(f1Service.getResultInfos(season, round));
    }

    @GetMapping(value = "/winner", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MRData> getSeasonWinner (@RequestParam Integer season) {

        return ResponseEntity.ok(f1Service.getSeasonWinner(season));
    }
}
