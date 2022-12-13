package ru.kvesko.tik_tak_toe.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class CoordinatesDTO {
    @JsonProperty("x")
    private int x;

    @JsonProperty("y")
    private int y;
}
