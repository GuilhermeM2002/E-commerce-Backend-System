package br.com.onlineStore.shoppingCartms.application.dto;

import br.com.onlineStore.shoppingCartms.core.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private OffsetDateTime dateCreation;
    private String token;
    private Status status;
}
