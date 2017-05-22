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
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GOREVOJD on 05.05.2017.
 */

@Controller
public class WebClientController {

    TempPlayerInfo plInfo = new TempPlayerInfo();

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

    static TempPlayerInfo getPlayerInfoFromRequest(HttpServletRequest request){
        TempPlayerInfo playerInfo = new TempPlayerInfo();

        playerInfo.info_Login = request.getParameter("login");
        playerInfo.info_Password = request.getParameter("password");

        String CoinCountStr = request.getParameter("coinCount");
        playerInfo.info_CoinCount = Integer.valueOf(CheckIfNotEmpty0(CoinCountStr));

        String LastLevelStr = request.getParameter("lastLevel");
        playerInfo.info_LastLevel = Integer.valueOf(CheckIfNotEmpty0(LastLevelStr));

        String RedDiamondCountStr = request.getParameter("redDiamondCount");
        playerInfo.info_RedDiamondCount = Integer.valueOf(CheckIfNotEmpty0(RedDiamondCountStr));

        String GreenDiamondCountStr = request.getParameter("greenDiamondCount");
        playerInfo.info_GreenDiamondCount = Integer.valueOf(CheckIfNotEmpty0(GreenDiamondCountStr));

        String BlueDiamondCountStr = request.getParameter("blueDiamondCount");
        playerInfo.info_BlueDiamondCount = Integer.valueOf(CheckIfNotEmpty0(BlueDiamondCountStr));

        return(playerInfo);
    }

    private final PlayerRepository playerRepository;

    public WebClientController(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }


    /*
    @RequestMapping(
            value = "/web_client_action",
            method = RequestMethod.GET,
            params = {"login", "password", "coinCount", "redDiamondCount", "greenDiamondCount", "blueDiamondCount"})
    public String getPlayerByLogin(HttpServletRequest request, Model model){

        plInfo = getPlayerInfoFromRequest(request);

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

        System.out.println("WEB CLIENT GET: Login " + plInfo.info_Login);

        return("hello");
    }

    @RequestMapping(value = "/web_client_action", method =RequestMethod.POST)
    public String addPlayerInfo(HttpServletRequest request, Model model) {

        plInfo = getPlayerInfoFromRequest(request);

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

        return("hello");
    }

    @RequestMapping(value = "/web_client_action", method =RequestMethod.PUT)
    public String updatePlayerInfo(HttpServletRequest request, Model model) {

        plInfo = getPlayerInfoFromRequest(request);

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

        return("hello");
    }

    @RequestMapping(
            value="/web_client_action",
            method = RequestMethod.DELETE,
            params = {"login", "password", "coinCount", "redDiamondCount", "greenDiamondCount", "blueDiamondCount"})
    public String deletePlayerByLogin(HttpServletRequest request, Model model){

        plInfo = getPlayerInfoFromRequest(request);

        System.out.println("WEB CLIENT DELETE: Login " + plInfo.info_Login);
        playerRepository.deleteByLogin(plInfo.info_Login);

        return("hello");
    }
    */


