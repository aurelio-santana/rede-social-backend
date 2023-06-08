package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.follow.FollowUserRequest;
import com.sysmap.socialNetwork.services.follow.GetAllFollowsResponse;
import com.sysmap.socialNetwork.services.follow.GetFollowsListByUserId;
import com.sysmap.socialNetwork.services.follow.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user/follow")
public class FollowController {

    @Autowired
    private IFollowService _followService;

    @PostMapping("/following")
    public ResponseEntity<String> followAndUnfollowUser(@RequestBody FollowUserRequest request) {
        System.out.println("userid"+request.userId);
        System.out.println("usertofollow"+request.userIdToFollow);
        var response = _followService.followAndUnfollowUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<GetAllFollowsResponse> getAllFollows() {
        var response = ResponseEntity.ok().body(_followService.getAllFollows());
        return response;
    }

    @GetMapping("/get/id")
    public ResponseEntity<GetFollowsListByUserId> getFollowListByUserId(String userId) {
//        var followerList = _followService.getFollowerListByUserId(UUID.fromString(userId));
//        var followingList = _followService.getFollowingListByUserId(UUID.fromString(userId));
//        List response = new ArrayList();
//        response.add(followerList);
//        response.add(followingList);
        var response = _followService.getFollowsListByUserId(UUID.fromString(userId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
