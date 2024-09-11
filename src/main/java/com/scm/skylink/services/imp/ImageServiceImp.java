package com.scm.skylink.services.imp;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.skylink.helper.AppConstants;
import com.scm.skylink.services.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {

        // upload image on cloud
        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", filename));
            return this.getURLFromPublicId(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return URL
        return "";
    }

    public String getURLFromPublicId(String public_id) {
        return cloudinary.url()
                .transformation(
                        new Transformation<>()
                                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                                .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                                .crop(AppConstants.CONTACT_IMAGE_CROP))
                .generate(public_id);

    }

}
