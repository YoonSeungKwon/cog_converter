package com.test.project.presentation;

import com.test.project.common.dto.FileRequestDto;
import com.test.project.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/converts")
public class ConvertController {

    private final ConvertService convertService;

    @GetMapping("/loads")
    public Object loadFileList(){
        return convertService.loadFiles();
    }

    @GetMapping("/load")
    public void convertFile(@RequestBody FileRequestDto dto){
        convertService.tifToCog(dto);
        return;
    }

}
