package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data




public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String position;
}
