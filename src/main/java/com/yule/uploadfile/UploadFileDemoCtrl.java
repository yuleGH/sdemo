package com.yule.uploadfile;

import com.yule.common.utils.LocalFileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * WEB 安全之文件上传漏洞
 * Created by yule on 2018/7/11 22:07.
 */
@Controller
@RequestMapping("/uploadFileDemoCtrl")
public class UploadFileDemoCtrl {

    @RequestMapping("/index")
    public String index(){
        return "yule/uploadfile/uploadFileDemo";
    }

    /**
     * 文件上传
     * 有漏洞的上传文件代码
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request) throws IOException {
        // 转型为MultipartHttpRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件到map容器中
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        if(fileMap == null || fileMap.size() == 0){
            System.out.println("没有文件！");
            return "yule/uploadfile/uploadFileDemo";
        }

        String root = request.getServletContext().getRealPath("/upload");
        File savePathFile = new File(root);
        if(!savePathFile.exists()){
            savePathFile.mkdirs();
        }

        String fileName = null;
        String suffixName = null;
        MultipartFile mf = null;
        InputStream fileIn = null;
        List<InputStream> isList = new ArrayList<InputStream>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            mf = entity.getValue();
            fileName = mf.getOriginalFilename();//获取原文件名
            suffixName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            try {
                fileIn = mf.getInputStream();
                isList.add(mf.getInputStream());
                LocalFileUtils.upload(fileIn, root, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fileIn != null){
                  fileIn.close();
                }
            }
        }

        return "yule/uploadfile/uploadFileDemo";
    }

    /**
     * 攻击上传文件漏洞 示例
     * @param request
     */
    @RequestMapping("/deleteFile")
    @ResponseBody
    public void deleteFile(HttpServletRequest request){
        String root = request.getServletContext().getRealPath("/upload");
        System.out.println(("系统部署文件上传的绝对路径:"+root));
        File file = new File(root);
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (root.endsWith(File.separator)) {
                temp = new File(root + tempList[i]);
            } else {
                temp = new File(root + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
        }
        file.delete();
    }

    /**
     * 文件下载
     * 有目录遍历攻击漏洞的代码
     */
    @RequestMapping("/downLoadFile")
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取项目部署绝对路径下的upload文件夹路径,下载upload目录下面的文件
        String root = request.getServletContext().getRealPath("/upload");
        //获取文件名
        String filename = request.getParameter("filename");
        File file = new File(root + "/" + filename);
        //根据文件路径创建输入流
        FileInputStream fis = new FileInputStream(file);
        //设置响应头,弹出下载框
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        byte[] b = new byte[fis.available()];
        fis.read(b);
        response.getOutputStream().write(b);
    }

}
