package controller;

import java.util.List;

import po.RootAndPoint;
import prepro.FileManipulation;

import com.alibaba.fastjson.JSON;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data=new FileManipulation().readJson("step1.json");
		System.out.println(data);
		List<RootAndPoint> roots = JSON.parseArray(data, RootAndPoint.class);
		//System.out.println( JSON.toJSONString(roots));
		new FileManipulation().WriteJson("step2.json", JSON.toJSONString(roots));
	}

}
