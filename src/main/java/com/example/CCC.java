package com.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.util.EntityUtils;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by GOREVOJD on 03.05.2017.
 */
public class CCC extends HttpServlet {
/*
    public static final String ServerName = "floating-forest-12376.herokuapp.com";
    public static final String ProtocolName = "https";
*/

//??????????????????
    public static final String ServerName = "localhost";
    public static final String ProtocolName = "http";

    public static final String AppURL = ProtocolName + "://" + ServerName + "/";

    public String action;

    public String info_Login;
    public String info_Password;
    public int info_CoinCount;
    public int info_LastLevel;
    public int info_RedDiamondCount;
    public int info_GreenDiamondCount;
    public int info_BlueDiamondCount;

    @Override
    public void init() throws ServletException
    {
        super.init();


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
       /* info_Login = request.getParameter("login");
        info_Password = request.getParameter("password");
        info_CoinCount = Integer.valueOf(request.getParameter("coinCount"));
        info_LastLevel = Integer.valueOf(request.getParameter("lastLevel"));
        info_RedDiamondCount = Integer.valueOf(request.getParameter("redDiamondCount"));
        info_GreenDiamondCount = Integer.valueOf(request.getParameter("greenDiamondCount"));
        info_BlueDiamondCount = Integer.valueOf(request.getParameter("blueDiamondCount"));

        action = request.getParameter("action");

        if(action != null){
            try{
                HttpClient hc = HttpClients.createDefault();
                if(action.equals("Find")){

                    String uri = AppURL + "player/" + info_Login;

                    HttpGet gm = new HttpGet(uri);

                    HttpResponse localResponse =  hc.execute(gm);
                    HttpEntity entity = localResponse.getEntity();

                    *//*
                    response.setContentType("text/html");
                    PrintWriter pw = response.getWriter();
                    pw.println(EntityUtils.toString(entity));

                    pw.flush();
                    *//*

                    System.out.println(EntityUtils.toString(entity));
                }
                else if(action.equals("Add")){
                    String uri = AppURL + "players";

                    String DataJSON = "{" +
                            "\"login\": \"" + info_Login + "\"," +
                            "\"password\": \"" + info_Password + "\"," +
                            "\"lastLevel\": \"" + info_LastLevel + "\"," +
                            "\"coinCount\": \"" + info_CoinCount + "\"," +
                            "\"redDiamondCount\": \"" + info_RedDiamondCount + "\"," +
                            "\"greenDiamondCount\": \"" + info_GreenDiamondCount + "\"," +
                            "\"blueDiamondCount\": \"" + info_BlueDiamondCount + "\"" +
                            "}";

                    HttpPost postm = new HttpPost(uri);
                    postm.setHeader("Content-Type", "application/json");
                    HttpEntity entity = new ByteArrayEntity(DataJSON.getBytes("UTF-8"));
                    postm.setEntity(entity);

                    HttpResponse localResponse = hc.execute(postm);

                }
                else if(action.equals("Update")){
                    String uri = AppURL + "player/" + info_Login;

                    String DataJSON = "{" +
                            "\"login\": \"" + info_Login + "\"," +
                            "\"password\": \"" + info_Password + "\"," +
                            "\"lastLevel\": \"" + info_LastLevel + "\"," +
                            "\"coinCount\": \"" + info_CoinCount + "\"," +
                            "\"redDiamondCount\": \"" + info_RedDiamondCount + "\"," +
                            "\"greenDiamondCount\": \"" + info_GreenDiamondCount + "\"," +
                            "\"blueDiamondCount\": \"" + info_BlueDiamondCount + "\"" +
                            "}";

                    HttpPut putm = new HttpPut(uri);
                    putm.setHeader("Content-Type", "application/json");
                    HttpEntity entity = new ByteArrayEntity(DataJSON.getBytes("UTF-8"));
                    putm.setEntity(entity);

                    HttpResponse localResponse = hc.execute(putm);
                }
                else if(action.equals("Delete")){
                    String uri = AppURL + "player/" + info_Login;

                    HttpDelete delm = new HttpDelete(uri);
                    hc.execute(delm);
                }
            }
            catch(java.io.IOException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("ERROR: INVALID ACTION");
        }*/
    }

}
