<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Email</title>
<link rel="stylesheet" type="text/css" media="all" href="style.css">
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">


  <style type="text/css">
  	body {
        background: #2B3238;
    }
    
    .loginbox {
        width: 250px;
        height: auto;
        margin: auto;
        margin-top: 10%;
    }
    
    .loginbox input {
        border: none;
        background: #fff;
        font-family: Lato;
        font-weight: 700;
        display: block;
        height: 40px;
        outline: none;
        width: 100%;
        margin: auto;
        padding: 6px 12px 6px 12px;
    }
    .loginbox input[type="text"] {
        border-radius: 5px 5px 0px 0px;
    }
    
    .loginbox input[type="password"] {
        border-radius: 0px 0px 5px 5px;
    }
    
    a.forgot_link {
        color: #666;
        text-decoration: none;
        font-size: 11px;
        position: relative;
        left: 193px;
        top: -31px;
    }
    a.forgot_link:hover {
        text-decoration:none;
        color: #111;
        
    }
    
    .loginbox button {
        width: 100%;
        border-radius: 5px;
        background: #EA4C89;
        text-decoration: center;
        border: none;
        color: #ffffff;
        margin-top: -5px;
        padding-top: 10px;
        padding-bottom: 10px;
        outline: none;
        font-size: 16px;
        border-bottom: 3px solid #E8377E;
        cursor: pointer;
    }
    .separtor {
        width: 100%;
        height: 1px;
        background: #fff;
    }
    
    .separtor i {
        width: 95%;
        margin: auto;
        height: 1px;
        display: block;
        background: #d1d1d1;
    }
    
    
  </style>
</head>
<body>
	<h1 style="color: white; text-align: center;">Enter username, password and your new email address</h1>

	<form action="update.do" method="post">
			<div class="loginbox">
			<input type="text" placeholder="username" name="Username" required  ><br>
			<div class="separtor">
			    <i></i>
			</div>
			<input type="password" placeholder="password" name="Password"  required ><br>
			<div class="separtor">
			    <i></i>
			</div>
			<input type="email" placeholder="new email address" name="Email" required><br>

			<button type="submit" name="Submit">Update Email</button>
			</div>
	</form>

</body>
</html>