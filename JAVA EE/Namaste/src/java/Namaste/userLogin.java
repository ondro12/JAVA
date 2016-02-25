package Namaste;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Matúš Ondris
 * sluzi na zobrazenie sprav a funkcii po tom ako sa uzivatel prihlasi 
 * je mozne prezerat spravy resp. ich kompletnu historiu a po potvrdeni pridania noveho prispevku
 * zavola chatWindow
 */

@WebServlet(name = "userLogin", urlPatterns = {"/userLogin"})
public class userLogin extends HttpServlet {
    
public String username;
public int pocetprispevkov = 5;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=utf-8");
        
        username=request.getParameter("user"); //Ziskanie prihlasovacieho mena

        PrintWriter out=response.getWriter();
        
        try{            
           session=request.getSession();
           session.setAttribute("username",request.getParameter("user")); //nastavenie atributu
           String username = session.getAttribute("username").toString(); //Extrahujem uzivatelske prihlasovacie meno
       
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
                         
         // ziskam vsetky spravy z db       
            try{
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_chat","root","");
             
                Statement st=con.createStatement();                 
                ResultSet rs=st.executeQuery("select *from hello_message ORDER BY id desc ");
            
                // vypis kompletnej historie sprav
                while(rs.next()){
                    String messages =rs.getString(1)+ ": " + rs.getString(2);
                    out.println(messages);
                }
                con.close();
            }
            catch(Exception ex1){
                System.err.println(ex1.getMessage());
            }
         
         out.println("</textarea>");
         out.println("<hr>");
         out.println("</form>");
         out.println("</body>");
         out.println("</html>");
                   
        }
        
        catch(Exception e){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet </title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Sevlet failed</h1>");
            out.println("</body>");
            out.println("</html>");
            System.out.println(e);
        }
                  
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Tento servlet sluzi na zobrazenie sprav v chate potom ako sa uzivatel prihlasi";
    }// </editor-fold>
HttpSession session;
}


