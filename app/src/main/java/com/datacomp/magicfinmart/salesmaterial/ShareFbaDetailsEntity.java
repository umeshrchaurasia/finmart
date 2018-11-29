package com.datacomp.magicfinmart.salesmaterial;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rajeev Ranjan on 26/07/2018.
 */

public class ShareFbaDetailsEntity {
    String fbaNAme, fbaDesg, fbaEmail, fbaMobNo;
    URL fbaPhotoUrl;

    public String getFbaNAme() {
        return fbaNAme;
    }

    public ShareFbaDetailsEntity() {
        this.fbaDesg = "FBA SUPPORT ASSISTANT";
        try {
            this.fbaPhotoUrl = new URL("http://qa.mgfm.in/images/profile_pic.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setFbaNAme(String fbaNAme) {
        this.fbaNAme = fbaNAme;
    }

    public String getFbaDesg() {
        return fbaDesg;
    }

    public void setFbaDesg(String fbaDesg) {
        this.fbaDesg = fbaDesg;
    }

    public String getFbaEmail() {
        return fbaEmail;
    }

    public void setFbaEmail(String fbaEmail) {
        this.fbaEmail = fbaEmail;
    }

    public String getFbaMobNo() {
        return fbaMobNo;
    }

    public void setFbaMobNo(String fbaMobNo) {
        this.fbaMobNo = fbaMobNo;
    }
}
