package br.com.desafioicarros.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {

    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("long")
    private String longitude;

    private String locality;
    private String country;
}
