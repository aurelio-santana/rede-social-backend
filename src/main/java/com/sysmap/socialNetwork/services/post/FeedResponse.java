package com.sysmap.socialNetwork.services.post;
import com.sysmap.socialNetwork.entities.Post;
import java.util.List;

public class FeedResponse {
    public List<List<Post>> posts;
    public FeedResponse(List<List<Post>> posts){
        this.posts = posts;
    }
}
