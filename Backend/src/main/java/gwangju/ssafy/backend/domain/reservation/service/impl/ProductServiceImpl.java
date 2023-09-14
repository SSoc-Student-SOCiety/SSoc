package gwangju.ssafy.backend.domain.reservation.service.impl;

import gwangju.ssafy.backend.domain.reservation.dto.ProductSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.Product;
import gwangju.ssafy.backend.domain.reservation.repository.ProductRepository;
import gwangju.ssafy.backend.domain.reservation.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // 해당 그룹의 대여 물품들 전체 조회
    @Override
    public List<ProductSimpleInfo> searchProductMyGroup(Long groupId) {
        List<Product> productList = productRepository.findAllByGroupId(groupId);
        List<ProductSimpleInfo> productSimpleInfoList = new ArrayList<>();
        for(Product product: productList) {
            ProductSimpleInfo productSimpleInfo = new ProductSimpleInfo();
            productSimpleInfo.convert(product);
            productSimpleInfoList.add(productSimpleInfo);
        }
        return productSimpleInfoList;
    }
}
