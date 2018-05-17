package prepro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.util.List;

import org.apache.commons.io.FileUtils;

import po.*;

import com.alibaba.fastjson.*;

public class Preprocess implements Process {

	public void doProcess() {
		// TODO Auto-generated method stub
		String content = new FileManipulation().readJson("result.json");
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
		new FileManipulation().WriteJson("step1.json", rootsStr);
	}
}
