package service.scenario_1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.scenario_1.access.RestrictUserAccess;
import service.scenario_1.bean.TokenPool;
import service.scenario_1.constant.UserLevel;
import service.scenario_1.entity.UploadedData;
import service.scenario_1.entity.User;
import service.scenario_1.repository.UploadedDataRepository;
import service.scenario_1.repository.UserRepository;
import service.scenario_1.vo.Inputform;
import service.scenario_1.vo.Loginform;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {

    @Autowired
    private UploadedDataRepository uploadedDataRepository;
    @Autowired
    TokenPool tokenPool;
    @Autowired
    private UserRepository userRepository;

    @RestrictUserAccess(requiredLevel = UserLevel.admin)
    @RequestMapping(value="/uploaddata",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Double> uploadData(@RequestHeader("token") String token, @RequestBody UploadedData uploadedData){
        double distance = distanceCalculation(uploadedData.getCurrentLatitude(),uploadedData.getCurrentLongitude(),uploadedData.getTargetLatitude(),uploadedData.getTargetLongitude());
        uploadedData.setDistance(distance);
        uploadedDataRepository.save(uploadedData);
        return new ResponseEntity<>(distance, HttpStatus.CREATED);
    }



    @RequestMapping(value="/authentication",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> authentication(@RequestBody Inputform inputform){
        return new ResponseEntity<>(inputform.getSpeed() > 0,HttpStatus.ACCEPTED);
    }

    @RestrictUserAccess(requiredLevel = UserLevel.admin)
    @RequestMapping(value="/license",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,String>> getdriverlicense(@RequestHeader("token") String token){
        Map<String,String> map=new HashMap<>();
        User user= userRepository.findByid(tokenPool.getUserIdByToken(token));
        map.put("license",user.getDriverlicense());
        return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }



    //helper function which Calculate distance with two location which result in meters
    private double distanceCalculation(double CurrentLatitude,double CurrentLongitude,double targetLatitude,double targetLongitude){
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(targetLatitude-CurrentLatitude);
        double dLng = Math.toRadians(targetLongitude-CurrentLongitude);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(CurrentLatitude)) * Math.cos(Math.toRadians(targetLatitude)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float distance = (float) (earthRadius * c);
        return distance;
    }
}
