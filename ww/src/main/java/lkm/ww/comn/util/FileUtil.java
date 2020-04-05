package lkm.ww.comn.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

/**
 * 파일유틸
 * @author lkm
 * @since 2020. 04. 02
 */
public class FileUtil {

	private static final double SPACE_KB = 1024;
	private static final double SPACE_MB = 1024 * SPACE_KB;
	private static final double SPACE_GB = 1024 * SPACE_MB;
	private static final double SPACE_TB = 1024 * SPACE_GB;

	private static final Pattern PATTERN_PERCENTS_PATH = Pattern.compile("(%)([0-9a-fA-F])([0-9a-fA-F])");
	private static final Pattern PATTERN_FILE_BLACKLIST = Pattern.compile("([*?<>|])|(\\.\\.\\/)|(\\.\\.\\\\)");

	/** line.separator 시스템 프로퍼티 **/
	public static final String LINE_SEPARATOR = StringUtil.nvl(System.getProperty("line.separator"), "\r\n");

	/**
	 * 원본 파일에 대한 복사본 생성.
	 * @param from	원본파일객체
	 * @param to	복사파일객체
	 * @return		성공여부
	 */
	public static boolean copy(File from, File to) {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(from);
			output = new FileOutputStream(to);

			IOUtils.copyLarge(input, output);
			output.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
		return false;
	}

	/**
	 * 원본 파일에 대한 복사본 생성.
	 * @param from	원본파일객체
	 * @param to	복사파일경로
	 * @return		성공여부
	 */
	public static boolean copy(File from, String to) {
		return copy(from, new File(to));
	}

	/**
	 * 원본 파일에 대한 복사본 생성.
	 * @param from	원본파일경로
	 * @param to	복사파일객체
	 * @return		성공여부
	 */
	public static boolean copy(String from, File to) {
		return copy(new File(from), to);
	}

	/**
	 * 원본 파일에 대한 복사본 생성.
	 * @param from	원본파일경로
	 * @param to	복사파일경로
	 * @return		성공여부
	 */
	public static boolean copy(String from, String to) {
		return copy(new File(from), new File(to));
	}

	/**
	 * 파일삭제.
	 * @param file	삭제 파일객체
	 * @return 성공여부
	 */
	public static boolean delete(File file) {
		return file.delete();
	}

	/**
	 * 파일삭제.
	 * @param pathname	삭제 파일경로
	 * @return 성공여부
	 */
	public static boolean delete(String pathname) {
		return delete(new File(pathname));
	}

	/**
	 * 파일확장자명 추출.
	 * @param file	파일객체
	 * @return 파일확장자
	 */
	public static String extractFileExtension(File file) {
		return extractFileExtension(file.getName());
	}

	/**
	 * 파일확장자명 추출.
	 * @param pathname	파일경로
	 * @return 파일확장자
	 */
	public static String extractFileExtension(String pathname) {
		String name = extractFileName(pathname);
		int idx = name.lastIndexOf('.');
		if (idx > 0) {
			return name.substring(idx + 1);
		}
		return StringUtil.EMPTY;
	}

	/**
	 * 파일명 추출.
	 * @param pathname	파일경로
	 * @return 파일명
	 */
	public static String extractFileName(String pathname) {
		String fpath = replaceFileSeparator(pathname);
		return fpath.substring(fpath.lastIndexOf(File.separatorChar) + 1);
	}

	/**
	 * 파일사이즈(바이트) 문자열화.
	 * <pre>
	 * 786 Bytes = 786 Byte(s)
	 * 456321 Bytes = 445.63 KB
	 * 896789489 Bytes = 855.25 MB
	 * 989678948985 Bytes = 921.71 GB
	 * 1698296768946289482 Bytes = 1,544,591.91 TB
	 * </pre>
	 * @param file 파일객체
	 * @return 변환값
	 */
	public static String formatFileSize(File file) {
		if (!file.isFile()) {
			throw new IllegalArgumentException("Invalid file: [" + file.getPath() + "]");
		}
		return formatFileSize(file.length());
	}

