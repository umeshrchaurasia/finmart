package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class DocsEntity extends RealmObject implements Parcelable {
    /**
     * company_id : 2
     * Company_Name : Bharti
     * language :
     * image_path : uploads/sales_material/image.jpg
     */

    private String company_id;
    private String Company_Name;
    private String language;
    private String image_path;

    protected DocsEntity(Parcel in) {
        company_id = in.readString();
        Company_Name = in.readString();
        language = in.readString();
        image_path = in.readString();
    }

    public static final Creator<DocsEntity> CREATOR = new Creator<DocsEntity>() {
        @Override
        public DocsEntity createFromParcel(Parcel in) {
            return new DocsEntity(in);
        }

        @Override
        public DocsEntity[] newArray(int size) {
            return new DocsEntity[size];
        }
    };

    public DocsEntity() {
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String Company_Name) {
        this.Company_Name = Company_Name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(company_id);
        dest.writeString(Company_Name);
        dest.writeString(language);
        dest.writeString(image_path);
    }
}