package cn.dreampie.resource.file;

import java.io.File;

import cn.dreampie.common.http.UploadedFile;
import cn.dreampie.common.util.stream.FileRenamer;
import cn.dreampie.resource.ApiResource;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.route.annotation.POST;
import cn.dreampie.route.core.multipart.FILE;
import cn.dreampie.security.Subject;

@API("/file")
public class FileResource extends ApiResource{
	
	//上传文件
	/**大小限制10M
	 * @param filename
	 * @param testfile
	 * @param des
	 * @return
	 */
	@POST("/upload")
	@FILE(dir="c:/upload",overwrite=true,max=10485760,encoding="utf8")
	public UploadedFile upload(String filename, UploadedFile testfile, String des) {
	    //注意UploadedFile  参数的名字 需要和input的name对应
	    //如 <input type="file" name="x"> 用UploadedFile x来接收文件
	    //如果上传多个文件，使用Map<String,UploadedFile> files来接收所有的文件，key为input的name x
	    System.out.println("*****************"+des);
	    return testfile;
	}
	
	//下载文件 支持断点续传
	@GET("download")
	public File download() {
	    return new File(getRealPath() + "c:/upload/resty.jar");
	}
	
	/**得到真实路径
	 * @return
	 */
	public String getRealPath(){
		return "";
	}
}
