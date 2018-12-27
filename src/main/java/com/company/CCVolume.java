package com.company;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

class Month_low {
    private String price_usd;
    private String updated_at;

    public String getPrice_usd() {
        return price_usd;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}

class Month_high {
    private String price_usd;
    private String updated_at;

    public String getPrice_usd() {
        return price_usd;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}

class ATH {
    private String coin;
    private String updated_at;
    private String price_usd;

    public String getCoin() {
        return coin;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getPrice_usd() {
        return price_usd;
    }
}

public class CCVolume {
    private OkHttpClient okHttpClient = new OkHttpClient();
    ATH ath = new ATH();
    Month_low month_low = new Month_low();
    Month_high month_high = new Month_high();

    void ddsmth(String text, String chatid) {
        com.company.CryptoBot reader = new com.company.CryptoBot();
        Request r = new Request.Builder()
                .url("https://chasing-coins.com/api/v1/std/highlow/" + text)
                .get()
                .build();
        Call newsCall = okHttpClient.newCall(r);
        try {

            Response response = newsCall.execute();
            ResponseBody body = response.body();
            String bodystring = body.string();
            CCVolume volume = new Gson().fromJson(bodystring, CCVolume.class);
            reader.sendMessage("Month lower price: " + Double.parseDouble(volume.month_low.getPrice_usd()) + "$  " + volume.month_low.getUpdated_at() + "\n\n" +
                    "Month higher price: " + Double.parseDouble(volume.month_high.getPrice_usd()) + "$    " + volume.month_high.getUpdated_at(), chatid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
