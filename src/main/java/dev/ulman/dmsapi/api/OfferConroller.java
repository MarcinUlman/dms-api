package dev.ulman.dmsapi.api;

import dev.ulman.dmsapi.model.Offer;
import dev.ulman.dmsapi.model.OfferDetails;
import dev.ulman.dmsapi.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/offers")
@Controller
public class OfferConroller {

    private final OfferService offerService;

    @Autowired
    public OfferConroller(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getOfferById(@PathVariable("id") long id){
        Offer offer = offerService.getOfferById(id);

        if (offer == null)
            return new ResponseEntity<>("Offer does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Offer>(offer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewOffer(@RequestBody Offer newOffer){
            offerService.add(newOffer);
        return new ResponseEntity<Offer>(newOffer, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable("id") long id){
        offerService.delete(id);
        return new ResponseEntity<>("Offer is no longer in the database", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> editOffer(@PathVariable("id") long offerId, @RequestBody OfferDetails offerDetails){
        Offer offer = offerService.getOfferById(offerId);
        if (offer == null)
            return new ResponseEntity<>("Offer does not exist", HttpStatus.NOT_FOUND);

        offerService.editOffer(offerId, offerDetails);
        return new ResponseEntity<Offer>(offer, HttpStatus.OK);
    }
}
