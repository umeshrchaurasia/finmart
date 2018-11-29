package com.datacomp.magicfinmart.salesmaterial;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rajeev Ranjan on 26/07/2018.
 */

public class SharePospDetailsEntity {
    String pospNAme, pospDesg, pospEmail, PospMobNo;
    URL pospPhotoUrl;

    public SharePospDetailsEntity() {
        this.pospDesg = "LandMark POSP";
        try {
            this.pospPhotoUrl = new URL("http://qa.mgfm.in/images/profile_pic.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public URL getPospPhotoUrl() {
        return pospPhotoUrl;
    }

    public void setPospPhotoUrl(URL pospPhotoUrl) {
        this.pospPhotoUrl = pospPhotoUrl;
    }

    public String getPospNAme() {
        return pospNAme;
    }

    public void setPospNAme(String pospNAme) {
        this.pospNAme = pospNAme;
    }

    public String getPospDesg() {
        return pospDesg;
    }

    public void setPospDesg(String pospDesg) {
        this.pospDesg = pospDesg;
    }

    public String getPospEmail() {
        return pospEmail;
    }

    public void setPospEmail(String pospEmail) {
        this.pospEmail = pospEmail;
    }

    public String getPospMobNo() {
        return PospMobNo;
    }

    public void setPospMobNo(String pospMobNo) {
        PospMobNo = pospMobNo;
    }
}
