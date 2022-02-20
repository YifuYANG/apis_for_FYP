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
    private UserRepository userRepository;
    @Autowired
    TokenPool tokenPool;

    @RestrictUserAccess(requiredLevel = UserLevel.admin)
    @RequestMapping(value="/uploaddata",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> uploadData(@RequestHeader("token") String deviceid,@RequestBody UploadedData uploadedData){
        uploadedData.setDistance(distanceCalculation(uploadedData.getCurrentLatitude(),uploadedData.getCurrentLongitude(),uploadedData.getTargetLatitude(),uploadedData.getTargetLongitude()));
        uploadedDataRepository.save(uploadedData);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }



    @RequestMapping(value="/authentication",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> authentication(@RequestBody Inputform inputform){
        return new ResponseEntity<>(inputform.getSpeed() > 0,HttpStatus.ACCEPTED);
    }


    @RequestMapping(value="/authentication/pass",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map> login(@RequestBody Loginform loginform){
        User user = userRepository.findByUserBytrustdvice(loginform.getDeviceid());
        if(loginform.getPassword().equals(user.getPassword())){
        String token=tokenPool.generateToken();
        tokenPool.login(loginform.getDeviceid(),token);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            return null;
        }
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
