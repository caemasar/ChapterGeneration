package idv.caemasar.chapterGeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ChapterGeneration {

	static String CHN_NUMBER[] = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	static String CHN_UNIT[] = { "", "十", "百", "千" }; // 权位
	static String CHN_UNIT_SECTION[] = { "", "万", "亿", "万亿" }; // 节权位

	public static void main(String[] args) throws FileNotFoundException {

		String tamp = new String();
		String prefix = new String();
		String suffix = new String();
		System.out.print("----输入初始数：-----\n<======");
		Scanner scanner = new Scanner(System.in);
		int min = scanner.nextInt();
		System.out.print("----输入结束数：-----\n<======");
		int max = scanner.nextInt();
		tamp = scanner.nextLine();
		System.out.print("----输入前缀：-----\n<======");
		tamp = scanner.nextLine();
		if ("".equals(tamp)) {
			prefix = "第";
		} else {
			prefix = tamp;
		}
		tamp = "";
		System.out.print("----输入后缀：-----\n<======");
		tamp = scanner.nextLine();
		if ("".equals(tamp)) {
			suffix = "章";
		} else {
			suffix = tamp;
		}
		System.out.print("----请选择输出方式：\n----1.输出到控制台-----\n----2.输出到文件D:\\ChapterGeneration.txt-----\n<======");
		int choose = scanner.nextInt();
		System.out.println("----------开始----------\n");
		if (choose == 1) {
			for (int i = min; i <= max; i++) {
				System.out.println(prefix + number2CN(i) + suffix + " \n\n");
			}
		} else {
			FileOutputStream fs = new FileOutputStream(new File("D:\\ChapterGeneration.txt"));
			PrintStream p = new PrintStream(fs);

			for (int i = min; i <= max; i++) {
				// for (int i = 1; i <= 1000000; i++) {
				p.println(prefix + number2CN(i) + suffix + " \n\n");
			}
			p.close();
		}
		System.out.println("----------结束----------");
		// System.out.println(toChineseNumerals(192342).charAt(5));
		// System.out.println(toChineseNumerals(192342));
		// System.out.println(initCHN_UNITOfDigits(countNumberOfDigits(192342)));
		// System.out.println(toChineseNumerals(192342).reverse());
		// System.out.println(initCHN_UNITOfDigits(countNumberOfDigits(192342)).reverse());

		// System.out.println(number2CN(190342));
		// System.out.println(number2CN(192342));
		// System.out.println(number2CN(109));
		// System.out.println(number2CN(19));

		// System.out.println(initCHN_UNITOfDigits(countNumberOfDigits(10)));
		// System.out.println(initCHN_UNITOfDigits(countNumberOfDigits(100)));
		// System.out.println(initCHN_UNITOfDigits(countNumberOfDigits(1000)));
		// System.out.println(initCHN_UNITOfDigits(countNumberOfDigits(10000)));
		// System.out.println(initCHN_UNITOfDigits(countNumberOfDigits(100000)));
	}

	private static String number2CN(int i) {
		StringBuffer numberCHN = toChineseNumerals(i).reverse();
		StringBuffer unitCHN = initCHN_UNITOfDigits(countNumberOfDigits(i)).reverse();
		StringBuffer result = new StringBuffer();
		for (int j = 0; j < numberCHN.length(); j++) {
			switch (j) {
			case 0:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					result.append(numberCHN.substring(j, j + 1));
				break;
			case 1:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					if (CHN_NUMBER[1].equals(numberCHN.substring(j, j + 1))) {
						result.insert(0, unitCHN.substring(j - 1, j));
					} else {
						result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
					}
				break;
			case 2:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					if (CHN_NUMBER[1].equals(numberCHN.substring(j - 1, j))) {
						result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j)
								+ numberCHN.substring(j - 1, j));
					} else if (CHN_NUMBER[0].equals(numberCHN.substring(j - 1, j))) {
						if (CHN_NUMBER[0].equals(numberCHN.substring(j - 2, j - 1))) {
							result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
						} else {
							result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j)
									+ numberCHN.substring(j - 1, j));
						}
					} else {
						result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
					}
				break;
			case 3:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
				break;
			case 4:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
				break;
			case 5:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					if (CHN_NUMBER[1].equals(numberCHN.substring(j, j + 1))) {
						result.insert(0, unitCHN.substring(j - 1, j));
					} else {
						result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
					}

				break;
			case 6:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
				break;
			case 7:
				if (!CHN_NUMBER[0].equals(numberCHN.substring(j, j + 1)))
					result.insert(0, numberCHN.substring(j, j + 1) + unitCHN.substring(j - 1, j));
				break;
			case 8:
				break;
			default:
				break;
			}
		}
		return result.toString();
	}

	private static StringBuffer toChineseNumerals(int i) {
		String str = String.valueOf(i);
		int cell;
		StringBuffer numberCHN = new StringBuffer();
		for (char c : str.toCharArray()) {
			cell = Character.getNumericValue(c);
			numberCHN.append(CHN_NUMBER[cell]);
		}
		return numberCHN;
	}

	public static int countNumberOfDigits(int i) {
		String str = String.valueOf(i);
		return str.length();
	}

	public static StringBuffer initCHN_UNITOfDigits(int len) {
		String str = new String();
		switch (len) {
		case 0:
			str = CHN_UNIT[0];
			break;
		case 1:
			str = CHN_UNIT[0];
			break;
		case 2:
			str = CHN_UNIT[1];
			break;
		case 3:
			str = CHN_UNIT[2] + CHN_UNIT[1];
			break;
		case 4:
			str = CHN_UNIT[3] + CHN_UNIT[2] + CHN_UNIT[1];
			break;
		case 5:
			str = CHN_UNIT_SECTION[1] + CHN_UNIT[3] + CHN_UNIT[2] + CHN_UNIT[1];
			break;
		case 6:
			str = CHN_UNIT[1] + CHN_UNIT_SECTION[1] + CHN_UNIT[3] + CHN_UNIT[2] + CHN_UNIT[1];
			break;
		case 7:
			str = CHN_UNIT[2] + CHN_UNIT[1] + CHN_UNIT_SECTION[1] + CHN_UNIT[3] + CHN_UNIT[2] + CHN_UNIT[1];
			break;
		case 8:
			str = CHN_UNIT[3] + CHN_UNIT[2] + CHN_UNIT[1] + CHN_UNIT_SECTION[1] + CHN_UNIT[3] + CHN_UNIT[2]
					+ CHN_UNIT[1];
			break;
		case 9:
			str = "九";
			break;
		default:
			str = null;
			break;
		}
		return new StringBuffer(str);
	}
}
