package ca.sheridancollege.chatjasj.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
private int userId;
private String userName;
private String encryptedPassword;

}
