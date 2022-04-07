package com.example.restapimvc.post.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Component
public class S3PresignerProvider {
    @Value("${aws.s3.access-key-id}")
    private String S3_ACCESS_KEY_ID;

    @Value("${aws.s3.secret-access-key}")
    private String S3_SECRET_ACCESS_KEY;

    @Value("${aws.s3.region}")
    private String S3_REGION;

    public S3Presigner getS3Presigner() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(S3_ACCESS_KEY_ID, S3_SECRET_ACCESS_KEY);
        return S3Presigner.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .region(Region.of(S3_REGION))
                .build();
    }

}
