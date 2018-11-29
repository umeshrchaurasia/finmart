package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;

public class LifeinsuranceEntity  extends RealmObject{
    /**
     * InsuID : 20
     * InsuName : Aen Life Insurance Company Limited.
     * InsuShorName : Aen Life
     */

    private int InsuID;
    private String InsuName;
    private String InsuShorName;

    public int getInsuID() {
        return InsuID;
    }

    public void setInsuID(int InsuID) {
        this.InsuID = InsuID;
    }

    public String getInsuName() {
        return InsuName;
    }

    public void setInsuName(String InsuName) {
        this.InsuName = InsuName;
    }

    public String getInsuShorName() {
        return InsuShorName;
    }

    public void setInsuShorName(String InsuShorName) {
        this.InsuShorName = InsuShorName;
    }
}