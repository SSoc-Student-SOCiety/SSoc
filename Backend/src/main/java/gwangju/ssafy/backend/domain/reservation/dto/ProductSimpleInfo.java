package gwangju.ssafy.backend.domain.reservation.dto;


import gwangju.ssafy.backend.domain.reservation.entity.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductSimpleInfo {
    private Long productId;
    private Long groupId;
    private String category;
    private String name;
    private String description;
    private String imageUrl;

    public void convert(Product product) {
        this.productId = product.getId();
        this.groupId = product.getGroup().getId();
        this.category = product.getCategory().getValue();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
    }
}
