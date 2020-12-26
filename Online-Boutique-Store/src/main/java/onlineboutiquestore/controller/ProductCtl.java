package onlineboutiquestore.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import onlineboutiquestore.bean.BaseBean;
import onlineboutiquestore.bean.CategoryBean;
import onlineboutiquestore.bean.ProductBean;
import onlineboutiquestore.exception.ApplicationException;
import onlineboutiquestore.exception.DuplicateRecordException;
import onlineboutiquestore.model.CategoryModel;
import onlineboutiquestore.model.ProductModel;
import onlineboutiquestore.util.DataUtility;
import onlineboutiquestore.util.DataValidator;
import onlineboutiquestore.util.PropertyReader;
import onlineboutiquestore.util.ServletUtility;

/**
 * Servlet implementation class ProductCtl
 */
@WebServlet(name = "ProductCtl", urlPatterns = { "/ctl/adminPortal/prod" })
@MultipartConfig(maxFileSize = 16177215)
public class ProductCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ProductCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("ProductCtl preload method start");
		CategoryModel model = new CategoryModel();
		try {
			List l = model.list();
			request.setAttribute("catList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("ProductCtl preload method end");
	}

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ProductCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("categoryId"))) {
			request.setAttribute("categoryId", PropertyReader.getValue("error.require", "Category Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}else if (DataValidator.isNotNull(request.getParameter("price"))
				&& !DataValidator.isDouble(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.double", "Price"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		log.debug("ProductCtl validate method end");
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
		log.debug("ProductCtl populateBean method start");
		ProductBean bean=new ProductBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCategoryId(DataUtility.getLong(request.getParameter("categoryId")));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("ProductCtl populateBean method end");
		return bean;
	}
	
	/**
	 * Contains display logic
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ProductCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       ProductModel model = new ProductModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            ProductBean bean;
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
	        log.debug("ProductCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ProductCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		ProductModel model=new ProductModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		String savePath = DataUtility.getString(PropertyReader.getValue("path")); 
		
		File fileSaveDir = new File(savePath);
	       if (!fileSaveDir.exists()) {
	           fileSaveDir.mkdir();
	       }
	       
	       
	       Part part = request.getPart("photo");
	       
	       String fileName = extractFileName(part);
	       part.write(savePath + File.separator + fileName);
	       
	       String filePath = fileName;
	       
	       System.out.println("Path----"+savePath + File.separator + fileName);
		
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			ProductBean bean=(ProductBean)populateBean(request);
			bean.setImage(filePath);
				try {
					if(id>0){
						
					model.update(bean);
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
		ProductBean bean=	(ProductBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(SOTGView.PRODUCT_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SOTGView.PRODUCT_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(SOTGView.PRODUCT_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("ProductCtl doPost method end");
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SOTGView.PRODUCT_VIEW;
	}

}
