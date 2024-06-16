package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    MessageResponse uploadImageForCategory(Integer id, MultipartFile multipartFile);
}