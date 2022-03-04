package service.scenario_2.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class TrafficInfoController {

    @RequestMapping(value="/traffic",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> requiretraffic(){
        System.out.println("qiuqiule");
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
}
