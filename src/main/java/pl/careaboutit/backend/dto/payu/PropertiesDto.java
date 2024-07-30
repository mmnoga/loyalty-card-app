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
public class PropertiesDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

}
