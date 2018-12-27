package com.company;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CryptoCurrency {
    private OkHttpClient okHttpClient = new OkHttpClient();
    private String price;

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    void ddsmth(String text, String chatid) {
        CryptoBot reader = new com.company.CryptoBot();
        String temp = "";
        List<String> list = new ArrayList<String>();{
            list.add("BTC");
            list.add("XRP");
            list.add("ETH");
            list.add("LTC");
            list.add("XMR");
            list.add("ETC");
        }
        for(int i=0;i<list.size();i++) {
            Request r = new Request.Builder()
                    .url("https://chasing-coins.com/api/v1/std/coin/" + list.get(i))
                    .get()
                    .build();
            Call newsCall = okHttpClient.newCall(r);
            try {
                Response response = newsCall.execute();
                ResponseBody body = response.body();
                String bodystring = body.string();
                CryptoCurrency volume = new Gson().fromJson(bodystring, CryptoCurrency.class);
                temp += list.get(i) + ": " + Double.parseDouble(volume.getPrice())+"\n";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reader.sendMessage(temp, chatid);
    }

    public String getPrice() {
        return price;
    }
}
