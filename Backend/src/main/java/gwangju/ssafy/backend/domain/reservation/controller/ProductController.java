package gwangju.ssafy.backend.domain.reservation.controller;

import gwangju.ssafy.backend.domain.reservation.dto.CreateProductRequest;
import gwangju.ssafy.backend.domain.reservation.dto.ProductSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ProductCategory;
import gwangju.ssafy.backend.domain.reservation.service.ProductService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/list/{groupId}")
    public ResponseEntity<Message<List<ProductSimpleInfo>>> searchProduct(
            @PathVariable("groupId") Long groupId) {
        List<ProductSimpleInfo> productSimpleInfoList = productService.searchProduct(groupId);
        return ResponseEntity.ok().body(Message.success(productSimpleInfoList));
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("list/{groupId}/{category}")
    public ResponseEntity<Message<List<ProductSimpleInfo>>> searchProductByCategory(
            @PathVariable("groupId") Long groupId,
            @PathVariable("category") ProductCategory category) {
        List<ProductSimpleInfo> productSimpleInfoList = productService.searchProductByCategory(groupId, category);
        return ResponseEntity.ok().body(Message.success(productSimpleInfoList));
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("create/{groupId}")
    public ResponseEntity<Message> createProduct(
            @PathVariable("groupId") Long groupId,
            @AuthenticationPrincipal LoginActiveUserDto login,
            @RequestBody CreateProductRequest request) {
        productService.createProduct(groupId, login.getId(), request);
        return ResponseEntity.ok().body(Message.success());
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @DeleteMapping("delete/{groupId}/{productId}")
    public ResponseEntity<Message> deleteProduct(
            @PathVariable("groupId") Long groupId,
            @AuthenticationPrincipal LoginActiveUserDto login,
            @PathVariable("productId") Long productId
    ) {
        productService.deleteProduct(groupId, login.getId(), productId);
        return ResponseEntity.ok().body(Message.success());
    }


}
