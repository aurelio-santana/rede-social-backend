package com.sysmap.socialNetwork.services.post;
import com.sysmap.socialNetwork.data.IPostRepository;

import com.sysmap.socialNetwork.entities.Comment;
import com.sysmap.socialNetwork.entities.Post;

import com.sysmap.socialNetwork.services.comment.CreateCommentRequest;
import com.sysmap.socialNetwork.services.comment.DeleteCommentRequest;
import com.sysmap.socialNetwork.services.comment.UpdateCommentRequest;
import com.sysmap.socialNetwork.services.fileUpload.IFileUploadService;
import com.sysmap.socialNetwork.services.follow.IFollowService;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikeCommentRequest;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikePostRequest;

import com.sysmap.socialNetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

    @Autowired
    private IFollowService _followSerivce;
    @Autowired
    private IPostRepository _postRepository;
    @Autowired
    private IUserService _userRepository;
    @Autowired
    private IFileUploadService _fileUploadService;

    //public String createPost(CreatePostRequest request, List<MultipartFile> photo) {
    public String createPost(CreatePostRequest request) {
        var name = _userRepository.getUserById(request.userId).getName();
        var post = new Post(request.title, request.content, request.userId, name, LocalDateTime.now());
        //_postRepository.save(post);


//        var photoUri = "";
//
//        try {
//            var fileName = post.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
//            photoUri = _fileUploadService.upload(photo, fileName);
//
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//        var fileName = "";
        //post.getPhotoUri().add("ds");
        //photo(file -> posts.add(_postRepository.findPostByUserId(id).get()));
        //photo(file -> post.getPhotoUri().add("ds"));

//        photo(file -> fileName = post.getId() + "." + photo.get(file).getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
//        posts.removeIf(p -> p.isEmpty());
        List<String> fileNames = new ArrayList<>();
        List<String> photoUri = new ArrayList<>();
        List<MultipartFile> Names = new ArrayList<>();
        MultipartFile file;
        System.out.println("passou aqui");




//        Arrays.stream(photo).map(file -> fileNames.add(
//                post.getId() + "." +  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
//        ));
        //file = photo.get(0);
        //System.out.println("agora: "+post.getId() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
        //Names.add(post.getId() + "." +  photo.get(0).getOriginalFilename().substring(photo.get(0).getOriginalFilename().lastIndexOf(".") + 1));


       // Number count = 0;
//        photo.stream().map(multipartFile -> fileNames.add(
//                post.getId() + "." + multipartFile + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1)
//        ));
        //Arrays teste[];








//        fileNames = photo.stream().map(multipartFile -> post.getId() + "-" + photo.indexOf(multipartFile) + "." +
//                        multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1)).
//                        collect(Collectors.toList());
//
//        fileNames.forEach(fileName -> photoUri.add(_fileUploadService.upload(photo.listIterator().next(), fileName)));
//        System.out.println("filenames :"+fileNames);
//        System.out.println("photouri :"+photoUri);
//
//        post.setPhotoUri(photoUri);
//        _postRepository.save(post);



        if (request.fileList != null) {
            fileNames = request.fileList.stream().map(multipartFile -> post.getId() + "-" + request.fileList.indexOf(multipartFile) + "." +
                            multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1)).
                    collect(Collectors.toList());

            fileNames.forEach(fileName -> photoUri.add(_fileUploadService.upload(request.fileList.listIterator().next(), fileName)));
            System.out.println("filenames :"+fileNames);
            System.out.println("photouri :"+photoUri);

            post.setPhotoUri(photoUri);

        }

        _postRepository.save(post);













//        try {
//            fileNames = Arrays.stream(photo)
//                    .map(MultipartFile::getOriginalFilename)
//                    .
//            return ResponseEntity.status(HttpStatus.OK).body(fileNames);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Unable to download files");
//        }





        return post.getId().toString();
    }

    public String updatePost(String postId, UpdatePostRequest request) {
        var post = _postRepository.findPostById(UUID.fromString(postId)).get();
        post.setTitle(request.title);
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

    public FindPostResponse findPostById(String id) {
        System.out.println("id invalido"+id);
        var post = _postRepository.findPostById(UUID.fromString(id)).get();
        var response = new FindPostResponse(post.getId(), post.getTitle(), post.getContent(), post.getComment(),
                        post.getLike(), post.getLikes(), post.getUserId(), post.getName(), post.getCreatedAt());
        return response;
    }

    public FeedResponse feed(String userId){
        var userIdUUID = UUID.fromString(userId);
        var follow = _followSerivce.getFollowsListByUserId(userIdUUID);

        List<List<Post>> posts = new ArrayList<>();

        follow.followsList.getFollowing().forEach(id -> posts.add(_postRepository.findPostByUserId(id).get()));
        posts.removeIf(p -> p.isEmpty());

        FeedResponse response = new FeedResponse(posts);
        return response;
    }

    public String createComment(String postId, CreateCommentRequest request) {
//        var post = _postRepository.findPostById(request.postId).get();
//        var comment = new Comment(request.postId, request.userId, request.content);
//        post.getComment().add(comment);
//
//        _postRepository.save(post);
//        return comment.getId().toString();

        var name = _userRepository.getUserById(request.userId).getName();
        System.out.println("name :"+ name);
        System.out.println("date :"+ LocalDateTime.now());
        var post = _postRepository.findPostById(UUID.fromString(postId)).get();
        var comment = new Comment(UUID.fromString(postId), request.userId, name, request.content, LocalDateTime.now());
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

    public List<UUID> LikeAndUnlikePost(String postId, LikeAndUnlikePostRequest request) {
//        var post = _postRepository.findPostById(request.postId).get();
//
//        if (!post.getLike().contains(request.userId)) {
//            post.getLike().add(request.userId);
//            post.setLikes(post.getLikes()+1);
//        } else {
//            post.getLike().remove(request.userId);
//            post.setLikes(post.getLikes()-1);
//        }
//
//        _postRepository.save(post);
//        return post.getLike();

        var post = _postRepository.findPostById(UUID.fromString(postId)).get();
        System.out.println("userid"+ request.userId);
        if (!post.getLike().contains(UUID.fromString(request.userId))) {
            post.getLike().add(UUID.fromString(request.userId));
            post.setLikes(post.getLikes()+1);
        } else {
            post.getLike().remove(UUID.fromString(request.userId));
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
