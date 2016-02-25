/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Namaste;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Matus Ondris
 * sluzi na pridavanie novych sprav + zobrazovanie predchadzajucich sprav
 */

@WebServlet(name = "chatWindow", urlPatterns = {"/chatWindow"})
public class chatWindow extends HttpServlet {
       
String username,tempName;
int pocetprispevkov = 5;  
int id;
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        // nastavenie typu pripojenia , co sa bude extrahovat
        response.setContentType("text/html;charset=utf-8");
        
        //pokusi sa ziskat spravu
        try (PrintWriter out = response.getWriter()) {
            
         String message = request.getParameter("txtMsg");  //ziskam spravu ktoru chcem vkladat
         String username = session.getAttribute("username").toString(); //ziskam pouzivatelske meno kto spravu pridal
        
         //jednoduche zobrazenie stranky
         out.println("<html>  <head> <body bgcolor=\"#16c41b\"> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> <title>Chatovacia miestnost</title>  </head>");
         out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> <center>");
         out.println("<h2>Ahoj ");
         out.println(username);
         out.println("<br>Vitaj v aplikacii jednoduchy chat");
         out.println("</h2><br><hr>");
         out.println("<body>");
         out.println("<form name=\"chatWindow\" action=\"chatWindow\">");
         out.println("Sprava: <input type=\"text\" name=\"txtMsg\" value=\"\" /><input type=\"submit\" value=\"Pridaj\" name=\"cmdSend\"/>");   
         out.println("<br> <a href=\"chatWindow\">Refresh</a>");
         out.println("<br>");
         out.println("Spravy v chate:");
         out.println("<br>");
         out.println("<textarea  readonly=\"readonly\"   name=\"txtMessage\" rows=\"20\" cols=\"60\">");
                  
         //ak ma vkladana sprava hodnotu null nevklada sa do databazy avsak nikdz k tomu nedojde aj prayna sprava ma hodnotu
        if(request.getParameter("txtMsg")!= null){
                       
            // ziskam id posledneho vkladaneho prispevku(najvacsiu) z databazy
            try{
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_chat","root","");
              
                Statement st=con.createStatement();     
                ResultSet rs=st.executeQuery("select *from hello_message");
                
                id = 0;
                // id ziskam tym , ze spocitam pocet vsetkych riadkov v databaze a navysim ho o 1
                while(rs.next()){
                    id++;
                }
            }
            catch(Exception ex1) {
                System.err.println(ex1.getMessage());
            }

            // vkladanie noveho prispevku do databazy
            try{
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_chat","root","");

                Statement st=con.createStatement();  
                String sql = "insert into hello_message values ('"+username+"','"+message+"','"+id+"')";
                st.executeUpdate(sql);

                st.execute("commit");

             }
            catch(Exception ex1){
               System.err.println(ex1.getMessage());
               String messages = "Chyba";
               out.println(messages);
            }
        }
        
         // ziskam a vypisem vsetky spravy z db        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_chat","root","");       
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select *from hello_message ORDER BY id DESC"); 
            
            // vypisem vsetky spravy
            while(rs.next()){
                String messages =rs.getString(1)+ ": " + rs.getString(2); 
                out.println(messages);
            }     
            con.close();
        }
        catch(Exception ex1) {
            System.err.println(ex1.getMessage());
        }
         
        out.println("</textarea>");
        out.println("<hr>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        //aktualne sedenie (session)
        session = request.getSession();
        
        if(username!=null){
            tempName=username;
        }
        
        processRequest(request, response);       
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet slzi na vkladanie novych prispevkov do chatu";
    }
 HttpSession session;
}
