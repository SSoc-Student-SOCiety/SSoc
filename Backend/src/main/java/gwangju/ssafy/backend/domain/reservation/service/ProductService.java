package gwangju.ssafy.backend.domain.reservation.service;

import gwangju.ssafy.backend.domain.reservation.dto.ProductSimpleInfo;

import java.util.List;

public interface ProductService {
    List<ProductSimpleInfo> searchProductMyGroup(Long groupId);
}
