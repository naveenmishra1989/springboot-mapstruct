package example.naveen.dto.mapper;

import example.naveen.dto.AddressDto;
import example.naveen.entity.Address;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring") --> optional if added <compilerArg> in pom file
@Mapper
public interface AddressMapper {

    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}

