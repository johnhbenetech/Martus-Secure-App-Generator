/*

Martus(TM) is a trademark of Beneficent Technology, Inc. 
This software is (c) Copyright 2015, Beneficent Technology, Inc.

Martus is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either
version 2 of the License, or (at your option) any later
version with the additions and exceptions described in the
accompanying Martus license file entitled "license.txt".

It is distributed WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, including warranties of fitness of purpose or
merchantability.  See the accompanying Martus License and
GPL license for more details on the required license terms
for this software.

You should have received a copy of the GNU General Public
License along with this program; if not, write to the Free
Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA 02111-1307, USA.

*/

package SAG;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class ObtainXFormController extends WebMvcConfigurerAdapter
{
	private static final String XML_TYPE = "xml";
	private static final String XML_FILE_LOCATION = "bin/static/xFormToUse.xml";  //TODO this will be based on build directory for this session
	private static final String XFORM_FILE_EXTENSION = ".xml";
	private static final String XFORMS_DEFAULT_DIRECTORY = "bin/static/xforms";
	private static final int NO_PATH_SEPARATOR_FOUND = -1;
	private static final char PATH_SEPARATOR = '/';

	@RequestMapping(value=WebPage.OBTAIN_XFORM, method=RequestMethod.GET)
    public String directError(HttpSession session, Model model) 
    {
		SecureAppGeneratorApplication.setInvalidResults(session);
        return WebPage.ERROR;
    }

	@RequestMapping(value=WebPage.OBTAIN_XFORM_PREVIOUS, method=RequestMethod.POST)
    public String goBack(HttpSession session, Model model) 
    {
		AppConfiguration config = (AppConfiguration) session.getAttribute(SessionAttributes.APP_CONFIG);
		model.addAttribute(SessionAttributes.APP_CONFIG, config);
		return WebPage.OBTAIN_LOGO;
    }

	@RequestMapping(value=WebPage.OBTAIN_XFORM_NEXT, method=RequestMethod.POST)
    public String retrieveXForm(HttpSession session, @RequestParam("file") MultipartFile file, @RequestParam("selectedForm") String formName, Model model, AppConfiguration appConfig)
    {
		Path xFormBuildPath = Paths.get(XML_FILE_LOCATION);
		if (file.isEmpty()) 
        {
			if(!copyXFormsFileSelectedToBuildDirectory(session, formName))
				return WebPage.ERROR; 

            AppConfiguration config = (AppConfiguration)session.getAttribute(SessionAttributes.APP_CONFIG);
			config.setAppXFormLocation(xFormBuildPath.getFileName().toString());
			config.setAppXFormName(getFormNameOnly(formName));
			session.setAttribute(SessionAttributes.APP_CONFIG, config);
        }
        else
        {
            try 
            {
            		if(!file.getContentType().contains(XML_TYPE))
             		return returnErrorMessage(model, appConfig, "Error: Xform must be of type xml."); 
            		SecureAppGeneratorApplication.saveMultiPartFileToLocation(file, xFormBuildPath.toString());
  
                isValidXForm(xFormBuildPath);
 
                AppConfiguration config = (AppConfiguration)session.getAttribute(SessionAttributes.APP_CONFIG);
        			String uploadedFormName = xFormBuildPath.getFileName().toString();
				config.setAppXFormLocation(uploadedFormName); 
        			config.setAppXFormName(getFormNameOnly(file.getOriginalFilename()));
        			session.setAttribute(SessionAttributes.APP_CONFIG, config);
            } 
            catch (Exception e) 
            {
            		try
				{
					Files.delete(xFormBuildPath);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
            		return returnErrorMessage(model, appConfig, "Error: Xform Invalid."); 
            }
        } 
		model.addAttribute(SessionAttributes.APP_CONFIG, appConfig);
		return WebPage.OBTAIN_CLIENT_TOKEN;
    }

	private void isValidXForm(Path fileLocation)
	{
		//TODO load XML file, then import into JavaRosa for validation
	}

	public String returnErrorMessage(Model model, AppConfiguration appConfig, String errorMsg)
	{
		appConfig.setAppXFormError(errorMsg);
		model.addAttribute(SessionAttributes.APP_CONFIG, appConfig);
		return WebPage.OBTAIN_XFORM;
	}

	public boolean copyXFormsFileSelectedToBuildDirectory(HttpSession session, String formName)
	{
		CopyOption[] options = new CopyOption[]{ StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES}; 
		Path source = Paths.get(formName);
		Path destination = Paths.get(XML_FILE_LOCATION);
		try
		{
			Files.copy(source, destination, options);
			return true;
		}
		catch (IOException e)
		{
			SecureAppGeneratorApplication.setInvalidResults(session, "Failed to copy file => " + e.getMessage());
		    return false;
		}
	}
	
	@ModelAttribute("formsImpMap")
	public static Map<String,String> populateFormsMap() throws MalformedURLException, IOException 
	{
	    Map<String,String> formsImpMap = new HashMap<String,String>();
		File xFormsDirectory = new File(XFORMS_DEFAULT_DIRECTORY);
		if(!xFormsDirectory.exists())
			return formsImpMap;
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(xFormsDirectory.listFiles()));
		files.forEach((file) -> addForms(formsImpMap, file));
	    return formsImpMap;
	}

	private static void addForms(Map<String,String> formsImpMap, File file)
	{
		String formName = file.getName();
		if(formName.toLowerCase().endsWith(XFORM_FILE_EXTENSION))
			formsImpMap.put(getFormNameOnly(formName), file.getAbsolutePath());
	}

	private static String getFormNameOnly(String formName)
	{
		int startPosition;
		int startOfFileName = formName.lastIndexOf(PATH_SEPARATOR);
		if(startOfFileName == NO_PATH_SEPARATOR_FOUND)
			startPosition = 0;
		else
			startPosition = startOfFileName + 1;
		
		int fileNameLengthWithoutXmlExtension = formName.length()-XFORM_FILE_EXTENSION.length();
		return formName.substring(startPosition, fileNameLengthWithoutXmlExtension);
	}

}
