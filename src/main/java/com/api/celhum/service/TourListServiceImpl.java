package com.api.celhum.service;

import com.api.celhum.model.TourList;
import com.api.celhum.repository.TourListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service(value = "tourListService")
public class TourListServiceImpl implements TourListService{
    @Autowired
    private TourListRepository tourListRepository;

    @Autowired private MongoTemplate mongoTemplate;

    public List<TourList>ShowAll(){
        return tourListRepository.findAll();
    }

    @Override
    public Optional<TourList> findById() {
        return Optional.empty();
    }

    @Override
    public List<TourList> ShowByLastName() {
        return tourListRepository.findByNameEndingWith("a");
    }

    @Override
    public TourList findByCountry(String name) {
        return tourListRepository.findTourByCountry(name);
    }

    @Override
    public List<TourList> findTourListByCountry(String name) {
        return tourListRepository.findTourListByCountry(name);
    }

    @Override
    public TourList findByTourId(String tourid) {
        return tourListRepository.findTourListByTourid(tourid);
    }

    @Override
    public List<TourList> findByCodeDepartList(String code) {
        return null;
    }

    @Override
    public TourList findByCodeDepart(String code) {

//
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("departure.code").is(code),Criteria.where("departure.price.$").is(code));

        BasicQuery query1 = new BasicQuery("{\"departure.code\":\""+code+"\"}");

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("departure.code").is(code).andOperator(Criteria.where("departure.price.$").is(code)));

        query1.fields().include("departure.price.$");

        TourList tourList = mongoTemplate.findOne(query1, TourList.class,"tourlist");

//        List<TourList> tourList2 = mongoTemplate.find(query1.limit(1),TourList.class,"tourlist");
        return tourList;
//        return tourListRepository.findTourListByDepartureCode(code);
    }

    @Override
    public List<TourList> findByShow(String show) {
        return tourListRepository.findTourListByShow(show);
    }

    @Override
    public List<TourList> findByPopular(String code) {
        return tourListRepository.findTourListByPopularOrderByOrder(code);
    }
}
