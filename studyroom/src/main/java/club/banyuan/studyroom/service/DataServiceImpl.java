//package club.banyuan.studyroom.service;
//
////import club.banyuan.blog.consts.WeatherCityCode;
////import club.banyuan.blog.dto.Weather;
////import club.banyuan.blog.utils.HttpUtils;
//import club.banyuan.studyroom.consts.WeatherCityCode;
//import club.banyuan.studyroom.dto.Weather;
//import club.banyuan.studyroom.utils.HttpUtils;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class DataServiceImpl implements DataService {
//
//    public Weather getWeather(String cityName) {
//        Weather weather = new Weather();
//        try {
//            String cityCode = WeatherCityCode.cities.get(cityName);
//
//            String url = "http://www.weather.com.cn/data/cityinfo/" + cityCode + ".html";
//            String json = HttpUtils.get(url);
//            JSONObject obj = JSONUtil.parseObj(json);
//            JSONObject weatherinfo = (JSONObject) obj.get("weatherinfo");
//            weather.setCityName((String) weatherinfo.get("city"));
//            weather.setMinTemp((String) weatherinfo.get("temp1"));
//            weather.setMaxTemp((String) weatherinfo.get("temp2"));
//            weather.setWeather((String) weatherinfo.get("weather"));
//        } catch (IOException e) {
//            // TODO log
//        }
//        return weather;
//    }
//}