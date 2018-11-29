package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MasterSalesMaterialPromotionEntity;

/**
 * Created by Nilesh Birhade on 22-02-2018.
 */

public class SalesPromotionResponse extends APIResponse {

    /**
     * MasterDataEntity : {"company":[{"company_id":"1","Company_Name":"Bharti"},{"company_id":"3","Company_Name":"Liberty Videocon"}],"docs":[{"company_id":"2","Company_Name":"Bharti","language":"","image_path":"uploads/sales_material/image.jpg"},{"company_id":"4","Company_Name":"Bharti","language":"","image_path":"uploads/sales_material/1/image.jpg"},{"company_id":"","Company_Name":null,"language":"","image_path":"uploads/sales_material/1/image.jpg"},{"company_id":"1","Company_Name":"Bharti","language":"","image_path":"uploads/sales_material/1/image.png"},{"company_id":"1","Company_Name":"Bharti","language":"","image_path":"uploads/sales_material/1/image.jpg"},{"company_id":"3","Company_Name":"Liberty Videocon","language":"English","image_path":"uploads/sales_material/1/3/image.png"},{"company_id":"1","Company_Name":"Bharti","language":"English","image_path":"uploads/sales_material/1/1/image.png"}]}
     */

    private MasterSalesMaterialPromotionEntity MasterData;

    public MasterSalesMaterialPromotionEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterSalesMaterialPromotionEntity MasterData) {
        this.MasterData = MasterData;
    }


}
