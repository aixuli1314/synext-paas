package com.synext.module.demo;

import co.synext.module.demo.model.EsUserDoc;

import co.synext.GovernApplication;

//import com.synext.module.demo.repository.EsUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * elasticsearch
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GovernApplication.class)
public class ElasticSearchTest {

	/*
	 * @Autowired // private ElasticsearchRestTemplate elasticsearchTemplate;
	 * private ElasticsearchTemplate elasticsearchTemplate;
	 * 
	 * @Autowired private EsUserRepository esUserRepository;
	 * 
	 * @Test public void createIndex() {
	 * elasticsearchTemplate.createIndex(EsUserDoc.class); }
	 * 
	 * @Test public void deleteIndex() {
	 * elasticsearchTemplate.deleteIndex(EsUserDoc.class); }
	 * 
	 * 
	 * @Test public void insert() { esUserRepository.save(EsUserDoc.builder()
	 * .username("xu.ran") .phone("13303820660") .email("xu.ran@xu.ran.net")
	 * .loginNum(100) .status(1) .userType(1) .uid(Long.valueOf(100)) .build()); }
	 * 
	 * @Test public void search() {
	 * System.out.println(esUserRepository.findByEmail("xu.ran")); }
	 */

}
