package br.com.desafioicarros.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.net.URL;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Circuit implements Serializable {

    @Indexed(background = true)
    private String circuitId;
    private String circuitName;
    private URL url;

    @JsonProperty("Location")
    private Location location;
}
