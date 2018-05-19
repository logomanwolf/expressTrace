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

	public static void main(String[] args) {
		String content = new FileManipulation().readJson("less.json");
		// System.out.println("content:="+content);
		List<Root> roots = JSON.parseArray(content, Root.class);
		String lngAndLats = new FileManipulation().readJson("lngAndLat.json");
		// System.out.println("lngAndLats:="+lngAndLats);
		List<Point> points = JSON.parseArray(lngAndLats, Point.class);
		System.out.println(roots.size());
		System.out.println(points.size());
		List<Root> rootscopy = new ArrayList<Root>(roots);
		ArrayList<RootAndPoint> rootAndPList = new ArrayList<RootAndPoint>();
		for (Root root : rootscopy) {
			RootAndPoint rootAndPoint = new RootAndPoint();
			ArrayList<Trace> newTraces = new ArrayList<Trace>();
			ArrayList<String> lats = new ArrayList<String>();
			ArrayList<String> lngs = new ArrayList<String>();
			for (int j = 0; j < root.getTraces().size(); j++) {
				if (j + 1 < root.getTraces().size()) {
					if (!points.get(j).getLng()
							.equals(points.get(j + 1).getLng())) {
						lats.add(points.get(j).getLat());
						lngs.add(points.get(j).getLng());
						newTraces.add(root.getTraces().get(j));
						System.out.println(root.getTraces().get(j)
								.getAcceptStation()
								+ "lat:" + points.get(j).getLat());
					}
				} /*
				 * else if (j == root.getTraces().size() - 1) {
				 * lats.add(points.get(j).getLat());
				 * lngs.add(points.get(j).getLng());
				 * newTraces.add(root.getTraces().get(j));
				 * System.out.println(root.getTraces().get(j)
				 * .getAcceptStation()); }
				 */

			}
			root.setTraces(newTraces);
			rootAndPoint.setRoot(root);
			rootAndPoint.setLats(lats);
			rootAndPoint.setLngs(lngs);
			rootAndPList.add(rootAndPoint);
		}

		JSONArray jsonArray = new JSONArray();

		for (RootAndPoint rap : rootAndPList) {
			int i = 0;
			for (Trace tce : rap.getRoot().getTraces()) {
				JSONObject jo = new JSONObject();
				jo.put("name", tce.getAcceptStation());
				jo.put("point", new Point(rap.getLats().get(i), rap.getLngs()
						.get(i)));
				jsonArray.add(jo);
				i++;
			}
		}

		/*String rootsStr = JSON.toJSONString(rootAndPList);
		System.out.println(rootsStr);*/
		String rootsStr = JSON.toJSONString(jsonArray);
		System.out.println(rootsStr);
		new FileManipulation().WriteJson("address.json", rootsStr);
	}
}
