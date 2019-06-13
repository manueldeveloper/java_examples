package com.manueldeveloper.elasticsearch.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "users", type = "user")
@JsonDeserialize(builder = UserModel.UserModelBuilder.class)
@Getter
@Builder
class UserModel {

    @Id
    private Long id;
    private String username;

    @JsonPOJOBuilder(withPrefix = "")
    static class UserModelBuilder {}
}
