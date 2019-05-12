package com.politechnika.housing.service;

import com.politechnika.housing.model.Bill;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.service.impl.converter.PremisesBillConverter;
import com.politechnika.housing.service.inf.BillService;
import com.politechnika.housing.service.inf.OccupantService;
import com.politechnika.housing.service.inf.PremisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
@EnableAsync
@EnableScheduling
@Component
public class ScheduleService {

    @Autowired
    private PremisesService premisesService;
    @Autowired
    private BillService billService;
    @Scheduled(cron="0 15 * * * ?")
    public void addUnfilledBills(){
        List<Premises> premisesList = premisesService.getAllPremises();

        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();

        for(Premises premises : premisesList) {
            if(!isPaidAll(premises.getBills()) && premises.getOccupant() != null) {
                Bill bill = new Bill();
                bill.setDate(df.format(today));
                bill.setHeating(123);
                bill.setHotWater(142);
                bill.setGas(54);
                bill.setColdWater(78);
                bill.setElectricity(32);

                List<Bill> bills = premises.getBills();
                Bill convertedBill = PremisesBillConverter.convert(bill);
                convertedBill.setAccepted(true);
                bills.add(convertedBill);
                convertedBill.setPremises(premises);
                premises.setBills(bills);
                billService.save(convertedBill);
                premisesService.save(premises);
            }
        }
    }


    boolean isPaidAll(List<Bill> bills) {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        int thisMonth = Calendar.getInstance().getTime().getMonth();

        for(Bill bill : bills) {
            Date billDate = null;
            try {
                billDate = df.parse(bill.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (billDate.getMonth() == thisMonth) {
                return true;
            }
        }
        return false;
    }
}
