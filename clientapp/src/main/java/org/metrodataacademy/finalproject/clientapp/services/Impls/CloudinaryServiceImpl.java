package org.metrodataacademy.finalproject.clientapp.services.Impls;

import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.MessageResponse;
import org.metrodataacademy.finalproject.clientapp.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public MessageResponse uploadImageForCategory(Integer id, MultipartFile multipartFile) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("multipartFile", new MultipartInputStreamFileResource(multipartFile.getInputStream(), multipartFile.getOriginalFilename()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate
                .exchange(
                        "http://localhost:8080/api/admin/category/upload-image/" + id,
                        HttpMethod.POST,
                        requestEntity,
                        MessageResponse.class
                ).getBody();
    }

    private static class MultipartInputStreamFileResource extends InputStreamResource {
        private final String filename;

        MultipartInputStreamFileResource(InputStream inputStream, String filename) {
            super(inputStream);
            this.filename = filename;
        }

        @Override
        public String getFilename() {
            return this.filename;
        }

        @Override
        public long contentLength() {
            return -1;
        }
    }
}