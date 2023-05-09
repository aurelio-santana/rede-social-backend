package com.sysmap.socialNetwork.services.post;
import com.sysmap.socialNetwork.data.IPostRepository;
import com.sysmap.socialNetwork.entities.Comment;
import com.sysmap.socialNetwork.entities.Post;
import com.sysmap.socialNetwork.services.comment.CreateCommentRequest;
import com.sysmap.socialNetwork.services.comment.DeleteCommentRequest;
import com.sysmap.socialNetwork.services.comment.UpdateCommentRequest;
import com.sysmap.socialNetwork.services.follow.IFollowService;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikeCommentRequest;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikePostRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService implements IPostService {

    @Autowired
    private IFollowService _followSerivce;
    @Autowired
    private IPostRepository _postRepository;

    public String createPost(CreatePostRequest request) {
        var post = new Post(request.content, request.userId);
        _postRepository.save(post);
        return post.getId().toString();
    }

    public String updatePost(String postId, UpdatePostRequest request) {
        var post = _postRepository.findPostById(UUID.fromString(postId)).get();
        post.setContent(request.content);

        _postRepository.save(post);
        return post.getId().toString();
    }

    public String deletePost(String postId) {
        var post = _postRepository.findPostById(UUID.fromString(postId)).get();
        _postRepository.delete(post);
        return post.getId().toString();
    }

    public FindAllPostsResponse getAllPosts() {
        var response = new FindAllPostsResponse(_postRepository.findAll());
        return  response;
    }

    public FindPostResponse findPostById(UUID id) {
        var post = _postRepository.findPostById(id).get();
        var response = new FindPostResponse(post.getId(), post.getContent(), post.getLikes(), post.getUserId());
        return response;
    }

    public FeedResponse feed(String userId){
        var userIdUUID = UUID.fromString(userId);
        var follow = _followSerivce.getFollowerListByUserId(userIdUUID);

        List<List<Post>> posts = new ArrayList<>();
        follow.getFollowing().forEach(id -> posts.add(_postRepository.findPostByUserId(id).get()));
        posts.removeIf(p -> p.isEmpty());

        FeedResponse response = new FeedResponse(posts);
        return response;
    }

    public String createComment(CreateCommentRequest request) {
        var post = _postRepository.findPostById(request.postId).get();
        var comment = new Comment(request.postId, request.userId, request.content);
        post.getComment().add(comment);

        _postRepository.save(post);
        return comment.getId().toString();
    }

    public String updateComment(UpdateCommentRequest request) {
        var post = _postRepository.findPostById(request.postId).get();
        var comment = post.getComment().stream().filter(id -> id.getId().equals(request.commentId)).findFirst().orElse(null);

        if (comment == null)
        {
            return null;
        }
        post.getComment().stream().filter(id -> id.getId().equals(request.commentId)).findFirst()
                        .get().setContent(request.content);

        _postRepository.save(post);
        return request.commentId.toString();
    }

    public String deleteComment(DeleteCommentRequest request) {
        var post = _postRepository.findPostById(request.postId).get();
        var comment = post.getComment().stream().filter(id -> id.getId().equals(request.commentId)).findFirst().orElse(null);

        if (comment == null)
        {
            return null;
        }
        post.getComment().remove(comment);

        _postRepository.save(post);
        return request.commentId.toString();
    }

    public List<UUID> LikeAndUnlikePost(LikeAndUnlikePostRequest request) {
        var post = _postRepository.findPostById(request.postId).get();

        if (!post.getLike().contains(request.userId)) {
            post.getLike().add(request.userId);
            post.setLikes(post.getLikes()+1);
        } else {
            post.getLike().remove(request.userId);
            post.setLikes(post.getLikes()-1);
        }

        _postRepository.save(post);
        return post.getLike();
    }

    public List<UUID> LikeAndUnlikeComment(LikeAndUnlikeCommentRequest request) {
        var post = _postRepository.findPostById(request.postId).get();

        if (!post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                .get().getLike().contains(request.userId)) {


            post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                    .get().getLike().add(request.userId); //like

            post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                    .get().setLikes(
                            post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId))
                                    .findFirst().get().getLikes()+1
                    ); //number of likes

        } else {
            post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                    .get().getLike().remove(request.userId); //unlike

            post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                    .get().setLikes(
                            post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId))
                                    .findFirst().get().getLikes()-1
                    ); //number of likes
        }

        _postRepository.save(post);
        return post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                .get().getLike();
    }
}
