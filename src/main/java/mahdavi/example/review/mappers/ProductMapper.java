package mahdavi.example.review.mappers;

import mahdavi.example.review.dtos.product.ProductDetailResponseDto;
import mahdavi.example.review.dtos.product.ProductRegisterDto;
import mahdavi.example.review.dtos.product.ProductResponseDto;
import mahdavi.example.review.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductMapper {


    Product registerDtoToProduct(ProductRegisterDto registerDto);


    @Mappings({
            @Mapping(target = "providerName", expression = "java(product.getProvider().getName())") ,
            @Mapping(target = "creationTime", expression = "java(product.getCreationTime().getTime())") ,
            @Mapping(target = "updateTime", expression = "java(product.getUpdateTime() == null ? null : product.getUpdateTime().getTime())")
    })
    ProductResponseDto productToResponseDto(Product product);



    @Mappings({
            @Mapping(target = "providerName", expression = "java(product.getProvider().getName())") ,
            @Mapping(target = "creationTime", expression = "java(product.getCreationTime().getTime())") ,
            @Mapping(target = "updateTime", expression = "java(product.getUpdateTime() == null ? null : product.getUpdateTime().getTime())")
    })
    ProductDetailResponseDto productToDetailResponseDto(Product product);

}
