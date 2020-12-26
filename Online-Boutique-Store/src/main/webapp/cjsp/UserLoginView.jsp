<%@page import="onlineboutiquestore.controller.UserLoginCtl"%>
<%@page import="onlineboutiquestore.util.DataUtility"%>
<%@page import="onlineboutiquestore.util.ServletUtility"%>
<%@page import="onlineboutiquestore.controller.SOTGView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">LOGIN</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=SOTGView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=SOTGView.USER_LOGIN_CTL%>">Login</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
    <div class="col-12 col-sm-3">
               

            </div>
        <div class="col">
            <div class="card">
                <div class="card-header bg-primary text-white"> Login
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
                </div>
                
                <div class="card-body">
                   <form action="<%=SOTGView.LOGIN_CTL%>" method="post">
                   
                   <jsp:useBean id="bean" class="onlineboutiquestore.bean.UserBean"
						scope="request"></jsp:useBean>
						
						<% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
						
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                   
                        
                        
                        <div class="form-group">
                            <label for="email">Login Id</label>
                            <input type="text" class="form-control" name="login" placeholder="Enter Login Id..." 
                                value="<%=DataUtility.getStringData(bean.getLogin())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" placeholder="Enter Password Here.." 
                                value="<%=DataUtility.getStringData(bean.getPassword())%>" >
                                <font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
                        </div>
                        
                        
                       
                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=UserLoginCtl.OP_SIGN_IN%>">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=UserLoginCtl.OP_SIGN_UP%>"></div>
                        
                        <div class="mx-auto">
                        <a href="<%=SOTGView.FORGET_PASSWORD_CTL%>">Forget Your Password?</a></div>
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