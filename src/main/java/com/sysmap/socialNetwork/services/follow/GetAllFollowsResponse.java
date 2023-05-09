package com.sysmap.socialNetwork.services.follow;
import com.sysmap.socialNetwork.entities.Follow;
import java.util.List;

public class GetAllFollowsResponse {
    public List<Follow> allFollows;

    public GetAllFollowsResponse(List<Follow> allFollows){
        this.allFollows = allFollows;
    }
}
