package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.follow.FollowUserRequest;
import com.sysmap.socialNetwork.services.follow.GetAllFollowsResponse;
import com.sysmap.socialNetwork.services.follow.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {

    @Autowired
    private IFollowService _followService;

    @PostMapping("/do")
    public ResponseEntity<String> followAndUnfollowUser(@RequestBody FollowUserRequest request) {
        var response = _followService.followAndUnfollowUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<GetAllFollowsResponse> getAllFollows() {
        var response = ResponseEntity.ok().body(_followService.getAllFollows());
        return response;
    }
}
