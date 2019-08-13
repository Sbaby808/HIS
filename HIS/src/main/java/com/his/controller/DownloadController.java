package com.his.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
* @ClassName: DownloadController  
* @Description: 用于下载文件
* @author Sbaby
* @date 2019年8月12日  下午5:40:47
*    
*/
@Controller
public class DownloadController {

	@RequestMapping( value = "/download", method = RequestMethod.GET )
    public void testDownload( HttpServletResponse res, String fileName ) {
     
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
     
        try {
          os = res.getOutputStream();
          bis = new BufferedInputStream(new FileInputStream( 
                    new File("d://HIS//" + fileName )));
          int i = bis.read(buff);
     
          while (i != -1) {
            os.write(buff, 0, buff.length);
            os.flush();
            i = bis.read(buff);
          }
        } catch ( IOException e ) {
          e.printStackTrace();
        } finally {
          if (bis != null) {
            try {
              bis.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        System.out.println("export file finish");
      }
	
}
