package gwangju.ssafy.backend.domain.reservation.dto;

import gwangju.ssafy.backend.domain.reservation.entity.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateProductRequest {
    @NotBlank(message = "카테고리는 필수 입력값입니다.")
    private ProductCategory category;
    @NotBlank(message = "물품명은 필수 입력값입니다.")
    private String name;
    @NotBlank(message = "물품 설명은 필수 입력값입니다.")
    private String description;
    @NotBlank(message = "물품 내용은 필수 입력값입니다.")
    private String content;
    private String imageUrl;

    public CreateProductRequest build() {
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = "https://stock.adobe.com/kr/search/images?k=no+image+available&asset_id=89551596";
        }
        return this;
    }
}
