package net.heronation.zeyo.rest.common.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.FileBase64Dto;
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

		String temp_file_name = UUID.randomUUID().toString().concat(System.nanoTime() + "").concat(file.getName());

		try {

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			byte[] bytes = file.getBytes();
			Path path = Paths.get(path_temp_upload.concat(File.separator).concat(dtf.format(now)).concat(File.separator)
					.concat(temp_file_name));
			log.debug(path.toString());

			File source = new File(path.toString());

			FileUtils.writeByteArrayToFile(source, bytes);

		} catch (IOException e) {
			e.printStackTrace();
			return return_fail("upload.failed");
		}

		return return_success(temp_file_name);
	}

	@PostMapping("/temp/upload/base64")
	public ResponseEntity<ResultDto> tempUploadBase64(@RequestBody FileBase64Dto file_content) {

		if (file_content == null) {
			return return_fail("file.empty");
		}
		String temp_file_name = UUID.randomUUID().toString().concat(System.nanoTime() + "");

		try {
			
			
			String utf8_file_content = new String(file_content.getFile().split("base64,")[1].getBytes(), "UTF-8");
						
			byte[] bytes = Base64Utils.decodeFromString(utf8_file_content);

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			Path path = Paths.get(path_temp_upload.concat(File.separator).concat(dtf.format(now)).concat(File.separator)
					.concat(temp_file_name));
			log.debug(path.toString());

			File source = new File(path.toString());
			
			FileUtils.writeByteArrayToFile(source, bytes);

		} catch (Exception e) {
			e.printStackTrace();
			return return_fail("upload.failed");
		}

		return return_success(temp_file_name);

	}

}
