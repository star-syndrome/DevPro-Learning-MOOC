package org.metrodataacademy.finalproject.clientapp.services;

import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    MessageResponse uploadImageForCategory(Integer id, MultipartFile multipartFile) throws IOException;
}