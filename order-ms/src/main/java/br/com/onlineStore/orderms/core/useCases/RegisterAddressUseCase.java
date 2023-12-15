package br.com.onlineStore.orderms.core.useCases;

import br.com.onlineStore.orderms.application.dto.AddressDto;

public interface RegisterAddressUseCase {
    AddressDto registerAddress(AddressDto addressDto);
    void validateAddressDto(AddressDto addressDto);
}
