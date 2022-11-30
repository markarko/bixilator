package com.biking.bixilator.controllers;

import com.biking.bixilator.entities.StationInfo;
import com.biking.bixilator.requests.DataFetch;
import com.biking.bixilator.services.BikeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class BikeController {

    @Autowired
    private BikeService bikeService;
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
    @GetMapping(value = "/search", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<StationInfo> reqTest(@RequestParam String query) {
        try{
            // returns all for now
            HttpResponse<String> response = DataFetch.fetchBikeData("https://gbfs.velobixi.com/gbfs/en/station_information.json");
            List<StationInfo> stationInfos = DataFetch.parseStationInfo(response.body());
            return stationInfos;
        } catch (NumberFormatException e){
            System.out.println("Query is not a longitude");
        }
        return new ArrayList<>();
    }
}
