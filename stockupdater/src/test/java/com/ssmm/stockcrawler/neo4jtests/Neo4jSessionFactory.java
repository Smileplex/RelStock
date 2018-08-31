package com.ssmm.stockcrawler.neo4jtests;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4jSessionFactory {
	private static Configuration configuration = new Configuration.Builder().uri("bolt://localhost")
			.credentials("neo4j", "tjehddn1178").build();
	private static SessionFactory sessionFactory = new SessionFactory(configuration,
			"com.ssmm.stockcrawler.neo4jtests");
	private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

	static Neo4jSessionFactory getInstance() {
		return factory;
	}

	private Neo4jSessionFactory() {
	}

	Session getNeo4jSession() {
		return sessionFactory.openSession();
	}
}
