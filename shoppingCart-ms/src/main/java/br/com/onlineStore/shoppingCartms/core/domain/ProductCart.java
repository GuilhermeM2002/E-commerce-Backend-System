package br.com.onlineStore.shoppingCartms.core.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codeProduct")
@Entity
@Table(name = "product_cart")
public class ProductCart {
    @Id
    @Column(name = "id")
    private Long codeProduct;
    private String name;
    private double price;
    private int quantity;
}
