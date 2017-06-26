package easy.work.source.module.login.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import easy.work.source.commom.context.SessionContext;
import easy.work.source.commom.encrypt.RSAUtils;
import easy.work.source.module.user.pojo.BackUserPojo;

@Controller
@RequestMapping("/login")
@Scope("prototype")
public class LoginController {

	/**
	 * 验证码的内容
	 */
	private static final String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z", "2", "3", "4", "5", "6", "7", "8", "9" };
	/**
	 * 验证码的宽度
	 */
	private static final int width = 70;
	/**
	 * 验证码的高度
	 */
	private static final int height = 25;
	/**
	 * 随机数
	 */
	private static final Random random = new Random();

	@RequestMapping(value = "/toLogin")
	public ModelAndView login(BackUserPojo pojo) throws Exception {
		ModelAndView mv = new ModelAndView();
		String account = RSAUtils.decryptByPrivateKey(pojo.getUserAccount());
		String pwd = RSAUtils.decryptByPrivateKey(pojo.getUserPwd());
		System.out.println("账号:" + account + "\n" + "密码:" + pwd);
		mv.setViewName("redirect:main.action");
		return mv;
	}

	@RequestMapping(value = "/checkCode")
	public void createCheckCode(HttpServletRequest request, HttpServletResponse response) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.getHSBColor(random.nextFloat(), random.nextFloat(), random.nextFloat()));
		g.setFont(new Font("宋体", Font.PLAIN, 18));

		StringBuilder sRand = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			String rand = letters[random.nextInt(letters.length)];
			sRand.append(rand);
			g.drawString(rand, 14 * i + 8, 20);
		}
		g.dispose();
		System.out.println(sRand.toString());
//		// 验证码保存到session中
//		SessionContext.setCheckCode(sRand.toString().toLowerCase());
		try {
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
