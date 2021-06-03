package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
/**/
@Data
@Document
public class financialModel {

    private String id;
    @Indexed(unique = true)
    private String symbol;
    private LocalDate date;
    private String period;
    private double revenueGrowth;
    private double ebitgrowth;
    private double operatingIncomeGrowth;
    private double netIncomeGrowth;
    private double epsgrowth;
    private double epsdilutedGrowth;
    private double weightedAverageSharesGrowth;
    private double weightedAverageSharesDilutedGrowth;
    private double dividendsperShareGrowth;
    private double operatingCashFlowGrowth;
    private double freeCashFlowGrowth;
    private double tenYRevenueGrowthPerShare;
    private double fiveYRevenueGrowthPerShare;
    private double threeYRevenueGrowthPerShare;
    private double tenYOperatingCFGrowthPerShare;
    private double fiveYOperatingCFGrowthPerShare;
    private double threeYOperatingCFGrowthPerShare;
    private double tenYNetIncomeGrowthPerShare;
    private double fiveYNetIncomeGrowthPerShare;
    private double threeYNetIncomeGrowthPerShare;
    private double tenYShareholdersEquityGrowthPerShare;
    private double fiveYShareholdersEquityGrowthPerShare;
    private double threeYShareholdersEquityGrowthPerShare;
    private double tenYDividendperShareGrowthPerShare;
    private double fiveYDividendperShareGrowthPerShare;
    private double threeYDividendperShareGrowthPerShare;
    private double receivablesGrowth;
    private double inventoryGrowth;
    private double assetGrowth;
    private double bookValueperShareGrowth;
    private double debtGrowth;
    private double rdexpenseGrowth;
    private double sgaexpensesGrowth;


    /*public financialModel() {

    }*/

   /* public financialModel(String symbol,
                          LocalDate date,
                          String period,
                          double revenueGrowth,
                          double ebitgrowth,
                          double operatingIncomeGrowth,
                          double netIncomeGrowth,
                          double epsgrowth,
                          double epsdilutedGrowth,
                          double weightedAverageSharesGrowth,
                          double weightedAverageSharesDilutedGrowth,
                          double dividendsperShareGrowth,
                          double operatingCashFlowGrowth,
                          double freeCashFlowGrowth,
                          double tenYRevenueGrowthPerShare,
                          double fiveYRevenueGrowthPerShare,
                          double threeYRevenueGrowthPerShare,
                          double tenYOperatingCFGrowthPerShare,
                          double fiveYOperatingCFGrowthPerShare,
                          double threeYOperatingCFGrowthPerShare,
                          double tenYNetIncomeGrowthPerShare,
                          double fiveYNetIncomeGrowthPerShare,
                          double threeYNetIncomeGrowthPerShare,
                          double tenYShareholdersEquityGrowthPerShare,
                          double fiveYShareholdersEquityGrowthPerShare,
                          double threeYShareholdersEquityGrowthPerShare,
                          double tenYDividendperShareGrowthPerShare,
                          double fiveYDividendperShareGrowthPerShare,
                          double threeYDividendperShareGrowthPerShare,
                          double receivablesGrowth,
                          double inventoryGrowth,
                          double assetGrowth,
                          double bookValueperShareGrowth,
                          double debtGrowth,
                          double rdexpenseGrowth,
                          double sgaexpensesGrowth)
    {
        this.date = date;
        this.period = period;
        this.revenueGrowth = revenueGrowth;
        this.ebitgrowth = ebitgrowth;
        this.operatingIncomeGrowth = operatingIncomeGrowth;
        this.netIncomeGrowth = netIncomeGrowth;
        this.epsgrowth = epsgrowth;
        this.epsdilutedGrowth = epsdilutedGrowth;
        this.weightedAverageSharesGrowth = weightedAverageSharesGrowth;
        this.weightedAverageSharesDilutedGrowth = weightedAverageSharesDilutedGrowth;
        this.dividendsperShareGrowth = dividendsperShareGrowth;
        this.operatingCashFlowGrowth = operatingCashFlowGrowth;
        this.freeCashFlowGrowth = freeCashFlowGrowth;
        this.tenYRevenueGrowthPerShare = tenYRevenueGrowthPerShare;
        this.fiveYRevenueGrowthPerShare = fiveYRevenueGrowthPerShare;
        this.threeYRevenueGrowthPerShare = threeYRevenueGrowthPerShare;
        this.tenYOperatingCFGrowthPerShare = tenYOperatingCFGrowthPerShare;
        this.fiveYOperatingCFGrowthPerShare = fiveYOperatingCFGrowthPerShare;
        this.threeYOperatingCFGrowthPerShare = threeYOperatingCFGrowthPerShare;
        this.tenYNetIncomeGrowthPerShare = tenYNetIncomeGrowthPerShare;
        this.fiveYNetIncomeGrowthPerShare = fiveYNetIncomeGrowthPerShare;
        this.threeYNetIncomeGrowthPerShare = threeYNetIncomeGrowthPerShare;
        this.tenYShareholdersEquityGrowthPerShare = tenYShareholdersEquityGrowthPerShare;
        this.fiveYShareholdersEquityGrowthPerShare = fiveYShareholdersEquityGrowthPerShare;
        this.threeYShareholdersEquityGrowthPerShare = threeYShareholdersEquityGrowthPerShare;
        this.tenYDividendperShareGrowthPerShare = tenYDividendperShareGrowthPerShare;
        this.fiveYDividendperShareGrowthPerShare = fiveYDividendperShareGrowthPerShare;
        this.threeYDividendperShareGrowthPerShare = threeYDividendperShareGrowthPerShare;
        this.receivablesGrowth = receivablesGrowth;
        this.inventoryGrowth = inventoryGrowth;
        this.assetGrowth = assetGrowth;
        this.bookValueperShareGrowth = bookValueperShareGrowth;
        this.debtGrowth = debtGrowth;
        this.rdexpenseGrowth = rdexpenseGrowth;
        this.sgaexpensesGrowth = sgaexpensesGrowth;
    }*/

