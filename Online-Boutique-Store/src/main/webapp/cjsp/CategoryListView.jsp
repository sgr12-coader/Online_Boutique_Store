<%@page import="onlineboutiquestore.controller.CategoryListCtl"%>
<%@page import="onlineboutiquestore.controller.CategoryCtl"%>
<%@page import="onlineboutiquestore.bean.ProductBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="onlineboutiquestore.bean.CategoryBean"%>
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

<form action="<%=SOTGView.CATEGORY_LIST_CTL%>" method="post">
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Category</h1>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<%=SOTGView.HOME_CTL%>">Home</a></li>
                    <li class="breadcrumb-item active"><a href="<%=SOTGView.CATEGORY_LIST_CTL%>">Categories</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        
        <div class="col">
            <div class="row">
             <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int size=(int)request.getAttribute("size");
                    int index = ((pageNo - 1) * pageSize) + 1;
                    CategoryBean bean=null;
                    List list = ServletUtility.getList(request);
                    Iterator<CategoryBean> it = list.iterator();

                    while (it.hasNext()) {

                         bean = it.next();
                %>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="card">
                        <a href="<%=SOTGView.PRODUCT_LIST_CTL%>?product=<%=bean.getName()%>&proId=<%=bean.getId()%>" ><img class="card-img-top" src="<%=SOTGView.APP_CONTEXT%>/images/<%=bean.getImage()%>" alt="Card image cap"></a>
                        <div class="card-body">
                            <h4 class="card-title"><a href="<%=SOTGView.PRODUCT_DETAIL_CTL%>?product=<%=bean.getName()%>&proId=<%=bean.getId()%>" title="View Product"><%=bean.getName()%></a></h4>
                            <div class="row">
                                <div class="col">
                                    <a href="<%=SOTGView.CATEGORY_LIST_CTL%>?dId=<%=bean.getId()%>" class="btn btn-danger btn-block" >Delete</a>
                                </div>
                                <div class="col"> 
                                    <a href="<%=SOTGView.CATEGORY_CTL%>?id=<%=bean.getId()%>" class="btn btn-success btn-block">Edit</a>
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
								value="<%=CategoryListCtl.OP_PREVIOUS%>"
								<%=(pageNo == 1) ? "disabled" : ""%>>
                            </li>
                            
                            
                          
                            <li class="page-item">
                                <input type="submit" name="operation" class="page-link"
						value="<%=CategoryListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>>
                            </li>
                             <li class="page-item">
                            </li>
                            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                             <li class="page-item">
                                <a class="btn btn-success btn-block" href="<%=SOTGView.CATEGORY_CTL%>">Add New Categories</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

    </div>
</div>


</form>
<%@ include file="Footer.jsp" %>
</body>
</html>