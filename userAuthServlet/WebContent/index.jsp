<html>
<head>
    <title>Quick Servlet Demo</title>
</head>
<body>
    <a href="./serv">Click here to send GET request</a>
     
    <br/><br/>
     
    <form action="serv" method="post">
        Width: <input type="text" size="5" name="width"/>
        Height <input type="text" size="5" name="height"/>
        <input type="submit" value="Calculate" />
    </form>
</body>
</html>
<!-- jar epp.war -C WebContent .   -->
<!-- javac -cp .\lib\servlet-api.jar -d .\WebContent\WEB-INF\classes .\src\servlets\Servlet.java -->