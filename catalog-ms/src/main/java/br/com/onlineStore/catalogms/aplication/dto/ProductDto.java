package br.com.onlineStore.catalogms.aplication.dto;

import br.com.onlineStore.catalogms.core.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long codeProduct;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private Category category;
}
