package com.sysmap.socialNetwork.services.post;
import com.sysmap.socialNetwork.data.IPostRepository;
import com.sysmap.socialNetwork.entities.Comment;
import com.sysmap.socialNetwork.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository _postRepository;
    public String createPost(CreatePostRequest request) {
        var post = new Post(request.content, request.likes, request.userId);
        _postRepository.save(post);
        return post.getId().toString();
    }

    public FindPostResponse findPostById(UUID id) {
        var post = _postRepository.findPostById(id).get();
        var response = new FindPostResponse(post.getId(), post.getContent(), post.getLikes(), post.getUserId());
        return response;
    }

    public String updatePost(String postId, UpdatePostRequest request) {
        var post = _postRepository.findPostById(UUID.fromString(postId));
        post.get().setContent(request.content);
        _postRepository.save(post.get());

        return post.get().getId().toString();
    }

    public void addComments(UUID postId, Comment comment) {
        var post = _postRepository.findPostById(postId).get();
        post.getComment().add(comment);
        _postRepository.save(post);
    }

    public List<UUID> addLikeToPost(String postId, String userId) {
        var post = _postRepository.findPostById(UUID.fromString(postId)).get();
        post.getLike().add(UUID.fromString(userId));
        _postRepository.save(post);

        return post.getLike();
    }

    public List<UUID> addLikeToComment(String postId, String commentId, String userId) {
        UUID postIdUUID = UUID.fromString(postId);
        UUID commentIdUUID = UUID.fromString(commentId);
        UUID userIdUUID = UUID.fromString(userId);

        var post = _postRepository.findPostById(postIdUUID).get();

        post.getComment().stream().filter(comment -> comment.getId().equals(commentIdUUID)).findFirst()
                .get().getLike().add(userIdUUID);

        _postRepository.save(post);
        return post.getLike();
    }
}
