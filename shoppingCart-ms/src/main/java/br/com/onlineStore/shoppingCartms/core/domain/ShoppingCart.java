package br.com.onlineStore.shoppingCartms.core.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "date_creation")
    private OffsetDateTime dateCreation;
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
