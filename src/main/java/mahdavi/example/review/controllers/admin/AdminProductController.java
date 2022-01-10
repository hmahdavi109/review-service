package mahdavi.example.review.controllers.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("admin/product")
public class AdminProductController {

    private final ProductService productService;

    @PostMapping
    public RestResponse<Void> registerProduct(@RequestBody @Validated ProductRegisterDto registerDto) {

        productService.registerProduct(registerDto);

        return RestResponse.ok();
    }



    @GetMapping
    public RestResponse<PageResponse<ProductResponseDto>> findProducts(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        PageResponse<ProductResponseDto> response = productService.findWithPage(page, size);

        return RestResponse.ok(response);
    }


    @GetMapping("update-reviewable")
    public RestResponse<Void> updateReviewable(@RequestParam("id") Long id, @RequestParam("isReviewable") Boolean isReviewable) {

        productService.updateReviewable(id, isReviewable);

        return RestResponse.ok();
    }


    @GetMapping("update-visible")
    public RestResponse<Void> updateVisible(@RequestParam("id") Long id, @RequestParam("isVisible") Boolean isVisible) {

        productService.updateVisible(id, isVisible);

        return RestResponse.ok();
    }


    @GetMapping("update-review-type")
    public RestResponse<Void> updateReviewType(@RequestParam("id") Long id, @RequestParam("reviewType") ReviewType reviewType) {

        productService.updateReviewType(id, reviewType);

        return RestResponse.ok();
    }

}
