package mahdavi.example.review.controllers.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.product.ProductDetailResponseDto;
import mahdavi.example.review.dtos.product.ProductRegisterDto;
import mahdavi.example.review.dtos.product.ProductResponseDto;
import mahdavi.example.review.enums.ReviewType;
import mahdavi.example.review.services.ProductService;
import mahdavi.example.review.utils.PageResponse;
import mahdavi.example.review.utils.RestResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/product")
public class ApiProductController {

    private final ProductService productService;



    @GetMapping
    public RestResponse<PageResponse<ProductDetailResponseDto>> findProducts(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        PageResponse<ProductDetailResponseDto> response = productService.findProductWithDetails(page, size);

        return RestResponse.ok(response);
    }


}
