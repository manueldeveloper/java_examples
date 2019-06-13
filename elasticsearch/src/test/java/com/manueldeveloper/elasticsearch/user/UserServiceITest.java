package com.manueldeveloper.elasticsearch.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.manueldeveloper.elasticsearch.config.ElasticConfig;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = ElasticConfig.Initializer.class)
public class UserServiceITest {

    private static final Long DEFAULT_ID = 1L;
    private static final String DEFAULT_USERNAME = "USER_NAME";

    @Autowired
    private UserService service;

    @After
    public void tearDown() {

        ElasticConfig.tearDown();
    }

    @Test
    public void Should_CreateNewUser_When_SaveIsCalled() {
        // Given
        final UserDomain expectedUser = generateDefaultUserDomain();
        service.save(expectedUser);

        // When
        final Optional<UserDomain> result = service.getUserById(expectedUser.getId());

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedUser);
    }

    private UserDomain generateDefaultUserDomain() {

        return UserDomain.builder()
                .id(DEFAULT_ID)
                .username(DEFAULT_USERNAME)
                .build();
    }
}
