package practice.project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.project.entity.enums.AuthProvider;

import javax.annotation.processing.Generated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String name;

    private String nickname;

    private String profileImageLink;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Builder
    public Member(String email, String name, String nickname, String profileImageLink, AuthProvider authProvider) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profileImageLink = profileImageLink;
        this.authProvider = authProvider;
    }
}
