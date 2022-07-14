package com.wqy.momento.util;


import org.apache.commons.lang3.StringUtils;

public final class DesensitizationUtil {
    private DesensitizationUtil() {
    }

    public static String chineseName(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        } else {
            String name = StringUtils.left(fullName, 1);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
    }

    public static String chineseName(String familyName, String givenName) {
        if (!StringUtils.isBlank(familyName) && !StringUtils.isBlank(givenName)) {
            if (familyName.length() > 1) {
                String name = StringUtils.left(familyName, familyName.length());
                return StringUtils.rightPad(name, StringUtils.length(familyName + givenName), "*");
            } else {
                return chineseName(familyName + givenName);
            }
        } else {
            return "";
        }
    }

    public static String address(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        } else {
            int length = StringUtils.length(address);
            return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
        }
    }

    public static String mobileEncrypt(String num, int index, int end) {
        return StringUtils.isBlank(num) ? "" : StringUtils.left(num, index).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, end), StringUtils.length(num), "*"), "***"));
    }

    public static String email(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        } else {
            int index = StringUtils.indexOf(email, "@");
            return index <= 1 ? email : StringUtils.rightPad(StringUtils.left(email, 0), 6, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }

    public static String hideLeftNum(String cardNum, int hideDigit) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        } else {
            int length = StringUtils.length(cardNum);
            return StringUtils.leftPad(StringUtils.right(cardNum, length - hideDigit), length, "*");
        }
    }

    public static String hideRightNum(String cardNum, int hideDigit) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        } else {
            int length = StringUtils.length(cardNum);
            return StringUtils.rightPad(StringUtils.left(cardNum, length - hideDigit), length, "*");
        }
    }

    public static String hideCenterNum(String cardNum, int hideDigit) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        } else {
            int length = StringUtils.length(cardNum);
            int index = length - hideDigit >> 1;
            if (hideDigit % 2 == 0) {
                ++index;
            }

            return StringUtils.left(cardNum, index).concat(StringUtils.leftPad(StringUtils.right(cardNum, length - index - hideDigit), length - index, "*"));
        }
    }

    public static String hideAllNum(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        } else {
            int length = StringUtils.length(cardNum);
            return StringUtils.leftPad(StringUtils.right(cardNum, 0), length, "*");
        }
    }
}