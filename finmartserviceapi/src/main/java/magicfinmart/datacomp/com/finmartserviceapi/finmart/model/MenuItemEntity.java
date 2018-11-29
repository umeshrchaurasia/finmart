package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItemEntity implements Parcelable {
    /**
     * menuid : 1
     * menuname : Reports
     * link : http://bo.magicfinmart.com
     * iconimage : http://api.magicfinmart.com/InsurerImages/car_1.png
     * isActive : 1
     * description : null
     * type : 1
     */

    private int menuid;
    private String menuname;
    private String link;
    private String iconimage;
    private int isActive;
    private String description;
    private int type;
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
        dest.writeString(this.sequence);
    }

    public MenuItemEntity() {
    }

    protected MenuItemEntity(Parcel in) {
        this.menuid = in.readInt();
        this.menuname = in.readString();
        this.link = in.readString();
        this.iconimage = in.readString();
        this.isActive = in.readInt();
        this.description = in.readString();
        this.type = in.readInt();
        this.sequence = in.readString();
    }

    public static final Creator<MenuItemEntity> CREATOR = new Creator<MenuItemEntity>() {
        @Override
        public MenuItemEntity createFromParcel(Parcel source) {
            return new MenuItemEntity(source);
        }

        @Override
        public MenuItemEntity[] newArray(int size) {
            return new MenuItemEntity[size];
        }
    };
}