package com.sysmap.socialNetwork.data;

import com.sysmap.socialNetwork.entities.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFollowRepository extends MongoRepository<Follow, UUID> {
    Optional<Follow> findFollowListByUserId(UUID userId);
}
