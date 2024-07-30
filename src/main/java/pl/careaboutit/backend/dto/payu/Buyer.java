package pl.careaboutit.backend.dto.payu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Buyer {

    String email;
    String phone;
    String firstName;
    String lastName;
    String language;

}
