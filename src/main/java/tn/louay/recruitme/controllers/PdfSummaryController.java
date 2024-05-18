package tn.louay.recruitme.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.louay.recruitme.services.PdfSummaryService;

@RestController
@RequestMapping("/pdf")
public class PdfSummaryController {

    @PostMapping("/summary")
    public String generateSummary(@RequestParam("file") MultipartFile file) throws IOException {

        String summary = PdfSummaryService.generateSummary(file);

        return summary;
    }
}