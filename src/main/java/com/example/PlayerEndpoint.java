package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetPlayerRequest;
import io.spring.guides.gs_producing_web_service.GetPlayerResponse;

import com.example.repository.PlayerRepository;

@Endpoint
public class PlayerEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerEndpoint(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPlayerRequest")
    @ResponsePayload
    public GetPlayerResponse getPlayer(@RequestPayload GetPlayerRequest request){
        GetPlayerResponse response = new GetPlayerResponse();

        com.example.model.tables.Player FoundInDB = playerRepository.findByLogin(request.getLogin());

        io.spring.guides.gs_producing_web_service.Player Result;
        Result = new io.spring.guides.gs_producing_web_service.Player();

        Result.setLogin(FoundInDB.getLogin());
        Result.setPassword(FoundInDB.getPassword());
        Result.setLastLevel(FoundInDB.getLastLevel());
        Result.setCoinCount(FoundInDB.getCoinCount());
        Result.setRedDiamondCount(FoundInDB.getRedDiamondCount());
        Result.setGreenDiamondCount(FoundInDB.getGreenDiamondCount());
        Result.setBlueDiamondCount(FoundInDB.getBlueDiamondCount());

        response.setPlayer(Result);

        return(response);
    }
}

