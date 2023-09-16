package gwangju.ssafy.backend.domain.reservation.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductInfo {
    private Long productId;
    private Long groupId;
    private String category;    // 카테고리
    private String name;    // 물품 이름
    private String description; // 물품 설명
    private String content; // 물품 내용
    private String createDate;  // 물품 생성 날짜
    private String imageUrl;    // 이미지 url
}
