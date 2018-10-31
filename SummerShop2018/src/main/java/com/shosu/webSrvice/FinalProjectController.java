package com.shosu.webSrvice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.shosu.dao.ShosuUserDAO;
import com.shosu.dto.mobile.BaseMDTO;
import com.shosu.orm.Category;
import com.shosu.orm.CookRecipe;
import com.shosu.orm.ShosuUser;
import com.shosu.ulti.Constants;
import com.shosu.ulti.Utility;

@RestController
@RequestMapping("CookingSummer")
public class FinalProjectController extends BaseController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ShosuUserDAO shosuUserDAO;

	private final static Gson gson = new Gson();

	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public BaseMDTO login(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = null;
		try {
			result = new BaseMDTO();
			String jsonParam = requestParam.get("json");
			Map<String, ?> json = gson.fromJson(jsonParam, Map.class);
			String userName = (String) json.get("userName");
			String password = (String) json.get("password");
			ShosuUser checkLoginResult = shosuUserDAO.checkLoginByUserPw(userName, password);
			result.setStatus(0);
			result.setContent(checkLoginResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "Registration", method = RequestMethod.POST)
	public BaseMDTO registrion(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = null;
		try {
			result = new BaseMDTO();
			String jsonParam = requestParam.get("json");
			Map<String, ?> json = gson.fromJson(jsonParam, Map.class);
			String userName = (String) json.get("userName");
			String password = (String) json.get("password");
			String email = (String) json.get("email");
			String role = "2";
			Date createdDate = new Date();
			UUID id = UUID.randomUUID();
			ShosuUser addinguser = new ShosuUser(id.toString(), userName, password, role, email, createdDate);
			int checkRegistration = shosuUserDAO.insert(addinguser);
			result.setStatus(0);
			result.setContent(checkRegistration);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static Gson getGson() {
		return gson;
	}

	@RequestMapping(value = "UploadRecipe", method = RequestMethod.POST)
	public BaseMDTO uploadRecipe(@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = null;
		try {
			UUID generatedId = UUID.randomUUID();
			result = new BaseMDTO();
			// getIncomming date
			result = new BaseMDTO();
			String jsonParam = requestParam.get("json");
			Map<String, ?> json = gson.fromJson(jsonParam, Map.class);
			String article = (String) json.get("article");
			String description = (String) json.get("description");
			String userId = (String) json.get("userId");
			Date createdDate = new Date();
			Date lastModifiedDate = createdDate;
			String categoryId = (String) json.get("categoryId");
			String createdBy = (String) json.get("createdBy");
			String lastModifiedBy = (String) json.get("createdBy");

			String path = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = URLDecoder.decode(path, "UTF-8");
			String pathArr[] = fullPath.split("/WEB-INF/classes/");
			fullPath = pathArr[0];

			if (!image.isEmpty()) {
				byte[] bytes = image.getBytes();

				Utility.convertByteToFile(bytes, fullPath + File.separator +"src"+File.separator+"recipe"+File.separator
						+ generatedId.toString() + ".jpg");
			}
			CookRecipe recipeForAdding = new CookRecipe(generatedId.toString(), article, description, userId,
					createdDate, lastModifiedDate, categoryId, createdBy, lastModifiedBy);
			shosuUserDAO.insert(recipeForAdding);
			result.setStatus(0);
			result.setContent(1);
		} catch (Exception e) {
			result.setStatus(1);
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "GetRecipe", method = RequestMethod.POST)
	public BaseMDTO getRecipe(@RequestParam Map<String, String> requestParam) {
		BaseMDTO result = null;
		try {
			result = new BaseMDTO();
			String jsonParam = requestParam.get("json");
			Map<String, ?> json = gson.fromJson(jsonParam, Map.class);
			String category = (String) json.get("category");
			if(category==null) {
				
			}

			List<CookRecipe> list  = shosuUserDAO.getAllproduct(category);
			List<CookRecipe> listTemp  = new ArrayList<CookRecipe>();
			for(CookRecipe temp : list) {
				temp.setCategory(null);
				temp.setShosuUser(null);
				listTemp.add(temp);
			}
			result.setStatus(0);
			result.setContent(listTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	

}
