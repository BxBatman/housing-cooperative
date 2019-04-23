package com.politechnika.housing.controller;

import com.politechnika.housing.exception.OccupantNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Bill;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.service.inf.BillService;
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

    @Autowired
    private BillService billService;

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

    @RequestMapping(value = "/bill/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addBillToPremises(@RequestBody Bill bill, @PathVariable("id") int id) {
        premisesService.addBillToPremises(bill,id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{billId}/{premisesId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteBillFromPremises(@PathVariable("billId") int billId, @PathVariable("premisesId") int premisesId) {
        premisesService.deleteBillFromPremises(billId, premisesId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/occupant/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPremisesForOccupant(@PathVariable("id") int occupantId) throws OccupantNotFoundException {
       return ResponseEntity.ok(premisesService.getPremisesForSpecificOccupant(occupantId));
    }

    @RequestMapping(value = "/bills/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getBillsForPremises(@PathVariable("id") int premisesId) throws PremisesNotFoundException {
        return ResponseEntity.ok(billService.getBillsForSpecificPremises(premisesId));
    }

    @RequestMapping(value = "/bill/accept/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity setBillAccepted(@PathVariable("id") int billId) {
        premisesService.setBillAccepted(billId);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/available", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllAvailablePremies(){
        return ResponseEntity.ok(premisesService.getAllAvailablePremises());
    }
}
