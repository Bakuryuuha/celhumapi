package com.api.celhum.controller;

import com.api.celhum.misc.ProjectStatus;
import com.api.celhum.model.Account;
import com.api.celhum.model.Booking;
import com.api.celhum.model.KredivoPayments;
import com.api.celhum.model.TourList;
import com.api.celhum.repository.BookingRepository;
import com.api.celhum.service.AccountService;
import com.api.celhum.service.BookingRepositoryCustom;
import com.api.celhum.service.TourListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping(path="/booking", method = GET)
public class BookingController {

    public static class PassengerValue {
        String adult;
        String child;
        String extrabed;
        String singlesup;

        public PassengerValue(String adult, String child, String extrabed, String singlesup) {
            this.adult = adult;
            this.child = child;
            this.extrabed = extrabed;
            this.singlesup = singlesup;
        }

        public String getAdult() {
            return adult;
        }

        public void setAdult(String adult) {
            this.adult = adult;
        }

        public String getChild() {
            return child;
        }

        public void setChild(String child) {
            this.child = child;
        }

        public String getExtrabed() {
            return extrabed;
        }

        public void setExtrabed(String extrabed) {
            this.extrabed = extrabed;
        }

        public String getSinglesup() {
            return singlesup;
        }

        public void setSinglesup(String singlesup) {
            this.singlesup = singlesup;
        }
    }

    @Autowired
    private BookingRepositoryCustom bookingRepositoryCustom;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    TourListService tourListService;

    @GetMapping("/passengerform/{adult},{child},{extrabed},{singlesup}")
    public @ResponseBody
    ResponseEntity<PassengerValue> PassengerForm(@PathVariable(value = "adult") String adult,@PathVariable(value = "child") String child,@PathVariable(value = "extrabed") String extrabed,@PathVariable(value = "singlesup") String singlesup){

        return new ResponseEntity<PassengerValue>(new PassengerValue(adult,child,extrabed,singlesup),HttpStatus.OK);
    }

    @PostMapping("/passengerform2")
    public @ResponseBody
    PassengerValue PassengerForm2(@RequestBody PassengerValue pvalue){

        return pvalue;
    }

    @PostMapping("/paymentkredivo")
    public @ResponseBody
    ResponseEntity<KredivoPayments> KredivoCheckPayments(@RequestBody KredivoPayments kredivoPayments){
        //KREDIVO SECTION #POST PAYMENT

        final String uri = " https://sandbox.kredivo.com/kredivo";
        RestTemplate restTemplate = new RestTemplate();
        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        // create request body
        String input = "{" +
                "\"server_key\":\"8tLHIx8V0N6KtnSpS9Nbd6zROFFJH7\"," +
                "\"amount\":"+kredivoPayments.getAmount()+"," +
                "\"items\":[{" +
                "\"id\":\""+kredivoPayments.getItems().get(0).getId()+"\","+
                "\"name\":\""+kredivoPayments.getItems().get(0).getId()+"\","+
                "\"price\":"+kredivoPayments.getItems().get(0).getId()+","+
                "\"url\":\""+kredivoPayments.getItems().get(0).getId()+"\","+
                "\"type\":\""+kredivoPayments.getItems().get(0).getId()+"\","+
                "\"quantity\":"+kredivoPayments.getItems().get(0).getId()+""+
                "}]" +
                "}";


        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Authorization", "Basic " + "xxxxxxxxxxxx");
        HttpEntity<String> entity = new HttpEntity<String>(input, headers);

        // send request and parse result
        ResponseEntity<KredivoPayments> response = restTemplate
                .exchange(uri+"/v2/payments ", HttpMethod.POST, entity, KredivoPayments.class);

        return response;
    }

