package com.sysmap.socialNetwork.services.post;
import com.sysmap.socialNetwork.data.IPostRepository;
import com.sysmap.socialNetwork.entities.Comment;
import com.sysmap.socialNetwork.entities.Post;
import com.sysmap.socialNetwork.entities.User;
import com.sysmap.socialNetwork.services.comment.CreateCommentRequest;
import com.sysmap.socialNetwork.services.comment.DeleteCommentRequest;
import com.sysmap.socialNetwork.services.comment.UpdateCommentRequest;
import com.sysmap.socialNetwork.services.follow.IFollowService;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikeCommentRequest;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService implements IPostService {

    @Autowired
    private IFollowService _followServce;

    @Autowired
    private IPostRepository _postRepository;

    public String createPost(CreatePostRequest request) {
        var post = new Post(request.content, request.likes, request.userId);
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

    public List<Post> getAllPosts() {
        return _postRepository.findAll();
    }

    public FindPostResponse findPostById(UUID id) {
        var post = _postRepository.findPostById(id).get();
        var response = new FindPostResponse(post.getId(), post.getContent(), post.getLikes(), post.getUserId());
        return response;
    }

//    public List<Post> feed(String userId){
//        var ze = UUID.fromString(userId);
//        var post = _postRepository.findAll().contains(ze);
//        var x = post;
//        var follow = _followServce.getFollowerListByUserId(userId);
//
//
//        return null;
//    } //TODO TERMINAR...

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

        if (!post.getLike().contains(request.userId)) { //like
            post.getLike().add(request.userId);
        } else {
            post.getLike().remove(request.userId); //unlike
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
        } else {
            post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                    .get().getLike().remove(request.userId); //unlike
        }
        _postRepository.save(post);
        return post.getComment().stream().filter(comment -> comment.getId().equals(request.commentId)).findFirst()
                .get().getLike();
    }
}
