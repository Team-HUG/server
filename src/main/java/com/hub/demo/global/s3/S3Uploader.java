package com.hub.demo.global.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hub.demo.global.exception.global.S3UploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile file) {
        try {
            String format = getFormat(file.getContentType());
            String originName = createPath(UUID.randomUUID().toString(), format);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, originName, file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
            return amazonS3Client.getUrl(bucket, originName).toString();

        } catch (Exception e) {
            throw S3UploadException.EXCEPTION;
        }
    }

    public static String getFormat(String contentType) {
        return contentType.substring(contentType.lastIndexOf('/') + 1);
    }

    public static String createPath(String fileId, String format) {
        return String.format("%s.%s", fileId, format);
    }
}
