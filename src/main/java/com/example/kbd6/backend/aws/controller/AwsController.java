package com.example.kbd6.backend.aws.controller;

import com.amazonaws.HttpMethod;
import com.amazonaws.SDKGlobalTime;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.kbd6.backend.aws.service.AwsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@RestController
//@RequestMapping("/aws")
public class AwsController {

//    private final AwsService awsService;
//
//
//    public AwsController(AwsService awsService) {
//        this.awsService = awsService;
//    }
//    @Autowired
//    private AmazonS3 amazonS3;
//
//    @Value("stockcontrolmanagementsaveimage")
//    private String bucketName;
//
//    @GetMapping("/generate-presigned-url")
//    public ResponseEntity<String> generatePresignedUrl(@RequestParam String extension ){
//        return ResponseEntity.ok(awsService.uploadFile(UUID.randomUUID()+"."+extension,bucketName, HttpMethod.PUT));
//    }
//
//    @GetMapping("/download")
//    public ResponseEntity<String> download(@RequestParam String filename ){
//        return ResponseEntity.ok(awsService.uploadFile(filename,bucketName, HttpMethod.GET));
//    }
//
//    @GetMapping("/get/{name}")
//    public void viewFile(@PathVariable String name,HttpServletResponse response) throws IOException {
//        S3Object s3Object = amazonS3.getObject(bucketName,name);
//        S3ObjectInputStream inputStream = s3Object.getObjectContent();
//        IOUtils.copy(inputStream, response.getOutputStream());
//        String publicLink = amazonS3.getUrl(bucketName,name).toString();
//        System.out.println(publicLink);
//
//    }
//
}
