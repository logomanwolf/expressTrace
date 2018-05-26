<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Leaflet 加载 Echarts3</title>
<link rel="stylesheet" href="css/leaflet.css">
<style>
html,body,#map {
	height: 100%;
	padding: 0;
	margin: 0;
}

#forkongithub a {
	background: #000;
	color: #fff;
	text-decoration: none;
	font-family: arial, sans-serif;
	text-align: center;
	font-weight: bold;
	padding: 5px 40px;
	font-size: 1rem;
	line-height: 2rem;
	position: relative;
	transition: 0.5s;
}

#forkongithub a:hover {
	background: #c11;
	color: #fff;
}

#forkongithub a::before,#forkongithub a::after {
	content: "";
	width: 100%;
	display: block;
	position: absolute;
	top: 1px;
	left: 0;
	height: 1px;
	background: #fff;
}

#forkongithub a::after {
	bottom: 1px;
	top: auto;
}

@media screen and (min-width:800px) {
	#forkongithub {
		position: fixed;
		display: block;
		top: 0;
		right: 0;
		width: 200px;
		overflow: hidden;
		height: 200px;
		z-index: 9999;
	}
	#forkongithub a {
		width: 200px;
		position: absolute;
		top: 60px;
		right: -60px;
		transform: rotate(45deg);
		-webkit-transform: rotate(45deg);
		-ms-transform: rotate(45deg);
		-moz-transform: rotate(45deg);
		-o-transform: rotate(45deg);
		box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.8);
	}
}
</style>
</head>
<body>
	
	<div id="map"></div>
	<script src="js/main.min.js"></script>
	<script>
		/* var dd = [ {
			"温州市(苍南县城分部)" : [ 120.445543, 27.434436 ]
		}, {
			"温州市(温州中转部)" : [ 120.690635, 28.002838 ]
		}, {
			"芜湖市(芜湖中转部)" : [ 118.384108, 31.36602 ]
		}, {
			"郑州市(豫北中转)" : [ 120.445543, 27.434436 ]
		}, {
			"濮阳市(濮阳县)" : [ 120.690635, 28.002838 ]
		}, {
			"北京市(北京)" : [ 120.445543, 27.434436 ]
		}, {
			"南充市(南充中转站)" : [ 120.690635, 28.002838 ]
		}, {
			"成都市(成都双流)" : [ 118.384108, 31.36602 ]
		}, {
			"东莞市(东莞中心)" : [ 120.445543, 27.434436 ]
		}, {
			"荆州市(荆州中转部)" : [ 120.690635, 28.002838 ]
		}, {
			"荆门市(钟祥)" : [ 118.384108, 31.36602 ]
		}, {
			"东莞市(东莞中心)" : [ 120.445543, 27.434436 ]
		}, {
			"武汉市(武汉中转部)" : [ 120.690635, 28.002838 ]
		}, {
			"黄冈市(黄冈团风县)" : [ 118.384108, 31.36602 ]
		}, {
			"金华市(金华中转部)" : [ 120.445543, 27.434436 ]
		}, {
			"深圳市(深圳中心)" : [ 120.690635, 28.002838 ]
		}, {
			"深圳市(罗湖笋岗)" : [ 118.384108, 31.36602 ]
		} ];
		var map1 = {};
		var x = dd.length;
		for (var i = 0; i < x; i++) {
			for ( var jsonname in dd[i]) {
				map1[jsonname] = dd[i][jsonname];
			}
		}
		console.log((map1["金华市(金华中转部)"])); */
		//console.log((map1["深圳市(罗湖笋岗)"]));
		//console.log((map1["jsonname"]));
		var map = L.map('map');
		var baseLayers = {
			"高德地图" : L
					.tileLayer(
							'http://webrd0{s}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=8&x={x}&y={y}&z={z}',
							{
								subdomains : "1234"
							}),
			'高德影像' : L
					.layerGroup([
							L
									.tileLayer(
											'http://webst0{s}.is.autonavi.com/appmaptile?style=6&x={x}&y={y}&z={z}',
											{
												subdomains : "1234"
											}),
							L
									.tileLayer(
											'http://t{s}.tianditu.cn/DataServer?T=cta_w&X={x}&Y={y}&L={z}',
											{
												subdomains : "1234"
											}) ]),
			"立体地图" : L
					.tileLayer(
							'https://a.tiles.mapbox.com/v3/examples.c7d2024a/{z}/{x}/{y}.png',
							{
								attribution : 'Map &copy; Pacific Rim Coordination Center (PRCC).  Certain data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
								key : 'BC9A493B41014CAABB98F0471D759707',
								styleId : 22677
							}),
			"Foursquare" : L
					.tileLayer(
							'https://a.tiles.mapbox.com/v3/foursquare.map-0y1jh28j/{z}/{x}/{y}.png',
							{
								attribution : 'Map &copy; Pacific Rim Coordination Center (PRCC).  Certain data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
								key : 'BC9A493B41014CAABB98F0471D759707',
								styleId : 22677
							}),
			'GeoQ灰色底图' : L
					.tileLayer(
							'http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineStreetPurplishBlue/MapServer/tile/{z}/{y}/{x}')
					.addTo(map)
		};
		L
				.tileLayer(
						'https://a.tiles.mapbox.com/v3/foursquare.map-0y1jh28j/{z}/{x}/{y}.png',
						{
							attribution : 'Map &copy; Pacific Rim Coordination Center (PRCC).  Certain data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
							key : 'BC9A493B41014CAABB98F0471D759707',
							styleId : 22677
						});
		var layercontrol = L.control.layers(baseLayers, {}, {
			position : "topleft"
		}).addTo(map);
		map.setView(L.latLng(37.550339, 104.114129), 4);
		var overlay = new L.echartsLayer3(map, echarts);
		var chartsContainer = overlay.getEchartsContainer();
		var myChart = overlay.initECharts(chartsContainer);
		//var data=${data};

		options2 = 
			   [
			        {
			            backgroundColor: '#ffffff',
			    title : {
			        text: '各抽检环节异议情况分析',
			        subtext: '2017年'
			    },
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)",

			    },
			    legend: {
			        
			        orient: 'vertical',
			        x: 'right',
			       itemWidth: 14,
			        itemHeight: 14,
			        align: 'left',
			    
			    },
			    series: [
			        {
			            name:'各主体业态检查批次比例',
			            type:'pie',
			            hoverAnimation: false,
			            legendHoverLink:false,
			            radius: ['31%', '35%'],
			            color: ['#915872', '#3077b7', '#9a8169'
			],
			            label: {
			                normal: {
			                    position: 'inner'
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                },
			               
			            },
			            tooltip: {
			               show:false,
			               
			               
			            },
			            data:[ {value:20392, name:''},
			                {value:13922, name:''},
			                {value:40392, name:''}]
			        },
			        {
			            name:'量化分级评定信息统计',
			            type:'pie',
			            radius: ['35%', '65%'],
			            color: ['#d74e67', '#0092ff', '#eba954'],
			            label: {
			                normal: {
			                    formatter: '{b}\n{d}%'
			                },
			          
			            },
			            data:[
			                {value:20392, name:'生产环节'},
			                {value:13922, name:'餐饮环节'},
			                {value:40392, name:'销售环节'}]
			        }
			    ]
			}
			];

		//飞机图标动画的效果
		overlay.setOption(options2);
		
		
		
	</script>
</body>
</html>