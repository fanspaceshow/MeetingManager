package org.zframework.web.multipart.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.zframework.core.web.listener.mProgressListener;

public class CustomMultipartResolver extends CommonsMultipartResolver {
	private HttpServletRequest request;
	private HttpSession session;
	private String sessionid;
	@Override
	protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
		
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		upload.setSizeMax(-1);
		if (request != null) {
			mProgressListener pListener = new mProgressListener();
			upload.setProgressListener(pListener);
			session = request.getSession();
			sessionid = session.getId();
			System.out.println("CustomMultipartResolver : "+sessionid);
			session.setAttribute("pListener", pListener);
		}
		System.gc();
		return upload;
	}

	@Override
	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest arg0)
			throws MultipartException {
		this.request = arg0;
		return super.resolveMultipart(arg0);
	}
}