package net.heronation.zeyo.rest.common.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.ResultDto;

@Controller
@Slf4j
@RequestMapping("/commons")
public class FileUploadController extends BaseController {
 
	@Value(value = "${zeyo.path.upload.temp}")
	private String path_temp_upload;
	
	@PostMapping("/temp/upload")
	public ResponseEntity<ResultDto> tempUpload(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			return return_fail("file.empty");
		}

		
		String temp_file_name = UUID.randomUUID().toString().concat(System.nanoTime()+"").concat(file.getName());
		
		try {

			// Get the file and save it somewhere
			log.debug(temp_file_name);
			log.debug(path_temp_upload.concat(File.separator).concat(temp_file_name));
			
			byte[] bytes = file.getBytes();
			Path path = Paths.get(path_temp_upload.concat(File.separator).concat(temp_file_name));
			Files.write(path, bytes);
 
			
			
			//org.apache.commons.io.FileUtils.copyFile(source, dest);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return return_fail("upload.failed");
		}

		return return_success(temp_file_name);
	}
 
}
