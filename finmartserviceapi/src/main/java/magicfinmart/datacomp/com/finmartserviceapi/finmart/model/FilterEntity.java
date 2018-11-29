package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public class FilterEntity {
    /**
     * CreditCardAmountFilterId : 1
     * Amount : <2.5Lac
     */

    private int CreditCardAmountFilterId;
    private String Amount;
    private int Priority;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public int getCreditCardAmountFilterId() {
        return CreditCardAmountFilterId;
    }

    public void setCreditCardAmountFilterId(int CreditCardAmountFilterId) {
        this.CreditCardAmountFilterId = CreditCardAmountFilterId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }
}