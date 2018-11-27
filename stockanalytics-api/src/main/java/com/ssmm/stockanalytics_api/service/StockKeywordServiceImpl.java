package com.ssmm.stockanalytics_api.service;

import com.ssmm.stockanalytics_api.model.StockKeyword;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockKeywordServiceImpl extends GenericService<StockKeyword> implements StockKeywordService {

    @Override
    public StockKeyword findByName(String name) {
        // TODO Auto-generated method stub
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return session.queryForObject(getEntityType(), "match (n:StockKeyword{name:{name}}) return n", params);
    }

    @Override
    public Iterable<StockKeyword> findAllWithin2DepthByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);


        return session.query(getEntityType(), "MATCH (sk1:StockKeyword{name:{name}})<-[r1:RELATED_TO]-(sk2:StockKeyword)<-[r2:RELATED_TO]-(sk3:StockKeyword)\n" +
                "match(sk1)-[r12:HAS]-(s1:Stock) \n" +
                "match(sk2)-[r13:HAS]-(s2:Stock)\n" +
                "match(sk3)-[r14:HAS]-(s3:Stock)\n" +
                "return sk1,r1,sk2,r2,sk3,r12,s1,r13,s2,r14,s3 limit 20", params);
    }

    @Override
    public StockKeyword save(StockKeyword entity) {
        // TODO Auto-generated method stub
        try (Transaction tx = session.beginTransaction()) {
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Class<StockKeyword> getEntityType() {
        // TODO Auto-generated method stub
        return StockKeyword.class;
    }

}