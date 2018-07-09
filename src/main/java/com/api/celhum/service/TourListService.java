package com.api.celhum.service;

import com.api.celhum.model.TourList;

import java.util.List;
import java.util.Optional;

public interface TourListService {
    List<TourList> ShowAll();

    Optional<TourList> findById();

    List<TourList> ShowByLastName();

    TourList findByCountry(String name);

    List<TourList> findTourListByCountry(String name);

    TourList findByTourId(String tourid);

    List<TourList> findByCodeDepartList(String code);

    TourList findByCodeDepart(String code);

    List<TourList> findByShow(String code);
    List<TourList> findByPopular(String code);
}
