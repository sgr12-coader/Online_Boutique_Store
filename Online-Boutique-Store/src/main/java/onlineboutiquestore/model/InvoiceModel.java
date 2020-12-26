package onlineboutiquestore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import onlineboutiquestore.bean.CategoryBean;
import onlineboutiquestore.bean.InvoiceBean;
import onlineboutiquestore.bean.ProductBean;
import onlineboutiquestore.bean.UserBean;
import onlineboutiquestore.exception.ApplicationException;
import onlineboutiquestore.exception.DatabaseException;
import onlineboutiquestore.exception.DuplicateRecordException;
import onlineboutiquestore.util.JDBCDataSource;

public class InvoiceModel {
	
	private static Logger log = Logger.getLogger(InvoiceModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM SO_INVOICE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting Next PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public Integer nextProductCode() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(OrderId) FROM SO_INVOICE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting Next Product Code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");

		if (pk > 0) {
			return pk + 1;
		} else {
			return 200101;
		}
	}

	public InvoiceBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SO_INVOICE WHERE ID=?");
		InvoiceBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new InvoiceBean();
				bean.setId(rs.getLong(1));
				bean.setOrderId(rs.getLong(2));
				bean.setUserId(rs.getLong(3));
				bean.setUserName(rs.getString(4));
				bean.setProductId(rs.getLong(5));
				bean.setProductName(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setAmount(rs.getDouble(8));
				bean.setMobileNo(rs.getString(9));
				bean.setShippingAddress(rs.getString(10));
				bean.setBillingAddress(rs.getString(11));
				bean.setEmailId(rs.getString(12));
				bean.setProductDescription(rs.getString(13));
				bean.setProductImage(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public InvoiceBean findByOrderId(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SO_INVOICE WHERE ORDERID=?");
		InvoiceBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new InvoiceBean();
				bean.setId(rs.getLong(1));
				bean.setOrderId(rs.getLong(2));
				bean.setUserId(rs.getLong(3));
				bean.setUserName(rs.getString(4));
				bean.setProductId(rs.getLong(5));
				bean.setProductName(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setAmount(rs.getDouble(8));
				bean.setMobileNo(rs.getString(9));
				bean.setShippingAddress(rs.getString(10));
				bean.setBillingAddress(rs.getString(11));
				bean.setEmailId(rs.getString(12));
				bean.setProductDescription(rs.getString(13));
				bean.setProductImage(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public long add(InvoiceBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		int orderId = 0;
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getUserId());
		bean.setUserName(uBean.getFirstName()+" "+uBean.getLastName());
		bean.setMobileNo(uBean.getMobileNo());
		bean.setEmailId(uBean.getEmailAddress());
		bean.setShippingAddress(uBean.getShippingAddress());
		bean.setBillingAddress(uBean.getBillingAddress());
		
		ProductModel pModel=new ProductModel();
		ProductBean pBean=pModel.findByPK(bean.getProductId());
		bean.setProductName(pBean.getName());
		bean.setProductDescription(pBean.getDescription());
		bean.setAmount(pBean.getPrice());
		bean.setProductImage(pBean.getImage());
		
		bean.setDate(new java.util.Date());
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			orderId=nextProductCode();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SO_INVOICE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,orderId);
			pstmt.setLong(3,bean.getUserId());
			pstmt.setString(4,bean.getUserName());
			pstmt.setLong(5,bean.getProductId());
			pstmt.setString(6,bean.getProductName());
			pstmt.setDate(7,new java.sql.Date(bean.getDate().getTime()));
			pstmt.setDouble(8, bean.getAmount());
			pstmt.setString(9, bean.getMobileNo());
			pstmt.setString(10,bean.getShippingAddress());
			pstmt.setString(11, bean.getBillingAddress());
			pstmt.setString(12, bean.getEmailId());
			pstmt.setString(13, bean.getProductDescription());
			pstmt.setString(14, bean.getProductImage());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Product");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	
	 public void delete(InvoiceBean bean) throws ApplicationException {
	        log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM SO_INVOICE WHERE ID=?");
	            pstmt.setLong(1, bean.getId());
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException(
	                    "Exception : Exception in delete Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model delete Started");
	    }
	 
	 public List search(InvoiceBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }

	    /**
	     * Search PRODUCT with pagination
	     * 
	     * @return list : List of Product
	     * @param bean
	     *            : Search Parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * 
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public List search(InvoiceBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM SO_INVOICE WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            
	            if (bean.getProductId() > 0) {
	                sql.append(" AND ProductId = " + bean.getProductId());
	            }
	            
	            if (bean.getUserId() > 0) {
	                sql.append(" AND UserId = " + bean.getUserId());
	            }
	            if (bean.getOrderId() > 0) {
	                sql.append(" AND OrderId = " + bean.getOrderId());
	            }
	            
	            if (bean.getProductName() != null && bean.getProductName().length() > 0) {
					sql.append(" AND ProductNAME LIKE '" + bean.getProductName() + "%'");
	            }
	            if (bean.getUserName() != null && bean.getUserName().length() > 0) {
					sql.append(" AND UserName LIKE '" + bean.getUserName() + "%'");
	            }
	            
	        }

	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" Limit " + pageNo + ", " + pageSize);
	            // sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new InvoiceBean();
	                bean.setId(rs.getLong(1));
					bean.setOrderId(rs.getLong(2));
					bean.setUserId(rs.getLong(3));
					bean.setUserName(rs.getString(4));
					bean.setProductId(rs.getLong(5));
					bean.setProductName(rs.getString(6));
					bean.setDate(rs.getDate(7));
					bean.setAmount(rs.getDouble(8));
					bean.setMobileNo(rs.getString(9));
					bean.setShippingAddress(rs.getString(10));
					bean.setBillingAddress(rs.getString(11));
					bean.setEmailId(rs.getString(12));
					bean.setProductDescription(rs.getString(13));
					bean.setProductImage(rs.getString(14));
					bean.setCreatedBy(rs.getString(15));
					bean.setModifiedBy(rs.getString(16));
					bean.setCreatedDatetime(rs.getTimestamp(17));
					bean.setModifiedDatetime(rs.getTimestamp(18));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Product");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model search End");
	        return list;
	    }
	    
	    public List list() throws ApplicationException {
	        return list(0, 0);
	    }

	    /**
	     * Get List of Product with pagination
	     * 
	     * @return list : List of Product
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public List list(int pageNo, int pageSize) throws ApplicationException {
	        log.debug("Model list Started");
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from SO_INVOICE");
	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                InvoiceBean bean = new InvoiceBean();
	                bean.setId(rs.getLong(1));
					bean.setOrderId(rs.getLong(2));
					bean.setUserId(rs.getLong(3));
					bean.setUserName(rs.getString(4));
					bean.setProductId(rs.getLong(5));
					bean.setProductName(rs.getString(6));
					bean.setDate(rs.getDate(7));
					bean.setAmount(rs.getDouble(8));
					bean.setMobileNo(rs.getString(9));
					bean.setShippingAddress(rs.getString(10));
					bean.setBillingAddress(rs.getString(11));
					bean.setEmailId(rs.getString(12));
					bean.setProductDescription(rs.getString(13));
					bean.setProductImage(rs.getString(14));
					bean.setCreatedBy(rs.getString(15));
					bean.setModifiedBy(rs.getString(16));
					bean.setCreatedDatetime(rs.getTimestamp(17));
					bean.setModifiedDatetime(rs.getTimestamp(18));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of Product");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model list End");
	        return list;

	    }
	 
}
