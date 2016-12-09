package com.example;

import com.facebook.ads.sdk.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


/**
 * Created by gimhani on 11/25/16.
 */

public class Example {

    public static final String ACCESS_TOKEN = "EAAaKOgXzdbwBAJ6hFOVKO8HeI4qc2ACivUevASAPyfoOYMi7ikGRbQTuK3qOaNmEqA6vvNXS8tbNSAzyHk2OFiWExYyZAKIbtHAP1ZAQYm3sTaZCMHBA9QFBngOlpFwCrQvTZAZCiJJig7cHw8tILNoEVVGADlrgnx3c44oJO4gZDZD";
    public static final Long ACCOUNT_ID = 119098785241386l;
    public static final String APP_SECRET = "d0e14944caccd7e63a6b9786b990b41f";

    public static final APIContext context = new APIContext(ACCESS_TOKEN, APP_SECRET);
    public static void main(String[] args) {
        try {
            //create campaign
            AdAccount account = new AdAccount(ACCOUNT_ID, context);
            Campaign campaign = account.createCampaign()
                    .setName("Java SDK Test Campaign 2")
                    .setObjective(Campaign.EnumObjective.VALUE_LINK_CLICKS)
                    .setSpendCap(10000L)
                    .setStatus(Campaign.EnumStatus.VALUE_PAUSED)
                    .execute();
            System.out.println(campaign.fetch());



        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}