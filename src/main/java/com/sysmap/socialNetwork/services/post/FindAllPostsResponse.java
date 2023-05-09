package com.sysmap.socialNetwork.services.post;
import com.sysmap.socialNetwork.entities.Post;
import java.util.List;

public class FindAllPostsResponse {
    public List<Post> posts;
    public FindAllPostsResponse(List<Post> posts){
        this.posts = posts;
    }
}
