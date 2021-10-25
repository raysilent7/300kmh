package br.com.desafioicarros.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Constructor {

    private String constructorId;
    private String url;
    private String name;
    private String nationality;
}
