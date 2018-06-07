package net.heronation.zeyo.rest.common.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.ResultVO;

@Controller
@Slf4j
@RequestMapping("/common")
public class FileUploadController extends BaseController {

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "D://TEST_SERVER_ROOT//zeyo_image//temp";

	@GetMapping("/uploadform")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/common/uploadStatus";
	}
	
	@PostMapping("/temp/upload")
	public ResponseEntity<ResultVO> tempUpload(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			return return_fail("file.empty");
		}

		
		String temp_file_name = UUID.randomUUID().toString().concat(System.nanoTime()+"").concat(file.getContentType());
		
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + temp_file_name);
			Files.write(path, bytes);


		} catch (IOException e) {
			e.printStackTrace();
			return return_fail("upload.failed");
		}

		return return_success(temp_file_name);
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
}
