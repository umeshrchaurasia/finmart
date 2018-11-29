package com.datacomp.magicfinmart.utility;

import java.util.Comparator;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;

public class SortbyInsurer implements Comparator<HealthQuoteEntity> {
    // Used for sorting in ascending order net premium
    public int compare(HealthQuoteEntity a, HealthQuoteEntity b) {

        /*if (a.getServicetaxincl().toLowerCase().equals("e") && b.getServicetaxincl().toLowerCase().equals("e")) {
            return ((int) Math.round(a.getNetPremium())) - ((int) Math.round(b.getNetPremium()));
        } else if (a.getServicetaxincl().toLowerCase().equals("i") && b.getServicetaxincl().toLowerCase().equals("i")) {
            return ((int) Math.round(a.getGrossPremium())) - ((int) Math.round(b.getGrossPremium()));
        } else {
            return ((int) Math.round(a.getNetPremium())) - ((int) Math.round(b.getNetPremium()));
        }
        */

        return ((int) Math.round(a.getDisplayPremium())) - ((int) Math.round(b.getDisplayPremium()));
    }
}