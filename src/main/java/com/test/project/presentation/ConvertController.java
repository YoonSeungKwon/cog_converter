package com.test.project.presentation;

import com.test.project.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/converts")
public class ConvertController {

    private final ConvertService convertService;

    @GetMapping("/loads")
    public Object loadFiles(){
        return convertService.loadFiles();
    }

}