	/**
	 * 파일사이즈(바이트) 문자열화.
	 * <pre>
	 * 786 Bytes = 786 Byte(s)
	 * 456321 Bytes = 445.63 KB
	 * 896789489 Bytes = 855.25 MB
	 * 989678948985 Bytes = 921.71 GB
	 * 1698296768946289482 Bytes = 1,544,591.91 TB
	 * </pre>
	 * @param fileLength 원본값
	 * @return 변환값
	 */
	public static String formatFileSize(long fileLength) {
		NumberFormat nf = new DecimalFormat();
		nf.setMaximumFractionDigits(2);
		try {
			if (fileLength < SPACE_KB) {
				return nf.format(fileLength) + " Byte(s)";
			} else if (fileLength < SPACE_MB) {
				return nf.format(fileLength / SPACE_KB) + " KB";
			} else if (fileLength < SPACE_GB) {
				return nf.format(fileLength / SPACE_MB) + " MB";
			} else if (fileLength < SPACE_TB) {
				return nf.format(fileLength / SPACE_GB) + " GB";
			} else {
				return nf.format(fileLength / SPACE_TB) + " TB";
			}
		} catch (Throwable t) {
			return fileLength + " Byte(s)";
		}
	}

	/**
	 * 디렉토리 생성.
	 * @param file	파일객체
	 * @return 성공여부
	 */
	public static boolean mkdir(File file) {
		if (!file.exists()) {
			return file.mkdirs();
		}
		return true;
	}

	/**
	 * 디렉토리 생성.
	 * @param pathname	생성 디렉토리경로
	 * @return 성공여부
	 */
	public static boolean mkdir(String pathname) {
		return mkdir(new File(pathname));
	}

	/**
	 * 파일 내용을 스트링으로 반환.
	 * @param file	파일객체
	 * @return 파일내용
	 */
	public static String read(File file) {
		return read(file, Charset.defaultCharset());
	}

	/**
	 * 파일 내용을 스트링으로 반환.
	 * @param file			파일객체
	 * @param charset		캐릭터세트
	 * @return 파일내용
	 */
	public static String read(File file, Charset charset) {
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			return IOUtils.toString(input, charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
		}
		return null;
	}

	/**
	 * 파일 내용을 스트링으로 반환.
	 * @param file		파일객체
	 * @param charset	캐릭터세트
	 * @return 파일내용
	 */
	public static String read(File file, String charset) {
		return read(file, Charsets.toCharset(charset));
	}

	/**
	 * 파일 내용을 스트링으로 반환.
	 * @param pathname	파일경로
	 * @return 파일내용
	 */
	public static String read(String pathname) {
		return read(new File(pathname), Charset.defaultCharset());
	}

	/**
	 * 파일 내용을 스트링으로 반환.
	 * @param pathname		파일경로
	 * @param charset		캐릭터세트
	 * @return 파일내용
	 */
	public static String read(String pathname, Charset charset) {
		return read(new File(pathname), charset);
	}

	/**
	 * 파일 내용을 스트링으로 반환.
	 * @param pathname	파일경로
	 * @param charset	캐릭터세트
	 * @return 파일내용
	 */
	public static String read(String pathname, String charset) {
		return read(new File(pathname), Charsets.toCharset(charset));
	}

	/**
	 * 파일 상대경로(../,..\)를 제거.
	 * @param pathname	파일경로
	 * @return 변환문자열
	 */
	public static String removeFileRelativePath(String pathname) {
		if (pathname.indexOf('%') > -1) {
			pathname = pathname
						.replaceAll("(?i)\\%2F", "\\" + File.separatorChar)
						.replaceAll("(?i)\\%5C", "\\" + File.separatorChar);
		}
		return pathname
				.replaceAll("\\.\\.\\/", StringUtil.EMPTY)
				.replaceAll("\\.\\.\\\\", StringUtil.EMPTY);
	}

	/**
	 * 파일명 변경.
	 * @param from	원본파일객체
	 * @param to	변경파일객체
	 */
	public static boolean rename(File from, File to) {
		return from.renameTo(to);
	}

	/**
	 * 파일명 변경.
	 * @param from	원본파일객체
	 * @param to	변경파일경로
	 */
	public static boolean rename(File from, String to) {
		return rename(from, new File(to));
	}

	/**
	 * 파일명 변경.
	 * @param from	원본파일경로
	 * @param to	변경파일객체
	 */
	public static boolean rename(String from, File to) {
		return rename(new File(from), to);
	}

	/**
	 * 파일명 변경.
	 * @param from	원본파일경로
	 * @param to	변경파일경로
	 */
	public static boolean rename(String from, String to) {
		return rename(new File(from), new File(to));
	}

