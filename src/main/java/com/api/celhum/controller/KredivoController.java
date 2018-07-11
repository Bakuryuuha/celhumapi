package com.api.celhum.controller;

import com.api.celhum.misc.ProjectStatus;
import com.api.celhum.model.Booking;
import com.api.celhum.model.KredivoCheckoutResponse;
import com.api.celhum.model.KredivoConfirmPurchase;
import com.api.celhum.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import static com.api.celhum.misc.GlobalSetting.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path="/kredivo", method = GET)
public class KredivoController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/notif")
    public @ResponseBody
    ResponseEntity<ProjectStatus> NotifKredivo(@RequestBody KredivoConfirmPurchase kredivoConfirmPurchase){

        Booking curBook = bookingRepository.findBookingById(kredivoConfirmPurchase.getOrder_id());
        curBook.setBookstatus(kredivoConfirmPurchase.getTransaction_status());

        Booking.Kredivopayment kredivopayment = new Booking.Kredivopayment();
        kredivopayment.setTransaction_id(kredivoConfirmPurchase.getTransaction_id());
        kredivopayment.setSignature_key(kredivoConfirmPurchase.getSignature_key());

        curBook.setKredivopayment(kredivopayment);
        bookingRepository.save(curBook);

        // set headers
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Basic VlQtc2VydmVyLVZYOFFXSFBxV1hZUUFIZ0twZjVWYTE0Vzo="); //sandbox
//        headers.set("Accept","application/json");
//
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//        // send request and parse result
//        ResponseEntity<KredivoCheckoutResponse> response = restTemplate
//                .exchange(URL_SANDBOX_KREDIVO+"/v2/checkout_url", HttpMethod.POST, entity, KredivoCheckoutResponse.class);

        return new ResponseEntity<ProjectStatus>(new ProjectStatus("sukses","good"), HttpStatus.OK);
    }

    @PostMapping("/updatenotif")
    public @ResponseBody
    ResponseEntity<ProjectStatus> UpdateNotifKredivo(@RequestBody KredivoConfirmPurchase kredivoConfirmPurchase){

        // set headers
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Basic VlQtc2VydmVyLVZYOFFXSFBxV1hZUUFIZ0twZjVWYTE0Vzo="); //sandbox
//        headers.set("Accept","application/json");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // send request and parse result
        ResponseEntity<KredivoCheckoutResponse> response = restTemplate
                .exchange(URL_SANDBOX_KREDIVO+"/v2/checkout_url", HttpMethod.GET, entity, KredivoCheckoutResponse.class);

        return new ResponseEntity<ProjectStatus>(new ProjectStatus("sukses","good"), HttpStatus.OK);
    }
}
