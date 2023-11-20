package br.com.onlineStore.orderms.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "cart")
public class Cart{
    @Id
    private Long id;
    @Column(name = "user_email")
    private String userEmail;
    private String token;
}
