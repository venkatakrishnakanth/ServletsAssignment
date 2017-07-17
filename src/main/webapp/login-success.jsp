<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success Notification Boxes</title>
  <link rel="shortcut icon" href="favicon.ico">
  <link rel="icon" href="favicon.ico">
  <link rel="stylesheet" type="text/css" media="all" href="style.css">
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">


  <style type="text/css">
     @import url("http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css");
body { margin-top:20px; }
.fa { font-size: 50px;text-align: right;position: absolute;top: 7px;right: 27px;outline: none; }
a { transition: all .3s ease;-webkit-transition: all .3s ease;-moz-transition: all .3s ease;-o-transition: all .3s ease; }
/* Visitor */
a.visitor i,.visitor h4.list-group-item-heading { color:#E48A07; }
a.visitor:hover { background-color:#E48A07; }
a.visitor:hover * { color:#FFF; }
/* Facebook */
a.facebook-like i,.facebook-like h4.list-group-item-heading { color:#3b5998; }
a.facebook-like:hover { background-color:#3b5998; }
a.facebook-like:hover * { color:#FFF; }
/* Google */
a.google-plus i,.google-plus h4.list-group-item-heading { color:#dd4b39; }
a.google-plus:hover { background-color:#dd4b39; }
a.google-plus:hover * { color:#FFF; }

  </style>
</head>
<body style="background:black">

	<h1 style="text-align:center; color:white;"> Login Success!!!!!!!</h1>
	<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="list-group">
                <a href= "Billing.jsp" class="list-group-item visitor">
                    <h3 class="pull-right">
                        <i class="fa fa-eye"></i>
                    </h3>
                    <h4 class="list-group-item-heading count">
                        1000</h4>
                    <p class="list-group-item-text">
                        View Bills</p>
                </a><a href="PayBills.jsp" class="list-group-item facebook-like">
                    <h3 class="pull-right">
                        <i class="fa fa-credit-card" aria-hidden="true"></i>
                    </h3>
                    <h4 class="list-group-item-heading count">
                        1000</h4>
                    <p class="list-group-item-text">
                        Pay Bills</p>
                </a><a href="Update.jsp" class="list-group-item google-plus">
                    <h3 class="pull-right">
                        <i class="fa fa-envelope"></i>
                    </h3>
                    <h4 class="list-group-item-heading count">
                        1000</h4>
                    <p class="list-group-item-text">
                        Update Email</p>
                
                </a>
            </div>
        </div>
        
    </div>
</div>
<%
		String info = request.getAttribute("status").toString();
		out.print(info);
	%>
	
	
	
</body>
</html>