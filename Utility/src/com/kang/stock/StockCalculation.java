package com.kang.stock;
import java.text.DecimalFormat;


public class StockCalculation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		
//		Stock s = new Stock();
//		s.setName("500ETF");
//		s.setCode("600002");
//		s.setAmount(6000);
//		s.setTaxPerThousand(0);
//		s.setTransFeePerThousand(3);
//		s.setBuyPrice(1.400);
//		s.setTransitFee(0);
//		s.setSellPrice(1.420);
//		//s.quickCalculateSellPrice();
		

		Stock s = new Stock();
		s.setName("ZGZT");
		s.setCode("600002");
		s.setAmount(200);
		s.setTaxPerThousand(1);
		s.setTransFeePerThousand(2);
		s.setBuyPrice(4.26);
		s.setTransitFee(1);
		s.setSellPrice(4.29);
		//s.quickCalculateSellPrice();
//		
		s.calculateStockEarn();
	}
}

class Stock {
	private String name;
	private String code;
	private double buyPrice;
	private double sellPrice;
	private double amount;
	private double taxPerThousand = 0.00; 
	private double transFeePerThousand;
	private double minimumPay = 5.00;
	private double transitFee = 1.00;
	private double earn = 0;
	
	public void quickCalculateSellPrice() {
		double transFee = formatDouble (buyPrice * amount * transFeePerThousand /1000);
		if (transFee < minimumPay) transFee = formatDouble (minimumPay);
		
		transFee = formatDouble (transFee * 2);
		double tax = formatDouble (buyPrice * amount * taxPerThousand /1000);
		
		double targetPrice = formatDouble (buyPrice * amount + transFee + tax + transitFee)/amount;
		
		System.out.println("Name: " + name + "\n" +
							"Code: " + code + "\n" +
							"Buy price: " + formatDouble (buyPrice) + "\n" + 
							"Amount: " + formatDouble(amount) + "\n" +
							"Sell Price To Be: " + formatDouble (targetPrice)
		);
		
	}
	
	public void calculateStockEarn(){
		double tempTotalBuyTrans = formatDouble (buyPrice * amount * transFeePerThousand/1000);
		if (tempTotalBuyTrans < minimumPay) {
			tempTotalBuyTrans = formatDouble(minimumPay);
		}
		
		double tempTotalSellTrans = formatDouble (sellPrice * amount * transFeePerThousand/1000);
		if (tempTotalSellTrans < minimumPay) {
			tempTotalSellTrans = formatDouble (minimumPay);
		}
		
		double taxTotal = formatDouble (sellPrice * taxPerThousand / 1000);
		
		earn = formatDouble ((sellPrice - buyPrice) * amount - tempTotalBuyTrans - tempTotalSellTrans - taxTotal - transitFee); 
		
		System.out.println("Name: " + name + "\nCode: " + code + "\nProfit: " + earn + "\n");
	}
	
	public Stock () {
		
	}
	
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getMinimumPay() {
		return minimumPay;
	}
	public void setMinimumPay(double minimumPay) {
		this.minimumPay = minimumPay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getTaxPerThousand() {
		return taxPerThousand;
	}
	public void setTaxPerThousand(double taxPerThousand) {
		this.taxPerThousand = taxPerThousand;
	}
	public double getTransFeePerThousand() {
		return transFeePerThousand;
	}
	public void setTransFeePerThousand(double transFeePerThousand) {
		this.transFeePerThousand = transFeePerThousand;
	}
	
	private double formatDouble(double origValue) {
		double result;
		DecimalFormat df = new DecimalFormat("#.##"); 
        df.setMinimumFractionDigits(3);
        result =Double.parseDouble(df.format(origValue));

        return result;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getEarn() {
		return earn;
	}

	public void setEarn(double earn) {
		this.earn = earn;
	}

	public double getTransitFee() {
		return transitFee;
	}

	public void setTransitFee(double transitFee) {
		this.transitFee = transitFee;
	}
	
	
	
}
