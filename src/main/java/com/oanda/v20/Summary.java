package com.oanda.v20;

import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;

public class Summary {
    public static void main(String[] args) {
        Context ctx = new Context("https://api-fxpractice.oanda.com","de7895a23b01d7cc4c8896d1139e706b-2daacfbab11b921a924b461a8e2a7438");
        try {
            AccountSummary summary = ctx.account.summary(new AccountID("101-004-27326966-001")).getAccount();
            System.out.println(summary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
