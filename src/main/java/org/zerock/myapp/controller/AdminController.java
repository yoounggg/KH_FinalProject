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
	
	
	
	
//	=========================== 상품 ===========================
	
//	[별이] 메소드 추가
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
		
			log.trace("register({}, {}) invoked. (관리자 페이지 상품 등록-POST)", dto, rttrs);
		
			try {
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
	
	
	
	/* [별이 4/17] 첨부 파일 업로드용 컨트롤러 */
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] main_image) throws ControllerException {
		
		log.info("uploadAjaxActionPOST......");
		
		
		/* 이미지 파일 체크 */
		for(MultipartFile multipartFile: main_image) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				log.info("MIME TYPE : "+ type);
			} catch (IOException e) {
				throw new ControllerException(e);
			} // try-catch
			
			if(!type.startsWith("image")) {
				
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				
			} // if
			
		} // for
		
		String uploadFolder = "C:\\upload";
		
		/* 날짜 폴더 경로 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		
		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		/* 이미지 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();
		
		/* 향상된 for */
		for(MultipartFile multipartFile : main_image) {
			log.info("==================================================");
			log.info("파일 이름 : " + multipartFile.getOriginalFilename());
			log.info("파일 타입 : " + multipartFile.getContentType());
			log.info("파일 크기 : " + multipartFile.getSize());
			
			/* 이미지 정보 객체 */
			AttachImageVO vo = new AttachImageVO();
			
			/* 파일 이름 */
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			/* uuid 적용 파일 이름 - 각 파일이 저장될 때 고유한 이름을 갖도록 함*/
			String uuid = UUID.randomUUID().toString();
			uploadFileName = uuid + "_" + uploadFileName;
			vo.setUuid(uuid);
			
			/* 파일 위치, 파일 이름을 합친 File 객체 */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* 파일 저장 */
			try {
				multipartFile.transferTo(saveFile);
				
				/* 썸네일 이미지 저장 */
				File sub_image1 = new File(uploadPath, "s_" + uploadFileName);
				File sub_image2 = new File(uploadPath, "s_" + uploadFileName);
				File sub_image3 = new File(uploadPath, "s_" + uploadFileName);
				File sub_image4 = new File(uploadPath, "s_" + uploadFileName);
//				
//				BufferedImage bo_image = ImageIO.read(saveFile);
//				
//					/* 비율 */
//					double ratio = 3;
//					
//					/* 넓이 높이 */
//					int width = (int)(bo_image.getWidth()/ratio);
//					int height = (int)(bo_image.getHeight()/ratio);
//					
//				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//				
//				Graphics2D graphic = bt_image.createGraphics();
//				
//				graphic.drawImage(bo_image, 0, 0, width, height, null);
//				
//				ImageIO.write(bt_image, "jpg", sub_image1);
//				ImageIO.write(bt_image, "jpg", sub_image2);
//				ImageIO.write(bt_image, "jpg", sub_image3);
//				ImageIO.write(bt_image, "jpg", sub_image4);
				
				File thumnailFile = new File(uploadPath, "s_" + uploadFileName); 
				
					Thumbnails.of(saveFile)
					.size(160, 160)
					.toFile(sub_image1);
					
					
					Thumbnails.of(saveFile)
					.size(160, 160)
					.toFile(sub_image2);
					
					
					Thumbnails.of(saveFile)
					.size(160, 160)
					.toFile(sub_image3);
					
					
					Thumbnails.of(saveFile)
					.size(160, 160)
					.toFile(sub_image4);
				
			} catch(Exception e) {
				throw new ControllerException(e);
			} // try-catch
			
			list.add(vo);
			
		} // for
		
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;
		
	} //uploadAjaxActionPOST
	
	
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