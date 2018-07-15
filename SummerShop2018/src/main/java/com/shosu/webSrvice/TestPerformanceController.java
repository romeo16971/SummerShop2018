package com.shosu.webSrvice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shosu.dto.mobile.BaseMDTO;
import com.shosu.orm.CurrentStatus;
import com.shosu.orm.Driver;
import com.shosu.thread.DriverChangeLocatonThread;
import com.shosu.thread.RiderRequestLocatonThread;



@RestController
@RequestMapping("TestPerformance")
public class TestPerformanceController extends BaseController {

	@Autowired
	Ignite ignite;

	@RequestMapping(value = "/TestPerformanceDriver", method = RequestMethod.POST)
	public BaseMDTO testPerformanceDriver(@RequestParam Map<String, String> requestParams) {
		BaseMDTO dto = new BaseMDTO();
		try {
			String json = requestParams.get("json");
			Map<String, String> criteria = gson.fromJson(json, Map.class);
			String domain = criteria.get("domain");
			// GET ALL ID

			IgniteCache<String, CurrentStatus> cityCache = ignite.cache("CurrentLocationFactory");
			SqlFieldsQuery query = new SqlFieldsQuery("Select driverId FROM currentStatus E   ");
			List<Driver> listDriver = new ArrayList<Driver>();
			FieldsQueryCursor<List<?>> cursor = cityCache.query(query);
			Iterator<List<?>> iterator = cursor.iterator();
			while(iterator.hasNext()) {
				List<?> row = iterator.next();
				Driver add = new Driver((String)row.get(0));
				listDriver.add(add);
			}

			for (Driver temp : listDriver) {
				DriverChangeLocatonThread nigga = new DriverChangeLocatonThread(temp.getDriverId(), temp, domain);
				nigga.start();
			}
			dto.setStatus(0);
		}  catch (Exception e) {
			dto.setStatus(1);
		}
		return dto;
	}
	
	@RequestMapping(value = "/TestPerformanceRider", method = RequestMethod.POST)
	public BaseMDTO testPerformanceRider(@RequestParam Map<String, String> requestParams) {
		BaseMDTO dto = new BaseMDTO();
		try {
			String json = requestParams.get("json");
			Map<String, ?> criteria = gson.fromJson(json, Map.class);
			String domain = (String) criteria.get("domain");
			Double totalThread =(Double)criteria.get("total");
			

			for (int i=0;i<totalThread;i++) {
				UUID id = UUID.randomUUID();
				RiderRequestLocatonThread nigga = new RiderRequestLocatonThread(id.toString(), domain);
				nigga.start();
			}
			dto.setStatus(0);
		}  catch (Exception e) {
			dto.setStatus(1);
			e.printStackTrace();
		}
		return dto;
	}

}
