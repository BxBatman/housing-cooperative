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

    @RequestMapping(value = "/pdf/{username}/{id}/{lang}", method = RequestMethod.GET,produces = "application/pdf")
    @ResponseBody
    public ResponseEntity getPdf(@PathVariable("username") String username, @PathVariable("id") int id, @PathVariable("lang") String lang) {
       if (billService.getPdfForBillId(username,id,lang) ==null) {
           return ResponseEntity.noContent().build();
       } else {
           return ResponseEntity.ok(billService.getPdfForBillId(username,id,lang));
       }
    }
}
