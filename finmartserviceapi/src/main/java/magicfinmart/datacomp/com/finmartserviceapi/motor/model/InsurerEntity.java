package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InsurerEntity implements Parcelable {
    public static final Creator<InsurerEntity> CREATOR = new Creator<InsurerEntity>() {
        @Override
        public InsurerEntity createFromParcel(Parcel in) {
            return new InsurerEntity(in);
        }

        @Override
        public InsurerEntity[] newArray(int size) {
            return new InsurerEntity[size];
        }
    };
    /**
     * _id : 58be94835f761783caf9408d
     * Insurer_ID : 12
     * Insurer_Name : The New India Assurance Co. Ltd.
     * IsActive : 1
     * CreatedOn : 2012-02-09
     * Insurer_Logo_Name : new_india.png
     * IsInternal : ""
     * Insurer_Code : New India Assurance
     * Insurer_Logo_Name_Mobile : "m-rel.png"
     */

    private String _id;
    private String Insurer_ID;
    private String Insurer_Name;
    private String IsActive;
    private String CreatedOn;
    private String Insurer_Logo_Name;
    private String IsInternal;
    private String Insurer_Code;
    private String Insurer_Logo_Name_Mobile;

    protected InsurerEntity(Parcel in) {
        _id = in.readString();
        Insurer_ID = in.readString();
        Insurer_Name = in.readString();
        IsActive = in.readString();
        CreatedOn = in.readString();
        Insurer_Logo_Name = in.readString();
        IsInternal = in.readString();
        Insurer_Code = in.readString();
        Insurer_Logo_Name_Mobile = in.readString();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getInsurer_ID() {
        return Insurer_ID;
    }

    public void setInsurer_ID(String Insurer_ID) {
        this.Insurer_ID = Insurer_ID;
    }

    public String getInsurer_Name() {
        return Insurer_Name;
    }

    public void setInsurer_Name(String Insurer_Name) {
        this.Insurer_Name = Insurer_Name;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String IsActive) {
        this.IsActive = IsActive;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public String getInsurer_Logo_Name() {
        return Insurer_Logo_Name;
    }

    public void setInsurer_Logo_Name(String Insurer_Logo_Name) {
        this.Insurer_Logo_Name = Insurer_Logo_Name;
    }

    public String getIsInternal() {
        return IsInternal;
    }

    public void setIsInternal(String IsInternal) {
        this.IsInternal = IsInternal;
    }

    public String getInsurer_Code() {
        return Insurer_Code;
    }

    public void setInsurer_Code(String Insurer_Code) {
        this.Insurer_Code = Insurer_Code;
    }

    public String getInsurer_Logo_Name_Mobile() {
        return Insurer_Logo_Name_Mobile;
    }

    public void setInsurer_Logo_Name_Mobile(String Insurer_Logo_Name_Mobile) {
        this.Insurer_Logo_Name_Mobile = Insurer_Logo_Name_Mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(Insurer_ID);
        dest.writeString(Insurer_Name);
        dest.writeString(IsActive);
        dest.writeString(CreatedOn);
        dest.writeString(Insurer_Logo_Name);
        dest.writeString(IsInternal);
        dest.writeString(Insurer_Code);
        dest.writeString(Insurer_Logo_Name_Mobile);
    }
}
