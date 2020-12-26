<%@page import="onlineboutiquestore.controller.ContactListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="onlineboutiquestore.bean.ContectBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Contacts</h1>
     </div>
</section>
<form action="<%=SOTGView.CONTECT_LIST_CTL%>"></form>
<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                        	<th scope="col">S No.</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email Address</th>
                            <th scope="col">Message</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int size=(int)request.getAttribute("size");
                   	ContectBean bean=null;
                    List list = ServletUtility.getList(request);
                    Iterator<ContectBean> it = list.iterator();
                    while (it.hasNext()) {

                         bean = it.next();
                         
               	 %>
                        <tr>
                        	<td><%=index++%></td>
                            <td><%=bean.getName()%></td>
                            
                             <td><%=bean.getEmail()%></td>
                             <td><%=bean.getMessage()%></td>
                        </tr>
                        
                       <%} 
                   
                       
                       %>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <input type="submit" name="operation" class="page-link"
								value="<%=ContactListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                     <input type="submit" name="operation" class="page-link"
						value="<%=ContactListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="Footer.jsp" %>
</body>
</html>