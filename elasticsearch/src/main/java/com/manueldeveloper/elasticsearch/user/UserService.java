package com.manueldeveloper.elasticsearch.user;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public void save(UserDomain user) {

        repository.save(mapper.fromDomain(user));
    }

    public Optional<UserDomain> getUserById(Long id) {

        return repository.findById(id)
                .map(mapper::fromModel);
    }
}
