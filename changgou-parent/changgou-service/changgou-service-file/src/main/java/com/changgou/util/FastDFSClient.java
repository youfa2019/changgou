package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FastDFSClient {
    /**
     * 初始化Tracker信息
     */
    static {
        try {
            //获取tracker的配置文件fdfs_client的位置
            String filePath=new ClassPathResource("fdfs_client.conf").getPath();
            //加载tracker的配置信息
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param file:要上传的文件信息封装->FastDFSFile
     * @return String[]
     *          1:文件上传所存储的组名
     *          2:文件存储路径
     */
    public static String[] upload(FastDFSFile file){
        //获取文件作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] =new NameValuePair(file.getAuthor());

        /***
         * 文件上传后的返回值
         * uploadResults[0]:文件上传所存储的组名，例如:group1
         * uploadResults[1]:文件存储路径,例如：M00/00/00/wKjThF0DBzaAP23MAAXz2mMp9oM26.jpeg
         */
        String[] uploadResults = null;
        try {
            //获取StorageClient对象
            StorageClient storageClient = getStorageClient();
            //执行文件上传
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResults;

    }

    /**
     * 获取文件信息
     * @param groupName 组名
     * @param remoteFileName 文件存储完整名
     * @return
     */
    public static FileInfo getFile(String groupName,String remoteFileName){
        try {
            //获取StorageClient对象
            StorageClient storageClient = getStorageClient();
            //获取文件信息
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     * @param groupName 组名
     * @param remoteFileName 文件存储完整名
     * @return
     */
    public static InputStream downloadFile(String groupName,String remoteFileName){
        try {
            //获取StorageClient对象
            StorageClient storageClient = getStorageClient();
            //通过StorageClient下载文件
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            //将字节数组转换成字节输入流
            return new ByteArrayInputStream(fileByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件删除实现
     * @param groupName 组名
     * @param remoteFileName 文件存储完整名
     */
    public static void deleteFile(String groupName,String remoteFileName){
        try {
            //获取StorageClient对象
            StorageClient storageClient = getStorageClient();
            //通过StorageClient删除文件
            storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取组信息
     * @param
     * @return
     */
    public static StorageServer getStorages(String groupName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取组信息
            return trackerClient.getStoreStorage(trackerServer,groupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据文件组名和文件存储路径名获取Storage服务的ip,端口信息
     * @param groupName 组名
     * @param remoteFileName 文件存储完整名
     * @return
     */
    public static ServerInfo[] getServerInfo(String groupName,String remoteFileName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取服务信息
            return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Tracker服务地址
     * @return
     */
    public static String getTrackerUrl(){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Tracker地址
            return "http://"+trackerServer.getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取TrackerServer
     * @return
     */
    public static TrackerServer getTrackerServer() throws Exception {
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    /**
     * 获取StorageClient
     * @return
     * @throws Exception
     */
    public static StorageClient getStorageClient() throws Exception {
        //获取TrackerServer
        TrackerServer trackerServer = getTrackerServer();
        //通过TrackerServer创建StorageClient
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }
    //main方法,用于测试文件下载,文件删除,获取Storage信息
    //获取Storage组的IP和端口信息
    //获取Tracker的信息
    public static void main(String[] args) throws Exception {
        //获取文件地址及大小信息
        //FileInfo fileInfo = getFile("group1", "M00/00/00/wKjThF1JJlGASq1jAAA5N0a3Wdc081.JPG");
        //System.out.println("文件地址"+fileInfo.getSourceIpAddr());
        //System.out.println("文件大小"+fileInfo.getFileSize());

        //文件下载
        //InputStream is=downloadFile("group1","M00/00/00/wKjThF1JJlGASq1jAAA5N0a3Wdc081.JPG");
        //将文件写入到本地磁盘
        //FileOutputStream os = new FileOutputStream("F:/1.jpg");
        //定义一个缓冲区
        //byte[] buffer=new byte[1024];
        //while (is.read(buffer)!=-1){
           // os.write(buffer);
        //}
       //os.flush();
        //os.close();
        //is.close();
        //文件删除
        deleteFile("group1","M00/00/00/wKjThF1JM0CADc_YAAC1oJynhbY462.JPG");
        //获取Storage信息
        //StorageServer storageServer = getStorages("group1");
        //System.out.println(storageServer.getStorePathIndex());
        //System.out.println("IP信息:"+storageServer.getInetSocketAddress().getHostString());
        ////获取Storage组的IP和端口信息
        //ServerInfo[] group1s = getServerInfo("group1", "M00/00/00/wKjThF1JMIeAW4wRAAC1oJynhbY456.JPG");
        //for (ServerInfo group : group1s) {
        //    System.out.println(group.getIpAddr());
        //    System.out.println(group.getPort());
        //}
        ////获取Tracker的信息
        //System.out.println(getTrackerUrl());

    }


}
