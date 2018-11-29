package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

public class AddonNcbProtectionCoverEntity {
    /**
     * min : 100
     * max : 100
     */

    private double min;
    private double max;
    private boolean isSelected;

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
