package org.zframework.core.web.listener;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

@Component
public class mProgressListener implements ProgressListener {
	String pCount = "0";
	long read = 0;
	long context = 0;
	int sa = 0;

	public String getpCount() {
		return pCount;
	}

	@Override
	public void update(long readLength, long contextLength, int items) {
/**		double result = (readLength / (double) 1048576)
				/ (contextLength / (double) 1048576);*/
		if (contextLength < 0) {

		} else {
			read = readLength / 1048576;
			context = contextLength / 1048576;
			sa = (int) Math.round((100.0) * read / context);
		}
		// DecimalFormat df = new DecimalFormat("#.00");
		// System.out.println(read+"/"+context+"="+sa+"：contextLength="+contextLength);

		//NumberFormat nf = NumberFormat.getPercentInstance();
		// System.out.println((readLength / (double)1048576) + "/" +(
		// contextLength / (double)1048576)
		// + "=" + nf.format(result));
		pCount = sa + "";
	}

	@Override
	public String toString() {
		return "mProgressListener [pCount=" + pCount + ", read=" + read
				+ ", context=" + context + ", sa=" + sa + "]";
	}
	
}