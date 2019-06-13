package com.manueldeveloper.elasticsearch.user;

import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserModel fromDomain(UserDomain domain) {

        return UserModel.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .build();
    }

    UserDomain fromModel(UserModel model) {

        return UserDomain.builder()
                .id(model.getId())
                .username(model.getUsername())
                .build();
    }
}
