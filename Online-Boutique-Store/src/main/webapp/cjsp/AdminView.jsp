<%@page import="onlineboutiquestore.controller.AdminCtl"%>
<%@page import="onlineboutiquestore.util.DataUtility"%>
<%@page import="onlineboutiquestore.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Add New tailor</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=SOTGView.HOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=SOTGView.ADMIN_CTL%>">Add Admin</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<form action="<%=SOTGView.ADMIN_CTL%>" method="post">
                   
                   <jsp:useBean id="bean" class="onlineboutiquestore.bean.UserBean"
						scope="request"></jsp:useBean>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <%if(bean.getId()>0){%>
                <div class="card-header bg-primary text-white">Update Admin
				<%}else{ %>
				<div class="card-header bg-primary text-white">Add Admin</div>
				<%} %>                
                
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
                <div class="card-body">
                   
						
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
                   
                        <div class="form-group">
                            <label for="name">First Name</label>
                            <input type="text" class="form-control" name="firstName" placeholder="Enter First Name..." 
                                value="<%=DataUtility.getStringData(bean.getFirstName())%>" >
                                <font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
                        </div>
                        <div class="form-group">
                            <label for="name">Last Name</label>
                            <input type="text" class="form-control" name="lastName" placeholder="Enter Last Name..." 
                                alue="<%=DataUtility.getStringData(bean.getLastName())%>" >
                            <font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
                        </div>
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
                        
                        <div class="form-group">
                            <label for="password">Confirm Password</label>
                            <input type="password" class="form-control" name="confirmPassword" placeholder="ReEnter Password Here.." 
                                value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>" >
                                <font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email  Address</label>
                            <input type="text" class="form-control" name="email" placeholder="Enter Email Address..." 
                                value="<%=DataUtility.getStringData(bean.getLogin())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("email", request)%></font>
                        </div>
                        
                        <div class="form-group">
                            <label for="name">Mobile No.</label>
                            <input type="text" class="form-control" name="mobile" placeholder="Enter 10 Digits mobile No."
                                value="<%=DataUtility.getStringData(bean.getMobileNo())%>" >
                                <font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
                        </div>
                        
                       
                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=AdminCtl.OP_SAVE%>">
                                <% if(bean.getId()>0){ %>
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=AdminCtl.OP_CANCEL%>">
                        <%}else{ %>
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=AdminCtl.OP_RESET%>">
                        <%} %>
                        </div>
                   
                </div>
            </div>
            
             </div>
        <div class="col-12 col-sm-4">
               

            </div>
        </div>
        </div>
        </form>
   

<br>
<%@ include file="Footer.jsp" %>
</body>
</html>