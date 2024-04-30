package com.example.kbd6.backend.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

//    @Value("AKIAYPNNYFMDABAR63YK")
//    private String accessKeyId;
//
//    @Value("IE6hBuqhuUNUnRb1JIGE+/pqeXpcSpZYWZL87pHS")
//    private String accessKeySecret;
//
//    @Value("us-east-1")
//    private String regionName;
//
//    @Bean
//    public AmazonS3 getAmaxonS3Client(){
//        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId,accessKeySecret);
//        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
//                .withRegion(regionName)
//                .build();
//
//    }
}
