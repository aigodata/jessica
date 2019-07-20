package com.aigodata.jessica.basic.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.aigodata.jessica.basic.common.web.annotation.JsonParam;
import com.aigodata.jessica.basic.model.ResultModel;
import com.google.common.collect.Lists;
import com.maxmind.db.CHMCache;
import com.maxmind.db.Reader;

@RestController
@RequestMapping("/geo")
public class GeoController {

	private ArrayList<String> specialIso = Lists.newArrayList("tw", "hk", "mo");

	@PostMapping
	public ResultModel geo(@JsonParam String[] ips) throws IOException {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("geo/GeoLite2-City.mmdb");
		Map<String, Object> result = new HashMap<>();
		try (Reader reader = new Reader(inputStream, new CHMCache())) {
			for (String ip : ips) {
				boolean special = false;
				InetAddress address;
				try {
					address = InetAddress.getByName(ip);
				} catch (Exception e) {
					// 跳过错误IP
					continue;
				}
				JsonNode response = reader.get(address);
				if (response == null || response.isNull()) {
					continue;
				}
				Map<String, Object> ipMap = new HashMap<>();
				result.put(ip, ipMap);

				JsonNode locationNode = response.get("location");

				if (locationNode != null) {
					// 精度
					JsonNode longitudeNode = locationNode.get("longitude");
					// 纬度
					JsonNode latitudeNode = locationNode.get("latitude");
					if (longitudeNode != null && latitudeNode != null) {
						double longitude = longitudeNode.doubleValue();
						double latitude = latitudeNode.doubleValue();
						ipMap.put("geo", new double[] { longitude, latitude });
					}
					// 时区
					JsonNode timeZoneNode = locationNode.get("time_zone");
					if (timeZoneNode != null) {
						String timeZone = timeZoneNode.textValue();
						ipMap.put("time_zone", timeZone);
					}
				}
				// 国家
				JsonNode countryNode = response.get("country");
				String countryNameEn = null;
				String countryNameZh = null;
				if (countryNode != null) {
					JsonNode isoNode = countryNode.get("iso_code");
					if (isoNode != null) {
						String isoCode = isoNode.textValue().toLowerCase();
						if (specialIso.contains(isoCode)) {
							special = true;
						} else {
							ipMap.put("iso_code", isoCode);
						}
					}
					JsonNode namesNode = countryNode.get("names");
					if (namesNode != null) {
						JsonNode enNode = namesNode.get("en");
						if (enNode != null) {
							countryNameEn = enNode.textValue();
							if (!special) {
								ipMap.put("country", countryNameEn);
							}
						}
						JsonNode zhNode = namesNode.get("zh-CN");
						if (zhNode != null) {
							countryNameZh = zhNode.textValue();
						}
					}
				}
				// 城市
				String cityNameZh = null;
				JsonNode cityNode = response.get("city");
				if (cityNode != null) {
					JsonNode namesNode = cityNode.get("names");
					if (namesNode != null) {
						JsonNode enNode = namesNode.get("en");
						if (enNode != null) {
							String cityNameEn = enNode.textValue();
							ipMap.put("city", cityNameEn);
						}
						JsonNode zhNode = namesNode.get("zh-CN");
						if (zhNode != null) {
							cityNameZh = zhNode.textValue();
						}
					}
				}
				//
				JsonNode subdivisionsNode = response.get("subdivisions");
				String subdivisionsNameZh = null;
				if (subdivisionsNode != null) {
					JsonNode namesNode = subdivisionsNode.get(0).get("names");
					if (namesNode != null) {
						JsonNode enNode = namesNode.get("en");
						if (enNode != null) {
							String subdivisionsNameEn = enNode.textValue();
							ipMap.put("subdivisions", subdivisionsNameEn);
						}
						JsonNode zhNode = namesNode.get("zh-CN");
						if (zhNode != null) {
							subdivisionsNameZh = zhNode.textValue();
						}
					}
				}
				if (special) {
					ipMap.put("iso_code", "cn");
					ipMap.put("country", "China");
					ipMap.put("city", countryNameEn);
					cityNameZh = countryNameZh;
					countryNameZh = "中国";
				}
				// 中文
				Map<String, Object> zh = new HashMap<>();
				if (countryNameZh != null) {
					zh.put("country", countryNameZh);
				}
				if (cityNameZh != null) {
					zh.put("city", cityNameZh);
				}
				if (subdivisionsNameZh != null) {
					zh.put("subdivisions", subdivisionsNameZh);
				}
				ipMap.put("zh-CN", zh);
			}
		}
		return ResultModel.success(result);
	}

}
