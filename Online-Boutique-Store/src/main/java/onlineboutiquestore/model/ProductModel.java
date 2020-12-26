package onlineboutiquestore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import onlineboutiquestore.bean.CategoryBean;
import onlineboutiquestore.bean.ProductBean;
import onlineboutiquestore.exception.ApplicationException;
import onlineboutiquestore.exception.DatabaseException;
import onlineboutiquestore.exception.DuplicateRecordException;
import onlineboutiquestore.util.JDBCDataSource;

public class ProductModel {

	private static Logger log = Logger.getLogger(ProductModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM SO_PRODUCT");
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
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(productCode) FROM SO_PRODUCT");
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
			return 100101;
		}
	}

	public ProductBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SO_PRODUCT WHERE NAME=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setProductCode(rs.getLong(4));
				bean.setName(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setPrice(rs.getDouble(7));
				bean.setImage(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by Fined By Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Name End");
		return bean;
	}

	public ProductBean findByCategoryAndName(long catId, String name) throws ApplicationException {
		log.debug("Model findBy CategoryIdAndName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SO_PRODUCT WHERE categoryId=? And Name=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1,catId);
			pstmt.setString(2, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setProductCode(rs.getLong(4));
				bean.setName(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setPrice(rs.getDouble(7));
				bean.setImage(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by Fined By CategoryId And Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy CategoryIdAndName End");
		return bean;
	}
	
	
	public ProductBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SO_PRODUCT WHERE ID=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setProductCode(rs.getLong(4));
				bean.setName(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setPrice(rs.getDouble(7));
				bean.setImage(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
	
	
	public ProductBean findByProductCode(long pk) throws ApplicationException {
		log.debug("Model findByProductCode Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SO_PRODUCT WHERE ProductCode=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setCategoryId(rs.getLong(2));
				bean.setCategoryName(rs.getString(3));
				bean.setProductCode(rs.getLong(4));
				bean.setName(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setPrice(rs.getDouble(7));
				bean.setImage(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by Product Code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByProductCode End");
		return bean;
	}
	
	
	public long add(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		int NextProductCode = 0;
		ProductBean duplicataProduct = findByCategoryAndName(bean.getCategoryId(),bean.getName());

		// Check if create Product already exist
		if (duplicataProduct != null) {
			throw new DuplicateRecordException("Product Is Already Exist This Category");
		}
		
		CategoryModel cModel=new CategoryModel();
		CategoryBean cBean= cModel.findByPK(bean.getCategoryId());
		bean.setCategoryName(cBean.getName());
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			NextProductCode=nextProductCode();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SO_PRODUCT VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,bean.getCategoryId());
			pstmt.setString(3,bean.getCategoryName());
			pstmt.setLong(4,NextProductCode);
			pstmt.setString(5, bean.getName());
			pstmt.setString(6, bean.getDescription());
			pstmt.setDouble(7,bean.getPrice());
			pstmt.setString(8,bean.getImage());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
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

	 public void delete(ProductBean bean) throws ApplicationException {
	        log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM SO_PRODUCT WHERE ID=?");
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
	
	 public List search(ProductBean bean) throws ApplicationException {
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
	    public List search(ProductBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM SO_PRODUCT WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            
	            if (bean.getProductCode() > 0) {
	                sql.append(" AND ProductCode = " + bean.getProductCode());
	            }
	            
	            if (bean.getCategoryId() > 0) {
	                sql.append(" AND CategoryId = " + bean.getCategoryId());
	            }
	            
	            if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
	            }
	            if (bean.getCategoryName() != null && bean.getCategoryName().length() > 0) {
					sql.append(" AND CategoryName LIKE '" + bean.getCategoryName() + "%'");
	            }
	            if (bean.getDescription() != null
	                    && bean.getDescription().length() > 0) {
					sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription()
	                        + "%'");
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
	                bean = new ProductBean();
	                bean.setId(rs.getLong(1));
					bean.setCategoryId(rs.getLong(2));
					bean.setCategoryName(rs.getString(3));
					bean.setProductCode(rs.getLong(4));
					bean.setName(rs.getString(5));
					bean.setDescription(rs.getString(6));
					bean.setPrice(rs.getDouble(7));
					bean.setImage(rs.getString(8));
					bean.setCreatedBy(rs.getString(9));
					bean.setModifiedBy(rs.getString(10));
					bean.setCreatedDatetime(rs.getTimestamp(11));
					bean.setModifiedDatetime(rs.getTimestamp(12));
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
	        StringBuffer sql = new StringBuffer("select * from SO_PRODUCT");
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
	                ProductBean bean = new ProductBean();
	                bean.setId(rs.getLong(1));
					bean.setCategoryId(rs.getLong(2));
					bean.setCategoryName(rs.getString(3));
					bean.setProductCode(rs.getLong(4));
					bean.setName(rs.getString(5));
					bean.setDescription(rs.getString(6));
					bean.setPrice(rs.getDouble(7));
					bean.setImage(rs.getString(8));
					bean.setCreatedBy(rs.getString(9));
					bean.setModifiedBy(rs.getString(10));
					bean.setCreatedDatetime(rs.getTimestamp(11));
					bean.setModifiedDatetime(rs.getTimestamp(12));
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
	    
	    
	    /**
	     * Update a Category
	     * 
	     * @param bean
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public void update(ProductBean bean) throws ApplicationException,
	            DuplicateRecordException {
	        log.debug("Model update Started");
	        Connection conn = null;
	        ProductBean duplicataProduct = findByCategoryAndName(bean.getCategoryId(),bean.getName());

	        // Check if updated Role already exist
	        if (duplicataProduct != null && duplicataProduct.getId() != bean.getId()) {
	            throw new DuplicateRecordException("Product Is Already Exist This Category");
	        }
	        
	        CategoryModel cModel=new CategoryModel();
			CategoryBean cBean= cModel.findByPK(bean.getCategoryId());
			bean.setCategoryName(cBean.getName());
			
			ProductBean pBean=findByPK(bean.getId());
	        
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("UPDATE SO_PRODUCT SET CATEGORYID=?,CATEGORYNAME=?,PRODUCTCODE=?, NAME=?,DESCRIPTION=?,PRICE=?,IMAGE=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
	            pstmt.setLong(1,bean.getCategoryId());
				pstmt.setString(2,bean.getCategoryName());
				pstmt.setLong(3,pBean.getProductCode());
				pstmt.setString(4, bean.getName());
				pstmt.setString(5, bean.getDescription());
				pstmt.setDouble(6,bean.getPrice());
				pstmt.setString(7,bean.getImage());
				pstmt.setString(8, bean.getCreatedBy());
				pstmt.setString(9, bean.getModifiedBy());
				pstmt.setTimestamp(10, bean.getCreatedDatetime());
				pstmt.setTimestamp(11, bean.getModifiedDatetime());
	            pstmt.setLong(12, bean.getId());
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException("Exception in updating Product ");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model update End");
	    }
	
}
