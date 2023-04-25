package org.zerock.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

@Log4j2
@AllArgsConstructor

@Component

@Controller
@RequestMapping("/admin/*")
public class AdminController {
   
   
   private ProductService service;
   private ProductDTO dto;
   
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
   public String processMultipart(@RequestParam("files") List<MultipartFile> files, ProductDTO dto, RedirectAttributes rttrs) throws ControllerException {
      log.trace("processMultiPart({}) invoked", files);
      try {
          
            files.forEach( f-> {
               log.info("\t+1.Name:{}", f.getName());
               log.info("\t+2.originalfilename:{}", f.getOriginalFilename());
               log.info("\t+3.Contenttype:{}", f.getContentType());
               log.info("\t+4.Size:{}", f.getSize());
               log.info("\t ---------------------------");
               
               if(!"".equals(f.getOriginalFilename())) {
                  
                  //방법1
                  try {
                     f.transferTo(new File("C:/app/2023/eclipse/workspace/jee-2022-06/mymg_admin/src/main/webapp/resources/product/" +f.getOriginalFilename()));
                     for (int i = 0; i < files.size(); i++) {
                    	    MultipartFile file = files.get(i);
                    	    String filename = file.getOriginalFilename();

                    	    // 각 파일의 이름에 따라 DTO 객체의 필드 설정
                    	    switch (i) {
                    	        case 0:
                    	            dto.setMain_image(filename);
                    	            break;
                    	        case 1:
                    	            dto.setMain_image2(filename);
                    	            break;
                    	        case 2:
                    	            dto.setSub_image1(filename);
                    	            break;
                    	        case 3:
                    	            dto.setSub_image2(filename);
                    	            break;
                    	        case 4:
                    	            dto.setSub_image3(filename);
                    	            break;
                    	        case 5:
                    	            dto.setSub_image4(filename);
                    	            break;
                    	        case 6:
                    	            dto.setContent_image(filename);
                    	            break;
                    	        default:
                    	            break;
                    	    }
                    	}
                  } catch(IOException e) {
                     e.printStackTrace();
                  } // try catch
                  
               } // if
            }); // .forEach
            
                 boolean success = this.service.register(dto);
                 log.info("\t: success: {}", success);

                 // 비지니스 요청 처리용 전송파라미터 전송처리
                 rttrs.addAttribute("result", (success)? "success" : "failure"); 
                 // KEY = 등록처리결과

                 return "redirect:/admin/main"; // 실패했든 성공했든 여기로 이동

            } catch (Exception e) {
               throw new ControllerException(e);
            } // try-catch
         
      } // register-post

   
   
   
   
   
   
   
   
   
   
   
   
   
 
   /* 이미지 삭제 */
   @PostMapping("/deleteFile")
   public ResponseEntity<String> deleteFile(String fileName) {
      
      log.info("deleteFile....................." + fileName);
      
      File file = null;
      
      try {
         /* 썸네일 파일 삭제 */
         file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
         file.delete();
         
         /* 원본파일 삭제 */
         String originFileName = file.getAbsolutePath().replaceFirst("s_", "");
         log.info("originFileName : "+originFileName);
         file = new File(originFileName);
         file.delete();
         
         
      }catch (Exception e) {

         e.printStackTrace();
         return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
      } // try-catch
      
      return new ResponseEntity<String>("success", HttpStatus.OK);
      
   } // deleteFile
   
   
} // end class