    public financialModel(String symbol, LocalDate date, String period,
                          double revenueGrowth, double ebitgrowth, double operatingIncomeGrowth,
                          double netIncomeGrowth, double epsgrowth, double epsdilutedGrowth,
                          double weightedAverageSharesGrowth, double weightedAverageSharesDilutedGrowth, double dividendsperShareGrowth, double operatingCashFlowGrowth, double freeCashFlowGrowth, double tenYRevenueGrowthPerShare, double fiveYRevenueGrowthPerShare, double threeYRevenueGrowthPerShare, double tenYOperatingCFGrowthPerShare, double fiveYOperatingCFGrowthPerShare, double threeYOperatingCFGrowthPerShare, double tenYNetIncomeGrowthPerShare, double fiveYNetIncomeGrowthPerShare, double threeYNetIncomeGrowthPerShare, double tenYShareholdersEquityGrowthPerShare, double fiveYShareholdersEquityGrowthPerShare, double threeYShareholdersEquityGrowthPerShare, double tenYDividendperShareGrowthPerShare, double fiveYDividendperShareGrowthPerShare, double threeYDividendperShareGrowthPerShare, double receivablesGrowth, double inventoryGrowth, double assetGrowth, double bookValueperShareGrowth, double debtGrowth, double rdexpenseGrowth, double sgaexpensesGrowth) {
        this.symbol = symbol;
        this.date = date;
        this.period = period;
        this.revenueGrowth = revenueGrowth;
        this.ebitgrowth = ebitgrowth;
        this.operatingIncomeGrowth = operatingIncomeGrowth;
        this.netIncomeGrowth = netIncomeGrowth;
        this.epsgrowth = epsgrowth;
        this.epsdilutedGrowth = epsdilutedGrowth;
        this.weightedAverageSharesGrowth = weightedAverageSharesGrowth;
        this.weightedAverageSharesDilutedGrowth = weightedAverageSharesDilutedGrowth;
        this.dividendsperShareGrowth = dividendsperShareGrowth;
        this.operatingCashFlowGrowth = operatingCashFlowGrowth;
        this.freeCashFlowGrowth = freeCashFlowGrowth;
        this.tenYRevenueGrowthPerShare = tenYRevenueGrowthPerShare;
        this.fiveYRevenueGrowthPerShare = fiveYRevenueGrowthPerShare;
        this.threeYRevenueGrowthPerShare = threeYRevenueGrowthPerShare;
        this.tenYOperatingCFGrowthPerShare = tenYOperatingCFGrowthPerShare;
        this.fiveYOperatingCFGrowthPerShare = fiveYOperatingCFGrowthPerShare;
        this.threeYOperatingCFGrowthPerShare = threeYOperatingCFGrowthPerShare;
        this.tenYNetIncomeGrowthPerShare = tenYNetIncomeGrowthPerShare;
        this.fiveYNetIncomeGrowthPerShare = fiveYNetIncomeGrowthPerShare;
        this.threeYNetIncomeGrowthPerShare = threeYNetIncomeGrowthPerShare;
        this.tenYShareholdersEquityGrowthPerShare = tenYShareholdersEquityGrowthPerShare;
        this.fiveYShareholdersEquityGrowthPerShare = fiveYShareholdersEquityGrowthPerShare;
        this.threeYShareholdersEquityGrowthPerShare = threeYShareholdersEquityGrowthPerShare;
        this.tenYDividendperShareGrowthPerShare = tenYDividendperShareGrowthPerShare;
        this.fiveYDividendperShareGrowthPerShare = fiveYDividendperShareGrowthPerShare;
        this.threeYDividendperShareGrowthPerShare = threeYDividendperShareGrowthPerShare;
        this.receivablesGrowth = receivablesGrowth;
        this.inventoryGrowth = inventoryGrowth;
        this.assetGrowth = assetGrowth;
        this.bookValueperShareGrowth = bookValueperShareGrowth;
        this.debtGrowth = debtGrowth;
        this.rdexpenseGrowth = rdexpenseGrowth;
        this.sgaexpensesGrowth = sgaexpensesGrowth;
    }

