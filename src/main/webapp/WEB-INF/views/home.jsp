<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang = "pl">
<head>
    <title> Strona główna wojska </title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/main.css" />
    <link href = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel = "stylesheet" id = "bootstrap-css">
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/main.js"></script>
</head>
<body>
<div class = "container">
    <div class = "row">
        <div class = "col-md-6 col-md-offset-3">
            <div class = "panel panel-login">
                <div class = "panel-heading">
                    <div class = "row">
                        <div class = "col-xs-6">
                            <a href = "#" class = "active" id = "login-form-link"> Logowanie </a>
                        </div>
                        <div class = "col-xs-6">
                            <a href = "#" id = "register-form-link"> Rejestracja </a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class = "panel-body">
                    <div class = "row">
                        <div class = "col-lg-12">
                            <div id = "login">
                                <form id = "login-form" action = "/login" method = "POST" role = "form" style = "display: block;">
                                    <div class = "form-group">
                                        <div class = "input-group">
                                            <span class = "input-group-addon"> <i class = "glyphicon glyphicon-user"> </i> </span>
                                            <input type = "text" placeholder = "Login" id = "username" name = "username" tabindex = "1" class = "form-control" required>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "input-group">
                                            <span class = "input-group-addon"> <i class = "glyphicon glyphicon-lock"> </i> </span>
                                            <input type = "password" placeholder = "Hasło" id = "password" name = "password" tabindex = "2" class = "form-control" required>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "row">
                                            <div class = "col-sm-6 col-md-offset-3">
                                                <input type = "submit" value = "Zaloguj" name = "login-submit" id = "login-submit" tabindex = "4" class = "form-control btn btn-login">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div id = "register">
                                <form id = "register-form" action = "/register" method = "POST" role = "form" style = "display: none;">
                                    <div class = "form-group">
                                        <select class = "form-control" id = "select">
                                            <c:forEach var="s" items="${soldiers}">
                                                <option value = "${s.soldierId}"> ${s.firstName} ${s.lastName} </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "input-group">
                                            <span class = "input-group-addon"> <i class = "glyphicon glyphicon-user"> </i> </span>
                                            <input type = "text" placeholder = "Nazwa użytkownika" name = "username" id = "username1" onKeyUp = "checkUsername();check()" tabindex = "1" class = "form-control" required>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "input-group">
                                            <span class = "input-group-addon"> <i class = "glyphicon glyphicon-envelope"> </i> </span>
                                            <input type = "email" placeholder = "E-mail" name = "email" id = "email1" onKeyUp = "checkEmail();check()" tabindex = "1" class = "form-control" required>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "input-group">
                                            <span class = "input-group-addon"> <i class = "glyphicon glyphicon-lock"> </i> </span>
                                            <input type = "password" placeholder = "Hasło" name = "password" id = "password1" onKeyUp = "passwordMatch();check()" tabindex = "2" class = "form-control" required>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "input-group">
                                            <span class = "input-group-addon"> <i class = "glyphicon glyphicon-lock"> </i> </span>
                                            <input type = "password" placeholder = "Powtórz hasło" name = "confirmPassword" id = "confirmPassword" onKeyUp = "passwordMatch();check()" tabindex = "2" class = "form-control" required>
                                        </div>
                                    </div>
                                    <div class = "form-group">
                                        <div class = "row">
                                            <div class = "col-sm-6 col-sm-offset-3">
                                                <input type = "submit" value = "Zarejestruj" name = "registerSubmit" id = "register-submit" tabindex = "4" class = "form-control btn btn-register">
                                            </div>
                                        </div>
                                    </div>
                                    <div class = "row">
                                        <div class = "col-md-10 col-md-offset-1 text-center">
                                            <div id = "usernameResponse"></div>
                                            <div id = "emailResponse"></div>
                                            <div id = "passwordResponse"></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
