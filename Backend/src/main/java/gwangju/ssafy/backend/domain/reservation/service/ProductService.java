package gwangju.ssafy.backend.domain.reservation.service;

import gwangju.ssafy.backend.domain.reservation.dto.CreateProductRequest;
import gwangju.ssafy.backend.domain.reservation.dto.ProductInfo;
import gwangju.ssafy.backend.domain.reservation.dto.ProductSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ProductCategory;

import java.util.List;

public interface ProductService {
    List<ProductSimpleInfo> searchProduct(Long groupId);

    List<ProductSimpleInfo> searchProductByCategory(Long groupId, ProductCategory category);

    void createProduct(Long groupId, Long loginMemberId, CreateProductRequest request);

    void deleteProduct(Long groupId, Long loginMemberId, Long productId);
}
