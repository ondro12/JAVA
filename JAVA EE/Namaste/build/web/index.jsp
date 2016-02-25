<%-- 
    Document   : index
    Created on : 22.2.2016, 22:33:05
    Author     : Ondro
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>jednoducha chat aplikacia</title>
    </head>
    
    <body bgcolor="#16c41b">
         <center>
        <h1>jednoduchy chat</h1>
        <br>
        
        <h2>Prihlasenie</h2>
        <form name="userLogin" action="userLogin" method="POST">
        
            <table border="0" width="30" cellspacing="8" cellpadding="20">

                <tbody>
                    <tr>
                        <td>Meno:</td>
                        <td><input type="text" name="user" value="anonym" /></td>
                    </tr>
                    
                </tbody>
            </table>   
            <input type="submit" value="Vstup" name="log in" /> 
            
        </form>
         
       </center>
    </body>
</html>