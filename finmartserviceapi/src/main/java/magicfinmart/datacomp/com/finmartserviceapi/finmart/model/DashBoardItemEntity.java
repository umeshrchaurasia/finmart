package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DashBoardItemEntity implements Parcelable {
    /**
     * menuid : 6
     * menuname : Dashboard 1
     * link : http://bo.magicfinmart.com
     * iconimage : http://bo.magicfinmart.com
     * isActive : 1
     * description : Dashboard 1
     * type : 2
     */

    private int menuid;
    private String menuname;
    private String link;
    private String iconimage;
    private int isActive;
    private String description;
    private int type;
    /**
     * dashboard_type : 0
     */

    private int dashboard_type;
    private String sequence;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIconimage() {
        return iconimage;
    }

    public void setIconimage(String iconimage) {
        this.iconimage = iconimage;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDashboard_type() {
        return dashboard_type;
    }

    public void setDashboard_type(int dashboard_type) {
        this.dashboard_type = dashboard_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.menuid);
        dest.writeString(this.menuname);
        dest.writeString(this.link);
        dest.writeString(this.iconimage);
        dest.writeInt(this.isActive);
        dest.writeString(this.description);
        dest.writeInt(this.type);
        dest.writeInt(this.dashboard_type);
        dest.writeString(this.sequence);
    }

    public DashBoardItemEntity() {
    }

    protected DashBoardItemEntity(Parcel in) {
        this.menuid = in.readInt();
        this.menuname = in.readString();
        this.link = in.readString();
        this.iconimage = in.readString();
        this.isActive = in.readInt();
        this.description = in.readString();
        this.type = in.readInt();
        this.dashboard_type = in.readInt();
        this.sequence = in.readString();
    }

    public static final Parcelable.Creator<DashBoardItemEntity> CREATOR = new Parcelable.Creator<DashBoardItemEntity>() {
        @Override
        public DashBoardItemEntity createFromParcel(Parcel source) {
            return new DashBoardItemEntity(source);
        }

        @Override
        public DashBoardItemEntity[] newArray(int size) {
            return new DashBoardItemEntity[size];
        }
    };
}