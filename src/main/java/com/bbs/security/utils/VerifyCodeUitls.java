package com.bbs.security.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @className: VerifyCodeUitls
 * @author: q-linyu
 * @description: 图片验证码工具类
 * @date: 2020/02/16 16:55
 * @version: 1.0
 */
public class VerifyCodeUitls {

    /**
     * 设置宽度
     */
    private static final Integer WITDH = 200;

    /**
     * 设置高度
     */
    private static final Integer HEIGHT = 69;

    /**
     * 数字和字母的组合
     */
    private static final String NUM_LETTER = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 生成验证码
     * @author q-linyu
     * @date 2020/2/16 0016 17:19
     * @param request
     * @param response
     * @return void
     */
    public static void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        try {
            // 旋转原点的 x 坐标
            int x = 10;

            // 1.创建一张图片,并且长度为4
            BufferedImage image = new BufferedImage(WITDH,HEIGHT,BufferedImage.TYPE_INT_BGR);

            // 2.绘图
            Graphics2D graphics2D = (Graphics2D) image.getGraphics();
            // 2-1.设置背景颜色
            graphics2D.setColor(Color.WHITE);
            // 2-2.填充背景
            graphics2D.fillRect(0,0,WITDH,HEIGHT);
            // 2-3.设置字体
            graphics2D.setFont(new Font("微软雅黑",Font.BOLD,40));

            StringBuffer sb = new StringBuffer();

            String ch = null;

            // 3.生成随机数
            Random random = new Random();
            for (int i = 0;i < 4;i++){
                graphics2D.setColor(getRandomColor());
                // 设置字体旋转角度
                int degree = random.nextInt() % 30;
                int dot = random.nextInt(NUM_LETTER.length());
                ch = NUM_LETTER.charAt(dot) + "";
                sb.append(ch);

                // 正向旋转
                graphics2D.rotate(degree * Math.PI / 180,x,45);
                graphics2D.drawString(ch,x,45);

                // 反向旋转
                graphics2D.rotate(-degree * Math.PI / 180,x,45);

                x += 48;
            }

            // 4.画干扰性
            for (int i = 0; i < 30;i++){
                int w = random.nextInt(WITDH);
                int h = random.nextInt(HEIGHT);

                graphics2D.setColor(getRandomColor());
                graphics2D.fillRect(w,h,2,2);
            }

            // 5.生成随机数
            String randomText = sb.toString();
            // 存储到Session中
            request.getSession().setAttribute("key",randomText);

            // 6.生成
            request.getSession().setAttribute("verifyCode",randomText);
            response.setContentType("image/png");
            //获取文件输出流
            OutputStream os = response.getOutputStream();
            //输出图片流
            ImageIO.write(image,"png",os);
            // 缓存
            os.flush();
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 随机取色
     * @return
     */
    private static Color getRandomColor(){
        Random random = new Random();
        return new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
    }


}
