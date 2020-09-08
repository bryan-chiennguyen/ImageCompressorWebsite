<%-- 
    Document   : results
    Created on : Sep 6, 2020, 10:50:19 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div>
            <div id="first-part"><jsp:include page="index.html"/></div>
            <br><br>
            <div>
                <div id="second-part">
                    <form id="display-form">
                    <label for="compressor-output">Your Image Compressor Output: </label>
                    <br>
                    <% String answer = (String)request.getAttribute("finalVal"); %>
                    <textarea class="text-area" id="compressor-output" rows="20" cols="60" name="compressor-output"><%=answer%></textarea>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
