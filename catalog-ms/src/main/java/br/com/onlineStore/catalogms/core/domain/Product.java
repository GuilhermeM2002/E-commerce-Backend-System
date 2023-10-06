package br.com.onlineStore.catalogms.core.domain;

import br.com.onlineStore.catalogms.aplication.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codeProduct")
@Entity(name = "product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_product")
    private Long codeProduct;
    @NotBlank
    @Size(max = 40)
    private String name;
    @NotNull
    private double price;
    @NotBlank
    private String description;
    @NotBlank
    private int quantity;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;
}
