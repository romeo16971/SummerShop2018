package com.shosu.webSrvice;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.h2.engine.Session;
import org.hibernate.SessionFactory;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shosu.dto.EmployeeDTO;
import com.shosu.dto.ProductDTO;
import com.shosu.dto.mobile.BaseMDTO;
import com.shosu.dto.mobile.SearchCriteriaMoblie;
import com.shosu.orm.CurrentStatus;

@RestController
@RequestMapping("ProductController")
public class ProductController extends BaseController {

	@Autowired
	private Ignite ignite;
	
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "getAllProduct", method = RequestMethod.GET)
	public BaseMDTO getAllProduct(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = new BaseMDTO();

		try {
			String json = requestParam.get("json");
			SearchCriteriaMoblie criteria = gson.fromJson(json, SearchCriteriaMoblie.class);
			result.setStatus(0);
			result.setContent("ahihi");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "loadCache", method = RequestMethod.GET)
	public BaseMDTO loadCache(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = new BaseMDTO();

		try {
			IgniteCache<Integer, EmployeeDTO> cityCache = ignite.cache("viet");
			cityCache.loadCache(null);
//			IgniteCache<String, CurrentStatus> currentCache = ignite.cache("CurrentLocationFactory");
//			currentCache.loadCache(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "loadCache2", method = RequestMethod.GET)
	public BaseMDTO loadCache2(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = new BaseMDTO();

		try {
//			IgniteCache<Integer, EmployeeDTO> cityCache = ignite.cache("viet");
//			cityCache.loadCache(null);
			Collection<String> nameCache =ignite.cacheNames();
			for (String name :nameCache) {
				IgniteCache<String, CurrentStatus> currentCache = ignite.cache(name);
//				currentCache.putAll(arg0);
				currentCache.loadCache(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "getEmployee", method = RequestMethod.GET)
	@Transactional
	public BaseMDTO getEmployee(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = new BaseMDTO();

		try {
			String json = requestParam.get("json");
			Map<String, String> criteria = gson.fromJson(json, Map.class);
			int id = Integer.valueOf(criteria.get("id"));
			IgniteCache<Integer, EmployeeDTO> cityCache = ignite.cache("viet");
			SqlFieldsQuery query = new SqlFieldsQuery("SELECT * FROM employeedto E ");
			FieldsQueryCursor<List<?>> cursor = cityCache.query(query);
			Iterator<List<?>> iterator = cursor.iterator();
			int count=0;
			while (iterator.hasNext()) {
				List<?> row = iterator.next();
				count++;
				System.out.println(row.get(0) + ", " + row.get(1));
			}
			
			result.setStatus(count);
			result.setContent("ahihi");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "countCurrent", method = RequestMethod.GET)
	@Transactional
	public BaseMDTO countCurrent(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = new BaseMDTO();

		try {
			String json = requestParam.get("json");
			Map<String, String> criteria = gson.fromJson(json, Map.class);
			int id = Integer.valueOf(criteria.get("id"));
			IgniteCache<Integer, EmployeeDTO> cityCache = ignite.cache("CurrentLocationFactory");
			SqlFieldsQuery query = new SqlFieldsQuery("SELECT count(*) FROM currentStatus E ");
			FieldsQueryCursor<List<?>> cursor = cityCache.query(query);
			Iterator<List<?>> iterator = cursor.iterator();
			List<?> row = iterator.next();
			Long count =(Long) row.get(0);

			System.out.println(count);

			result.setStatus(count.intValue());
			result.setContent("ahihi");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	@Transactional
	public BaseMDTO delete(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = new BaseMDTO();

		try {
			String json = requestParam.get("json");
			Map<String, String> criteria = gson.fromJson(json, Map.class);
			String id = criteria.get("id");
			IgniteCache<String, CurrentStatus> cityCache = ignite.cache("CurrentLocationFactory");
			SqlFieldsQuery query = new SqlFieldsQuery("Select currentLatitude, currentLongtitude  FROM currentStatus E WHERE driverId  = ?  ");
			query.setArgs(id);
			FieldsQueryCursor<List<?>> cursor = cityCache.query(query);
			Iterator<List<?>> iterator = cursor.iterator();
			List<?> row = iterator.next();
			
			System.out.println("currentLatitude : " +row.get(0) + " currentLongtitude: " + row.get(1));

			result.setStatus(1);
			result.setContent("ahihi");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "addCurrentStatus", method = RequestMethod.GET)
	@Transactional
	public BaseMDTO addCurrentStatus(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = new BaseMDTO();

		try {
			Random rand = new Random();
			org.hibernate.Session session = sessionFactory.getCurrentSession();
			
			CurrentStatus adding = new CurrentStatus();
			String json = requestParam.get("json");
			Map<String, String> criteria = gson.fromJson(json, Map.class);
			int numberAdding = Integer.valueOf(criteria.get("id"));
			String[] carType = {"SAV","BIK","BU","DEL","ECH","ECO","LUX"};
			for(int i =0; i< numberAdding;i++) {
				UUID id = UUID.randomUUID();
				int  carTypeIndex = rand.nextInt(6) ;
				adding = new CurrentStatus();
				adding.setAutoOnline(false);
				adding.setCompanyGroupId(1);
				adding.setCompanyId(1);
				adding.setCurrentLatitude(Math.random()*100);
				adding.setCurrentLongtitude(Math.random()*100);
				adding.setCurrentStatus("AC");
				adding.setDriverId(id.toString());
				adding.setLanguageCode("vi");
				adding.setLevel(carType[carTypeIndex]);
				adding.setLastModifiedDate(new Date() );
				adding.setSelfControlled(true);
				session.persist(adding);			
			}
			
			result.setStatus(1);
			result.setContent("ahihi");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
