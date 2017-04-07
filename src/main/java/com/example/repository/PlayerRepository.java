package com.example.repository;

import com.example.model.tables.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by GOREVOJD on 03.04.2017.
 */
/*
@RepositoryRestResource(collectionResourceRel = "players", path = "players")
public interface PlayerRepository extends MongoRepository<Player, String>{
    Player findByLogin(@Param("playerLogin") String playerLogin);
    void deleteByLogin(@Param("playerLogin") String playerLogin);
}
*/

public interface PlayerRepository extends MongoRepository<Player, String>{
    Player findByLogin(String login);
    Player deleteByLogin(String login);
}
