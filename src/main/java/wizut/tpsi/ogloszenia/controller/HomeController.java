/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.services.OffersService;
import wizut.tpsi.ogloszenia.web.OfferFilter;





/**
 *
 * @author frymu
 */
@Controller
public class HomeController {
    
    @Autowired
    OffersService offer;
    
    @RequestMapping("/")
    public String home(Model model, OfferFilter offerFilter) {        
        
    List<CarManufacturer> carManufacturers = offer.getcarManufacturers();
    List<CarModel> carModels = offer.getCarModels();

    List<Offer> offers;

    if(offerFilter.getManufacturerId()!= 0) {
        offers = offer.getOffersByMAnufacturer(offerFilter.getManufacturerId());
    } else {
        offers = offer.getOffers();
    }

    model.addAttribute("carManufacturers", carManufacturers);
    model.addAttribute("carModels", carModels);
    model.addAttribute("offers", offers);

    return "offersList";
    }
    
    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer of = offer.getOffer(id);
        model.addAttribute("offer", of);
        return "offerDetails";
    }
    @GetMapping("/newoffer")
    public String newOfferForm(Model model, Offer of) {
        List<CarModel> carModels = offer.getCarModels();
        List<BodyStyle> bodyStyles = offer.getBodyTypes();
        List<FuelType> fuelTypes = offer.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        return "offerForm";
    }
    @PostMapping("/newoffer")
    public String saveNewOffer(Model model, @Valid Offer of, BindingResult binding) {
        if(binding.hasErrors()) {
            List<CarModel> carModels = offer.getCarModels();
            List<BodyStyle> bodyStyles = offer.getBodyTypes();
            List<FuelType> fuelTypes = offer.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

        return "offerForm";
    }
    of = offer.createOffer(of);

    return "redirect:/offer/" + of.getId();
}
}