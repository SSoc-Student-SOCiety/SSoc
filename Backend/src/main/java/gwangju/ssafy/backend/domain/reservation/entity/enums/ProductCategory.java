package gwangju.ssafy.backend.domain.reservation.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductCategory {
    // 편의성, 행사용품, 전공서적
    CONVENIENCE, PARTY, BOOK;

    @JsonCreator
    public static ProductCategory from(String value) {
        for(ProductCategory productCategory: ProductCategory.values()) {
            if(productCategory.name().equals(value)) {
                return productCategory;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }

}
