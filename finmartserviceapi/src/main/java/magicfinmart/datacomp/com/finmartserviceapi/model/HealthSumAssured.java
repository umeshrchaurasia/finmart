package magicfinmart.datacomp.com.finmartserviceapi.model;

/**
 * Created by Nilesh Birhade on 07-02-2018.
 */

public class HealthSumAssured {

    private String DisplayValue;
    private long SumAssuredAmount;
    private boolean isSelected;

    public HealthSumAssured(String displayValue, long sumAssuredAmount, boolean isSelected) {
        DisplayValue = displayValue;
        SumAssuredAmount = sumAssuredAmount;
        this.isSelected = isSelected;
    }

    public String getDisplayValue() {
        return DisplayValue;
    }

    public void setDisplayValue(String displayValue) {
        DisplayValue = displayValue;
    }

    public long getSumAssuredAmount() {
        return SumAssuredAmount;
    }

    public void setSumAssuredAmount(long sumAssuredAmount) {
        SumAssuredAmount = sumAssuredAmount;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
