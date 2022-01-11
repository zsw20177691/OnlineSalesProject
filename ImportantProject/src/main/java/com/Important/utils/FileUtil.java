package com.Important.utils;

import com.Important.entity.FileEntity;
import com.Important.mapper.FileEntityMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传相关
 */
@Component
public class FileUtil {
    /**
     * 文件上传路径
     */
    @Value(value = "${file.updatePath}")
    private String  filePath;
    /**
     * 文件访问路径
     */
    @Value(value = "${file.viewPath}")
    private String  viewPath;
    /**
     * 项目启动端口号
     */
    @Value(value = "${server.port}")
    private String  port;
    /**
     * 项目访问前置路径
     */
    @Value(value = "${server.servlet.context-path}")
    private String contextPath;

    @Resource
    private FileEntityMapper fileEntityMapper;

//    public List<FileEntity> updatePath(MultipartFile[]   files){
//        try{
//            List<FileEntity> fileEntities = new ArrayList<FileEntity>();
//            for (MultipartFile file:files ){
//                //只能使用一次，使用完之后流会被回收掉
//                //原文件名称
//                String originalFilename = file.getOriginalFilename();
//                //上传到ftp的附件名称
//                String  ftpName= UUID.randomUUID().toString().replace("-", "")+originalFilename.substring(originalFilename.lastIndexOf("."));
//                //文件存储路径
//                String path = filePath +ftpName;
//                //虚拟机ip地址
//                InetAddress localHost = Inet4Address.getLocalHost();
//                //获得ip4地址
//                String hostAddress = localHost.getHostAddress();
//                //文件网络访问路径
//                LocalDateTime parse = LocalDateTime.parse(LocalDateTime.now().toString().replace("T"," ").substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                //判断文件路径是否存在 如果不存在则创建文件本地路径
//                String paths = filePath + ftpName;
//                File dir=new File(paths);
//                if (!dir.getParentFile().exists()){
//                    dir.getParentFile().mkdirs();
//                }
//                String url="http://"+hostAddress+":"+port+contextPath+"/"+viewPath+"/"+ftpName;
//                file.transferTo(dir);
//                FileEntity build = FileEntity.builder()
//                        .createDate(parse)
//                        .name(originalFilename)
//                        .realPath(path)
//                        .url(url)
//                        .ftpName(ftpName)
//                        .build();
//                fileEntityMapper.insert(build);
//                fileEntities.add(build);
//            }
//            return fileEntities;
//        }catch (Exception   e){
//            System.out.println(e);
//            throw new ExceptionResult("文件上传失败");
//        }
//    }


    public List<FileEntity> updatePath1(MultipartFile[]   files){
        try{
            List<FileEntity> fileEntities = new ArrayList<FileEntity>();
            for (MultipartFile file:files ){
                //获取文件名
                String originalFilename = file.getOriginalFilename();
                //判断路径是否存在 否创建本地路径
                File file1 = new File(filePath);
                if (!file1.exists()){
                    file1.mkdirs();
                }
                //创建真实路径
                String s = filePath + originalFilename;
                try{
                    file.transferTo(new File(s));
                }catch (Exception   e){
                    e.printStackTrace();
                    throw new ExceptionResult("文件上传失败");
                }
                //创建网络路径
                String webPath = "http://"+ "127.0.0.1:8099"+contextPath + "/"+viewPath+"/" + originalFilename;
                FileEntity build = FileEntity.builder()
                        .url(webPath)
                        .createDate(TimeFormatUtil.nowDateTime())
                        .name(originalFilename)
                        .realPath(s)
                        .build();
                fileEntityMapper.insert(build);
                fileEntities.add(build);
            }
            return fileEntities;
        }catch (Exception   e){
            System.out.println(e);
            throw new ExceptionResult("文件上传失败");
        }
    }




}
