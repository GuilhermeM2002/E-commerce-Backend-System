package br.com.onlineStore.orderms.application.useCasesImpl;

import br.com.onlineStore.orderms.adapters.repository.AddressRepository;
import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.core.domain.Address;
import br.com.onlineStore.orderms.core.useCases.RegisterAddressUseCase;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
        validateAddressDto(addressDto);
        var address = addressRepository.save(mapper.map(addressDto, Address.class));

        return mapper.map(address, AddressDto.class);
    }

@Override
public void validateAddressDto(AddressDto addressDto) {
    var fields = new HashMap<String, String>();
    fields.put("CEP", addressDto.getCep());
    fields.put("City", addressDto.getCity());
    fields.put("Neighborhood", addressDto.getNeighborhood());
    fields.put("Number", addressDto.getNumber());
    fields.put("State", addressDto.getState());
    fields.put("Complement", addressDto.getComplement());
    fields.put("Street", addressDto.getStreet());

    for (Map.Entry<String, String> entry : fields.entrySet()) {
        if (StringUtils.isEmpty(entry.getValue())) {
            throw new IllegalArgumentException(entry.getKey() + " cannot be null or empty");
        }
    }
}

}
