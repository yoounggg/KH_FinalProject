package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/fileUpload")
@Controller("fileUploadController")
public class FileUploadController { // pojo
   
   @GetMapping("/page")
   public void uploadPage() {
      log.trace("uploadpage() invoked");
      
   } //uploadPage
   
   @PostMapping("/doit")
   public void processMultipart(
		   @RequestParam("main_image") List<MultipartFile> main_image,
		   @RequestParam("main_image2") List<MultipartFile> main_image2,
		   @RequestParam("sub_image1") List<MultipartFile> sub_image1,
		   @RequestParam("sub_image2") List<MultipartFile> sub_image2,
		   @RequestParam("sub_image3") List<MultipartFile> sub_image3,
		   @RequestParam("sub_image4") List<MultipartFile> sub_image4,
		   @RequestParam("content_image") List<MultipartFile> content_image
		   
		   ) {
      log.trace("processMultiPart({}, {}, {}, {}, {}, {}, {}) invoked", main_image, main_image2, sub_image1, sub_image2, sub_image3, sub_image4, content_image);

   } // processMultipart
   

} // end class