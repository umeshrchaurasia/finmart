package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MenuMasterEntity implements Parcelable {

    private List<MenuItemEntity> Menu;
    private List<DashBoardItemEntity> Dashboard;

    public List<MenuItemEntity> getMenu() {
        return Menu;
    }

    public void setMenu(List<MenuItemEntity> Menu) {
        this.Menu = Menu;
    }

    public List<DashBoardItemEntity> getDashboard() {
        return Dashboard;
    }

    public void setDashboard(List<DashBoardItemEntity> Dashboard) {
        this.Dashboard = Dashboard;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.Menu);
        dest.writeList(this.Dashboard);
    }

    public MenuMasterEntity() {
    }

    protected MenuMasterEntity(Parcel in) {
        this.Menu = in.createTypedArrayList(MenuItemEntity.CREATOR);
        this.Dashboard = new ArrayList<DashBoardItemEntity>();
        in.readList(this.Dashboard, DashBoardItemEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<MenuMasterEntity> CREATOR = new Parcelable.Creator<MenuMasterEntity>() {
        @Override
        public MenuMasterEntity createFromParcel(Parcel source) {
            return new MenuMasterEntity(source);
        }

        @Override
        public MenuMasterEntity[] newArray(int size) {
            return new MenuMasterEntity[size];
        }
    };
}