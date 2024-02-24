package com.github.diogopeixoto.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class App implements RequestHandler<String, String> {

    private final S3Client s3 = S3Client.builder().region(Region.CA_CENTRAL_1).build();

    @Override
    public String handleRequest(String input, Context context) {
        GetObjectRequest request = buildObjectRequest();
        GetObjectResponse response = getObject(s3, request);

        return response.contentType();
    }

    private GetObjectRequest buildObjectRequest() {
        return GetObjectRequest.builder().bucket("s3Name").key("image.png").build();
    }

    private GetObjectResponse getObject(S3Client s3, GetObjectRequest request) {
        return s3.getObject(request).response();
    }
}
