package mahdavi.example.review.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.provider.ProviderRegisterDto;
import mahdavi.example.review.dtos.provider.ProviderResponseDto;
import mahdavi.example.review.entities.Provider;
import mahdavi.example.review.exceptions.GeneralException;
import mahdavi.example.review.mappers.ProviderMapper;
import mahdavi.example.review.repositories.ProviderRepository;
import mahdavi.example.review.utils.PageResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {

    private final ProviderRepository providerRepository;

    private ProviderMapper providerMapper = Mappers.getMapper(ProviderMapper.class);


    public void registerProvider(ProviderRegisterDto registerDto)
    {

        if (providerRepository.existsByName(registerDto.getName()))
        {
            throw new GeneralException("provider.name.is.unique");
        }

        Provider provider = providerMapper.registerDtoToProvider(registerDto);

        providerRepository.save(provider);

    }




    public PageResponse<ProviderResponseDto> findWithPage(Integer page , Integer size)
    {

        Page<Provider> providerPage = providerRepository.findAll(PageRequest.of(page , size));

        List<ProviderResponseDto> responseDtos = providerPage.getContent().stream().map(provider -> providerMapper.providerToResponseDto(provider)).collect(Collectors.toList());

        return new PageResponse<>(responseDtos, providerPage.isFirst(), providerPage.isLast(), providerPage.getTotalElements());

    }


}
