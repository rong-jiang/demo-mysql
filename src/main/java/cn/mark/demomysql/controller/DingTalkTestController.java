package cn.mark.demomysql.controller;

import cn.mark.demomysql.ulit.HttpUtils;
import com.alibaba.fastjson.JSON;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.google.common.collect.Lists;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableScheduling
public class DingTalkTestController {
    @Scheduled(cron = "*/10 * * * * ?")
    public static void DingTalkTest() {
        try {
            //业务实现
            String dateTime = "2021-07-29 11:02:12";
            boolean b = dataTime(dateTime);
            if (b) {
                //钉钉采用的是HmacSHA256加密，secret 密钥是机器人安全设置页面加签
                String sign=appendSign();
                //钉钉机器人地址(配置机器人的webhook)
                String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=c0ed90c6abe5643a81b7cf51f861e39742717db7af71e205955fd051240a3460";

                String url=dingUrl+sign;
                //是否通知所有人
                boolean isAtAll = false;

                //通知具体人的手机号码列表
                List mobileList = Lists.newArrayList();
                mobileList.add("17680943126");

                //钉钉机器人消息内容
                String content ="大帅哥，你好！";

                //msgtype类型为link
                /*OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
                link.setMessageUrl("https://www.dingtalk.com/");
                link.setPicUrl("");
                link.setTitle("时代的火车向前开");
                link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
                        "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林”？");
*/

                //msgtype类型为markdown
                OapiRobotSendRequest.Markdown markdown= new OapiRobotSendRequest.Markdown();
                markdown.setTitle("杭州天气");
                markdown.setText("#### 杭州天气 @17680943126\n"+
                        "                 > 9度，西北风1级，空气良89，相对温度73%\n\n"+
                        "                 > ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
                        "                 > ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");

                //组装请求内容
                String reqStr = buildReqStr(content, isAtAll, mobileList,markdown);

                System.out.println("组装请求内容:" + reqStr);

                //推送消息(http请求)
                String result = HttpUtils.postJson(url, reqStr);

                System.out.println("result == " + result);
            } else {
                log.info("没到时间，未发送");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 组装请求报文
     *
     * @param content
     * @return
     */

    private static String buildReqStr(String content, boolean isAtAll, List mobileList,OapiRobotSendRequest.Markdown markdown) {
        //消息内容
        Map contentMap = Maps.newHashMap();

//        contentMap.put("content", content);

        //通知人
        Map atMap = Maps.newHashMap();

        //1.是否通知所有人
        atMap.put("isAtAll", isAtAll);

        //2.通知具体人的手机号码列表
        atMap.put("atMobiles", mobileList);

        //
        Map reqMap = Maps.newHashMap();

        reqMap.put("msgtype", "markdown");
        reqMap.put("markdown", markdown);
        reqMap.put("at", atMap);

        return JSON.toJSONString(reqMap);

    }

    /***
     * 钉钉采用的是HmacSHA256加密，secret 密钥是机器人安全设置页面加签
     * @return
     */
    private static String appendSign(){
        try{
            Long timestamp = System.currentTimeMillis();
            String secret="SECf94054dd2ff227f41bef4fb8a1dd9f567a9ea4e8aaea546d1893a29e639384b1";
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign= URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            return "&timestamp="+timestamp+"&sign="+sign;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    //判断当前时间是否在指定时间的两小时前中
    private static boolean dataTime(String dateTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String StrTime="2021-07-29 11:02:12";
        String StrTime = dateTime;
        Date dateTimeTo = simpleDateFormat1.parse(StrTime);
        String time1 = simpleDateFormat.format(new Date());
        String time2 = simpleDateFormat.format(dateTimeTo);
        System.out.println(time1);
        System.out.println(time2);
        //判断同一天
        if (time1.equals(time2)) {
            //时分秒
            SimpleDateFormat dateFormater = new SimpleDateFormat("HHmm");
            String date1 = dateFormater.format(new Date());
            String date2 = dateFormater.format(dateTimeTo);
            System.out.println("date1:============" + date1);
            System.out.println("date2:============" + date2);
            int timeD1 = Integer.parseInt(date1);
            int timeD2 = Integer.parseInt(date2);
            System.out.println("timeD1:====" + timeD1);
            System.out.println("timeD2:====" + timeD2);
            int start = timeD2 - timeD1;
            System.out.println("start:=================" + start);
            //时分秒判断是否在时间内
            if (timeD1 > start && timeD1 < timeD2) {
                System.out.println("在时间段内");
                return true;
            } else {
                System.out.println("不在这个时间段内");
                return false;
            }
        } else {
            System.out.println("不是同一天");
            return false;
        }
    }

}
