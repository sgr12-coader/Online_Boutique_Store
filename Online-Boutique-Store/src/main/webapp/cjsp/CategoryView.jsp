<%@page import="onlineboutiquestore.controller.CategoryCtl"%>
<%@page import="onlineboutiquestore.util.DataUtility"%>
<%@page import="onlineboutiquestore.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Categories</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=SOTGView.WELCOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item"><a href="<%=SOTGView.CATEGORY_LIST_CTL%>">Categories</a></li>
                    <li class="breadcrumb-item active"><a href="<%=SOTGView.CART_CTL%>">Add Categories</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<form action="<%=SOTGView.CATEGORY_CTL%>" method="post" enctype="multipart/form-data">
                   
                   
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
            <jsp:useBean id="bean" class="onlineboutiquestore.bean.CategoryBean"
						scope="request"></jsp:useBean>
            
            	<%if(bean.getId()>0){%>
                <div class="card-header bg-primary text-white">Update Categories
				<%}else{ %>
				<div class="card-header bg-primary text-white">Add Categories
				<%} %>                
                </div>
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
                            <label for="name">Name</label>
                             <input type="text" class="form-control" name="name" placeholder="Enter Category Name..." 
                                value="<%=DataUtility.getStringData(bean.getName())%>" >
                               <font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
                        </div>
                        <div class="form-group">
                            <label for="name">Image</label>
                            <input type="file" class="form-control" name="photo" placeholder="Upload Image Here..."
                                value="<%=DataUtility.getStringData(bean.getImage())%>" >
                                <font color="red"> <%=ServletUtility.getErrorMessage("photo", request)%></font>
                        </div>
                       
                        
                      
                        
                        <div class="form-group">
                            <label for="message">Description</label>
                            <textarea rows="5" class="form-control" name="description" placeholder="Enter Description Here.." ><%=DataUtility.getStringData(bean.getDescription()) %></textarea>
                            	<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
                        </div>
                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=CategoryCtl.OP_SAVE%>">
                        <% if(bean.getId()>0){ %>
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=CategoryCtl.OP_CANCEL%>">
                        <%}else{ %>
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=CategoryCtl.OP_RESET%>">
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