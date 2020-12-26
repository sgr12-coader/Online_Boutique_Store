<%@page import="onlineboutiquestore.controller.CheckoutCtl"%>
<%@page import="onlineboutiquestore.bean.ProductBean"%>
<%@page import="onlineboutiquestore.model.ProductModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="onlineboutiquestore.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
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
                    <li class="breadcrumb-item active"><a href="#">Review Order</a></li>
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
<div class="container">
    <div class="row">
    
        <div class="col">
            <div class="card">
                <div class="card-header bg-primary text-white"> Confirm Detail
                
                </div>
            	<% UserBean uBean=(UserBean)session.getAttribute("user"); %>
                <div class="card-body">
 
                    	<div class="form-group">
                            <label for="name"><b>Name</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            :&nbsp;&nbsp;<font style="font-size:13px"><%=uBean.getFirstName()+" "+uBean.getLastName()%></font></label>
                        		
                        </div>
                        <div class="form-group">
                            <label for="name"><b>Email</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             :&nbsp;&nbsp;<font style="font-size:13px"><%=uBean.getEmailAddress()%></font></label>
                        </div>
                        <div class="form-group">
                            <label for="name"><b>Mobile No</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            :&nbsp;&nbsp;<font style="font-size:13px"><%=uBean.getMobileNo()%></font></label>
                        </div>
                        <div class="form-group">
                            <label for="name"><b>Shipping Address</b>&nbsp;&nbsp; :&nbsp;&nbsp;<font style="font-size:13px"><%=uBean.getShippingAddress()%></font></label>
                        </div>
                        <div class="form-group">
                            <label for="name"><b>Billing Address</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :&nbsp;&nbsp;<font style="font-size:13px"><%=uBean.getBillingAddress()%></font></label>
                        </div>

                        <div class="mx-auto">
                        <input type="submit" name="operation" class="btn btn-primary text-right" value="<%=CheckoutCtl.OP_SUBMIT%>">
                    
                </div>
            </div>
            
             </div>
       
        </div>
        <!-- <div class="col-12 col-sm-4">
               

            </div> -->
        </div>
       
   
</div>
</form>
<br>
<%@ include file="Footer.jsp" %>
</body>
</html>