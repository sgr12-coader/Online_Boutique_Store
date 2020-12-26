<%@page import="onlineboutiquestore.controller.InvoiceListCtl"%>
<%@page import="onlineboutiquestore.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="onlineboutiquestore.bean.InvoiceBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>History</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">History</h1>
     </div>
</section>
<form action="<%=SOTGView.INVOICE_LIST_CTL%>"></form>
<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                        	<th scope="col">S No.</th>
                            <th scope="col">Product</th>
                            <%if(userBean.getRoleId()==1){%>
                            <th scope="col">User Name</th>
                            <%}%>
                            <th scope="col">Description</th>
                            <th scope="col">Shipping Address</th>
                            <th scope="col">Billing Address</th>
                            <th scope="col">Date</th>
                            <th scope="col" class="text-right">Price</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int size=(int)request.getAttribute("size");
                   	InvoiceBean bean=null;
                    List list = ServletUtility.getList(request);
                    Iterator<InvoiceBean> it = list.iterator();
                    while (it.hasNext()) {

                         bean = it.next();
                         
               	 %>
                        <tr>
                        	<td><%=index++%></td>
                            <td><%=bean.getProductName()%></td>
                            <%if(userBean.getRoleId()==1){%>
                             <td><%=bean.getUserName()%></td>
                             <%} %>
                             <td><%=bean.getShippingAddress()%></td>
                             <td><%=bean.getBillingAddress()%></td>
                             <td class="text-right">$<%=DataUtility.getDateString(bean.getDate())%></td>
                            <td class="text-right">$<%=bean.getAmount()%></td>
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
								value="<%=InvoiceListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                     <input type="submit" name="operation" class="page-link"
						value="<%=InvoiceListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="Footer.jsp" %>
</body>
</html>