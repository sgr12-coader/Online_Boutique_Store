<%@page import="onlineboutiquestore.controller.ProductListCtl"%>
<%@page import="onlineboutiquestore.bean.ProductBean"%>
<%@page import="onlineboutiquestore.util.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="onlineboutiquestore.bean.CategoryBean"%>
<%@page import="onlineboutiquestore.model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product</title>
</head>
<body>
<%@ include file="Header.jsp" %>


<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Product</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="<%=SOTGView.HOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=SOTGView.PRODUCT_LIST_CTL%>">Product</a></li>
                  
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12 col-sm-3">
            <div class="card bg-light mb-3">
                <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                <ul class="list-group category_block">
                	<% CategoryModel cModel=new CategoryModel();
						CategoryBean cBean=null;
						List cList=cModel.list();
                		Iterator<CategoryBean> cit=cList.iterator();
                		while(cit.hasNext()){
                			cBean=cit.next();
                	%>			
                    <li class="list-group-item"><a href="<%=SOTGView.PRODUCT_LIST_CTL%>?Category=<%=cBean.getName()%>&cId=<%=cBean.getId()%>"><%=cBean.getName()%></a></li>
                	<%} %>
                </ul>
            </div>
            
        </div>
        <div class="col">
            <div class="row">
             <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int size=(int)request.getAttribute("size");
                    int index = ((pageNo - 1) * pageSize) + 1;
                    ProductBean bean=null;
                    List list = ServletUtility.getList(request);
                    Iterator<ProductBean> it = list.iterator();

                    while (it.hasNext()) {

                         bean = it.next();
                %>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="card">
                        <a href="<%=SOTGView.PRODUCT_DETAIL_CTL%>?product=<%=bean.getName()%>&proId=<%=bean.getId()%>" ><img class="card-img-top" src="<%=SOTGView.APP_CONTEXT%>/images/<%=bean.getImage()%>" alt="Card image cap"></a>
                        <div class="card-body">
                            <h4 class="card-title"><a href="<%=SOTGView.PRODUCT_DETAIL_CTL%>?product=<%=bean.getName()%>&proId=<%=bean.getId()%>" title="View Product"><%=bean.getName()%></a></h4>
                          
                            <p class="bloc_left_price">Rs: -<%=bean.getPrice()%></p>
                            <div class="row">
                                <div class="col">
                                
                                    <a href="<%=SOTGView.PRODUCT_LIST_CTL%>?prdId=<%=bean.getId()%>" class="btn btn-danger btn-block">Delete</a>
                                </div>
                                <div class="col">
                                    <a href="<%=SOTGView.PRODUCT_CTL%>?id=<%=bean.getId()%>" class="btn btn-success btn-block">Edit</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <%} %>
              
               
               
                <div class="col-12">
                    <nav aria-label="...">
                        <ul class="pagination">
                             <li class="page-item disabled">
                                <input type="submit" name="operation" class="page-link"
								value="<%=ProductListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>>
                            </li>
                            
                            
                          
                            <li class="page-item">
                                <input type="submit" name="operation" class="page-link"
						value="<%=ProductListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>>
                            </li>
                             <li class="page-item">
                            </li>
                          
                          	&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                             <li class="page-item">
                                <a class="btn btn-success btn-block" href="<%=SOTGView.PRODUCT_CTL%>">Add New Product</a>
                            </li>  
                          
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

    </div>
</div>


<%@ include file="Footer.jsp" %>
</body>
</html>