package com.sysmap.socialNetwork.api;


import com.sysmap.socialNetwork.data.IFollowRepository;
import com.sysmap.socialNetwork.entities.Follow;
import com.sysmap.socialNetwork.entities.User;
import com.sysmap.socialNetwork.services.follow.FollowUserRequest;
import com.sysmap.socialNetwork.services.follow.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {

    @Autowired
    private IFollowService _followService;

    @PostMapping
    public ResponseEntity<String> followUser(@RequestBody FollowUserRequest request) {
        var response = _followService.followUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Follow>> getAllFollows() {
        var response = ResponseEntity.ok().body(_followService.getAllFollows());
        return response;
    }
}
