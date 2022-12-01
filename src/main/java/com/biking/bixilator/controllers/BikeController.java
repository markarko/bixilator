package com.biking.bixilator.controllers;

import com.biking.bixilator.entities.StationInfo;
import com.biking.bixilator.requests.DataFetch;
import com.biking.bixilator.services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class BikeController {

    @Autowired
    private BikeService bikeService;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping(value = "/search", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<StationInfo> search(@RequestParam String query) {
        List<StationInfo> stationInfos = DataFetch.getAllStationInfos();
        return bikeService.filterByName(stationInfos, query);
    }
}
