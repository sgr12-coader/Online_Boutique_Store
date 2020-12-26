<%@page import="onlineboutiquestore.controller.CheckoutCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>

</head>
<body>
	<%@ include file="Header.jsp"%>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Payment</h1>
     </div>
</section>
	<div class="container">

		


		<div class="row">
			<aside class="col-sm-6">
			<p>Payment</p>

			<article class="card">
			<div class="card-body p-5">
				<!-- <p>
					<img
						src="http://bootstrap-ecommerce.com/main/images/icons/pay-visa.png">
					<img
						src="http://bootstrap-ecommerce.com/main/images/icons/pay-mastercard.png">
					<img
						src="http://bootstrap-ecommerce.com/main/images/icons/pay-american-ex.png">
				</p> -->
				

				<form role="form" action="<%=SOTGView.CHECKOUT_CTL%>" method="post">
					<div class="form-group">
						<label for="username">Full name (on the card)</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-user"></i></span>
							</div>
							<input type="text" class="form-control" name="username"
								placeholder="" required="">
						</div>
						<!-- input-group.// -->
					</div>
					<!-- form-group.// -->

					<div class="form-group">
						<label for="cardNumber">Card number</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa fa-credit-card"></i></span>
							</div>
							<input type="text" class="form-control" name="cardNumber"
								placeholder="">
						</div>
						<!-- input-group.// -->
					</div>
					<!-- form-group.// -->

					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label><span class="hidden-xs">Expiration</span> </label>
								<div class="form-inline">
									<select class="form-control" style="width: 45%">
										<option>MM</option>
										<option>01 - Janiary</option>
										<option>02 - February</option>
										<option>03 - February</option>
									</select> <span style="width: 10%; text-align: center"> / </span> <select
										class="form-control" style="width: 45%">
										<option>YY</option>
										<option>2018</option>
										<option>2019</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label data-toggle="tooltip" title=""
									data-original-title="3 digits code on back side of the card">CVV
									<i class="fa fa-question-circle"></i>
								</label> <input class="form-control" required="" type="text">
							</div>
							<!-- form-group.// -->
						</div>
					</div>
					<!-- row.// --> 
					<input type="submit" class="subscribe btn btn-primary btn-block" name="operation" value="<%=CheckoutCtl.OP_CONFIRM_PAYMENT%>">
				</form>
			</div>
			<!-- card-body.// --> </article> <!-- card.// --> </aside>
			<!-- col.// -->
			
			

			
			
				

				
							

							
							
							
						
					
					
					
						
						
					
			

			
			
	
	
	

		
		</div>
	</div>
	<br>
	<br>

	<%@ include file="Footer.jsp"%>
</body>
</html>