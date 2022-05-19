package com.example.restapimvc.post.infra;

import com.example.restapimvc.common.FileMetaData;
import com.example.restapimvc.post.command.domain.FileUploadRepository;
import com.example.restapimvc.post.command.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class S3FileUploadRepository implements FileUploadRepository {

    @Value("${aws.s3.image-bucket}")
    private String S3_IMAGE_BUCKET;

    private final S3PresignerProvider s3PresignerProvider;

    @Override
    public List<FileDto.UploadUrl> getFileUploadUrls(List<FileMetaData> fileMetaData) {
        S3Presigner s3Presigner = s3PresignerProvider.getS3Presigner();
        List<FileDto.UploadUrl> presignedUrls = new ArrayList<>();
        for (FileMetaData metaData: fileMetaData) {
            AwsRequestOverrideConfiguration override = AwsRequestOverrideConfiguration.builder()
                    .putRawQueryParameter("x-amz-acl", "private")
                    .build();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(S3_IMAGE_BUCKET)
                    .key(metaData.getFileName())
                    .contentType(metaData.getContentType())
                    .overrideConfiguration(override)
                    .build();
            PutObjectPresignRequest putObjectPresignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(3))
                    .putObjectRequest(putObjectRequest)
                    .build();
            PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(putObjectPresignRequest);
            presignedUrls.add(FileDto.UploadUrl.builder().url(presignedRequest.url().toString()).build());
        }
        return presignedUrls;

    }
}
