package com.example.board.domain.vo;

import com.example.board.domain.dto.UsersDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class UsersVO {

    private String name;
    private String profilePic;
    private String provider;
    private String providerId;
    private String createAt;
    private String updateAt;

    private String phoneNumber;
    private String address;
    private String role;

    @Builder
    public UsersVO(String name, String profilePic, String provider, String providerId, String createAt, String updateAt, String phoneNumber, String address, String role) {
        this.name = name;
        this.profilePic = profilePic;
        this.provider = provider;
        this.providerId = providerId;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public static UsersVO toEntity(UsersDTO dto) {
        return UsersVO.builder().name(dto.getName())
                .profilePic(dto.getProfilePic())
                .provider(dto.getProvider())
                .providerId(dto.getProviderId())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .role(dto.getRole())
                .build();
    }

}
