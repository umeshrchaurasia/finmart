package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CreditCardEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.FilterEntity;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class CreditCardMasterResponse extends APIResponse {


    /**
     * MasterDataEntity : {"filter":[{"CreditCardAmountFilterId":1,"Amount":"<2.5Lac"},{"CreditCardAmountFilterId":2,"Amount":">2.5Lac"},{"CreditCardAmountFilterId":3,"Amount":">5.0Lac"},{"CreditCardAmountFilterId":4,"Amount":">10Lac"},{"CreditCardAmountFilterId":5,"Amount":">15Lac"}],"filterdata":[{"CreditCardDetailId":1,"CreditCardId":1,"CreditCardTypeId":"1","ImagePath":null,"Description":"<h3 class=\"text-left\">Titanium Delight Card<\/h3>\n\t\t\t\t\t <ul class=\"rbl-cr-lst text-left\">\n\t\t\t\t\t <li>Exclusive Wednesday offers - free movie tickets, value back on groceries &amp; pizzas<\/li>\n\t\t\t\t\t <li>4,000 bonus reward points on crossing spends of Rs. 1.2 lacs<\/li>\n\t\t\t\t\t <li>1 Reward point on every Rs.100 spent except fuel<\/li>\n\t\t\t\t\t <li>Waiver of fuel surcharge up to Rs. 100 every month<\/li>\n           <li>Joining Fee Rs.750<\/li>\n\t\t\t\t\t <\/ul>","RBID":"1","BankName":"RBL  CREDIT CARD","CreditCardType":"Titanium Delight Card","CreditCardAmountFilterId":1,"Amount":"<2.5Lac"},{"CreditCardDetailId":2,"CreditCardId":1,"CreditCardTypeId":"2","ImagePath":null,"Description":"<h3 class=\"text-left\">Platinum Maxima Card<\/h3>\n\t\t\t\t\t<ul class=\"rbl-cr-lst text-left\">\n\t\t\t\t\t <li>Free movie ticket every month<\/li>\n\t\t\t\t\t <li>10 Reward points on every Rs. 100 spent on dining, entertainment, utility bills, fuel and every international spends<\/li>\n\t\t\t\t\t <li>2 Reward points for every Rs.100 spent and 5X Reward on selected categories<\/li>\n\t\t\t\t\t <li>20,000 bonus Reward Points every year on crossing 3.5 lacs spends<\/li>\n\t\t\t\t\t <li>Complimentary access to Airport Lounges<\/li>\n           <li>Joining Fee Rs.2000<\/li>\n\t\t\t\t\t <\/ul>","RBID":"2","BankName":"RBL  CREDIT CARD","CreditCardType":"Platinum Maxima Card","CreditCardAmountFilterId":1,"Amount":"<2.5Lac"},{"CreditCardDetailId":3,"CreditCardId":1,"CreditCardTypeId":"3","ImagePath":null,"Description":"<h3 class=\"text-left\">Platinum Delight Card<\/h3>\n\t\t\t\t\t<ul class=\"rbl-cr-lst text-left\">\n\t\t\t\t\t <li>2 Reward Points on every Rs. 100 spent on weekdays<\/li>\n\t\t\t\t\t <li>2X Reward Points on every Rs. 100 spent on weekends<\/li>\n\t\t\t\t\t <li>1,000 bonus Reward Points every month on 5 transactions of Rs.1000 or more<\/li>\n\t\t\t\t\t <li>Waiver of fuel surcharge up to Rs.150 every month<\/li>\n           <li>Joining Fee Rs.1000<\/li>\n\t\t\t\t\t <\/ul>","RBID":"3","BankName":"RBL  CREDIT CARD","CreditCardType":"Platinum Delight Card","CreditCardAmountFilterId":1,"Amount":"<2.5Lac"},{"CreditCardDetailId":4,"CreditCardId":2,"CreditCardTypeId":"4","ImagePath":null,"Description":" <h3>Platinum Chip Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>No Joining Fee. No Annual Fee<\/li>\n            <li>Earn 2 PAYBACK points on every Rs.100 spent<\/li>\n            <li>Min. 15% savings on dining at participating restaurants<\/li>\n           <li>Save on 1% fuel surcharge, waived off at HPCL petrol pumps<\/li>\n\n      <\/ul>","RBID":"Platinum-Chip-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"LIFESTYLE","CreditCardAmountFilterId":2,"Amount":">2.5Lac"},{"CreditCardDetailId":5,"CreditCardId":2,"CreditCardTypeId":"4","ImagePath":null,"Description":" <h3>Coral Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>No Joining Fee. No Annual Fee<\/li>\n            <li>Earn 2 PAYBACK points on every Rs.100 spent<\/li>\n            <li>Min. 15% savings on dining at participating restaurants<\/li>\n           <li>Save on 1% fuel surcharge, waived off at HPCL petrol pumps<\/li>\n\n      <\/ul>","RBID":"Coral-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"LIFESTYLE","CreditCardAmountFilterId":3,"Amount":">5.0Lac"},{"CreditCardDetailId":6,"CreditCardId":2,"CreditCardTypeId":"4","ImagePath":null,"Description":" <h3>Rubyx Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>Exclusive privileges - entertainment, dining, wellness and golf<\/li>\n            <li>Complimentary airport lounge access<\/li>\n            <li>Buy 1, get 1 movie ticket free at BookMyShows<\/li>\n           <li>A set of complimentary Sennheiser HD 221 headphones on joining<\/li>\n\n      <\/ul>","RBID":"Rubyx-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"LIFESTYLE","CreditCardAmountFilterId":4,"Amount":">10Lac"},{"CreditCardDetailId":7,"CreditCardId":2,"CreditCardTypeId":"4","ImagePath":null,"Description":" <h3>Sapphiro Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>Exclusive privileges - entertainment, dining, wellness and golf<\/li>\n            <li>Complimentary membership to the Priority Pass programme<\/li>\n            <li>Buy 1, get 1 movie ticket free at BookMyShow<\/li>\n            <li>Complimentary All New Kindle on joining<\/li>\n\n      <\/ul>","RBID":"Sapphiro-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"LIFESTYLE","CreditCardAmountFilterId":5,"Amount":">15Lac"},{"CreditCardDetailId":8,"CreditCardId":2,"CreditCardTypeId":"5","ImagePath":null,"Description":" <h3>Jet Coral Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>Joining Fee - ?.1,250 + ST<\/li>\n            <li>2,500 Welcome Bonus JPMiles on joining<\/li>\n            <li>2,500 activation bonus JPMiles on achieving spends threshold of ?50,000<\/li>\n            <li>1,250 bonus JPMiles every year on renewal each year<\/li>\n\n      <\/ul>","RBID":"Jet-Coral-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"TRAVEL","CreditCardAmountFilterId":3,"Amount":">5.0Lac"},{"CreditCardDetailId":9,"CreditCardId":2,"CreditCardTypeId":"5","ImagePath":null,"Description":"<h3>Jet Rubyx Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>Joining Fee - ?.2,500 + ST<\/li>\n        <li>Welcome: 5,000 JPMiles + Complimentary Jet Airways Ticket<\/li>\n        <li>5,000 activation bonus JPMiles on achieving spends threshold of ?75,000<\/li>\n        <li>Renewal: 2,500 JPMiles + Complimentary Jet Airways Ticket<\/li>\n\n      <\/ul>","RBID":"Jet-Rubyx-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"TRAVEL","CreditCardAmountFilterId":4,"Amount":">10Lac"},{"CreditCardDetailId":10,"CreditCardId":2,"CreditCardTypeId":"5","ImagePath":null,"Description":"<h3>Jet Sapphiro Credit Card<\/h3>         <ul class=\"pad-lft\">         <li>Joining Fee - ?.5,000 + ST<\/li>             <li>Welcome: 10,000 JPMiles + Complimentary Jet Airways Ticket<\/li>             <li>10,000 activation bonus JPMiles on achieving spends threshold of ?1,00,000<\/li>            <li>Renewal: 5,000 JPMiles + Complimentary Jet Airways Ticket<\/li>        <\/ul>","RBID":"Jet-Sapphiro-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"TRAVEL","CreditCardAmountFilterId":5,"Amount":">15Lac"},{"CreditCardDetailId":11,"CreditCardId":2,"CreditCardTypeId":"6","ImagePath":null,"Description":"<h3>Ferrari Platinum Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>Limited period offer: Joining fee Rs.500<\/li>\n            <li>2X cash rewards on dining, groceries and at supermarkets<\/li>\n            <li>Buy 1, get 1 movie ticket free at BookMyShow<\/li>\n           <li>Get a complimentary tie from Provogue on joining<\/li>\n\n      <\/ul>","RBID":"Ferrari-Platinum-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"MOTORSPORTS","CreditCardAmountFilterId":3,"Amount":">5.0Lac"},{"CreditCardDetailId":12,"CreditCardId":2,"CreditCardTypeId":"6","ImagePath":null,"Description":"<h3>Ferrari Signature Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>Complimentary Scuderia Ferrari watch on joining<\/li>\n            <li>Discounts on Ferrari merchandise at Ferrari Online Store<\/li>\n            <li>2 complimentary domestic airport lounge visits/ quarter<\/li>\n            <li>Buy 1, get 1 movie ticket free at BookMyShow<\/li>\n\n      <\/ul>","RBID":"Ferrari-Signature-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"MOTORSPORTS","CreditCardAmountFilterId":4,"Amount":">10Lac"},{"CreditCardDetailId":13,"CreditCardId":2,"CreditCardTypeId":"6","ImagePath":null,"Description":"<h3>Ferrari Signature Credit Card<\/h3>\n        <ul class=\"pad-lft\">\n        <li>Complimentary Scuderia Ferrari watch on joining<\/li>\n            <li>Discounts on Ferrari merchandise at Ferrari Online Store<\/li>\n            <li>2 complimentary domestic airport lounge visits/ quarter<\/li>\n            <li>Buy 1, get 1 movie ticket free at BookMyShow<\/li>\n      <\/ul>","RBID":"Ferrari-Signature-Credit-Card","BankName":"ICICI CREDIT CARD","CreditCardType":"MOTORSPORTS","CreditCardAmountFilterId":5,"Amount":">15Lac"}]}
     */

    private MasterDataBean MasterData;

    public MasterDataBean getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataBean MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataBean {
        private List<FilterEntity> filter;
        private List<CreditCardEntity> filterdata;

        public List<FilterEntity> getFilter() {
            return filter;
        }

        public void setFilter(List<FilterEntity> filter) {
            this.filter = filter;
        }

        public List<CreditCardEntity> getFilterdata() {
            return filterdata;
        }

        public void setFilterdata(List<CreditCardEntity> filterdata) {
            this.filterdata = filterdata;
        }




    }
}
