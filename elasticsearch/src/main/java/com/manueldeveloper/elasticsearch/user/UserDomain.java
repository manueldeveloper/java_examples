package com.manueldeveloper.elasticsearch.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
public class UserDomain {

    private Long id;
    private String username;
}
