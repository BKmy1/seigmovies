package com.example.seigmovies.controller;

import com.example.seigmovies.entity.Contact;
import com.example.seigmovies.entity.Mail;
import com.example.seigmovies.service.ContactService;
import com.example.seigmovies.utils.MD5;
import com.example.seigmovies.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@EnableAsync
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private MailUtil mailUtil;


    @GetMapping(value = "/contact/index")
    public String getContactIndex() {
        return "contact/index";
    }

    @PostMapping(value = "/contact/doContact")
    public String doContact(MultipartFile files,HttpServletRequest request, HttpSession session) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = MD5.encrypt(password);
        String textarea = request.getParameter("textarea");
        String upload = uploadFile(files, session);
        System.out.println(email + "," + password + "," + textarea);
        String userId = UUID.randomUUID().toString().substring(0, 7);
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setContent(textarea);
        contact.setPassword(password);
        contact.setUserId(userId);
        contact.setUpload(upload);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date()).toString();
        contact.setPublishTime(now);
        int i = contactService.addContact(contact);
        Mail mail = new Mail();
        mail.setSendername(email);
        mail.setRecipient("1026845072@qq.com");
        mail.setSubject("视频留言");
        mail.setContent(textarea);
        mail.setFile(new File(upload));
//        mail.setFile(new File("C:\\Users\\admin\\Desktop\\1.png"));
        mail.setName(upload);
        mailUtil.sendSimpleMail(mail);
        System.out.println("插入成功");

        return "redirect:/contact/index";
    }

    // 上传文件
    public String uploadFile(MultipartFile files,HttpSession session) throws IOException {
        // 获取网站根目录
//        String photoPath = "http://124.220.158.140/images/";
        // 获取上传的文件的文件名
        String fileName = files.getOriginalFilename();
        System.out.println(fileName);
        // 获取上传的文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 将UUID作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // 将uuid和后缀名拼接后的结果作为最终的文件名
        fileName = uuid + suffixName;
        // 通过servletContext获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        System.out.println(finalPath);
        files.transferTo(new File(finalPath));
        System.out.println("上传完成！");
        return finalPath;
    }

    // 下载文件
    @GetMapping("/download/{numId}")
    public ResponseEntity<byte[]> testResponseEntity(@PathVariable("numId") String numId, HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        int num = Integer.parseInt(numId);
        if (num < 10) {
            numId = "0" + numId;
        }
        //获取服务器中文件的真实路径
        //http://124.220.158.140/video/1101/E01.mp4;
        //String realPath = servletContext.getRealPath("/static/img/2.jpg");
        String realPath = "http://124.220.158.140/video/1101/E"+numId+".mp4".replace("\r\n","");
//        String realPath = "http://124.220.158.140/images/101.jpg";
        System.out.println(realPath);
        //创建输入流
        URL url = new URL(realPath);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
//        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[inputStream.available()];
        //将流读到字节数组中
        inputStream.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.mp4");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        System.out.println(statusCode);
        //关闭输入流
        inputStream.close();
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        System.out.println("下载ok");
        return responseEntity;
    }
}
