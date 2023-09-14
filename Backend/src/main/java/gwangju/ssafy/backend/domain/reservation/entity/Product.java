package gwangju.ssafy.backend.domain.reservation.entity;

import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ProductCategory;
import gwangju.ssafy.backend.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Group group;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

}