    @PostMapping("/submit")
    public @ResponseBody
    ResponseEntity<ProjectStatus> BookingSubmit(@RequestBody Booking book){

        //get single code
        String curCode = book.getDeparture().getCode();
        book.setTourid(curCode);
        //check tour id if VALID
        TourList curTour = tourListService.findByCodeDepart(curCode);
        if(curTour == null){
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Failed","Failed"), HttpStatus.NOT_ACCEPTABLE);
        }

        int k = book.getPassengerdetail().size();
        int sizePassenger = 1;


        //Date yesterday = new Date(System.currentTimeMillis()-5*60*60*1000);

        //get price
        TourList.Departure departure = curTour.getDeparture().get(0);
        String priceAdult = departure.getPrice().getPrice_adult();
        String priceChild = departure.getPrice().getPrice_child();
        String priceExtraBed = departure.getPrice().getPrice_room_child();
        String priceSingleSup = departure.getPrice().getPrice_room_adult();


//        //get passenger amount
//        String adult = book.getAdult();
//        String child = book.getChild();
//        String extrabed = book.getExtrabed();
//        String singlesup = book.getSinglesup();
//
//        //calculate price per passenger type
//        Long adultTotal = Long.valueOf(adult) * Long.valueOf(priceAdult);
//        Long childTotal = Long.valueOf(child) * Long.valueOf(priceChild);
//        Long extrabedTotal = Long.valueOf(extrabed) * Long.valueOf(priceExtraBed);
//        Long singlesupTotal = Long.valueOf(singlesup) * Long.valueOf(priceSingleSup);

//        Long grandTotal = adultTotal + childTotal + extrabedTotal + singlesupTotal + bookingRepository.countBookingByDepartdateIsBefore(today) ;
        String name = "";
        int adultAmount = 0;
        int childAmount = 0;
        int extraBedAmount = 0;
        int singleSupAmount = 0;
        //looping passenger details list
        for (Booking.PassengerDetail passDetail :book.getPassengerdetail()) {
            name += passDetail.getFirst_name();
            if(passDetail.getPassenger_type().equals("adult")){
                adultAmount++;
                if(passDetail.isPassenger_addon()){
                    singleSupAmount++;
                }
            }
            if(passDetail.getPassenger_type().equals("child")){
                childAmount++;
                if(passDetail.isPassenger_addon()){
                    extraBedAmount++;
                }
            }
//            if(passDetail.getPassenger_addon()!=null){
//                if(passDetail.getPassenger_addon().equals("extrabed")){
//                    extraBedAmount++;
//                }
//                if(passDetail.getPassenger_addon().equals("singlesup")){
//                    singleSupAmount++;
//                }
//            }
        }
        //calculate price
        Long adultTotal = adultAmount * Long.valueOf(priceAdult);
        Long childTotal = childAmount * Long.valueOf(priceChild);
        Long extrabedTotal = extraBedAmount * Long.valueOf(priceExtraBed);
        Long singlesupTotal = singleSupAmount * Long.valueOf(priceSingleSup);
        Long grandTotal = adultTotal + childTotal + extrabedTotal + singlesupTotal + bookingRepository.countBookingsByBookstatusEquals("pending");
//       return new ResponseEntity<ProjectStatus>(new ProjectStatus("Inside Looping "+adultAmount,name), HttpStatus.OK);
        // total = k * 9000000 + bookingRepository.countBookingByDepartdateIsBefore(today);
        String totalStr = String.valueOf(grandTotal);
        book.setTotalpayment(totalStr);

        //find id user and email when submit
        Account curAccount = accountService.findByEmail(book.getEmail());
        book.setOwner(curAccount.getId());
        book.setPassenger_count(String.valueOf(k));
        book.setBookstatus("pending");
        book.setDepartdate(departure.getDatedepart());
        //set start transaction date
        Date today = new Date();

        Date future = new Date(System.currentTimeMillis()+5*60*60*1000);
        book.setEndtransdate(future);
        book.setStarttransdate(today);
        bookingRepository.save(book);

        DateFormat df = new SimpleDateFormat("dd MMMM yyyy 'at' hh:mm");

        String dateToString = df.format(future);


        //KREDIVO SECTION #POST CHECKOUT
//        final String uri2 = "url";
//        RestTemplate restTemplate2 = new RestTemplate();
//        // Add the Jackson message converter
//        restTemplate2.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        // create request body
//        String input2 = "{" +
//                "\"server_key\":\"8tLHIx8V0N6KtnSpS9Nbd6zROFFJH7\"," +
//                "\"payment_type\":\""+grandTotal+"\"," +
//                    "\"transaction_details\":{" +
//                        "\"amount\":\""+adultAmount+"\","+
//                        "\"order_id\":\""+adultAmount+"\","+
//                        "\"items\":[{" +
//                                "\"id\":\""+adultAmount+"\","+
//                                "\"name\":\""+adultAmount+"\","+
//                                "\"price\":\""+adultAmount+"\","+
//                                "\"type\":\""+adultAmount+"\","+
//                                "\"quality\":\""+adultAmount+"\""+
//                            "}]"+
//                    "}," +
//                    "\"sellers\":[{" +
//                        "\"id\":\""+adultAmount+"\","+
//                        "\"name\":\""+adultAmount+"\","+
//                        "\"email\":\""+adultAmount+"\","+
//                        "\"address\":{"+
//                            "\"first_name\":\""+adultAmount+"\","+
//                            "\"last_name\":\""+adultAmount+"\","+
//                            "\"address\":\""+adultAmount+"\","+
//                            "\"city\":\""+adultAmount+"\","+
//                            "\"postal_code\":\""+adultAmount+"\","+
//                            "\"phone\":\""+adultAmount+"\","+
//                            "\"country_code\":\""+adultAmount+"\""+
//                        "}"+
//                    "}],"+
//                    "\"customer_details\":{" +
//                        "\"first_name\":\""+adultAmount+"\","+
//                        "\"last_name\":\""+adultAmount+"\","+
//                        "\"email\":\""+adultAmount+"\","+
//                        "\"phone\":\""+adultAmount+"\""+
//                    "},"+
//                    "\"billing_address\":{" +
//                        "\"first_name\":\""+adultAmount+"\","+
//                        "\"last_name\":\""+adultAmount+"\","+
//                        "\"address\":\""+adultAmount+"\","+
//                        "\"city\":\""+adultAmount+"\","+
//                        "\"postal_code\":\""+adultAmount+"\","+
//                        "\"phone\":\""+adultAmount+"\","+
//                        "\"country_code\":\""+adultAmount+"\""+
//                    "},"+
//                    "\"shipping_address\":{" +
//                        "\"first_name\":\""+adultAmount+"\","+
//                        "\"last_name\":\""+adultAmount+"\","+
//                        "\"address\":\""+adultAmount+"\","+
//                        "\"city\":\""+adultAmount+"\","+
//                        "\"postal_code\":\""+adultAmount+"\","+
//                        "\"phone\":\""+adultAmount+"\","+
//                        "\"country_code\":\""+adultAmount+"\","+
//                    "},"+
//                    "\"push_uri\":\""+adultAmount+"\","+
//                    "\"back_to_store_uri\":\""+adultAmount+"\""+
//                "}";
//
//
//        // set headers
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.setContentType(MediaType.APPLICATION_JSON);
//        headers2.set("Authorization", "Basic " + "xxxxxxxxxxxx");
//        HttpEntity<String> entity2 = new HttpEntity<String>(input, headers);
//
//        // send request and parse result
//        ResponseEntity<String> response2 = restTemplate2
//                .exchange(uri2, HttpMethod.POST, entity2, String.class);

        //end of kredivo section

//        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Succeed... "+adultAmount +" * "+priceAdult +" = "+adultTotal+", child = "+childAmount+" * "+priceChild +" = "+childTotal + extrabedTotal + singlesupTotal,totalStr), HttpStatus.OK);
        return new ResponseEntity<ProjectStatus>(new ProjectStatus(""+dateToString,totalStr), HttpStatus.OK);

    }

