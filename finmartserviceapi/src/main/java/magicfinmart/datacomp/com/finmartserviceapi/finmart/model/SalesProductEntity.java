package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SalesProductEntity extends RealmObject implements Parcelable {

    /**
     * Product_Id : 1
     * Product_Name : Health Insurance
     * Product_image : api.magicfinmart.com/images/salesmaterial/health.png
     * Count : 7
     */
    @PrimaryKey
    private int Product_Id;
    private String Product_Name;
    private String Product_image;
    private int Count;
    private int OldCount;

    public SalesProductEntity()
    {

    }

    public SalesProductEntity(int id, String Product_Name ,String Product_image ,int Count) {

      this.Product_Id = id ;
       this.Product_Name = Product_Name;
        this.Product_image = Product_image;
        this.Count  = Count;
        this.OldCount = 0;

    }

    protected SalesProductEntity(Parcel in) {
        Product_Id = in.readInt();
        Product_Name = in.readString();
        Product_image = in.readString();
        Count = in.readInt();
        OldCount = in.readInt();
    }

    public static final Creator<SalesProductEntity> CREATOR = new Creator<SalesProductEntity>() {
        @Override
        public SalesProductEntity createFromParcel(Parcel in) {
            return new SalesProductEntity(in);
        }

        @Override
        public SalesProductEntity[] newArray(int size) {
            return new SalesProductEntity[size];
        }
    };


    public int getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(int product_Id) {
        Product_Id = product_Id;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getProduct_image() {
        return Product_image;
    }

    public void setProduct_image(String product_image) {
        Product_image = product_image;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }


    public int getOldCount() {
        return OldCount;
    }

    public void setOldCount(int oldCount) {
        OldCount = oldCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Product_Id);
        dest.writeString(Product_Name);
        dest.writeString(Product_image);
        dest.writeInt(Count);
        dest.writeInt(OldCount);
    }
}