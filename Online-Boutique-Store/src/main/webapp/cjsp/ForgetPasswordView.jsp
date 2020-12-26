<%@page import="onlineboutiquestore.controller.ForgetPasswordCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Forget Password</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=SOTGView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item "><a href="<%=SOTGView.LOGIN_CTL%>">Login</a></li>
                     <li class="breadcrumb-item active"><a href="<%=SOTGView.FORGET_PASSWORD_CTL%>">Forget Password</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
    
        <div class="col">
            <div class="card">
                <div class="card-header bg-primary text-white"> Forget Password
                
                </div>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
                <div class="card-body">
                   <form action="<%=SOTGView.FORGET_PASSWORD_CTL%>" method="post">
                   
                   <jsp:useBean id="bean" class="onlineboutiquestore.bean.UserBean"
						scope="request"></jsp:useBean>
						
						
						
			<input type="hidden" name="id" value="<%=bean.getId()%>">
                   
                          <div class="form-group">
                            <label for="password">Submit your email address and we'll send you password.</label>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email Id</label>
                            <input type="text" class="form-control" name="login" placeholder="Enter Email Id..." 
                                value="<%=ServletUtility.getParameter("login", request)%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                        </div>
                      

                        
                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=ForgetPasswordCtl.OP_GO%>">
                        
                       </div>
                    </form>
                </div>
            </div>
            
             </div>
        <div class="col-12 col-sm-3">
               

            </div>
        </div>
        </div>
       
   

<br>
<%@ include file="Footer.jsp" %>
</body>
</html>