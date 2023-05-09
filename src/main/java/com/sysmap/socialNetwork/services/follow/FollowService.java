package com.sysmap.socialNetwork.services.follow;

import com.sysmap.socialNetwork.data.IFollowRepository;
import com.sysmap.socialNetwork.entities.Follow;
import com.sysmap.socialNetwork.services.user.IUserService;
import com.sysmap.socialNetwork.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FollowService implements IFollowService {

    @Autowired
    private IFollowRepository _followRepository;
    @Autowired
    private IUserService _userServce;


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
            var userToFollowed = followingList.getFollowing().stream().filter(followed -> followed.equals(request.userIdToFollow))
                    .findFirst().orElse(null);
            //Verfica na lista Seguindo, se já segue o usuário a ser seguido, se contem o id informado.


            if (userToFollowed == null) { //Se não segue.
                followingList.getFollowing().add(userFollowed.getId()); //follow


                if (!(followersList == null)) { //Verifica se esse usuário já tem registro de Seguidores, se não é vazia
                    followersList.getFollowers().add(request.userId); //follow

                } else { //Se usuário nunca recebeu registros, se é vazia
                    var newFollowerList = new Follow(request.userIdToFollow); //Cria a lista pela primeira vez
                    newFollowerList.getFollowers().add(request.userId); //follow
                    followersList = newFollowerList;
                }


            } else { //Se já segue.
                followingList.getFollowing().remove(request.userIdToFollow); //unfollow
                followersList.getFollowers().remove(request.userId); //remove o user que deu unfollow da lista de seguidores
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

    public List<Follow> getAllFollows(){
        return _followRepository.findAll();
    }

//    public List<Follow> getFollowerListByUserId(String userId) {
//        var ze = UUID.fromString(userId);
//        var follow = _followRepository.findAll().stream().toList();
//        System.out.println(follow);
//        var x = _followRepository.findAll().stream().filter(f -> follow.contains(ze));
//        System.out.println(x);
//        var y = _followRepository.findAll().stream().filter(f -> follow.equals(ze));
//        System.out.println(y);
//        var jj = _followRepository.findAll();
//        var n = _followRepository.equals(userId);
//
//        Follow f = new Follow(UUID.fromString(userId));
//
//
//
//        return follow;
//    } //TODO TERMINAR...
}
