package br.com.onlineStore.orderms.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "order_item",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "item_cart_id"))
    private Set<ItemCart> itemCart;
    @ManyToOne
    @JoinColumn(name = "address_fk")
    private Address address;
    @Column(name = "dt_order")
    private OffsetDateTime date;
    @Column(name = "vl_order")
    private double value;
    @Column(name = "tracking_code")
    private String trackingCode;
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
