package com.example;

import com.facebook.ads.sdk.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by gimhani on 11/25/16.
 */
@Component
public class ApiFacade {
    public static final String ACCESS_TOKEN = "EAAaKOgXzdbwBAISBqI5o3h1ZBFRDhshUG2FCizk2UZBh4uMeTgELuWStMkjzJcZCgLr2WYpiq2PUXDEzoyRg1UC16lXz6blujNYOsGfrjrv7KyDnmcwKGjd7toOSZAFWkByz9spIP5cbQ1oneulKp56WfJAjsfmb773ZB1By7BgZDZD";
    public static final Long ACCOUNT_ID = 119098785241386l;
    public static final String APP_SECRET = "d0e14944caccd7e63a6b9786b990b41f";

    public static final APIContext context = new APIContext(ACCESS_TOKEN, APP_SECRET);


    public Response addPost(Request request){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://graph.facebook.com/"+Constants.PAGE_ID+"/feed?message="+request.getMessage()+"&amp;link=www.projecteuler.net";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+Constants.PAGE_ACCESS_TOKEN);
        HttpEntity<Request> entity = new HttpEntity<>(null, headers);
        Response response = restTemplate.postForObject(url, entity, Response.class);
        System.out.println(response.getId());
        return response;
    }

    public String getPageAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url ="https://graph.facebook.com/"+Constants.PAGE_ID+"?fields=access_token&access_token="+Constants.USER_ACCESS_TOKEN;
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    public String addCampaign() throws APIException {
        AdAccount account = new AdAccount(ACCOUNT_ID, context);
        Campaign campaign = account.createCampaign()
                .setName("Java SDK Test Campaign")
                .setObjective(Campaign.EnumObjective.VALUE_LINK_CLICKS)
                .setSpendCap(10000L)
                .setStatus(Campaign.EnumStatus.VALUE_PAUSED)
                .execute();
        System.out.println(campaign.fetch());

        //create adset
        return campaign.fetch().toString();
    }

    public String createAdSet(Campaign campaign,String start, String end) throws APIException {
        //create adset
        AdSet adSet = new AdAccount(ACCOUNT_ID, context).createAdSet()
                .setName("My Ad Set")
                .setOptimizationGoal(AdSet.EnumOptimizationGoal.VALUE_REACH)
                .setBillingEvent(AdSet.EnumBillingEvent.VALUE_IMPRESSIONS)
                .setBidAmount(2L)
                .setDailyBudget(1000L)
                .setCampaignId(campaign.getId())
                .setTargeting(
                        new Targeting()
                                .setFieldGeoLocations(
                                        new TargetingGeoLocation()
                                                .setFieldCountries(Arrays.asList("US"))
                                )
                )
                .setStartTime(start)
                .setEndTime(end)
                .setStatus(AdSet.EnumStatus.VALUE_PAUSED)
                .execute();
        return adSet.getId();
    }
}

