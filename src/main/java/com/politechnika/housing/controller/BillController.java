package com.politechnika.housing.controller;

import com.politechnika.housing.service.inf.BillService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bill")
public class BillController {
    private static final Logger logger = Logger.getLogger(OccupantController.class);

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET,produces = "application/pdf")
    @ResponseBody
    public ResponseEntity getPdf(@PathVariable("id") int id) {
       return ResponseEntity.ok(billService.getPdfForBillId(id));
    }
}
