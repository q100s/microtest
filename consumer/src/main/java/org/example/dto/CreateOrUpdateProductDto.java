package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateProductDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    @NotBlank
    private BigDecimal price;
}