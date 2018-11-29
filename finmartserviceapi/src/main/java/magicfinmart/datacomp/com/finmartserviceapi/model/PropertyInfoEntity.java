package magicfinmart.datacomp.com.finmartserviceapi.model;

import io.realm.RealmObject;

/**
 * Created by IN-RB on 22-01-2018.
 */

public class PropertyInfoEntity extends RealmObject {

    /**
     * Property_Id : 3
     * Property_Type : Resale Property
     */

    private int Property_Id;
    private String Property_Type;

    public PropertyInfoEntity() {
    }

    public PropertyInfoEntity(int property_Id, String property_Type) {
        Property_Id = property_Id;
        Property_Type = property_Type;
    }


    public int getProperty_Id() {
        return Property_Id;
    }

    public void setProperty_Id(int Property_Id) {
        this.Property_Id = Property_Id;
    }

    public String getProperty_Type() {
        return Property_Type;
    }

    public void setProperty_Type(String Property_Type) {
        this.Property_Type = Property_Type;
    }
}
