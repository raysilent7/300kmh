package br.com.desafioicarros.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@Data
public class Race implements Serializable {

    @Id
    private String id;
    private String raceName;
    private String url;
    private int totalRaces;

    @Indexed(background = true)
    private Integer season;

    @Indexed(background = true)
    private Integer round;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ssz", timezone = "UTC")
    private LocalTime time;

    @JsonProperty("Circuit")
    private Circuit circuit;

    @JsonProperty("Results")
    private List<Result> results;
}
