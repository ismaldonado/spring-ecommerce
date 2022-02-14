package com.ecommerce.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	// nombre de la carpeta
	private String folder = "images//";

	public String saveImg(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			// transformo el archivo en bytes
			byte[] bytes = file.getBytes();
			// se guarda toda la ruta
			Path path = Paths.get(folder + file.getOriginalFilename());

			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}

	public void deleteImg(String name) {
		String url = "images//";
		File file = new File(url + name);
		file.delete();
	}

}
