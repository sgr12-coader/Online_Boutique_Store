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
import onlineboutiquestore.bean.UserBean;
import onlineboutiquestore.exception.ApplicationException;
import onlineboutiquestore.model.CartModel;
import onlineboutiquestore.util.ServletUtility;

/**
 * Servlet implementation class InvoiceCtl
 */
@WebServlet(name = "InvoiceCtl", urlPatterns = { "/ctl/invoice" })
public class InvoiceCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(InvoiceCtl.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("InvoiceCtl doGet method start");
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
			Iterator<CartBean> it=list.iterator();
			while (it.hasNext()) {
				CartBean cartBean = (CartBean) it.next();
				model.delete(cartBean);
			}
			ServletUtility.setSuccessMessage("Your Payment Is Successfully Done.", request);
			ServletUtility.forward(getView(), request, response);
			
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		
		log.debug("InvoiceCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SOTGView.INVOICE_VIEW;
	}

}
