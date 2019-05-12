package com.politechnika.housing.service.inf;

import com.itextpdf.text.Document;
import com.politechnika.housing.exception.BillNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Bill;

import java.util.List;

public interface BillService {

    Bill get(int id) throws BillNotFoundException;
    void save(Bill bill);
    List<Bill> getBillsForSpecificPremises(int id) throws PremisesNotFoundException;
    byte[] getPdfForBillId(String username,int id,String lang);
}
