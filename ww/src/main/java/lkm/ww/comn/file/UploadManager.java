package lkm.ww.comn.file;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lkm.ww.comn.exception.FileHandlerException;
import lkm.ww.comn.util.FileUtil;
import lkm.ww.comn.util.LogUtil;
import lkm.ww.comn.util.ObjectUtil;
import lkm.ww.comn.util.StringUtil;
import lkm.ww.comn.vo.AttachVO;


/**
 * 파일업로드 매니저
 * @author lkm
 * @since 2020. 04. 02
 * @see
 * <pre>
 * UploadManager uploadManager = new UploadManager(경로, 허용확장자, 최대크기);
 * List<AttachVO> uploadList = uploadManager.upload(request);
 * </pre>
 */
public class UploadManager extends LogUtil {

	private static final long serialVersionUID = -5800267651892875042L;
	private String atPath;									// 업로드 경로
	private String extensions;								// 사용 가능 확장자
	private long maxSize;									// 업로드 허용 최대 크기
	
	public UploadManager(String atPath, String extensions, long maxSize) {
		this.atPath = atPath;
		this.extensions = extensions;
		this.maxSize = maxSize;
	}
	
	/**
	 * 파일 업로드
	 * @param HttpServletRequest
	 * @return List<AttachVO>
	 */
	public List<AttachVO> upload(MultipartHttpServletRequest request) {
		
		final MultipartHttpServletRequest mRequest = request;
		
		Collection<MultipartFile> mFileList = mRequest.getFileMap().values();
		
		List<AttachVO> uploadList = new ArrayList<AttachVO>();
		
		String userAgent = request.getHeader("User-Agent");
		
		try {
			for(MultipartFile multi : mFileList) {	
				// 파일명이 비어있는 경우
				if(StringUtil.isNullOrBlank(multi.getOriginalFilename())) continue;
				
				String atOrigNm = null;
				
				// 브라우저가 인터넷 익스플로러일 경우
				if(userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
					atOrigNm = FilenameUtils.getName(multi.getOriginalFilename());
				} else {
					atOrigNm = multi.getOriginalFilename();
				}
				
				File renameFile = new File(atPath, rename(atOrigNm));
				String atSaveNm = renameFile.getName();
				long atSz = multi.getSize();
				
				String[] arrExtends = extensions.split("\\|");
				
				debug("###############################################################################");
				debug("1. 사용가능확장자 : " + extensions);
				debug("2. 첨부 경로 : " + atPath);
				debug("3. 원본 파일명 : " + atOrigNm);
				debug("4. 저장 파일명 : " + atSaveNm);
				debug("5. 첨부 사이즈 : " + atSz);
				debug("###############################################################################");
				
				// 지원하지 않는 확장자인 경우
				if (!containsFileExtension(atOrigNm, arrExtends)) {
					// 업로드된 리스트의 파일을 삭제
					for(int i=0; i<uploadList.size(); i++) {
						String filePath = uploadList.get(i).getAtPath();
						String deleteFileNm = uploadList.get(i).getAtSaveNm();
						FileUtil.delete(new File(filePath, deleteFileNm));
					}
					
					// 업로드 리스트 클리어
					uploadList.clear();
					
					throw new FileHandlerException(
									FileHandlerException.FILE_EXTENSION_EXCEPTION_NAME,
									new Object[] { StringUtil.join(arrExtends, "|") });
				}
				
				// 최대크기를 초과한 경우
				if (maxSize < atSz) {
					// 업로드된 리스트의 파일을 삭제
					for(int i=0; i<uploadList.size(); i++) {
						String filePath = uploadList.get(i).getAtPath();
						String deleteFileNm = uploadList.get(i).getAtSaveNm();
						FileUtil.delete(new File(filePath, deleteFileNm));
					}
					
					// 업로드 리스트 클리어
					uploadList.clear();
					
					throw new FileHandlerException(
									FileHandlerException.FILE_MAX_SIZE_EXCEPTION_NAME,
									new Object[] { FileUtil.formatFileSize(maxSize) });
				}
				
				multi.transferTo(renameFile);
				
				AttachVO attachVO = new AttachVO();
				attachVO.setAtPath(atPath);
				attachVO.setAtOrigNm(atOrigNm);
				attachVO.setAtSaveNm(atSaveNm);
				attachVO.setAtSz(String.valueOf(atSz));
				uploadList.add(attachVO);
			}
		} catch (Exception e) {
			// 예외 발생 시 업로드된 리스트의 파일을 삭제
			for(int i=0; i<uploadList.size(); i++) {
				String filePath = uploadList.get(i).getAtPath();
				String deleteFileNm = uploadList.get(i).getAtSaveNm();
				FileUtil.delete(new File(filePath, deleteFileNm));
			}
			
			// 업로드 리스트 클리어
			uploadList.clear();
			
			e.printStackTrace();
		}
		return uploadList;
	}
	
