<%@page import="onlineboutiquestore.bean.ProductBean"%>
<%@page import="onlineboutiquestore.model.ProductModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>

</head>
<body>
<%@ include file="Header.jsp" %>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Cart</h1>
     </div>
</section>
<form action="<%=SOTGView.CART_LIST_CTL%>" method="post">
<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Product</th>
                            <th scope="col">Description</th>
                            <th scope="col" class="text-center">Quantity</th>
                            <th scope="col" class="text-right">Price</th>
                            <th scope="col" class="text-right">Total</th>
                            <th>Delete</th>
                            <th>View</th>
                             <th>Update</th>
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
					int i=1;
                    while (it.hasNext()) {

                         bean = it.next();
                         
                         ProductModel pModel=new ProductModel();
                         ProductBean pBean= pModel.findByPK(bean.getProductId());
               	 %>
                        <tr>
                            <td><%=pBean.getName()%></td>
                             <td><%=pBean.getDescription()%></td>
                            <td><input class="form-control" type="text" name="quantity<%=i++%>" value="<%=(bean.getQuantity()==0)?1:bean.getQuantity()%>"/>
                            </td>
                            
                            <td class="text-right">$<%=pBean.getPrice()%></td>
                            <td class="text-right">$<%=bean.getFinalAmount()%></td>
                            <td class="text-right"><a href="<%=SOTGView.CART_LIST_CTL%>?dcid=<%=bean.getId()%>" class="btn btn-sm btn-danger" ><i class="fa fa-trash"></i></a> </td>
                      		<td class="text-right"><a href="<%=SOTGView.PRODUCT_DETAIL_CTL%>?product=<%=pBean.getName()%>&proId=<%=pBean.getId()%>" class="btn btn-sm btn-primary" ><i class="fa fa-eye"></i></a></td>
                      		
                      		<td class="text-right">
                      		<input type="submit" name="operation" value="Update" class="btn btn-sm btn-primary">
                      		</td>
                      		
                      	<% 
                        		n1=bean.getFinalAmount();
                        		n2=n3+n1;
                        		n3=n2;
                        	%>
                        </tr>
                        
                       <%} 
                    double sipping=6.90;
                       
                       %>
                       <%if(list.size()>0){ %>
                        <tr>
                         <td></td>
                           <td></td>
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
                            <td></td>
                            <td></td>
                            <td>Shipping</td>
                            <td class="text-right">$<%=sipping%></td>
                        </tr>
                        <tr>
                         <td></td>  <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Total</strong></td>
                            <td class="text-right"><strong>$<%=sipping+n3%></strong></td>
                        </tr>
                        <%} %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                     <a href="<%=SOTGView.USER_PRODUCT_LIST_CTL%>" class="btn btn-block btn-light" >Continue Shopping</a>
                </div>
                <%if(list.size()>0){  %>
                <div class="col-sm-12 col-md-6 text-right">
                    <a href="<%=SOTGView.CHECKOUT_CTL%>" class="btn btn-lg btn-block btn-success text-uppercase" >Checkout</a>
                </div>
                <%} %>
            </div>
        </div>
    </div>
</div>
</form>
<%@ include file="Footer.jsp" %>
</body>
</html>