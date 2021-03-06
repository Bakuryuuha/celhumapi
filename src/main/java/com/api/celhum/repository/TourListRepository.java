package com.api.celhum.repository;

import com.api.celhum.model.TourList;
import com.api.celhum.service.TourListService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TourListRepository extends MongoRepository<TourList, String> {

    List<TourList> findByNameEndingWith(String regexp);

    TourList findTourByCountry(String name);

    List<TourList> findTourListByCountry(String name);

    TourList findTourListByTourid(String tourid);

    TourList findByDeparture_Code(String code);
    List<TourList> findTourListByShow(String show);
    List<TourList> findTourListByPopularOrderByOrder(String popular);

    @Query("{'departure.code': ?0},{'departure.price.$': 1}")
    TourList findTourListByDepartureCode(String code);
}

