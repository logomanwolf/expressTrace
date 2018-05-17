package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.util.List;

import org.apache.commons.io.FileUtils;

import po.*;

import com.alibaba.fastjson.*;

public class Preprocess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("D:/junior/expressTrace/WebRoot/json/result2.json");
		String content = "";
		try {
			content = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Root> roots = JSON.parseArray(content, Root.class);
		// System.out.println(roots.size());
		CityPreprocess cityPreprocess = new CityPreprocess();
		for (int i = 0; i < roots.size(); i++) {
			List<Trace> traces = roots.get(i).getTraces();
			for (int j = 0; j < traces.size(); j++) {
				String[] temp = cityPreprocess.extractCity(traces.get(j)
						.getAcceptStation());
				traces.get(j).setAcceptStation(temp[0] + temp[1]);
			}
		}
		String rootsStr = JSON.toJSONString(roots);
		new FileManipulation().writeFile("step1.json", rootsStr);
	}
}
