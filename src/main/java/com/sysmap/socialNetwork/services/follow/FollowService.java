package com.sysmap.socialNetwork.services.follow;

import com.sysmap.socialNetwork.data.IFollowRepository;
import com.sysmap.socialNetwork.entities.Follow;
import com.sysmap.socialNetwork.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FollowService implements IFollowService {

    @Autowired
    private IFollowRepository _followRepository;
    @Autowired
    private UserService _userServce;


    //MÉTODO QUE MANIPULA OS 3 EVENTOS DE FOLLOW DAS 2 LISTAS
    //1. SEGUIR E DESSEGUIR DA LISTA SEGUINDO (following)
    //2. SEGUIDO DA LISTA SEGUIDORES (follower)

    public String followUser(FollowUserRequest request) {

        var user = _userServce.getUserById(request.userId);
        var userFollowed = _userServce.getUserById(request.userIdToFollow);

        if (user == null && userFollowed == null) {
            return null;
        }

        var repo = _followRepository.findAll();
        var followingList = repo.stream().filter(userId -> userId.getUserId().equals(request.userId))
                .findFirst().orElse(null);
        //Filtra o repositório com base no Id, UsuarioSeguidor
        //Se o filtro não encontrar registros, coloca null

        var followersList = repo.stream().filter(userId -> userId.getUserId().equals(request.userIdToFollow))
                .findFirst().orElse(null);
        //Filtra o repositório com base no Id, UsuarioASerSeguido
        //Se o filtro não encontrar registros, coloca null


        if (!(followingList == null)) { //Verifica se esse usuário já tem registro de Seguindo, se não é vazia
            var follower = followingList.getFollowing().stream().filter(followed -> followed.equals(request.userIdToFollow))
                    .findFirst().orElse(null);
            //Verfica na lista Seguindo, se já segue o usuário a ser seguido, se contem o id informado.


            if (follower == null) { //Se não segue.
                followingList.getFollowing().add(userFollowed.getId()); //follow


                if (!(followersList == null)) { //Verifica se esse usuário já tem registro de Seguidores, se não é vazia
                    followersList.getFollowers().add(request.userId); //follow

                } else { //Se usuário nunca recebeu registros, se é vazia
                    var newFollowerList = new Follow(request.userIdToFollow); //Cria a lista pela primeira vez
                    newFollowerList.getFollowers().add(request.userId); //follow
                    followersList = newFollowerList; //
                }


            } else { //Se já segue.
                followingList.getFollowing().remove(request.userIdToFollow); //unfollow
                followersList.getFollowers().remove(request.userId); //unfollow
//                _followRepository.deleteAll();
               // _followRepository.delete(followList);
            }


        } else { //Se usuário nunca recebeu registros
            var newFollowingList = new Follow(request.userId); //Cria a lista pela primeira vez

            newFollowingList.getFollowing().add(userFollowed.getId()); //follow

            var newFollowerList = new Follow(request.userIdToFollow); //Cria a lista pela primeira vez
            newFollowerList.getFollowers().add(request.userId);
            //Adiciona o usuário seguidor (userId) na lista de seguidores do usuário que seguido (userIdToFollow).

            followingList = newFollowingList;
            followersList = newFollowerList;
        }

        _followRepository.save(followingList);
        _followRepository.save(followersList);
        return user.getId().toString();
    }

    public Follow findFollowListByUserId(UUID userId) {
        return _followRepository.findById(userId).get();
    }


//    public String updateFollowers(UUID userId, UUID userFollowed) {
//
//        var repo = _followRepository.findAll();
//        System.out.println(repo);
//
//        var followList = repo.stream().filter(id -> id.getUserId().equals(userFollowed))
//                .findFirst().orElse(null);
//        //Filtra o repositório com base no Id informado
//        //Verifica se já existe registros desse Id, usuarioSeguido
//
//
//        if (!(followList == null)) { //Se já existe registros
//            var follower = followList.getFollowers().stream().filter(f -> f.equals(userFollowed))
//                    .findFirst().orElse(null);
//            //Verfica na lista Seguidores, se já é era seguido antes, se contem o id informado.
//
//            if (follower == null) { //Se ela é vazia
//                followList.getFollowers().add(userId);
//                //Atualiza ela com o novo seguidor
//
//            } else { //Se não é vazia, se já era seguido............ //TODO
//                followList.getFollowing().remove(userFollowed); //unfollow
////                _followRepository.deleteAll();
//                // _followRepository.delete(followList);
//            }
//
//        } else {
//            var newFollowList = new Follow(request.userId);
//
//            newFollowList.getFollowing().add(userFollowed.getId());
//            followList = newFollowList;
//
//        }
//
//
//        var deleteRepo = repo.stream().filter(userId -> userId.getUserId().equals(request.userId))
//                .findFirst().orElse(null);
////        System.out.println(deleteRepo);
////        var teste = deleteRepo;
////
////        _followRepository.delete(deleteRepo);
//
//        _followRepository.save(followList);
//
//
//
//
//        return null;
//    }


}
