package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MemberListEntity implements Parcelable {
    /**
     * HealthMemberListId : 37
     * MemberDOB : 07-06-1984
     * MemberGender : M
     * MemberNumber : 1
     * MemberTypeID : 1
     * HealthRequestId : 13
     */

    private String HealthMemberListId;
    private String MemberDOB;
    private String MemberGender;
    private String MemberNumber;
    private String MemberTypeID;
    private String HealthRequestId;
    private String MemberType;
    private String MemberRelationShip;
    private int Age;
    private String MemberDOBTemp;


    public String getMemberRelationShip() {
        return MemberRelationShip;
    }

    public void setMemberRelationShip(String memberRelationShip) {
        MemberRelationShip = memberRelationShip;
    }

    public String getMemberType() {
        return MemberType;
    }

    public void setMemberType(String memberType) {
        MemberType = memberType;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getHealthMemberListId() {
        return HealthMemberListId;
    }

    public void setHealthMemberListId(String HealthMemberListId) {
        this.HealthMemberListId = HealthMemberListId;
    }

    public String getMemberDOB() {
        return MemberDOB;
    }

    public void setMemberDOB(String MemberDOB) {
        this.MemberDOB = MemberDOB;
    }
    public String getMemberDOBTemp() {
        return MemberDOBTemp;
    }

    public void setMemberDOBTemp(String memberDOBTemp) {
        MemberDOBTemp = memberDOBTemp;
    }


    public String getMemberGender() {
        return MemberGender;
    }

    public void setMemberGender(String MemberGender) {
        this.MemberGender = MemberGender;
    }

    public String getMemberNumber() {
        return MemberNumber;
    }

    public void setMemberNumber(String MemberNumber) {
        this.MemberNumber = MemberNumber;
    }

    public String getMemberTypeID() {
        return MemberTypeID;
    }

    public void setMemberTypeID(String MemberTypeID) {
        this.MemberTypeID = MemberTypeID;
    }

    public String getHealthRequestId() {
        return HealthRequestId;
    }

    public void setHealthRequestId(String HealthRequestId) {
        this.HealthRequestId = HealthRequestId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.HealthMemberListId);
        dest.writeString(this.MemberDOB);
        dest.writeString(this.MemberGender);
        dest.writeString(this.MemberNumber);
        dest.writeString(this.MemberTypeID);
        dest.writeString(this.HealthRequestId);
        dest.writeString(this.MemberType);
        dest.writeString(this.MemberRelationShip);
        dest.writeInt(this.Age);
        dest.writeString(this.MemberDOBTemp);
    }

    public MemberListEntity() {
    }

    protected MemberListEntity(Parcel in) {
        this.HealthMemberListId = in.readString();
        this.MemberDOB = in.readString();
        this.MemberGender = in.readString();
        this.MemberNumber = in.readString();
        this.MemberTypeID = in.readString();
        this.HealthRequestId = in.readString();
        this.MemberType = in.readString();
        this.MemberRelationShip = in.readString();
        this.Age = in.readInt();
        this.MemberDOBTemp = in.readString();
    }

    public static final Parcelable.Creator<MemberListEntity> CREATOR = new Parcelable.Creator<MemberListEntity>() {
        @Override
        public MemberListEntity createFromParcel(Parcel source) {
            return new MemberListEntity(source);
        }

        @Override
        public MemberListEntity[] newArray(int size) {
            return new MemberListEntity[size];
        }
    };
}
     