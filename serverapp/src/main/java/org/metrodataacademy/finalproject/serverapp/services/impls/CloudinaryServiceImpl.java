package org.metrodataacademy.finalproject.serverapp.services.impls;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.MessageResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Category;
import org.metrodataacademy.finalproject.serverapp.repositories.CategoryRepository;
import org.metrodataacademy.finalproject.serverapp.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public MessageResponse uploadImageForCategory(Integer id, MultipartFile multipartFile) {
        log.info("Try to upload image for category with id {}", id);
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));

            Map<?, ?> uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
                    ObjectUtils.emptyMap());
            String imageURL = uploadResult.get("url").toString();

            category.setLinkPhoto(imageURL);
            categoryRepository.save(category);

            log.info("Uploaded image for category with id {} successful!", id);
            return MessageResponse.builder().message(imageURL).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}