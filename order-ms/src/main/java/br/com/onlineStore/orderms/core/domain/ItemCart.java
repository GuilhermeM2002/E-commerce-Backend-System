package br.com.onlineStore.orderms.core.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "item")
public class ItemCart {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cart_fk")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_fk")
    private Product product;
    private int quantity;
}
