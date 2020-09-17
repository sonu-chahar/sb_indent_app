package com.chahar.indent.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chahar.indent.model.UserMaster;
import com.chahar.indent.service.UserMasterService;
import com.chahar.indent.util.Constants;

@Controller
@RequestMapping("**/myProfile")
public class ProfileController extends AbstractPageController {
	@Autowired
	Environment environment;

	@Autowired
	private UserMasterService userMasterService;

	@GetMapping(value = "**/showProfile")
	public ModelAndView showMyProfile(ModelMap model, HttpServletRequest request) {
		log.debug("show User Profile Page......");

		UserMaster userMaster = (UserMaster) request.getSession(false).getAttribute("userDtl");

		String imageStatus = StringUtils.isNotBlank(request.getParameter(CONSTANT_FOR_IMAGE_UPLOAD_STATUS))
				? request.getParameter(CONSTANT_FOR_IMAGE_UPLOAD_STATUS)
				: CONSTANT_BLANK_STRING;
		String status = StringUtils.isNotBlank(request.getParameter(REQUEST_ATTRIBUTE_STATUS))
				? request.getParameter(REQUEST_ATTRIBUTE_STATUS)
				: CONSTANT_BLANK_STRING;
		if (status.equals(STATUS_FOR_UPDATE)) {
			return new ModelAndView(REDIRECT_URL_FOR_HOMEPAGE + status);
		}

		model.addAttribute(CONSTANT_FOR_IMAGE_UPLOAD_STATUS, imageStatus);

		model.addAttribute(MODEL_ATTRIBUTE_MESSAGE, getMessageAttributeForPage(request, USER_CLASSNAME_FOR_MESSAGE));

		String activeProfile = environment.getProperty("spring.profiles.active");
		model.addAttribute("activeProfile", activeProfile);
		model.addAttribute(MODEL_ATTRIBUTE_USER_MASTER, userMaster);

		return new ModelAndView(VIEW_NAME_PROFILE, model);
	}

	@PostMapping(value = "**/updateProfile")
	public ModelAndView updateProfile(@Valid @ModelAttribute(MODEL_ATTRIBUTE_USER_MASTER) UserMaster userMasterDTO,
			BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		log.debug("Received request to update user");
		if (result.hasErrors()) {
			log.error("Error in User Master object....");
			return new ModelAndView(REDIRECT_URL_FOR_PROFILE + CONSTANT_BLANK_STRING);
		}
		UserMaster oldUserMaster = (UserMaster) request.getSession(false)
				.getAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER);

		String status = CONSTANT_BLANK_STRING;

		try {
			userMasterDTO.setUsername(oldUserMaster.getUsername());
			userMasterDTO.setPassword(oldUserMaster.getPassword());
			userMasterDTO.setId(oldUserMaster.getId());
			userMasterDTO.setIsActive(true);

			if (STATUS_FOR_ERROR.equals(status)) {
				return new ModelAndView(REDIRECT_URL_FOR_PROFILE + status);
			}
			if (userMasterDTO.getImageFile() != null && userMasterDTO.getImageFile().getSize() > 0) {
				if (!saveImage(userMasterDTO)) {
					redirectAttributes.addFlashAttribute(CONSTANT_FOR_IMAGE_UPLOAD_STATUS, "Cannot upload image !");
				}
			} else {
				if (!deleteImage(userMasterDTO)) {
					redirectAttributes.addFlashAttribute(CONSTANT_FOR_IMAGE_UPLOAD_STATUS, "Image cannot be removed");
				}
			}

			userMasterDTO = userMasterService.save(userMasterDTO);
			request.getSession(false).setAttribute(SESSION_ATTRIBTE_FOR_USER_MASTER, userMasterDTO);
			status = STATUS_FOR_UPDATE;
		} catch (ConstraintViolationException e) {
			status = STATUS_FOR_DUPLICATE;
		} catch (Exception e) {
			log.debug(e.getStackTrace());
			status = STATUS_FOR_ERROR;
		}

