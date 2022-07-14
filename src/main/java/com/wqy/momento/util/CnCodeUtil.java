package com.wqy.momento.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.hashids.Hashids;

import java.util.Random;

/**
 * 根据名字生成code 类似bilibili BV号
 */
@Slf4j
public class CnCodeUtil {

	private static Random rand =new Random();
	private CnCodeUtil(){}

	/**
	 * 获取密码
	 *
	 * @param chineseLanguage
	 * @return
	 */
	public static String code(String chineseLanguage) {
		final String SALT =  getPinyinString(chineseLanguage) + getRandomString(8);
		final int MIN_HASH_LENGTH = 11;
		Hashids hashids = new Hashids(SALT, MIN_HASH_LENGTH);
		return hashids.encode(347L);
	}

	/**
	 * 获取汉字全拼音
	 *
	 * @param chineseLanguage
	 * @return
	 */
	public static String getPinyinString(String chineseLanguage) {
		StringBuilder hanyupinyin = new StringBuilder();
		try {
			char[] clChars = chineseLanguage.trim().toCharArray();
			for (char clChar : clChars) {
				String str = String.valueOf(clChar);
				if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
					HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
					defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音
					defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
					defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
					hanyupinyin.append(PinyinHelper.toHanyuPinyinStringArray(clChar, defaultFormat)[0]);
				} else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
					hanyupinyin.append(clChar);
				} else if(str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
					hanyupinyin.append(clChar);
				}else {
					hanyupinyin.append("");
				}

			}
		} catch (Exception e) {
			log.error("字符不能转成汉语拼音");
		}
		return hanyupinyin.toString();
	}

	/**
	 * 生成随机字符串
	 *
	 * @param length 表示生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = rand.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		final String SALT = code("李青");
		final int MIN_HASH_LENGTH = 11;

		Hashids hashids = new Hashids(SALT, MIN_HASH_LENGTH);
		String encryptString = hashids.encode(347L);

		System.out.println(encryptString); // Y5bAyr8dLO4
	}

}