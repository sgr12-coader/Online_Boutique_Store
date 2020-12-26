<%@page import="onlineboutiquestore.bean.CategoryBean"%>
<%@page import="onlineboutiquestore.model.CategoryModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="onlineboutiquestore.bean.ProductBean"%>
<%@page import="onlineboutiquestore.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<!-- 	<div class="home-content-wrapper">
		<div class="overlay-box">
			<div class="container">
				<div class="welcome-text">WELCOME TO <br/>Online-Boutique-Store</div>	
			</div>
		</div>	
	</div> -->
	
	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/Online-Boutique-Store/images/1.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/Online-Boutique-Store/images/fashion-shop-boutique-store-window-display-front-women-clothes-retail-female-mannequin-59661948.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/Online-Boutique-Store/images/lady-s-spring-fashion-shop-window-clothes-hongkong-china-asia-49505992.jpg" class="d-block w-100" alt="...">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

<%@ include file="Footer.jsp" %>
</body>
</html>