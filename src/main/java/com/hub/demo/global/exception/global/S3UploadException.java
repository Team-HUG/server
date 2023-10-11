package com.hub.demo.global.exception.global;

import com.hub.demo.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class S3UploadException extends CustomException {
    public static final CustomException EXCEPTION = new S3UploadException();

    private S3UploadException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 도중에 서버에 문제가 발생하였습니다.");
    }
}