	public List<AttachVO> uploadAndGenThumb(MultipartHttpServletRequest request, int width, int height) {
		
		final MultipartHttpServletRequest mRequest = request;
		
		Collection<MultipartFile> mFileList = mRequest.getFileMap().values();
		
		List<AttachVO> uploadList = new ArrayList<AttachVO>();
		
		String userAgent = request.getHeader("User-Agent");
		
		try {
			for(MultipartFile multi : mFileList) {	
				// 파일명이 비어있는 경우
				if(StringUtil.isNullOrBlank(multi.getOriginalFilename())) continue;
				
				String atOrigNm = null;
				
				// 브라우저가 인터넷 익스플로러일 경우
				if(userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
					atOrigNm = FilenameUtils.getName(multi.getOriginalFilename());
				} else {
					atOrigNm = multi.getOriginalFilename();
				}
				
				File renameFile = new File(atPath, rename(atOrigNm));
				String atSaveNm = renameFile.getName();
				long atSz = multi.getSize();
				
				String[] arrExtends = extensions.split("\\|");
				
				debug("###############################################################################");
				debug("1. 사용가능확장자 : " + extensions);
				debug("2. 첨부 경로 : " + atPath);
				debug("3. 원본 파일명 : " + atOrigNm);
				debug("4. 저장 파일명 : " + atSaveNm);
				debug("5. 저장 썸네일 : THUMB_" + atSaveNm);
				debug("6. 첨부 사이즈 : " + atSz);
				debug("###############################################################################");
				
				// 지원하지 않는 확장자인 경우
				if (!containsFileExtension(atOrigNm, arrExtends)) {
					// 업로드된 리스트의 파일을 삭제
					for(int i=0; i<uploadList.size(); i++) {
						String filePath = uploadList.get(i).getAtPath();
						String deleteFileNm = uploadList.get(i).getAtSaveNm();
						FileUtil.delete(new File(filePath, deleteFileNm));
					}
					
					// 업로드 리스트 클리어
					uploadList.clear();
					
					throw new FileHandlerException(
									FileHandlerException.FILE_EXTENSION_EXCEPTION_NAME,
									new Object[] { StringUtil.join(arrExtends, "|") });
				}
				
				// 최대크기를 초과한 경우
				if (maxSize < atSz) {
					// 업로드된 리스트의 파일을 삭제
					for(int i=0; i<uploadList.size(); i++) {
						String filePath = uploadList.get(i).getAtPath();
						String deleteFileNm = uploadList.get(i).getAtSaveNm();
						FileUtil.delete(new File(filePath, deleteFileNm));
					}
					
					// 업로드 리스트 클리어
					uploadList.clear();
					
					throw new FileHandlerException(
									FileHandlerException.FILE_MAX_SIZE_EXCEPTION_NAME,
									new Object[] { FileUtil.formatFileSize(maxSize) });
				}
				
				multi.transferTo(renameFile);
				makeThumbnail(atPath, atSaveNm, width, height);
				
				AttachVO attachVO = new AttachVO();
				attachVO.setAtPath(atPath);
				attachVO.setAtOrigNm(atOrigNm);
				attachVO.setAtSaveNm(atSaveNm);
				attachVO.setAtSz(String.valueOf(atSz));
				uploadList.add(attachVO);
			}
		} catch (Exception e) {
			// 예외 발생 시 업로드된 리스트의 파일을 삭제
			for(int i=0; i<uploadList.size(); i++) {
				String filePath = uploadList.get(i).getAtPath();
				String deleteFileNm = uploadList.get(i).getAtSaveNm();
				FileUtil.delete(new File(filePath, deleteFileNm));
			}
			
			// 업로드 리스트 클리어
			uploadList.clear();
			
			e.printStackTrace();
		}
		return uploadList;
	}
	
	private void makeThumbnail(String atPath, String atSaveNm, int width, int height) {
		
		try {
		    BufferedImage srcImg = ImageIO.read(new File(atPath + atSaveNm));

		    int dw = width;
		    int dh = height;

		    int ow = srcImg.getWidth();
		    int oh = srcImg.getHeight();

		    int pd = 0;
		    if(ow > oh) {
		    	pd = (int)(Math.abs((dh * ow / (double)dw) - oh) / 2d);
		    } else {
		    	pd = (int)(Math.abs((dw * oh / (double)dh) - ow) / 2d);
		    }
		    srcImg = Scalr.pad(srcImg, pd, Color.WHITE, Scalr.OP_ANTIALIAS);
			
		    ow = srcImg.getWidth();
		    oh = srcImg.getHeight();

		    int nw = ow;
		    int nh = (ow * dh) / dw;
		    if(nh > oh) {
				nw = (oh * dw) / dh;
				nh = oh;
		    }
			
		    BufferedImage cropImg = Scalr.crop(srcImg, (ow-nw)/2, (oh-nh)/2, nw, nh);
			
		    BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
			
		    String thumbName = atPath + "THUMB_" + atSaveNm;
		    File thumbFile = new File(thumbName);
		    ImageIO.write(destImg, FileUtil.extractFileExtension(atSaveNm), thumbFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 리네임 (UUID)
	 * @param String
	 * @return String
	 */
	public String rename(String origFileNm) {
		String extension = FileUtil.extractFileExtension(origFileNm);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
		return uuid;
	}
	
	/**
	 * 허용확장자 체크
	 * @param String, String[]
	 * @return boolean
	 */
	private boolean containsFileExtension(String filename, String[] extensions) {
		if (ObjectUtil.isEmpty(extensions)) {
			return true;
		}
		boolean result = false;
		String fileExtension = FileUtil.extractFileExtension(filename);
		for (String extension : extensions) {
			if (extension.equals(fileExtension)) {
				result = true;
				break;
			}
		}
		return result;
	}
}