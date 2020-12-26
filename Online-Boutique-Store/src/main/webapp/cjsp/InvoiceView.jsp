<%@page import="onlineboutiquestore.bean.ProductBean"%>
<%@page import="onlineboutiquestore.bean.UserBean"%>
<%@page import="onlineboutiquestore.model.ProductModel"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invoice</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Checkout</h1>
     </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active"><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<form action="<%=SOTGView.CHECKOUT_CTL%>" method="post">
<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                        	<th scope="col">S No.</th>
                            <th scope="col">Product</th>
                            <th scope="col">Description</th>
                            <th scope="col" class="text-center">Quantity</th>
                            <th scope="col" class="text-right">Price</th>
                            <th scope="col" class="text-right">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                   	CartBean bean=null;
                    List list = ServletUtility.getList(request);
                    Iterator<CartBean> it = list.iterator();
					double n1=0;
					double n2=0;
					double n3=0;
                    while (it.hasNext()) {

                         bean = it.next();
                         
                         ProductModel pModel=new ProductModel();
                         ProductBean pBean= pModel.findByPK(bean.getProductId());
               	 %>
                        <tr>
                        	<td><%=index++%></td>
                            <td><%=pBean.getName()%></td>
                             <td><%=pBean.getDescription()%></td>
                            <td class="text-center"><%=bean.getQuantity()%></td>
                            <td class="text-right">$<%=pBean.getPrice()%></td>
                             <td class="text-right">$<%=bean.getFinalAmount()%></td>
                            <td></td>
                      			<% 
                        		n1=bean.getFinalAmount();
                        		n2=n3+n1;
                        		n3=n2;
                        	%>
                        </tr>
                        
                       <%} 
                  		  double sipping=6.90;
                       
                       %>
                      
                     
                        <tr>
                        
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Sub-Total</td>
                            <td class="text-right">$<%=n3%></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Shipping</td>
                            <td class="text-right">$<%=sipping%></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Total</strong></td>
                            <td class="text-right"><strong>$<%=sipping+n3%></strong></td>
                        </tr>
                        
                         <% UserBean uBean=(UserBean)session.getAttribute("user");%>
                        <tr>
                            
                            <td colspan="2"><strong>Name :</strong></td>
                            <td colspan="4" class="text-left"><strong><%=uBean.getFirstName()+" "+uBean.getLastName()%></strong></td>
                        </tr>
                        <tr>
                            
                            <td colspan="2"><strong>Shipping Address :</strong></td>
                            <td colspan="4" class="text-left"><strong><%=uBean.getShippingAddress()%></strong></td>
                        </tr>
                        <tr>
                            
                            <td colspan="2"><strong>Billing Address :</strong></td>
                            <td colspan="4" class="text-left"><strong><%=uBean.getBillingAddress()%></strong></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
            </div>
        </div>
    </div>
</div>
</form>
<br>
<%@ include file="Footer.jsp" %>
</body>
</html>