package magicfinmart.datacomp.com.finmartserviceapi.express_loan.model;

public class ShortTermPersonalLoanEntity {
    /**
     * Bank_Id : 999
     * Bank_Name : SHORT TERM PERSONAL LOAN
     * Bank_Code : STPL
     * Document1 : http://www.rupeeboss.com/images/express/early.png
     * WebView : 0
     */

    private int Bank_Id;
    private String Bank_Name;
    private String Bank_Code;
    private String Document1;
    private int WebView;


    private String WebViewUrl;
    private String ProductType;

    public int getBank_Id() {
        return Bank_Id;
    }

    public void setBank_Id(int Bank_Id) {
        this.Bank_Id = Bank_Id;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String Bank_Name) {
        this.Bank_Name = Bank_Name;
    }

    public String getBank_Code() {
        return Bank_Code;
    }

    public void setBank_Code(String Bank_Code) {
        this.Bank_Code = Bank_Code;
    }

    public String getDocument1() {
        return Document1;
    }

    public void setDocument1(String Document1) {
        this.Document1 = Document1;
    }

    public int getWebView() {
        return WebView;
    }

    public void setWebView(int WebView) {
        this.WebView = WebView;
    }

    public String getWebViewUrl() {
        return WebViewUrl;
    }

    public void setWebViewUrl(String webViewUrl) {
        WebViewUrl = webViewUrl;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

}