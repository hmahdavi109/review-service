package mahdavi.example.review.mappers;

import mahdavi.example.review.dtos.provider.ProviderRegisterDto;
import mahdavi.example.review.dtos.provider.ProviderResponseDto;
import mahdavi.example.review.entities.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProviderMapper {


    Provider registerDtoToProvider(ProviderRegisterDto registerDto);


    @Mappings({
            @Mapping(target = "creationTime", expression = "java(provider.getCreationTime().getTime())") ,
            @Mapping(target = "updateTime", expression = "java(provider.getUpdateTime() == null ? null : provider.getUpdateTime().getTime())")
    })
    ProviderResponseDto providerToResponseDto(Provider provider);

}
