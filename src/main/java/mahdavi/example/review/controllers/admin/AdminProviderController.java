package mahdavi.example.review.controllers.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.provider.ProviderRegisterDto;
import mahdavi.example.review.dtos.provider.ProviderResponseDto;
import mahdavi.example.review.services.ProviderService;
import mahdavi.example.review.utils.PageResponse;
import mahdavi.example.review.utils.RestResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin/provider")
public class AdminProviderController {

    private final ProviderService providerService;

    @PostMapping
    public RestResponse<Void> registerProvider(@RequestBody @Validated ProviderRegisterDto registerDto) {

        providerService.registerProvider(registerDto);

        return RestResponse.ok();
    }



    @GetMapping
    public RestResponse<PageResponse<ProviderResponseDto>> findProviders(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        PageResponse<ProviderResponseDto> response = providerService.findWithPage(page, size);

        return RestResponse.ok(response);
    }

}
