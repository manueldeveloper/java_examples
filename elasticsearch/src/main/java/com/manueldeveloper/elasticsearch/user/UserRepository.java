package com.manueldeveloper.elasticsearch.user;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends ElasticsearchCrudRepository<UserModel, Long> {
}
