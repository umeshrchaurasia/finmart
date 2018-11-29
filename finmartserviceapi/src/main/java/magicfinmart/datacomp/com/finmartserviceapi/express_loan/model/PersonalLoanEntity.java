package magicfinmart.datacomp.com.finmartserviceapi.express_loan.model;

public class PersonalLoanEntity {
    /**
     * Bank_Id : 43
     * Bank_Name : RATNAKAR BANK LTD
     * Bank_Code : RBL
     * Document1 : http://erp.rupeeboss.com/Banklogo/rbl_bank.jpg
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