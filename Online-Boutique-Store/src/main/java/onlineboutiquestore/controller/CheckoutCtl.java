package onlineboutiquestore.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import onlineboutiquestore.bean.CartBean;
import onlineboutiquestore.bean.InvoiceBean;
import onlineboutiquestore.bean.UserBean;
import onlineboutiquestore.exception.ApplicationException;
import onlineboutiquestore.exception.DuplicateRecordException;
import onlineboutiquestore.model.CartModel;
import onlineboutiquestore.model.InvoiceModel;
import onlineboutiquestore.model.UserModel;
import onlineboutiquestore.util.DataUtility;
import onlineboutiquestore.util.PropertyReader;
import onlineboutiquestore.util.ServletUtility;

/**
 * Servlet implementation class CheckoutCtl
 */
@WebServlet(name = "CheckoutCtl", urlPatterns = { "/ctl/checkout" })
public class CheckoutCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(CheckoutCtl.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CartListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = 10;
		

		CartModel model = new CartModel();
		CartBean bean = new CartBean();
		
		try {
			
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			bean.setUserId(uBean.getId());
			
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("CartListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AdminCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
       
        if (OP_CONFIRM_PAYMENT.equalsIgnoreCase(op)) {
          
              InvoiceModel model=new InvoiceModel();
              InvoiceBean bean=new InvoiceBean();
              HttpSession session=request.getSession();
              UserBean uBean=(UserBean)session.getAttribute("user");
              
              CartModel cModel=new CartModel();
              CartBean cBean=null;
              CartBean ccBean=new CartBean();
              ccBean.setUserId(uBean.getId());
              try {
				List list=cModel.search(ccBean);
				Iterator<CartBean> it=list.iterator();
				while (it.hasNext()) {
					 cBean =it.next();
					 bean.setUserId(uBean.getId());
					 bean.setProductId(cBean.getProductId());
					 model.add(bean);
				}
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              ServletUtility.redirect(SOTGView.INVOICE_CTL, request, response);
           return;
        }  else if (OP_SUBMIT.equalsIgnoreCase(op)) {
        	ServletUtility.forward(SOTGView.PAYMENT_VIEW, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(SOTGView.ADMIN_CTL, request, response);
    		return;
    }
    				
    		
      
        

        log.debug("AdminCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SOTGView.CHECKOUT_VIEW;
	}

}
