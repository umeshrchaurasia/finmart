package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;

public class GeneralinsuranceEntity  extends RealmObject{
    /**
     * InsuID : 16
     * InsuName : Agriculture Insurance Co. of India Ltd.
     * InsuShorName : Agri.Ins
     * Health : 1
     */

    private int InsuID;
    private String InsuName;
    private String InsuShorName;
    private int Health;

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

    public int getHealth() {
        return Health;
    }

    public void setHealth(int Health) {
        this.Health = Health;
    }
}