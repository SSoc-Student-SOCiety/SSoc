package gwangju.ssafy.backend.domain.reservation.controller;

import gwangju.ssafy.backend.domain.reservation.dto.ProductSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ProductCategory;
import gwangju.ssafy.backend.domain.reservation.service.ProductService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/product/list/{groupId}")
    public ResponseEntity<Message<List<ProductSimpleInfo>>> searchProduct(
            @PathVariable("groupId") Long groupId) {
        List<ProductSimpleInfo> productSimpleInfoList = productService.searchProduct(groupId);
        return ResponseEntity.ok().body(Message.success(productSimpleInfoList));
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/product/list/{groupId}/{category}")
    public ResponseEntity<Message<List<ProductSimpleInfo>>> searchProductByCategory(
            @PathVariable("groupId") Long groupId,
            @PathVariable("category") ProductCategory category) {
        List<ProductSimpleInfo> productSimpleInfoList = productService.searchProductByCategory(groupId, category);
        return ResponseEntity.ok().body(Message.success(productSimpleInfoList));
    }

}
