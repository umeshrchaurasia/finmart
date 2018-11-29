package magicfinmart.datacomp.com.finmartserviceapi.model;

/**
 * Created by IN-RB on 16-04-2018.
 */

public class QualificationEntity {
    int id;
    String name;

    public QualificationEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
