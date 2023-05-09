package com.sysmap.socialNetwork.services.follow;

import com.sysmap.socialNetwork.data.IFollowRepository;
import com.sysmap.socialNetwork.entities.Follow;
import com.sysmap.socialNetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FollowService implements IFollowService {

    @Autowired
    private IFollowRepository _followRepository;
    @Autowired
    private IUserService _userServce;

    //Como recomendado, documentação em casos de lógicas complexas.

    //O método followAndUnfollowUser manipula os 3 eventos de follow das 2 listas.
    //1. Seguir e Desseguir da lista Seguindo (following).
    //2. Seguido da lista Seguidores (follower).

    public String followAndUnfollowUser(FollowUserRequest request) {

        var user = _userServce.getUserById(request.userId);
        var userFollowed = _userServce.getUserById(request.userIdToFollow);

        if (user == null && userFollowed == null) {
            return null;
        }

        var repo = _followRepository.findAll();
        var followingList = repo.stream().filter(userId -> userId.getUserId().equals(request.userId))
                .findFirst().orElse(null);
        //Filtra o repositório com base no Id, UsuarioSeguidor.
        //Se o filtro não encontrar registros, coloca null.

        var followersList = repo.stream().filter(userId -> userId.getUserId().equals(request.userIdToFollow))
                .findFirst().orElse(null);
        //Filtra o repositório com base no Id, UsuarioASerSeguido.
        //Se o filtro não encontrar registros, coloca null.


        if (!(followingList == null)) { //Verifica se esse usuário já "tem" lista de "Seguindo", se a lista dele "Não" é vazia.
            var userToFollowed = followingList.getFollowing().stream().filter(followed -> followed.equals(request.userIdToFollow))
                    .findFirst().orElse(null);
            //Verfica na lista Seguindo, se já segue o usuário a ser seguido, se contem o id informado.


            if (userToFollowed == null) { //Se não segue.
                followingList.getFollowing().add(userFollowed.getId()); //Follow, seguiu.


                if (!(followersList == null)) { //Verifica se esse usuário já tem lista de "Seguidores", se não é vazia.
                    followersList.getFollowers().add(request.userId); //Followed, foi seguido.

                } else { //Usuário não tem lista de "Seguidores", sua lista é vazia.
                    var newFollowerList = new Follow(request.userIdToFollow); //Cria a lista pela primeira vez.
                    newFollowerList.getFollowers().add(request.userId); //Followed, foi seguido.
                    followersList = newFollowerList;
                }


            } else { //Se já segue.
                followingList.getFollowing().remove(request.userIdToFollow); //Unfollow, deixa de seguir.
                followersList.getFollowers().remove(request.userId); //Remove o user que deu unfollow da lista de Seguidores.
            }


        } else { //Usuário não tem lista de "Seguindo", sua lista é vazia.
            var newFollowingList = new Follow(request.userId); //Cria a lista pela primeira vez.

            newFollowingList.getFollowing().add(userFollowed.getId()); //Follow, seguiu.

            //Usuário não tem lista de "Seguidores", sua lista é vazia.
            var newFollowerList = new Follow(request.userIdToFollow); //Cria a lista pela primeira vez.
            newFollowerList.getFollowers().add(request.userId);
            //Adiciona o usuário seguidor (userId) na lista de seguidores do usuário a ser seguido (userIdToFollow).

            followingList = newFollowingList;
            followersList = newFollowerList;
        }

        _followRepository.save(followingList);
        _followRepository.save(followersList);
        return user.getId().toString();
    }

    public GetAllFollowsResponse getAllFollows(){
        var response = new GetAllFollowsResponse(_followRepository.findAll());
        return response;
    }

    public Follow getFollowerListByUserId(UUID userId) {
        return _followRepository.getFollowerListByUserId(userId).get();
    }
}
