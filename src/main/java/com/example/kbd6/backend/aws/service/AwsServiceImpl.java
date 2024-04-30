package com.example.kbd6.backend.aws.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

@Service
public class AwsServiceImpl implements AwsService{

//    @Autowired
//    private AmazonS3 amazonS3;
//
//
//    @Override
//    public String uploadFile(String filePath, String bucketName, HttpMethod httpMethod) {
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.MINUTE, 10000);
//
//        return amazonS3.generatePresignedUrl(bucketName,filePath, calendar.getTime(),httpMethod).toString();
//    }


}
