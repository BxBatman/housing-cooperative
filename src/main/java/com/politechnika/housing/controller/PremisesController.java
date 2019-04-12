package com.politechnika.housing.controller;

import com.politechnika.housing.exception.OccupantNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Cost;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.service.inf.PremisesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/premises")
public class PremisesController {

    private static final Logger logger = Logger.getLogger(PremisesController.class);

    @Autowired
    private PremisesService premisesService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPremises(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(premisesService.get(id));
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createPremises(@RequestBody Premises premises){
        int id = premisesService.save(premises);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updatePremises(@RequestBody Premises premises) {
        int id = premisesService.save(premises);
        return ResponseEntity.ok(id);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePremises(@PathVariable int id) {
        premisesService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/cost/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addCostToPremises(@RequestBody Cost cost, @PathVariable("id") int id) {
        premisesService.addCostToPremises(cost,id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{costId}/{premisesId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteCostFromPremises(@PathVariable("costId") int costId, @PathVariable("premisesId") int premisesId) {
        premisesService.deleteCostFromPremises(costId, premisesId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/occupant/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPremiesForOccupant(@PathVariable("id") int occupantId) throws OccupantNotFoundException {
       return ResponseEntity.ok(premisesService.getPremisesForSpecificOccupant(occupantId));
    }


}
