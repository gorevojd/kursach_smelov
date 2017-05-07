package com.example.controller;

import com.example.TempPlayerInfo;
import com.example.model.tables.Player;
import com.example.repository.PlayerRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by GOREVOJD on 05.05.2017.
 */



@Controller
public class WebClientController {

    public static final String ServerName = "localhost.fiddler:8080";
    public static final String ProtocolName = "http";
    TempPlayerInfo plInfo = new TempPlayerInfo();

    public static final String AppURL = ProtocolName + "://" + ServerName + "/";

    public String action;


    static int CheckIfNotEmpty0(String str){
        int Result = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) > '9' ||
                    str.charAt(i) < '0')
            {
                return (0);
            }
        }

        if(str != null){
            if(!str.equals("")){
                Result = Integer.valueOf(str);
            }
        }


        return(Result);
    }

    private final PlayerRepository playerRepository;

    public WebClientController(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    /*

    @RequestMapping("web_client_action_get")
    public String FindPlayerByLogin(Model model){
        model.addAttribute("player", playerRepository.findByLogin());
    }

    @RequestMapping("web_client_action_put")
    public String UpdatePlayer(Model model){
        model.
    }*/

    /*
    @RequestMapping
    public String getInfo(Model model) {
        model.addAttribute("hello", this.plInfo());
    }
    */

    @RequestMapping(value = "/web_client_action")
    public String WebClientAction(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model)
    {
        plInfo.info_Login = request.getParameter("login");
        plInfo.info_Password = request.getParameter("password");

        String CoinCountStr = request.getParameter("coinCount");
        plInfo.info_CoinCount = Integer.valueOf(CheckIfNotEmpty0(CoinCountStr));

        String LastLevelStr = request.getParameter("lastLevel");
        plInfo.info_LastLevel = Integer.valueOf(CheckIfNotEmpty0(LastLevelStr));

        String RedDiamondCountStr = request.getParameter("redDiamondCount");
        plInfo.info_RedDiamondCount = Integer.valueOf(CheckIfNotEmpty0(RedDiamondCountStr));

        String GreenDiamondCountStr = request.getParameter("greenDiamondCount");
        plInfo.info_GreenDiamondCount = Integer.valueOf(CheckIfNotEmpty0(GreenDiamondCountStr));

        String BlueDiamondCountStr = request.getParameter("blueDiamondCount");
        plInfo.info_BlueDiamondCount = Integer.valueOf(CheckIfNotEmpty0(BlueDiamondCountStr));

        action = request.getParameter("action");

        if(action != null){
            try{
                HttpClient hc = HttpClients.createDefault();
                System.out.println(action);
                if(action.equals("Find")){
                    Player foundPl = playerRepository.findByLogin(plInfo.info_Login);

                    if(foundPl != null){
                        model.addAttribute("playerLogin", foundPl.getLogin());
                        model.addAttribute("playerPassword", foundPl.getPassword());
                        model.addAttribute("playerCoinCount", foundPl.getCoinCount());
                        model.addAttribute("playerLastLevel", foundPl.getLastLevel());
                        model.addAttribute("playerRedDiamondCount", foundPl.getRedDiamondCount());
                        model.addAttribute("playerGreenDiamondCount", foundPl.getGreenDiamondCount());
                        model.addAttribute("playerBlueDiamondCount", foundPl.getBlueDiamondCount());
                    }
                    else{
                        model.addAttribute("playerLogin", "NOT FOUND");
                        model.addAttribute("playerPassword", "NOT FOUND");
                        model.addAttribute("playerCoinCount", "NOT FOUND");
                        model.addAttribute("playerLastLevel", "NOT FOUND");
                        model.addAttribute("playerRedDiamondCount", "NOT FOUND");
                        model.addAttribute("playerGreenDiamondCount", "NOT FOUND");
                        model.addAttribute("playerBlueDiamondCount", "NOT FOUND");
                    }
                }
                else if(action.equals("Add")){
                    Player player = new Player(
                            plInfo.info_Login,
                            plInfo.info_Password,
                            plInfo.info_LastLevel,
                            plInfo.info_CoinCount,
                            plInfo.info_RedDiamondCount,
                            plInfo.info_GreenDiamondCount,
                            plInfo.info_BlueDiamondCount
                    );

                    if(player != null){
                        playerRepository.save(player);
                    }
                }
                else if(action.equals("Update")){
                    Player existPlayer;
                    if((existPlayer = playerRepository.findByLogin(plInfo.info_Login)) != null) {

                        existPlayer.setLogin(plInfo.info_Login);
                        existPlayer.setPassword(plInfo.info_Password);
                        existPlayer.setRedDiamondCount(plInfo.info_RedDiamondCount);
                        existPlayer.setGreenDiamondCount(plInfo.info_GreenDiamondCount);
                        existPlayer.setBlueDiamondCount(plInfo.info_BlueDiamondCount);
                        existPlayer.setCoinCount(plInfo.info_CoinCount);
                        existPlayer.setLastLevel(plInfo.info_LastLevel);

                        playerRepository.save(existPlayer);
                    }
                    else{
                        System.out.println("WEB CLIENT UPDATE: Player with login " + plInfo.info_Login + " NOOOOOT found.");
                    }
                }
                else if(action.equals("Delete")) {
                    System.out.println("WEB CLIENT DELETE: Login " + plInfo.info_Login);
                    playerRepository.deleteByLogin(plInfo.info_Login);
                }else{
                    System.out.println("ERROR: INVALID ACTION - WRONG");
                    throw new java.io.IOException();
                }
            }
            catch(java.io.IOException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("ERROR: INVALID ACTION - NULL");
        }

        return("hello");
    }



/*
    @RequestMapping(value = "/web_client_action")
    public void WebClientAction(
                    HttpServletRequest request,
                    HttpServletResponse response)
    {
        plInfo.info_Login = request.getParameter("login");
        plInfo.info_Password = request.getParameter("password");

        String CoinCountStr = request.getParameter("coinCount");
        plInfo.info_CoinCount = Integer.valueOf(CheckIfNotEmpty0(CoinCountStr));

        String LastLevelStr = request.getParameter("lastLevel");
        plInfo.info_LastLevel = Integer.valueOf(CheckIfNotEmpty0(LastLevelStr));

        String RedDiamondCountStr = request.getParameter("redDiamondCount");
        plInfo.info_RedDiamondCount = Integer.valueOf(CheckIfNotEmpty0(RedDiamondCountStr));

        String GreenDiamondCountStr = request.getParameter("greenDiamondCount");
        plInfo.info_GreenDiamondCount = Integer.valueOf(CheckIfNotEmpty0(GreenDiamondCountStr));

        String BlueDiamondCountStr = request.getParameter("blueDiamondCount");
        plInfo.info_BlueDiamondCount = Integer.valueOf(CheckIfNotEmpty0(BlueDiamondCountStr));

        action = request.getParameter("action");

        if(action != null){
            try{
                HttpClient hc = HttpClients.createDefault();
                System.out.println(action);
                if(action.equals("Find")){

                    String uri = AppURL + "player/" + plInfo.info_Login;

                    System.out.println(uri);

                    HttpGet gm = new HttpGet(uri);

                    HttpResponse localResponse =  hc.execute(gm);
                    HttpEntity entity = localResponse.getEntity();

                    response.setContentType("text/html");
                    PrintWriter pw = response.getWriter();
                    pw.println(EntityUtils.toString(entity));

                    pw.flush();

                    System.out.println(EntityUtils.toString(entity));
                }
                else if(action.equals("Add")){
                    System.out.println("IN ADD");
                    String uri = AppURL + "players";
                    System.out.println(uri);

                    String DataJSON = "{" +
                            "\"login\": \"" + plInfo.info_Login + "\"," +
                            "\"password\": \"" + plInfo.info_Password + "\"," +
                            "\"lastLevel\": \"" + plInfo.info_LastLevel + "\"," +
                            "\"coinCount\": \"" + plInfo.info_CoinCount + "\"," +
                            "\"redDiamondCount\": \"" + plInfo.info_RedDiamondCount + "\"," +
                            "\"greenDiamondCount\": \"" + plInfo.info_GreenDiamondCount + "\"," +
                            "\"blueDiamondCount\": \"" + plInfo.info_BlueDiamondCount + "\"" +
                            "}";

                    HttpPost postm = new HttpPost(uri);
                    postm.setHeader("Content-Type", "application/json");
                    HttpEntity entity = new ByteArrayEntity(DataJSON.getBytes("UTF-8"));
                    postm.setEntity(entity);

                    HttpResponse localResponse = hc.execute(postm);

                }
                else if(action.equals("Update")){
                    String uri = AppURL + "player/" + plInfo.info_Login;
                    System.out.println(uri);

                    String DataJSON = "{" +
                            "\"login\": \"" + plInfo.info_Login + "\"," +
                            "\"password\": \"" + plInfo.info_Password + "\"," +
                            "\"lastLevel\": \"" + plInfo.info_LastLevel + "\"," +
                            "\"coinCount\": \"" + plInfo.info_CoinCount + "\"," +
                            "\"redDiamondCount\": \"" + plInfo.info_RedDiamondCount + "\"," +
                            "\"greenDiamondCount\": \"" + plInfo.info_GreenDiamondCount + "\"," +
                            "\"blueDiamondCount\": \"" + plInfo.info_BlueDiamondCount + "\"" +
                            "}";

                    HttpPut putm = new HttpPut(uri);
                    putm.setHeader("Content-Type", "application/json");
                    HttpEntity entity = new ByteArrayEntity(DataJSON.getBytes("UTF-8"));
                    putm.setEntity(entity);

                    HttpResponse localResponse = hc.execute(putm);
                }
                else if(action.equals("Delete")){
                    String uri = AppURL + "player/" + plInfo.info_Login;
                    System.out.println(uri);

                    HttpDelete delm = new HttpDelete(uri);
                    hc.execute(delm);
                }
                else{
                    System.out.println("ERROR: INVALID ACTION - WRONG");
                }
            }
            catch(java.io.IOException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("ERROR: INVALID ACTION - NULL");
        }
    }*/
}
