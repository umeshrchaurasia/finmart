package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public class ContactRequestEntity {


    public String mobileno;
    public String name;

    public ContactRequestEntity() {
    }

    public ContactRequestEntity(String mobileno, String name) {
        this.mobileno = mobileno;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}