    @RequestMapping(value = "/web_client_action")
    public String WebClientAction(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model)
    {

        plInfo = getPlayerInfoFromRequest(request);

        action = request.getParameter("action");

        if (action != null) {
            try {
                HttpClient hc = HttpClients.createDefault();
                System.out.println(action);
                if (action.equals("Find")) {
                    Player foundPl = playerRepository.findByLogin(plInfo.info_Login);

                    if (foundPl != null) {
                        model.addAttribute("playerLogin", foundPl.getLogin());
                        model.addAttribute("playerPassword", foundPl.getPassword());
                        model.addAttribute("playerCoinCount", foundPl.getCoinCount());
                        model.addAttribute("playerLastLevel", foundPl.getLastLevel());
                        model.addAttribute("playerRedDiamondCount", foundPl.getRedDiamondCount());
                        model.addAttribute("playerGreenDiamondCount", foundPl.getGreenDiamondCount());
                        model.addAttribute("playerBlueDiamondCount", foundPl.getBlueDiamondCount());
                    } else {
                        model.addAttribute("playerLogin", "NOT FOUND");
                        model.addAttribute("playerPassword", "NOT FOUND");
                        model.addAttribute("playerCoinCount", "NOT FOUND");
                        model.addAttribute("playerLastLevel", "NOT FOUND");
                        model.addAttribute("playerRedDiamondCount", "NOT FOUND");
                        model.addAttribute("playerGreenDiamondCount", "NOT FOUND");
                        model.addAttribute("playerBlueDiamondCount", "NOT FOUND");
                    }
                }else if(action.equals("FindAll")) {

                    List<Player> players = playerRepository.findAll();

                    response.setContentType("text/html");

                    PrintWriter pw = response.getWriter();

                    pw.write("<html><body>");
                    pw.write("<table border=\"2\" bgcolor=\"#FFA500\" bordercolor=\"black\">");

                    pw.write("<tr>" +
                            "<td>login</td>" +
                            "<td>password</td>" +
                            "<td>lastLevel</td>" +
                            "<td>coinCount</td>" +
                            "<td>redDiamonds</td>" +
                            "<td>greenDiamonds</td>" +
                            "<td>blueDiamonds</td>" +
                            "</tr>");

                    for (int PlayerIndex = 0;
                         PlayerIndex < players.size();
                         PlayerIndex++)
                    {
                        pw.write("<tr>");

                        pw.write(
                                "<td>" + players.get(PlayerIndex).getLogin() + "</td>" +
                                        "<td>" + players.get(PlayerIndex).getPassword() + "</td>" +
                                        "<td>" + players.get(PlayerIndex).getLastLevel() + "</td>" +
                                        "<td>" + players.get(PlayerIndex).getCoinCount() + "</td>" +
                                        "<td>" + players.get(PlayerIndex).getRedDiamondCount() + "</td>" +
                                        "<td>" + players.get(PlayerIndex).getBlueDiamondCount() + "</td>" +
                                        "<td>" + players.get(PlayerIndex).getGreenDiamondCount() + "</td>");

                        pw.write("</tr>");
                    }

                    pw.write("</table>");
                    pw.write("</body></html>");

                    pw.close();
                }else if (action.equals("DeleteAll")){

                    playerRepository.deleteAll();

                    //Everything deleted
                    model.addAttribute("playerLogin", "EVERYTHING DELETED");
                    model.addAttribute("playerPassword", "EVERYTHING DELETED");
                    model.addAttribute("playerCoinCount", "EVERYTHING DELETED");
                    model.addAttribute("playerLastLevel", "EVERYTHING DELETED");
                    model.addAttribute("playerRedDiamondCount", "EVERYTHING DELETED");
                    model.addAttribute("playerGreenDiamondCount", "EVERYTHING DELETED");
                    model.addAttribute("playerBlueDiamondCount", "EVERYTHING DELETED");

                } else if (action.equals("Add")) {
                    Player player = new Player(
                            plInfo.info_Login,
                            plInfo.info_Password,
                            plInfo.info_LastLevel,
                            plInfo.info_CoinCount,
                            plInfo.info_RedDiamondCount,
                            plInfo.info_GreenDiamondCount,
                            plInfo.info_BlueDiamondCount
                    );


                    Player foundPlayerAdd = playerRepository.findByLogin(player.getLogin());
                    if (foundPlayerAdd == null) {
                        playerRepository.save(player);
                    }
                    else{
                        //Player alredy exist
                        model.addAttribute("playerLogin", "PLAYER ALREDY EXIST");
                        model.addAttribute("playerPassword", "PLAYER ALREDY EXIST");
                        model.addAttribute("playerCoinCount", "PLAYER ALREDY EXIST");
                        model.addAttribute("playerLastLevel", "PLAYER ALREDY EXIST");
                        model.addAttribute("playerRedDiamondCount", "PLAYER ALREDY EXIST");
                        model.addAttribute("playerGreenDiamondCount", "PLAYER ALREDY EXIST");
                        model.addAttribute("playerBlueDiamondCount", "PLAYER ALREDY EXIST");
                    }
                } else if (action.equals("Update")) {
                    Player existPlayer;
                    if ((existPlayer = playerRepository.findByLogin(plInfo.info_Login)) != null) {

                        existPlayer.setLogin(plInfo.info_Login);
                        existPlayer.setPassword(plInfo.info_Password);
                        existPlayer.setRedDiamondCount(plInfo.info_RedDiamondCount);
                        existPlayer.setGreenDiamondCount(plInfo.info_GreenDiamondCount);
                        existPlayer.setBlueDiamondCount(plInfo.info_BlueDiamondCount);
                        existPlayer.setCoinCount(plInfo.info_CoinCount);
                        existPlayer.setLastLevel(plInfo.info_LastLevel);

                        playerRepository.save(existPlayer);
                    } else {
                        System.out.println("WEB CLIENT UPDATE: Player with login " + plInfo.info_Login + " NOOOOOT found.");
                    }
                } else if (action.equals("Delete")) {
                    System.out.println("WEB CLIENT DELETE: Login " + plInfo.info_Login);
                    playerRepository.deleteByLogin(plInfo.info_Login);
                } else {
                    System.out.println("ERROR: INVALID ACTION - WRONG");
                    throw new java.io.IOException();
                }
            } catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("ERROR: INVALID ACTION - NULL");
        }

        return ("hello");
    }
}
