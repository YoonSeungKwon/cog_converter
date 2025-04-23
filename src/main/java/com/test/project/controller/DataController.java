package com.test.project.controller;

import com.test.project.common.dto.response.CogDataResponse;
import com.test.project.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
public class DataController {

    private final DataService dataService;


    @GetMapping("")
    public ResponseEntity<List<CogDataResponse>> getCogData(
            @RequestParam(name = "width", required = false) Integer width,
            @RequestParam(name = "height",required = false) Integer height,
            @RequestParam(name = "bandCount",required = false) Integer bandCount,
            @RequestParam(name = "compressType",required = false) String compressType){

        List<CogDataResponse> result = dataService.find(width, height, bandCount, compressType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
