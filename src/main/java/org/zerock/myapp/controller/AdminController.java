package org.zerock.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.AttachImageVO;
import org.zerock.myapp.domain.CategoryVO;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.exception.AException;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.ProductService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Log4j2
@AllArgsConstructor

@Controller
@RequestMapping("/admin/*")
public class AdminController {
   
   
   private ProductService service;
   
   //채영 테스트
   
   
   /* 1. 관리자 페이지 이동 */
   
   @GetMapping("/main")
   public String adminMain() throws AException{
      log.trace("adminMain() invoked. (관리자 페이지 이동)");
      
      return "admin/main";
      
      // 어드민 수정 테스트입니당~
      // 다시수정할게용
      // 졸리당
      // 테스트입니당당당
      // 테스트예요 TT
      // TEST
      
   } // adminMain
   
   
   
   
//   =========================== 상품 ===========================
   
//   [별이] 메소드 추가
   @GetMapping("/product/register")
   public void register(Model model) throws ControllerException, JsonProcessingException {
      log.trace("register() invoked. (관리자 페이지 상품 등록-GET)", model);
      
      try {
         ObjectMapper objm = new ObjectMapper();
         
         List<CategoryVO> list = this.service.getCateList();
         
         String cateList = objm.writeValueAsString(list);
         
         model.addAttribute("cateList", cateList); // view로 날아갈 model 상자 안에 model 데이터를 담음

         log.info("------변경 전 : " + list);
         log.info("------변경 후 : " + cateList);
         
      } catch (ServiceException e) {
         throw new ControllerException(e);
      } // try-catch
      

   } // register-get
   
   
   
   @PostMapping("/product/register")
   public String register(ProductDTO dto, RedirectAttributes rttrs) 
         throws ControllerException {
      
         log.trace("register({}, {}, {}) invoked. (관리자 페이지 상품 등록-POST)", dto, rttrs);
      
         try {
        	        	 
            boolean success = this.service.register(dto);
            log.info("\t: success: {}", success);

            rttrs.addAttribute("result", (success)? "success" : "failure"); 
         
            return "redirect:/admin/main"; // 실패했든 성공했든 여기로 이동
         } catch (Exception e) {
            throw new ControllerException(e);
         } // try-catch
      
   } // register-post
  
   
   
} // end class