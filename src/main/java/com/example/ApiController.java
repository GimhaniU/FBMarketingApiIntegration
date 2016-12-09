package com.example;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gimhani on 11/25/16.
 */
@Controller
@Component
@RequestMapping(path = "/api")
public class ApiController {
    private final ApiFacade apiFacade;

    public ApiController(ApiFacade apiFacade) {
        this.apiFacade = apiFacade;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/addpost")
    @ResponseBody
    public Response addPost(@RequestBody Request request){
        return apiFacade.addPost(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pagetoken")
    @ResponseBody
    public String getPageAccessToken(){
        return apiFacade.getPageAccessToken();
    }
}
