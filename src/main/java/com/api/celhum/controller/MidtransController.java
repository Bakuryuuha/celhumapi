package com.api.celhum.controller;

import com.api.celhum.misc.ProjectStatus;
import com.api.celhum.model.Booking;
import com.api.celhum.model.KredivoCheckoutResponse;
import com.api.celhum.model.KredivoConfirmPurchase;
import com.api.celhum.model.MidtransNotification;
import com.api.celhum.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.api.celhum.misc.GlobalSetting.URL_SANDBOX_KREDIVO;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path="/midtrans", method = GET)
public class MidtransController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/notif")
    public @ResponseBody
    ResponseEntity<ProjectStatus> NotifMidtrans(@RequestBody MidtransNotification midtransNotification){

        Booking curBook = bookingRepository.findBookingById(midtransNotification.getOrder_id());
        curBook.setBookstatus(midtransNotification.getTransaction_status());

        Booking.Midtranspayment midtranspayment = new Booking.Midtranspayment();
        midtranspayment.setTransaction_id(midtransNotification.getTransaction_id());
        curBook.setMidtranspayment(midtranspayment);
        bookingRepository.save(curBook);


        return new ResponseEntity<ProjectStatus>(new ProjectStatus("sukses","good"), HttpStatus.OK);
    }
}
