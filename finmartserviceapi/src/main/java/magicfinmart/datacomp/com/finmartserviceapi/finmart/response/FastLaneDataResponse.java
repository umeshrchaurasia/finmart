package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.FastLaneDataEntity;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class FastLaneDataResponse extends APIResponse {

    /**
     * HealthMasterData : {"Variant_Id":4659,"Variant_Name":"CS GLX","VehicleCity_Id":582,"FastLaneId":7172,"Make_ID":25,"Model_ID":202,"Fuel_ID":1,"Make_Name":"Tata","Model_Name":"Indigo CS","Fuel_Type":"Petrol","Seating_Capacity":5,"Cubic_Capacity":1193,"Manufacture_Year":"2010","Color":"S GOLD","Registration_Number":"MH04EQ2620","RTO_Code":4,"RTO_Name":"THANE","Chassis_Number":"MAT601652AWJ39560","Engin_Number":"475SI72JZYP76208","Registration_Date":"30/10/2010","Purchase_Date":"27/10/2010","ErrorMessage":"","FastlaneResponse":"{\"regn_no\":\"MH04EQ2620\",\"state_cd\":\"MH\",\"rto_cd\":\"4\",\"rto_name\":\"THANE\",\"chasi_no\":\"MAT601652AWJ39560\",\"eng_no\":\"475SI72JZYP76208\",\"regn_dt\":\"30/10/2010\",\"purchase_dt\":\"27/10/2010\",\"vh_class_desc\":\"LMV\",\"fla_vh_class_desc\":\"LMV\",\"vehicle_cd\":\"138004030108\",\"maker_desc\":\"TMLTATAMOTORSLTD\",\"fla_maker_desc\":\"TATA\",\"maker_model\":\"INDIGOCSEGLXMPFIBSIV\",\"fla_model_desc\":\"INDIGO\",\"fla_variant\":\"CSGLX\",\"color\":\"SGOLD\",\"fuel_type_desc\":\"PETROL\",\"fla_fuel_type_desc\":\"PETROL\",\"cubic_cap\":\"1193\",\"fla_cubic_cap\":\"1193\",\"manu_yr\":\"2010\",\"seat_cap\":\"5\",\"fla_seat_cap\":\"5\"}","FastlanePostResponse":""}
     */

    private FastLaneDataEntity MasterData;

    public FastLaneDataEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(FastLaneDataEntity MasterData) {
        this.MasterData = MasterData;
    }


}