    @GetMapping("/show")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    List<Booking> ShowAll(){
        List<Booking> bookLists = bookingRepository.findAll();
        return bookLists;
    }

    @PostMapping("/methodpayment")
    public @ResponseBody
    ResponseEntity<ProjectStatus> MethodPayment(@RequestBody Booking book) {

        Date yesterday = new Date(System.currentTimeMillis()+5*60*60*1000);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateToString = df.format(yesterday);

        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Succeed...",dateToString), HttpStatus.OK);
    }

    @PostMapping("/sukses")
    public @ResponseBody
    ResponseEntity<ProjectStatus> Succeed(@RequestBody Booking book) {
        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Succeed...","tester"), HttpStatus.OK);
    }

    @PostMapping("/showhist")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    List<Booking> ShowHistoryBook(@RequestBody Account account){

        Account curAcc = accountService.findByEmail(account.getEmail());
        List<Booking> tourLists = bookingRepository.findBookingByOwner(curAcc.getId());

        //List<Booking> newTourLists = tourLists.stream().filter(x->"pending".equals(x.getStatus_order())).collect(Collectors.toList());
        return tourLists;
    }

    @GetMapping("/detail/{id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Booking ShowDetailBookBackEnd(@PathVariable(value = "id") String id){
        //Account curAcc = accountService.findByEmail(id);
        Booking booking = bookingRepository.findBookingById(id);

        //List<Booking> newTourLists = tourLists.stream().filter(x->"transferred".equals(x.getStatus_order())).collect(Collectors.toList());
        return booking;
    }

    @GetMapping("/update/{id}/{status}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity<ProjectStatus> UpdateStatusBookingBackEnd(@PathVariable(value = "id") String id,@PathVariable(value = "status") String status){
        //Account curAcc = accountService.findByEmail(id);
        Booking booking = bookingRepository.findBookingById(id);

        //update status order booking to desired string (transferred/rejected)
        booking.setBookstatus(status);
        bookingRepository.save(booking);
        //List<Booking> newTourLists = tourLists.stream().filter(x->"transferred".equals(x.getStatus_order())).collect(Collectors.toList());
        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Succeed..."+booking.getBookstatus(),""), HttpStatus.OK);
    }



}
