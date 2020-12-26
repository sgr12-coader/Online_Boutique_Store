package onlineboutiquestore.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import onlineboutiquestore.bean.BaseBean;
import onlineboutiquestore.bean.CategoryBean;
import onlineboutiquestore.bean.ContectBean;
import onlineboutiquestore.exception.ApplicationException;
import onlineboutiquestore.exception.DuplicateRecordException;
import onlineboutiquestore.model.CategoryModel;
import onlineboutiquestore.model.ContectModel;
import onlineboutiquestore.util.DataUtility;
import onlineboutiquestore.util.DataValidator;
import onlineboutiquestore.util.PropertyReader;
import onlineboutiquestore.util.ServletUtility;

/**
 * Servlet implementation class ContectCtl
 */
@WebServlet(name = "ContectCtl", urlPatterns = { "/contact" })
public class ContectCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(ContectCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("ContectCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.name", "Name"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", "Email Address"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email",
					PropertyReader.getValue("error.email", "Email Address"));
			pass = false;
		}

        if (DataValidator.isNull(request.getParameter("message"))) {
            request.setAttribute("message",
                    PropertyReader.getValue("error.require", "Message"));
            pass = false;
        }

        log.debug("ContectCtl validate method end");
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
		log.debug("ContectCtl populateBean method start");
		ContectBean bean=new ContectBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMessage(DataUtility.getString(request.getParameter("message")));
		populateDTO(bean, request);
		log.debug("ContectCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ContectCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       ContectModel model = new ContectModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        
	        System.out.println("Idd In Category-------------------"+id);
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            ContectBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setOpration("Edit", request);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(getView(), request, response);
	        log.debug("ContectCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ContectCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		ContectModel model=new ContectModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		
		
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			ContectBean bean=(ContectBean)populateBean(request);
			
				try {
					if(id>0){
						
					/*model.update(bean);*/
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(SOTGView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		ContectBean bean=	(ContectBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(SOTGView.CATEGORY_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SOTGView.CATEGORY_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(SOTGView.CONTECT_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("ContectCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SOTGView.CONTECT_VIEW;
	}

}
