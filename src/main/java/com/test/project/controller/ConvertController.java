package com.test.project.controller;

import com.test.project.common.dto.request.ConvertAllRequest;
import com.test.project.common.dto.request.ConvertRequest;
import com.test.project.common.dto.response.CogDataResponse;
import com.test.project.common.dto.response.TifDataResponse;
import com.test.project.common.enums.FileFormat;
import com.test.project.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/converts")
public class ConvertController {

    private final ConvertService convertService;

    @GetMapping("/list")
    public ResponseEntity<List<TifDataResponse>> loadConvertibleFiles(){
        return new ResponseEntity<>(convertService.loadFiles(), HttpStatus.OK);
    }

    @PostMapping("/tif-cog")
    public ResponseEntity<CogDataResponse> convertFile(@RequestBody ConvertRequest dto){
        return new ResponseEntity<>(convertService.convertFile(dto, FileFormat.TIF, FileFormat.COG), HttpStatus.CREATED);
    }

    @PostMapping("/tif-cog/all")
    public ResponseEntity<List<CogDataResponse>> convertFiles(@RequestBody ConvertAllRequest dto) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(convertService.convertAll(dto, FileFormat.TIF, FileFormat.COG), HttpStatus.CREATED);
    }

}
