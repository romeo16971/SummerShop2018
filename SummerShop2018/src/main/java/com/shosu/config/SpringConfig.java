package com.shosu.config;

import java.util.Arrays;

import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.h2.util.Cache;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.shosu.dto.EmployeeDTO;
import com.shosu.orm.CurrentStatus;


@Configuration
public class SpringConfig {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
//	@Bean
//	public VietStoreFactory vietStoreFactory() {
//		
//		VietStoreFactory result = new VietStoreFactory();
////		result.setSessionFactory(sessionFactory);	
//		return result;
//	}
	
//	@Bean
//	public CurrentStatusFactory csFactory() {
//		
//		CurrentStatusFactory result = new CurrentStatusFactory();
//		result.setSessionFactory(sessionFactory);	
//		return result;
//	}
//	
	@Bean
	public IgniteConfiguration igniteConfiguration() {
		IgniteConfiguration result = new IgniteConfiguration();
		
		// discover setting
		result.setIgniteInstanceName("viet");
		TcpDiscoverySpi discovery = new TcpDiscoverySpi();
		result.setTimeServerPortBase(475000);
		TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
		
		ipFinder.setLocalAddress("viet");
		ipFinder.setAddresses(Arrays.asList(
			    "viet:47500",
			    "ws11:47500"
			));
		discovery.setIpFinder(ipFinder);
		
		result.setDiscoverySpi(discovery);
		
		//datasotre setting
		
//		DataStorageConfiguration datastoreConfig = new DataStorageConfiguration();
//		datastoreConfig.set
//		result.setDataStorageConfiguration(datastoreConfig);

		
		// cache setting
		CacheConfiguration cache = new CacheConfiguration("viet");
		cache.setCacheStoreFactory(FactoryBuilder.factoryOf(VietStoreFactory.class));
        cache.setReadThrough(true);
        cache.setIndexedTypes(Integer.class, EmployeeDTO.class);
        cache.setCacheMode(CacheMode.REPLICATED);
        cache.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
//        result.setCacheConfiguration(cache);
        
        
        CacheConfiguration currentCache = new CacheConfiguration("CurrentLocationFactory");
        currentCache.setCacheStoreFactory(FactoryBuilder.factoryOf(CurrentStatusFactory.class));
        currentCache.setReadThrough(true);
        currentCache.setIndexedTypes(String.class, CurrentStatus.class);
        currentCache.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        currentCache.setCacheMode(CacheMode.REPLICATED);
//        currentCache.setWriteThrough(true);
//        currentCache.setWriteBehindEnabled(true);
//        currentCache.
//        currentCache.setCacheWriterFactory(FactoryBuilder.factoryOf(CurrentStatusFactory.class));
        
        
        
        result.setCacheConfiguration(currentCache,cache);

        
		
		return result;
	}
	
	
	@Bean
	public Ignite ignite() {
		 
		return Ignition.getOrStart(igniteConfiguration());
	}
}
