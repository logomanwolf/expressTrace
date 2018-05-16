package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import org.apache.tomcat.util.http.fileupload.FileUtils;






import org.apache.commons.io.FileUtils;

import po.*;

import com.alibaba.fastjson.*;

public class Preprocess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("D:/software engineering/expressTrace/webRoot/json/result2.json");
        String content="";
		try {
			content = FileUtils.readFileToString(file,"UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Root> roots = JSON.parseObject(content,
				new TypeReference<ArrayList<Root>>() {
				});
		System.out.println(roots.size());
		
		
		// ArrayList<Student> students1 = JSONArray.parseObject(JSON_ARRAY_STR,
		// new TypeReference<ArrayList<Student>>()
		// {});//因为JSONArray继承了JSON，所以这样也是可以的

	}

}