		return new ModelAndView(REDIRECT_URL_FOR_PROFILE + status);

	}

	private boolean saveImage(UserMaster userMasterDTO) {
		boolean isImageSaved = false;
		String fileDir = Constants.pathString(CONSTANT_FOR_IMAGE_PATH);
		// Image
		File file = null;
		CommonsMultipartFile multipartFile = userMasterDTO.getImageFile();
		String fileName = CONSTANT_BLANK_STRING;
		String extension = CONSTANT_BLANK_STRING;
		if (multipartFile != null) {
			fileName = multipartFile.getOriginalFilename().substring(0,
					multipartFile.getOriginalFilename().indexOf(CONSTANT_FOR_DOT));
			extension = multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().indexOf(CONSTANT_FOR_DOT) + 1);

			String filePath = fileDir + userMasterDTO.getContactNumber();
			file = new File(filePath);
			boolean success = file.mkdirs();
			if (!success) {
				for (File fin : file.listFiles()) {
					FileDeleteStrategy.FORCE.deleteQuietly(fin);
				}
			}
			file = new File(filePath + CONSTANT_FOR_SLASH + fileName + CONSTANT_FOR_DOT + extension);
			try {
				success = file.createNewFile();
			} catch (IOException e1) {
				log.debug(e1.getStackTrace());
			}
			if (success) {
				try (FileOutputStream outputStream = new FileOutputStream(file);) {

					outputStream.write(multipartFile.getFileItem().get());
					userMasterDTO.setImageName(fileName);
					userMasterDTO.setFileExtension(extension);
					isImageSaved = true;

				} catch (Exception e) {
					log.debug(e.getStackTrace());
				}
			}
		}
		return isImageSaved;
	}

	private boolean deleteImage(UserMaster userMasterDTO) {
		if (StringUtils.isBlank(userMasterDTO.getImageName())
				&& StringUtils.isBlank(userMasterDTO.getFileExtension())) {
			String fileDir = Constants.pathString(CONSTANT_FOR_IMAGE_PATH);
			String filePath = fileDir + userMasterDTO.getContactNumber();
			try {
				File file = new File(filePath);
				boolean success = file.mkdirs();
				if (!success) {
					for (File fin : file.listFiles()) {
						FileDeleteStrategy.FORCE.deleteQuietly(fin);
					}
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@GetMapping(value = "/getImage/{mobileNumber}/{imageName}/{imageExtension}")
	@ResponseBody
	public void getImage(@PathVariable("mobileNumber") String mobileNumber, @PathVariable("imageName") String imageName,
			@PathVariable("imageExtension") String imageExtension, HttpServletResponse response) {
		log.debug("request to get Student Image....");
		String fileDir = Constants.pathString(CONSTANT_FOR_IMAGE_PATH);

		String filePath = fileDir + mobileNumber + CONSTANT_FOR_SLASH + imageName + CONSTANT_FOR_DOT + imageExtension;

		List<String> imageExtensionList = new ArrayList<>();
		imageExtensionList.add("png");
		imageExtensionList.add("jpeg");
		imageExtensionList.add("jpg");
		imageExtensionList.add("gif");
		if (imageExtensionList.contains(imageExtension)) {
			response.setContentType("image/" + imageExtension);
			BufferedImage img = null;
			try (OutputStream out = response.getOutputStream();) {
				img = ImageIO.read(new File(filePath));
				ImageIO.write(img, imageExtension, out);
			} catch (IOException e) {
				log.error("File is not present OR access denied!");
			} finally {
				img = null;
			}
		}
	}

//	@GetMapping(value = "/generateOtp/{mobileNumber}")
//	@ResponseBody
//	@Produces(MediaType.APPLICATION_JSON)
//	public OtpDTO generateOtpStatusInJSON(HttpServletRequest request,
//			@PathVariable("mobileNumber") String mobileNumber) {
//		char[] alphNum = Constants.ALPHANUMBERIC_NUMBER.toCharArray();
//
//		Random rnd = Constants.RANDOM_NUMBER;
//
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < 6; i++) {
//			sb.append(alphNum[rnd.nextInt(alphNum.length)]);
//		}
//		String generatedOtp = sb.toString();
//		request.getSession(false).setAttribute(SESSION_ATTRIBTE_FOR_GENERATED_OTP, generatedOtp);
//		String generatedMsg = "Your OTP for registration in SSO App is " + generatedOtp;
//
//		CloseableHttpResponse httpResponse = null;
//		HttpGet getRequest = null;
//		try {
//			getRequest = new HttpGet("http://sms.ndmc.gov.in/?SenderId=NDMCIT&Mobile="
//					+ URLEncoder.encode(mobileNumber, StandardCharsets.UTF_8.toString()) + "&message="
//					+ URLEncoder.encode(generatedMsg, StandardCharsets.UTF_8.toString()));
//		} catch (UnsupportedEncodingException e1) {
//			log.debug(e1.getStackTrace());
//		}
//		if (getRequest != null) {
//			try (CloseableHttpClient client = HttpClients.createDefault()) {
//
//				httpResponse = client.execute(getRequest);
//
//				int responseCode = httpResponse.getStatusLine().getStatusCode();
//
//				log.debug("**GET** request Url: {}", getRequest.getURI());
//				log.debug("Response Code: {}", responseCode);
//
//				HttpEntity httpEntity = httpResponse.getEntity();
//				String apiOutput = EntityUtils.toString(httpEntity);
//				ObjectMapper objectMapper = new ObjectMapper();
//				OtpDTO responseDTO = objectMapper.readValue(apiOutput, OtpDTO.class);
//				responseDTO.setData(responseDTO.getResponse().toString());
//				responseDTO.setStatus(responseDTO.getStatus());
//				log.debug("Content: {}", apiOutput);
//
//				return responseDTO;
//
//			} catch (UnsupportedOperationException | IOException e) {
//				log.debug(e.getStackTrace());
//				return null;
//			} finally {
//				if (httpResponse != null) {
//					try {
//						httpResponse.close();
//					} catch (IOException e) {
//						log.debug(e.getStackTrace());
//					}
//				}
//				httpResponse = null;
//			}
//		} else {
//			OtpDTO otpDTO = new OtpDTO();
//			otpDTO.setData("error occured while sending otp!!!");
//			otpDTO.setStatus("0");
//			return new OtpDTO();
//		}
//	}

}
