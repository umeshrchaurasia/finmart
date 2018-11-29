package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 12/09/2018.
 */

public class MenuMasterResponse extends APIResponse {

    /**
     * MasterData : {"Menu":[{"menuid":1,"menuname":"Reports","link":"http://bo.magicfinmart.com","iconimage":"http://api.magicfinmart.com/InsurerImages/car_1.png","isActive":1,"description":null,"type":1},{"menuid":2,"menuname":"","link":"","iconimage":"","isActive":0,"description":null,"type":1},{"menuid":3,"menuname":"","link":"","iconimage":"","isActive":0,"description":null,"type":1},{"menuid":4,"menuname":"","link":"","iconimage":"","isActive":0,"description":null,"type":1},{"menuid":5,"menuname":"","link":"","iconimage":"","isActive":0,"description":null,"type":1}],"Dashboard":[{"menuid":6,"menuname":"Dashboard 1","link":"http://bo.magicfinmart.com","iconimage":"http://bo.magicfinmart.com","isActive":1,"description":"Dashboard 1","type":2},{"menuid":7,"menuname":"Dashboard 2","link":"http://bo.magicfinmart.com","iconimage":"http://bo.magicfinmart.com","isActive":1,"description":"Dashboard 2","type":2}]}
     */

    private MenuMasterEntity MasterData;

    public MenuMasterEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(MenuMasterEntity MasterData) {
        this.MasterData = MasterData;
    }


}

