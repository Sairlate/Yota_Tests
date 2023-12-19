package org.example.model.user;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserModel {
    private String login;
    private String password;
    private String token;
}
