package gwangju.ssafy.backend.domain.reservation.controller;

import gwangju.ssafy.backend.domain.reservation.dto.ProductSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.service.ProductService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/product/list/{groupId}")
    public ResponseEntity<Message<List<ProductSimpleInfo>>> searchProductMyGroup(
            @PathVariable("groupId") Long groupId) {
        List<ProductSimpleInfo> productSimpleInfoList = productService.searchProductMyGroup(groupId);
        return ResponseEntity.ok().body(Message.success(productSimpleInfoList));
    }

}
