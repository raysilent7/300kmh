package br.com.desafioicarros.client;

import br.com.desafioicarros.domain.MRData;
import br.com.desafioicarros.domain.ResponseBody;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class F1Client {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://ergast.com/api";

    public MRData listSeasonTable () {
        return restTemplate.getForObject(BASE_URL + "/f1/seasons.json", ResponseBody.class).getMrData();
    }

    public MRData getSeasonInfos (Integer season) {
        return restTemplate.getForObject(BASE_URL + "/f1/" + season + ".json", ResponseBody.class).getMrData();
    }

    public MRData getResultInfos (Integer season, Integer round) {
        return restTemplate.getForObject(BASE_URL + "/f1/" + season + "/" + round + "/results.json", ResponseBody.class).getMrData();
    }
}
