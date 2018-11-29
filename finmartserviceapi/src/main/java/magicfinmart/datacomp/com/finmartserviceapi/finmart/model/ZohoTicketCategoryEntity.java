package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ZohoTicketCategoryEntity extends RealmObject {

    private RealmList<ZohoCategoryEntity> category;
    private RealmList<ZohoSubcategoryEntity> subcategory;
    private RealmList<ZohoClassificationEntity> classification;

    public RealmList<ZohoCategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(RealmList<ZohoCategoryEntity> category) {
        this.category = category;
    }

    public RealmList<ZohoSubcategoryEntity> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(RealmList<ZohoSubcategoryEntity> subcategory) {
        this.subcategory = subcategory;
    }

    public RealmList<ZohoClassificationEntity> getClassification() {
        return classification;
    }

    public void setClassification(RealmList<ZohoClassificationEntity> classification) {
        this.classification = classification;
    }
}