package lk.ijse.dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserTm {
    private String userId;
    private String username;
    private String password;
    private String position;
}
