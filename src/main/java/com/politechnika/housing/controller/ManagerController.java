package com.politechnika.housing.controller;

import com.politechnika.housing.exception.ManagerNotFoundException;
import com.politechnika.housing.model.Manager;
import com.politechnika.housing.service.inf.ManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/manager")
public class ManagerController {
    private static final Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getManager(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(managerService.get(id));
        } catch (ManagerNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createManager(@RequestBody Manager manager) {
        int id = managerService.save(manager);
        return ResponseEntity.ok(id);
    }

    @RequestMapping(value = "/id",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateManager(@RequestBody Manager manager){
        int id = managerService.update(manager);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteManager(@PathVariable("id") int id)  {
        managerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllManagers() {
        return ResponseEntity.ok(managerService.getAll());
    }


}
