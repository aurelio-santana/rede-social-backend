package com.sysmap.socialNetwork.api;


import com.sysmap.socialNetwork.data.IFollowRepository;
import com.sysmap.socialNetwork.services.follow.FollowUserRequest;
import com.sysmap.socialNetwork.services.follow.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {

    @Autowired
    private IFollowService _followService;

    @PostMapping
    public ResponseEntity<String> followUser(@RequestBody FollowUserRequest request) {
//        if (!_jwtService.isValidToken(getToken(), getUserId())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authenticate");
//        }
        var response = _followService.followUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
