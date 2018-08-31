package com.ssmm.stockcrawler.neo4jtests;

import org.neo4j.ogm.session.Session;

import junit.framework.TestCase;

public class Neo4jSessionFactoryTests extends TestCase {
	private Session session;

	public void test1() {
		// TODO Auto-generated method stub
		session = Neo4jSessionFactory.getInstance().getNeo4jSession();
		session.save(new Stock("삼성전자"));
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
