package pl.careaboutit.backend.dto.payu;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("unitPrice")
    private String unitPrice;

    @JsonProperty("quantity")
    private String quantity;

}
