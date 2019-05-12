package com.politechnika.housing.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.politechnika.housing.exception.BillNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Bill;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.repository.BillRepository;
import com.politechnika.housing.service.impl.generator.PdfGenerator;
import com.politechnika.housing.service.inf.BillService;
import com.politechnika.housing.service.inf.PremisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private PremisesService premisesService;
    @Autowired
    private BillRepository billRepository;


    @Override
    public Bill get(int id) throws BillNotFoundException {
      return  billRepository.findById(id).orElseThrow( () -> new BillNotFoundException("Bill id:" + id));
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public List<Bill> getBillsForSpecificPremises(int id) throws PremisesNotFoundException {
        Premises premises = premisesService.get(id);
        List<Bill> bills = premises.getBills();
        Collections.sort(bills);
        return bills;
    }

    @Override
    public byte[] getPdfForBillId(String username,int id, String lang) {
        Bill bill = null;
        try {
            bill = get(id);
        } catch (BillNotFoundException e) {
            e.printStackTrace();
        }

        if (bill!= null && !bill.getPremises().getOccupant().getUser().getUsername().equals(username)) {
            return null;
        }

        try {
         return PdfGenerator.generate(bill,lang);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