	/**
	 * 파일 Separator(\,/) 를 시스템 값으로 변경.
	 * @param pathname	파일경로
	 * @return 변환문자열
	 */
	public static String replaceFileSeparator(String pathname) {
		return pathname
				.replace('\\', File.separatorChar)
				.replace('/', File.separatorChar);
	}

	/**
	 * 디렉토리 삭제, 하위디렉토리 및 파일삭제.
	 * @param file	파일객체
	 * @return 성공여부
	 */
	public static boolean rmdir(File file) {
		if (file.exists()) {
			for (File f : file.listFiles()) {
				if (f.isDirectory()) {
					rmdir(f);
				} else {
					f.delete();
				}
			}
		}
		return file.delete();
	}

	/**
	 * 디렉토리 삭제, 하위디렉토리 및 파일삭제.
	 * @param pathname	삭제 디렉토리경로
	 * @return 성공여부
	 */
	public static boolean rmdir(String pathname) {
		return rmdir(new File(pathname));
	}

	/**
	 * Object를 파일로 저장.
	 * <p>
	 * InputStream, byte[], Object(toString)
	 * </p>
	 * @param in	원본객체
	 * @param file	저장파일객체
	 * @return		성공여부
	 */
	public static boolean save(Object in, File file) {
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
			if (in instanceof InputStream) {
				InputStream input = null;
				try {
					input = (InputStream) in;
					IOUtils.copyLarge(input, output);
					output.flush();
					return true;
				} finally {
					IOUtils.closeQuietly(input);
				}
			} else if (in instanceof byte[]) {
				output.write((byte[]) in);
			} else {
				output.write(in.toString().getBytes());
			}
			output.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(output);
		}
		return false;
	}

	/**
	 * Object를 파일로 저장.
	 * <p>
	 * InputStream, byte[], Object(toString)
	 * </p>
	 * @param in		원본객체
	 * @param pathname	파일저장경로
	 * @return			성공여부
	 */
	public static boolean save(Object in, String pathname) {
		return save(in, new File(pathname));
	}

	/**
	 * Object를 파일로 저장.
	 * <p>
	 * InputStream, byte[], Object(toString)
	 * </p>
	 * @param in			원본객체
	 * @param file			저장파일객체
	 * @param charset		캐릭터세트
	 * @return				성공여부
	 */
	public static boolean save(Object in, File file, Charset charset) {
		if (charset == null) {
			return save(in, file);
		}
		Reader reader = null;
		Writer writer = null;
		try {
			if (in instanceof InputStream) {
				reader = new BufferedReader(new InputStreamReader((InputStream) in, charset));
			} else if (in instanceof byte[]) {
				reader = new BufferedReader(new StringReader(new String((byte[]) in, charset)));
			} else {
				reader = new BufferedReader(new StringReader(in.toString()));
			}
			writer = new OutputStreamWriter(new FileOutputStream(file), charset);

			IOUtils.copyLarge(reader, writer);
			writer.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(writer);
		}
		return false;
	}

	/**
	 * Object를 파일로 저장.
	 * <p>
	 * InputStream, byte[], Object(toString)
	 * </p>
	 * @param in		원본객체
	 * @param file		저장파일객체
	 * @param charset	캐릭터세트
	 * @return			성공여부
	 */
	public static boolean save(Object in, File file, String charset) {
		return save(in, file, Charsets.toCharset(charset));
	}

	/**
	 * Object를 파일로 저장.
	 * <p>
	 * InputStream, byte[], Object(toString)
	 * </p>
	 * @param in			원본객체
	 * @param pathname		파일저장경로
	 * @param charset		캐릭터세트
	 * @return				성공여부
	 */
	public static boolean save(Object in, String pathname, Charset charset) {
		return save(in, new File(pathname), charset);
	}

	/**
	 * Object를 파일로 저장.
	 * <p>
	 * InputStream, byte[], Object(toString)
	 * </p>
	 * @param in		원본객체
	 * @param pathname	파일저장경로
	 * @param charset	캐릭터세트
	 * @return			성공여부
	 */
	public static boolean save(Object in, String pathname, String charset) {
		return save(in, new File(pathname), Charsets.toCharset(charset));
	}

	/**
	 * 파일(명) 경로에 사용할 수 없는 문자나 상대경로가 포함되었을 경우 예외발생.
	 * @param file 파일객체
	 */
	public static void throwIfUnSafeFile(File file) {
		if (file.isFile()) {
			throwIfUnSafeFile(file.getPath());
		} else {
			throwIfUnSafeFile(file.getPath() + File.separatorChar);
		}
	}

	/**
	 * 파일(명) 경로에 사용할 수 없는 문자나 상대경로가 포함되었을 경우 예외발생.
	 * @param pathname 파일경로
	 */
	public static void throwIfUnSafeFile(String pathname) {
		for (int ii = 0; ii < pathname.length(); ++ii) {
			int ch = pathname.charAt(ii);
			if (ch  < 33) {
				throw new IllegalArgumentException(
						"Invalid pathname: File path (" + pathname + ") contains unprintable character: " + ch);
			}
		}
		pathname = replaceFileSeparator(pathname);
		Matcher m1 = PATTERN_FILE_BLACKLIST.matcher(pathname);
		if (m1.find()) {
			throw new IllegalArgumentException("Invalid file: File path (" + pathname + ") contains illegal character: " + m1.group());
		}
		Matcher m2 = PATTERN_PERCENTS_PATH.matcher(pathname);
		if (m2.find()) {
			throw new IllegalArgumentException("Invalid file: File path (" + pathname + ") contains encoded characters: " + m2.group());
		}
		String fileName = pathname.substring(pathname.lastIndexOf(File.separatorChar) + 1);
		if (fileName.indexOf(':') > -1) {
			throw new IllegalArgumentException("Invalid file: File path (" + pathname + ") contains illegal character: conlon");
		}
	}

	/**
	 * 문자 배열을 파일 배열로 반환.
	 * @param pathnames 파일경로
	 * @return 파일 배열
	 */
	public static File[] toFile(String[] pathnames) {
		if (ObjectUtil.isEmpty(pathnames)) {
			return new File[0];
		}
		File[] temp = new File[pathnames.length];
		for (int ii = 0; ii < temp.length; ++ii) {
			temp[ii] = new File(pathnames[ii]);
		}
		return temp;
	}

	/**
	 * 파일경로(들)을 파일 구분자로 합쳐서 반환.
	 * @param pathnames 파일경로(들)
	 * @return 파일경로
	 */
	public static String appendPathname(String... pathnames) {
		return appendPathname(File.separatorChar, pathnames);
	}

	/**
	 * 파일경로(들)을 파일 구분자로 합쳐서 반환.
	 * @param separatorChar 파일구분자
	 * @param pathnames 파일경로
	 * @return 파일경로
	 */
	public static String appendPathname(char separatorChar, String... pathnames) {
		StringBuilder buff = new StringBuilder();
		int lengthSize = pathnames.length;
		int indexSize = lengthSize - 1;
		for (int ii = 0; ii < lengthSize; ++ii) {
			String pathname = pathnames[ii];
			if (ii != 0) {
				char firstChar = pathname.charAt(0);
				if (firstChar == '/' || firstChar == '\\') {
					pathname = pathname.substring(1);
				}
			}
			buff.append(pathname);
			if (ii != indexSize) {
				char lastChar = pathname.charAt(pathname.length() - 1);
				if (!(lastChar == '/' || lastChar == '\\')) {
					buff.append(separatorChar);
				}
			}
		}
		return buff.toString();
	}

	/**
	 * 파일 배열을 문자열 배열로 반환.
	 * @param files 파일객체
	 * @return 파일 경로 문자열 배열
	 */
	public static String[] toString(File[] files) {
		if (ObjectUtil.isEmpty(files)) {
			return new String[0];
		}
		String[] temp = new String[files.length];
		for (int ii = 0; ii < temp.length; ++ii) {
			temp[ii] = files[ii].getPath();
		}
		return temp;
	}

	/**
	 * 파일 존재시 파일의 최종수정일 변경, 그렇지 않을시 빈파일 생성.
	 * @param file	파일객체
	 * @return		성공여부
	 */
	public static boolean touch(File file) {
		OutputStream out = null;
		try {
			if (file.exists()) {
				if (!file.setLastModified(System.currentTimeMillis())) {
					try {
						Runtime run = Runtime.getRuntime();
						run.exec("touch " + file.getCanonicalPath());
					} catch (Exception ignore) {

					}
				}
			} else {
				out = new FileOutputStream(file);
				out.flush();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
		}
		return false;
	}

	/**
	 * 파일 존재시 파일의 최종수정일 변경, 그렇지 않을시 빈파일 생성.
	 * @param pathname	파일경로
	 * @return			성공여부
	 */
	public static boolean touch(String pathname) {
		return touch(new File(pathname));
	}
}