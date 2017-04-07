package com.example.controller;

import com.example.model.tables.Player;
import com.example.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by GOREVOJD on 03.04.2017.
 */

@RestController
public class PlayerController {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @RequestMapping(value = "/player/{login}", method =RequestMethod.GET)
    public Player getPlayerByLogin(@PathVariable String login){
        return playerRepository.findByLogin(login);
    }

    @RequestMapping(value = "/player/{login}", method = RequestMethod.DELETE)
    public List<Player> deletePlayerById(@PathVariable String login){
        playerRepository.deleteByLogin(login);
        return playerRepository.findAll();
    }

    @RequestMapping(value = "player", method = RequestMethod.POST)
    public List<Player> addNewPlayer(@RequestBody Player player){
        if(player != null){
            playerRepository.save(player);
        }
        return playerRepository.findAll();
    }

    @RequestMapping(value = "player/{login}", method = RequestMethod.PUT)
    public List<Player> updatePlayer(@PathVariable String login, @RequestBody Player player){
        if(player != null){
            Player existPlayer;
            if((existPlayer = playerRepository.findByLogin(login)) != null) {
                existPlayer.setLogin(player.getLogin());
                existPlayer.setPassword(player.getPassword());
                existPlayer.setRedDiamondCount(player.getRedDiamondCount());
                existPlayer.setGreenDiamondCount(player.getGreenDiamondCount());
                existPlayer.setBlueDiamondCount(player.getGreenDiamondCount());
                existPlayer.setCoinCount(player.getCoinCount());
                existPlayer.setLastLevel(player.getLastLevel());
            }
        }
        return playerRepository.findAll();
    }
}
