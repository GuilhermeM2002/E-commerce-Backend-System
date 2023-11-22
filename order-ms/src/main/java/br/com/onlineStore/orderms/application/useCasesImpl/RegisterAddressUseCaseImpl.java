package br.com.onlineStore.orderms.application.useCasesImpl;

import br.com.onlineStore.orderms.adapters.repository.AddressRepository;
import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.core.domain.Address;
import br.com.onlineStore.orderms.core.useCases.RegisterAddressUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterAddressUseCaseImpl implements RegisterAddressUseCase {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public AddressDto registerAddress(AddressDto addressDto) {
        var address = addressRepository.save(mapper.map(addressDto, Address.class));
        return mapper.map(address, AddressDto.class);
    }
}
