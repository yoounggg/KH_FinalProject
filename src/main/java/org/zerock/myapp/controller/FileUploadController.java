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
   public void processMultipart(@RequestParam("files") List<MultipartFile> files) {
      log.trace("processMultiPart({}) invoked", files);

   } // processMultipart
   

} // end class