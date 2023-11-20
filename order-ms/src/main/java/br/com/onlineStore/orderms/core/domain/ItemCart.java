package br.com.onlineStore.orderms.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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
    @JoinColumn(name = "cart_fk")
    private Cart cart;
    @JoinColumn(name = "product_fk")
    private Product product;
    private int quantity;
}
