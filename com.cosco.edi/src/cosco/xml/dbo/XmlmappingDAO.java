package cosco.xml.dbo;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cosco.vsagent.db.ConnectManager;
import cosco.vsagent.dbo.BaseDAO;
import cosco.xml.mapping.Xmlmapping;


/**
 * Data access object (DAO) for domain model class Xmlmapping.
 * @see cn.netstars.vsagent.mapping.vsl.Xmlmapping
 * @author MyEclipse - Hibernate Tools
 */

public class XmlmappingDAO extends BaseDAO {
	public static void main(String[] argv){
		XmlmappingDAO dao = new XmlmappingDAO();
		
		List<Xmlmapping> xmlmp = dao.getXmlmapping("",true);
		for(int i = 0;i < xmlmp.size();i++){
			Xmlmapping tmp = (Xmlmapping)xmlmp.get(i);
			System.out.println(tmp.getLccode());
		}
	}
	public  List<Xmlmapping> getXmlmapping(String sql, boolean flag){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try{
			if(flag == true)
				con = ConnectManager.getServerConnection();
			else
				con = ConnectManager.getConnection();
			
			if (con == null) return Collections.EMPTY_LIST;
			sm = con.createStatement();
//			rs = sm.executeQuery(sql);
			QueryRunner qRunner = new QueryRunner();
			List lBeans = (List) qRunner.query(con,
					" select * from Xmlmapping where partycode ='OZGHGA' And IEFLAG='I'", new BeanListHandler(
							Xmlmapping.class));
			
			List<Xmlmapping> list = new ArrayList<Xmlmapping>();
			for (int i = 0; i < lBeans.size(); i++) {
				Xmlmapping vals = (Xmlmapping) lBeans.get(i);
				list.add(vals);
//				System.out.println(vals.getBlno() + "-------------"
//						+ vals.getCarrtype());
			}
			
//			while(rs.next()){
//				list.add(Xmlmapping(rs, flag));
//			}
			return list;
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(con);
		}
		return Collections.EMPTY_LIST;
	}

    /*private static final Log log = LogFactory.getLog(XmlmappingDAO.class);

	//property constants
	public static final String PARTYCODE = "partycode";
	public static final String MAPTPCODE = "maptpcode";
	public static final String MAPTPNAME = "maptpname";
	public static final String LCCODE = "lccode";
	public static final String LCNAME = "lcname";
	public static final String EXCODE = "excode";
	public static final String EXNAME = "exname";
	public static final String NOTETEXT = "notetext";
	public static final String VERCODE = "vercode";
	public static final String IEFLAG = "ieflag";

    
    public void save(Xmlmapping transientInstance) {
        log.debug("saving Xmlmapping instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Xmlmapping persistentInstance) {
        log.debug("deleting Xmlmapping instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Xmlmapping findById( java.lang.Integer id) {
        log.debug("getting Xmlmapping instance with id: " + id);
        try {
            Xmlmapping instance = (Xmlmapping) getSession()
                    .get("cn.netstars.vsagent.mapping.vsl.Xmlmapping", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Xmlmapping instance) {
        log.debug("finding Xmlmapping instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.netstars.vsagent.mapping.vsl.Xmlmapping")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Xmlmapping instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Xmlmapping as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPartycode(Object partycode) {
		return findByProperty(PARTYCODE, partycode);
	}
	
	public List findByMaptpcode(Object maptpcode) {
		return findByProperty(MAPTPCODE, maptpcode);
	}
	
	public List findByMaptpname(Object maptpname) {
		return findByProperty(MAPTPNAME, maptpname);
	}
	
	public List findByLccode(Object lccode) {
		return findByProperty(LCCODE, lccode);
	}
	
	public List findByLcname(Object lcname) {
		return findByProperty(LCNAME, lcname);
	}
	
	public List findByExcode(Object excode) {
		return findByProperty(EXCODE, excode);
	}
	
	public List findByExname(Object exname) {
		return findByProperty(EXNAME, exname);
	}
	
	public List findByNotetext(Object notetext) {
		return findByProperty(NOTETEXT, notetext);
	}
	
	public List findByVercode(Object vercode) {
		return findByProperty(VERCODE, vercode);
	}
	
	public List findByIeflag(Object ieflag) {
		return findByProperty(IEFLAG, ieflag);
	}
	
    public Xmlmapping merge(Xmlmapping detachedInstance) {
        log.debug("merging Xmlmapping instance");
        try {
            Xmlmapping result = (Xmlmapping) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Xmlmapping instance) {
        log.debug("attaching dirty Xmlmapping instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Xmlmapping instance) {
        log.debug("attaching clean Xmlmapping instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }*/
}