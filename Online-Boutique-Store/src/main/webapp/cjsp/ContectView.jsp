<%@page import="onlineboutiquestore.controller.ContectCtl"%>
<%@page import="onlineboutiquestore.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contect</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Contact</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=SOTGView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=SOTGView.USER_REGISTRATION_CTL%>">Contact</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-header bg-primary text-white"> Contact
                
                </div>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
                <div class="card-body">
                   <form action="<%=SOTGView.CONTECT_CTL%>" method="post">
                   
                   <jsp:useBean id="bean" class="onlineboutiquestore.bean.ContectBean"
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
                            <label for="name">Name</label>
                             <input type="text" class="form-control" name="name" placeholder="Enter  Name..." 
                                value="<%=DataUtility.getStringData(bean.getName())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email Address</label>
                            <input type="text" class="form-control" name="email" placeholder="Enter Email Address..." 
                                value="<%=DataUtility.getStringData(bean.getEmail())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("email", request)%></font>
                        </div>
                       

                        
                        <div class="form-group">
                            <label for="message">Message</label>
                            <textarea rows="5" class="form-control" name="message" placeholder="Enter Message Here.." ><%=DataUtility.getStringData(bean.getMessage()) %></textarea>
                            <font color="red"> <%=ServletUtility.getErrorMessage("message", request)%></font>
                        </div>
                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=ContectCtl.OP_SAVE%>">
                                <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=ContectCtl.OP_RESET%>"></div>
                    </form>
                </div>
            </div>
            
             </div>
        <div class="col-12 col-sm-4">
                <div class="card bg-light mb-3">
                <div class="card-header bg-success text-white text-uppercase"><i class="fa fa-home"></i> Address</div>
                <div class="card-body">
                    <p>3 rue des Champs Elysées</p>
                    <p>75008 PARIS</p>
                    <p>France</p>
                    <p>Email : email@example.com</p>
                    <p>Tel. +33 12 56 11 51 84</p>

                </div>

            </div>

            </div>
        </div>
        </div>
       
   

<br>
<%@ include file="Footer.jsp" %>
</body>
</html>