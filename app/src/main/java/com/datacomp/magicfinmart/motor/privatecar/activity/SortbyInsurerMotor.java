package com.datacomp.magicfinmart.motor.privatecar.activity;

import java.util.Comparator;

import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;

public class SortbyInsurerMotor implements Comparator<ResponseEntity> {
    // Used for sorting in ascending order net premium
    public int compare(ResponseEntity a, ResponseEntity b) {

        /*if (a.getServicetaxincl().toLowerCase().equals("e") && b.getServicetaxincl().toLowerCase().equals("e")) {
            return ((int) Math.round(a.getNetPremium())) - ((int) Math.round(b.getNetPremium()));
        } else if (a.getServicetaxincl().toLowerCase().equals("i") && b.getServicetaxincl().toLowerCase().equals("i")) {
            return ((int) Math.round(a.getGrossPremium())) - ((int) Math.round(b.getGrossPremium()));
        } else {
            return ((int) Math.round(a.getNetPremium())) - ((int) Math.round(b.getNetPremium()));
        }
        */

        if (a.getFinal_premium_with_addon() != null && !a.getFinal_premium_with_addon().equals(""))
            return ((int) Math.round(Double.parseDouble(a.getFinal_premium_with_addon()))) - ((int) Math.round(Double.parseDouble(b.getFinal_premium_with_addon())));

        return ((int) Math.round(Double.parseDouble(a.getPremium_Breakup().getNet_premium()))) - ((int) Math.round(Double.parseDouble(b.getPremium_Breakup().getNet_premium())));
    }
}