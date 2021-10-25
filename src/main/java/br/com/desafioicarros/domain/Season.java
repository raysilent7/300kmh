package br.com.desafioicarros.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@Data
public class Season implements Serializable {

    @Id
    private String id;
    private String season;
    private String url;
}
