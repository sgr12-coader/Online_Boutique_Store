<%@page import="onlineboutiquestore.controller.UserRegistrationCtl"%>
<%@page import="onlineboutiquestore.util.DataUtility"%>
<%@page import="onlineboutiquestore.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Registration</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=SOTGView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item"><a href="<%=SOTGView.LOGIN_CTL%>">Login</a></li>
                    <li class="breadcrumb-item active"><a href="<%=SOTGView.USER_REGISTRATION_CTL%>">Registration</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header bg-primary text-white"> Registration
                
                </div>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
                <div class="card-body">
                   <form action="<%=SOTGView.USER_REGISTRATION_CTL%>" method="post">
                   
                   <jsp:useBean id="bean" class="onlineboutiquestore.bean.UserBean"
						scope="request"></jsp:useBean>
						
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
                        
                        <div class="form-group">
                            <label for="message">Shipping Address</label>
                           <textarea rows="5" class="form-control" name="shippingAdd" placeholder="Enter Shipping Address Here.." ><%=DataUtility.getStringData(bean.getShippingAddress()) %></textarea>
                            <font color="red"> <%=ServletUtility.getErrorMessage("shippingAdd", request)%></font>
                        </div>
                        
                        <div class="form-group">
                            <label for="message">Billing Address</label>
                            <textarea rows="5" class="form-control" name="billingAdd" placeholder="Enter Billing Address Here.." ><%=DataUtility.getStringData(bean.getBillingAddress()) %></textarea>
                            <font color="red"> <%=ServletUtility.getErrorMessage("billingAdd", request)%></font>
                        </div>
                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
                                <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=UserRegistrationCtl.OP_RESET%>"></div>
                    </form>
                </div>
            </div>
            
             </div>
        <div class="col-12 col-sm-4">
               

            </div>
        </div>
        </div>
       
   

<br>
<%@ include file="Footer.jsp" %>
</body>
</html>