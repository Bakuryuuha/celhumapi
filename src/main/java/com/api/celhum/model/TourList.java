package com.api.celhum.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document(collection = "tourlist")
public class TourList {
    @Id
    String id;
    String name;
    String country;
    String status;
    String kuota;
    String promo;
    //List<Date> departure_date;
    Price price;
    List<String>date_str;
    String tourid;
    String cover_image;
    String desc;
    List<Departure> departure;
    String downpayment;
    String show;
    String include;
    String exclude;
    String terms;
    String catatan;
    String durasi;
    String popular;
    String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(String downpayment) {
        this.downpayment = downpayment;
    }

    public List<Departure> getDeparture() {
        return departure;
    }

    public void setDeparture(List<Departure> departure) {
        this.departure = departure;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTourid() {
        return tourid;
    }

    public void setTourid(String tourid) {
        this.tourid = tourid;
    }

    public List<String> getDate_str() {
        return date_str;
    }

    public void setDate_str(List<String> date_str) {
        this.date_str = date_str;
    }

    public static class Departure{
        public static class Price{
            String price_adult;
            String price_child;
            String price_room_adult;
            String price_room_child;

            public String getPrice_adult() {
                return price_adult;
            }

            public void setPrice_adult(String price_adult) {
                this.price_adult = price_adult;
            }

            public String getPrice_child() {
                return price_child;
            }

            public void setPrice_child(String price_child) {
                this.price_child = price_child;
            }

            public String getPrice_room_adult() {
                return price_room_adult;
            }

            public void setPrice_room_adult(String price_room_adult) {
                this.price_room_adult = price_room_adult;
            }

            public String getPrice_room_child() {
                return price_room_child;
            }

            public void setPrice_room_child(String price_room_child) {
                this.price_room_child = price_room_child;
            }
        }

        Price price;

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        Date datedepart;
        String code;
        String remaining;

        public String getRemaining() {
            return remaining;
        }

        public void setRemaining(String remaining) {
            this.remaining = remaining;
        }

        public Date getDatedepart() {
            return datedepart;
        }

        public void setDatedepart(Date datedepart) {
            this.datedepart = datedepart;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    class Price{
        String price_adult;
        String price_child;
        String price_room_adult;
        String price_room_child;

        public Price(String price_adult, String price_child, String price_room_adult, String price_room_child) {
            this.price_adult = price_adult;
            this.price_child = price_child;
            this.price_room_adult = price_room_adult;
            this.price_room_child = price_room_child;
        }

        public String getPrice_room_child() {
            return price_room_child;
        }

        public void setPrice_room_child(String price_room_child) {
            this.price_room_child = price_room_child;
        }

        public String getPrice_room_adult() {
            return price_room_adult;
        }

        public void setPrice_room_adult(String price_room_adult) {
            this.price_room_adult = price_room_adult;
        }

        public String getPrice_adult() {
            return price_adult;
        }

        public void setPrice_adult(String price_adult) {
            this.price_adult = price_adult;
        }

        public String getPrice_child() {
            return price_child;
        }

        public void setPrice_child(String price_child) {
            this.price_child = price_child;
        }
    }

    class Itinerary{
        String order;
        String title;
        String desc;
        String image;
        String hotel_desc;
        String transport;
        String opt_desc;

        public Itinerary(String order, String title, String desc, String image, String hotel_desc, String transport, String opt_desc) {
            this.order = order;
            this.title = title;
            this.desc = desc;
            this.image = image;
            this.hotel_desc = hotel_desc;
            this.transport = transport;
            this.opt_desc = opt_desc;
        }

        public String getHotel_desc() {
            return hotel_desc;
        }

        public void setHotel_desc(String hotel_desc) {
            this.hotel_desc = hotel_desc;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }

        public String getOpt_desc() {
            return opt_desc;
        }

        public void setOpt_desc(String opt_desc) {
            this.opt_desc = opt_desc;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Itinerary> itinerary;

    public List<Itinerary> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<Itinerary> itinerary) {
        this.itinerary = itinerary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
}
