package gwangju.ssafy.backend.domain.reservation.service.impl;

import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.exception.GroupException;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.group.repository.GroupRepository;
import gwangju.ssafy.backend.domain.reservation.dto.CreateProductRequest;
import gwangju.ssafy.backend.domain.reservation.dto.ProductInfo;
import gwangju.ssafy.backend.domain.reservation.dto.ProductSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.Product;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ProductCategory;
import gwangju.ssafy.backend.domain.reservation.repository.ProductRepository;
import gwangju.ssafy.backend.domain.reservation.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static gwangju.ssafy.backend.domain.group.exception.GroupError.*;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final GroupMemberRepository groupMemberRepository;

    private final GroupRepository groupRepository;

    // 해당 그룹의 대여 물품들 전체 조회
    @Override
    public List<ProductSimpleInfo> searchProduct(Long groupId) {
        List<Product> productList = productRepository.findAllByGroupId(groupId);
        List<ProductSimpleInfo> productSimpleInfoList = new ArrayList<>();
        for(Product product: productList) {
            ProductSimpleInfo productSimpleInfo = new ProductSimpleInfo();
            productSimpleInfo.convert(product);
            productSimpleInfoList.add(productSimpleInfo);
        }
        return productSimpleInfoList;
    }

    // 해당 그룹의 대여 물품들 카테고리 별 조회
    @Override
    public List<ProductSimpleInfo> searchProductByCategory(Long groupId, ProductCategory category) {
        List<Product> productList = productRepository.findAllByGroupIdAndCategory(groupId, category);
        List<ProductSimpleInfo> productSimpleInfoList = new ArrayList<>();
        for(Product product: productList) {
            ProductSimpleInfo productSimpleInfo = new ProductSimpleInfo();
            productSimpleInfo.convert(product);
            productSimpleInfoList.add(productSimpleInfo);
        }
        return productSimpleInfoList;
    }

    // 해당 그룹에서 그룹장이 대여 물품 생성
    @Override
    public void createProduct(Long groupId, Long loginMemberId, CreateProductRequest request) {
        // 그룹장 조회한 뒤
        GroupMember groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, loginMemberId)
                .orElseThrow(() -> new GroupException((NOT_GROUP_MEMBER)));

        // 그룹장이 아닌 경우
        if (groupMember.getRole() != GroupMemberRole.MANAGER) {
            throw new GroupException(NOT_GROUP_MANAGER);
        }

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupException(NOT_EXISTS_GROUP));

        log.info(request.getImageUrl());
        request.build();
        Product product = Product.builder()
                .group(group)
                .category(request.getCategory())
                .name(request.getName())
                .description(request.getDescription())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .build();

        productRepository.save(product);
    }
}
