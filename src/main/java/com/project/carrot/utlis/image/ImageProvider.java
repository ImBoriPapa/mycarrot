package com.project.carrot.utlis.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ImageProvider {

    public List<ImageForm> saveImageProcessingV1(List<MultipartFile> files, String path) throws IOException {
        List<ImageForm> imageForms = new ArrayList<>();


        for (MultipartFile f : files) {
            if (!files.isEmpty()) {

                String originalFilename = f.getOriginalFilename();

                //get ext
                String ext = getExtension(originalFilename);

                //generateStoreName(ext)
                String storedFileName = generateStoredName(ext);

                //getFullPath(storeFileName)
                String fullPath = getFullPath(storedFileName, path);

                f.transferTo(new File(fullPath));

                ImageForm imageForm = new ImageForm(originalFilename, storedFileName);

                imageForms.add(imageForm);
            } else {
                throw new IllegalArgumentException("Files are Empty");
            }
        }
        return imageForms;
    }

    public List<ImageForm> saveImageProcessingV2(List<MultipartFile> files, String path) throws IOException {

        List<ImageForm> list = getImageFormList(files);

        saveInPath(files, path, list);

        return list;
    }

    private void saveInPath(List<MultipartFile> files, String path, List<ImageForm> list) throws IOException {
        for(int i = 0; i< files.size(); i++){
            files.get(i).transferTo(new File(getFullPath(list.get(i).getStoredImage(), path)));
        }
    }


    private List<ImageForm> getImageFormList(List<MultipartFile> files) {
        List<ImageForm> list = files.stream().map(f -> new ImageForm(f.getOriginalFilename(),
                generateStoredName(getExtension(f.getOriginalFilename()))
        )).collect(Collectors.toList());
        return list;
    }


    private String getExtension(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        return ext;
    }


    private String generateStoredName(String ext) {
        String uuid = UUID.randomUUID().toString();
        String storedFileName = uuid + "." + ext;
        return storedFileName;
    }


    private String getFullPath(String storedFileName, String path) {
        String fullPath = path + storedFileName;
        return fullPath;
    }
}
