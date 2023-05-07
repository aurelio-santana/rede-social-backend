package com.sysmap.socialNetwork.data;

import com.sysmap.socialNetwork.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
    Optional<Post> findPostById(UUID id);



}
