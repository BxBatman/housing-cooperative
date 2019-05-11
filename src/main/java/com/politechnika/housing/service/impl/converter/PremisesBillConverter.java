package com.politechnika.housing.service.impl.converter;

import com.politechnika.housing.model.Bill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PremisesBillConverter {

    private static double ELECTRICITY_FACTOR = 1.5;
    private static double GAS_FACTOR = 1.6;
    private static double COLD_WATER_FACTOR = 1.7;
    private static double HOT_WATOER_FACTOR = 1.4;
    private static double HEATING_FACTOR = 1.3;


    public static Bill convert(Bill bill) {
        Bill convertedBill = new Bill();
        convertedBill.setId(bill.getId());
        convertedBill.setElectricity(Math.round(bill.getElectricity() * ELECTRICITY_FACTOR*100.0)/100.0);
        convertedBill.setGas(Math.round(bill.getGas() * GAS_FACTOR *100.0)/100.0);
        convertedBill.setColdWater(Math.round(bill.getColdWater() * COLD_WATER_FACTOR*100.0)/100.0);
        convertedBill.setHotWater(Math.round(bill.getHotWater() * HOT_WATOER_FACTOR*100.0)/100.0);
        convertedBill.setHeating(Math.round(bill.getHeating() * HEATING_FACTOR*100.0)/100.0);

        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        convertedBill.setDate(df.format(today));
        convertedBill.setDone(true);
        convertedBill.setAccepted(false);
        return convertedBill;
    }
}
