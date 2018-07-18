package com.api.celhum.controller;

import com.api.celhum.misc.ProjectStatus;
import com.api.celhum.model.Account;
import com.api.celhum.model.TourList;
import com.api.celhum.repository.TourListRepository;
import com.api.celhum.service.TourListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path="/tourlist", method = GET)
public class TourListController {
    @Autowired
    TourListService tourListService;

    @Autowired
    TourListRepository tourListRepository;

    @GetMapping("/show")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    List<TourList> ShowAll(){
        List<TourList> tourLists = tourListService.findByShow("yes");

        return tourLists;
    }

    @GetMapping("/showpopular")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    List<TourList> ShowPopular(){
        List<TourList> tourLists = tourListService.findByPopular("true");

        return tourLists;
    }

    @GetMapping("/checktokencelhum")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Optional<String> CheckTokenCelhum(String email){
        String checker = "available";

        Optional<Authentication> authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        return authentication.map(a -> {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) a.getDetails();
           return (String) ((Map) details.getDecodedDetails()).get("email");
        });
        //return new ResponseEntity<ProjectStatus>(new ProjectStatus("available",checker), HttpStatus.OK);

    }

    @GetMapping("/showbycountry/{name}")
    public @ResponseBody
    List<TourList> GetListByCountry(@PathVariable(value = "name") String name){
        return tourListService.findTourListByCountry(name);
    }

    @GetMapping("/promo/all")
    public @ResponseBody
    List<TourList> GetListByPromo(@PathVariable(value = "name") String name){
        return tourListService.findTourListByCountry(name);
    }

    @GetMapping("/showdetail/{name}")
    public @ResponseBody
    TourList GetByUniqueId(@PathVariable(value = "name") String name){
        return tourListService.findByTourId(name);
    }

    @GetMapping("/checkpricebycode/{code}")
    public @ResponseBody
    TourList GetPriceByDate(@PathVariable(value = "code") String code){
        TourList curTour = tourListService.findByCodeDepart(code);

//        if(curTour!=null){
//            return new TourList();
//        }
//        else{
//
//        }
        TourList.Departure departure = curTour.getDeparture().get(0);
        departure.getPrice().getPrice_adult();

        return curTour;
    }

    @PostMapping("/savenewtour/{date}")
    public @ResponseBody
    ResponseEntity<ProjectStatus> SaveTourBackEnd(TourList tour) throws ParseException {

        //looping inside departure DATE
        int i = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dateInString = "T15:23:01Z";

        Date date = formatter.parse(dateInString.replaceAll("Z$", "+0000"));
//        System.out.println(date);

//        System.out.println("time zone : " + TimeZone.getDefault().getID());
//        System.out.println(formatter.format(date));
        tourListRepository.save(tour);

        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Succeed...","a"), HttpStatus.OK);
    }
}
