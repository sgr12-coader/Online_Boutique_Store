package onlineboutiquestore.controller;

public interface SOTGView {
	
	public String APP_CONTEXT = "/Online-Boutique-Store";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String NEW_PAGE_FOLDER = "/cjsp";
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String ADMIN_VIEW = NEW_PAGE_FOLDER + "/AdminView.jsp";	
	public String ADMIN_LIST_VIEW = PAGE_FOLDER + "/AdminListView.jsp";
	
	public String CONTECT_VIEW = NEW_PAGE_FOLDER + "/ContectView.jsp";	
	public String CONTECT_LIST_VIEW = NEW_PAGE_FOLDER + "/ContactListView.jsp";
	
	public String USER_REGISTRATION_VIEW = NEW_PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String CATEGORY_VIEW = NEW_PAGE_FOLDER + "/CategoryView.jsp";	
	public String CATEGORY_LIST_VIEW = NEW_PAGE_FOLDER + "/CategoryListView.jsp";
		
	public String PRODUCT_VIEW = NEW_PAGE_FOLDER + "/ProductView.jsp";	
	public String PRODUCT_LIST_VIEW = NEW_PAGE_FOLDER + "/ProductListView.jsp";
	
	public String CART_LIST_VIEW = NEW_PAGE_FOLDER + "/CartListView.jsp";
	
	public String INVOICE_LIST_VIEW = NEW_PAGE_FOLDER + "/InvoiceListView.jsp";
	
	public String PRODUCT_DETAIL_VIEW = NEW_PAGE_FOLDER + "/ProductDetailView.jsp";
	
	public String PAYMENT_VIEW = NEW_PAGE_FOLDER + "/PaymentView.jsp";
	
	public String USER_PRODUCT_LIST_VIEW = NEW_PAGE_FOLDER  + "/UserProductView.jsp";
	
	public String LOGIN_VIEW = NEW_PAGE_FOLDER + "/LoginView.jsp";
	
	public String CHECKOUT_VIEW = NEW_PAGE_FOLDER + "/CheckoutView.jsp";
	
	public String USER_LOGIN_VIEW = NEW_PAGE_FOLDER + "/UserLoginView.jsp";
	
	public String HOME_VIEW = NEW_PAGE_FOLDER + "/HomeView.jsp";
	
	public String INVOICE_VIEW = NEW_PAGE_FOLDER + "/InvoiceView.jsp";
	
	public String WELCOME_VIEW = NEW_PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = NEW_PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = NEW_PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = NEW_PAGE_FOLDER + "/ForgetPasswordView.jsp";

	public String ERROR_CTL = "/ctl/ErrorCtl";
	
	
	public String CHECKOUT_CTL = APP_CONTEXT + "/ctl/checkout";
	
	public String INVOICE_CTL = APP_CONTEXT + "/ctl/invoice";
	
	public String INVOICE_LIST_CTL = APP_CONTEXT + "/ctl/history/shop";

	public String CART_CTL = APP_CONTEXT + "/ctl/CartCtl";
	public String CART_LIST_CTL = APP_CONTEXT + "/ctl/shoppingCart/cart";
	
	public String ADMIN_CTL = APP_CONTEXT + "/ctl/adminPortal/admin";
	public String ADMIN_LIST_CTL = APP_CONTEXT + "/ctl/AdminListCtl";
	
	public String CONTECT_LIST_CTL = APP_CONTEXT + "/ctl/adminPortal/contactList";
	
	
	public String CATEGORY_CTL = APP_CONTEXT + "/ctl/adminPortal/category";
	public String CATEGORY_LIST_CTL = APP_CONTEXT + "/ctl/adminPortal/category/categoryList";
	
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/adminPortal/prod";
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/adminPortal/prod/prodList";
	
	public String PRODUCT_DETAIL_CTL = APP_CONTEXT + "/prodShelf/prod";
	
	public String USER_PRODUCT_LIST_CTL = APP_CONTEXT + "/prodShelf";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/newUser";
	
	public String USER_LOGIN_CTL = APP_CONTEXT + "/userLogin";
	
	public String LOGIN_CTL = APP_CONTEXT + "/Login";
	
	public String CONTECT_CTL = APP_CONTEXT + "/contact";
	
	public String WELCOME_CTL = APP_CONTEXT + "/home";
	
	public String HOME_CTL = APP_CONTEXT + "/adminPortal/home";
	
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/changePassword";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/updateProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