    @Override
    public String toString() {
        return "financialModel{" +
                "symbol='" + symbol + '\'' +
                ", date=" + date +
                ", period='" + period + '\'' +
                ", revenueGrowth=" + revenueGrowth +
                ", ebitgrowth=" + ebitgrowth +
                ", operatingIncomeGrowth=" + operatingIncomeGrowth +
                ", netIncomeGrowth=" + netIncomeGrowth +
                ", epsgrowth=" + epsgrowth +
                ", epsdilutedGrowth=" + epsdilutedGrowth +
                ", weightedAverageSharesGrowth=" + weightedAverageSharesGrowth +
                ", weightedAverageSharesDilutedGrowth=" + weightedAverageSharesDilutedGrowth +
                ", dividendsperShareGrowth=" + dividendsperShareGrowth +
                ", operatingCashFlowGrowth=" + operatingCashFlowGrowth +
                ", freeCashFlowGrowth=" + freeCashFlowGrowth +
                ", tenYRevenueGrowthPerShare=" + tenYRevenueGrowthPerShare +
                ", fiveYRevenueGrowthPerShare=" + fiveYRevenueGrowthPerShare +
                ", threeYRevenueGrowthPerShare=" + threeYRevenueGrowthPerShare +
                ", tenYOperatingCFGrowthPerShare=" + tenYOperatingCFGrowthPerShare +
                ", fiveYOperatingCFGrowthPerShare=" + fiveYOperatingCFGrowthPerShare +
                ", threeYOperatingCFGrowthPerShare=" + threeYOperatingCFGrowthPerShare +
                ", tenYNetIncomeGrowthPerShare=" + tenYNetIncomeGrowthPerShare +
                ", fiveYNetIncomeGrowthPerShare=" + fiveYNetIncomeGrowthPerShare +
                ", threeYNetIncomeGrowthPerShare=" + threeYNetIncomeGrowthPerShare +
                ", tenYShareholdersEquityGrowthPerShare=" + tenYShareholdersEquityGrowthPerShare +
                ", fiveYShareholdersEquityGrowthPerShare=" + fiveYShareholdersEquityGrowthPerShare +
                ", threeYShareholdersEquityGrowthPerShare=" + threeYShareholdersEquityGrowthPerShare +
                ", tenYDividendperShareGrowthPerShare=" + tenYDividendperShareGrowthPerShare +
                ", fiveYDividendperShareGrowthPerShare=" + fiveYDividendperShareGrowthPerShare +
                ", threeYDividendperShareGrowthPerShare=" + threeYDividendperShareGrowthPerShare +
                ", receivablesGrowth=" + receivablesGrowth +
                ", inventoryGrowth=" + inventoryGrowth +
                ", assetGrowth=" + assetGrowth +
                ", bookValueperShareGrowth=" + bookValueperShareGrowth +
                ", debtGrowth=" + debtGrowth +
                ", rdexpenseGrowth=" + rdexpenseGrowth +
                ", sgaexpensesGrowth=" + sgaexpensesGrowth +
                '}';
    }
}

