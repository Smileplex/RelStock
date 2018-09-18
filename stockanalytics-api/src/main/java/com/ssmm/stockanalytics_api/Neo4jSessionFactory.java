package com.ssmm.stockanalytics_api;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;


public class Neo4jSessionFactory {
	private static Configuration configuration = new Configuration.Builder().uri("bolt://localhost")
			.credentials("neo4j", "tjehddn1178").build();
	private static SessionFactory sessionFactory = new SessionFactory(configuration,
			"com.ssmm.stockanalytics_api.model");
	private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

	public static Neo4jSessionFactory getInstance() {
		return factory;
	}

	private Neo4jSessionFactory() {
	}

	public Session getNeo4jSession() {
		return sessionFactory.openSession();
	}
}
