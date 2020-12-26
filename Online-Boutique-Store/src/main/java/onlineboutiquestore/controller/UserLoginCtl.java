package onlineboutiquestore.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import onlineboutiquestore.bean.BaseBean;
import onlineboutiquestore.bean.RoleBean;
import onlineboutiquestore.bean.UserBean;
import onlineboutiquestore.exception.ApplicationException;
import onlineboutiquestore.model.RoleModel;
import onlineboutiquestore.model.UserModel;
import onlineboutiquestore.util.DataUtility;
import onlineboutiquestore.util.DataValidator;
import onlineboutiquestore.util.PropertyReader;
import onlineboutiquestore.util.ServletUtility;

/**
 * Servlet implementation class UserLoginCtl
 */
@WebServlet(name = "UserLoginCtl", urlPatterns = { "/userLogin" })
public class UserLoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
	public static String HIT_URI = null;
	
	private  static Logger log = Logger.getLogger(UserLoginCtl.class);

	

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserLoginCtl Method validate Started");
		
		boolean pass = true;
		
		String op = request.getParameter("operation");
		
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		
		
		String login = request.getParameter("login");
		
		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}
		log.debug("UserLoginCtl Method validate Ended");
		return pass;
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserLoginCtl Method populateBean Started");

		UserBean bean = new UserBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("UserLoginCtl Method PopulatedBean End");

		return bean;
	}

	/**
	 * Display Login form
	 * 
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("Method doGet Started");
		
		
		HttpSession session = request.getSession(true);
		String op = DataUtility.getString(request.getParameter("operation"));
		
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		long Pid=DataUtility.getLong(request.getParameter("proIdd"));

		if(Pid>0) {
			request.setAttribute("pId",Pid);
		}
		
		System.out.println("Product Id in Do get------"+Pid);
		
		if (id > 0) {
			UserBean userBean;
			try {
				userBean = model.findByPK(id);
				ServletUtility.setBean(userBean, request);
		
			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_LOG_OUT.equals(op)) {
			session = request.getSession(false);
			session.invalidate();
			ServletUtility.setSuccessMessage("You have been logged out successfully", request);
			
			ServletUtility.forward(SOTGView.LOGIN_VIEW, request, response);
			return;
		}
		if (session.getAttribute("user") != null) {
			ServletUtility.redirect(SOTGView.WELCOME_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("Method doGet end");
	}

	/**
	 * Submit Logic
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		log.debug(" UserLoginCtl Method doPost Started");
		
		HttpSession session = request.getSession(true);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get Model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		long ppId=DataUtility.getLong(request.getParameter("proDD"));
		
		
		
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				
				if (bean != null) {
					session.setAttribute("user", bean);
					session.setMaxInactiveInterval(10 * 6000);

					long rollId = bean.getRoleId();
					RoleBean roleBean = role.findByPK(rollId);
					if (roleBean != null) {
						session.setAttribute("role", roleBean.getName());
					}
					// save state of session remember URL
					String uri = request.getParameter("uri");
					
					System.out.println("Product Id in doPost-------"+ppId);
					if(ppId>0) {
						ServletUtility.redirect(SOTGView.PRODUCT_DETAIL_CTL+"?proId="+ppId, request, response);
					}else {
					if (uri == null || "null".equalsIgnoreCase(uri)) {
						ServletUtility.redirect(SOTGView.WELCOME_CTL, request, response);
						return;
					} else {
						ServletUtility.redirect(uri, request, response);
					}
					}
					return;
				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				
				return;
			}
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SOTGView.USER_REGISTRATION_CTL, request, response);
		return;
		}
		
		
		ServletUtility.forward(getView(), request, response);
		log.debug("UserLoginCtl Method doPost Ended");
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return SOTGView.USER_LOGIN_VIEW;
	}

}
