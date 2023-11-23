package br.com.onlineStore.orderms.application.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
}
