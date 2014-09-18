package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--图片(Images)  pass
 * @author chhzhang
 *
 */
public class FilefolderImagesTest extends BaseTest {

	// 图片(Images)
	public void test_FilefolderImages() {
		solo.sleep(Resource.TIME_BIG);
		// 下载任意文件→下载框中修改文件的后缀为以下的格式：
		// （*.bmp, *.gif, *.cur, *.ico, *.ief, *.jpeg, *.jpg, *.jpe, *.pcx,
		// *.png, *.svg,
		// *.svgz, //*.tiff, *.tif, *.djvu, *.djv, *.wbmp, *.ras, *.cdr, *.pat,
		// *.cdt, *.cpt, *.ico, *.art, //*.jng, *.bmp, *.psd, *.pnm, *.pbm,
		// *.pgm,
		// *.ppm, *.rgb, *.xbm, *.xpm, *.xwd）
		// →下载完成后查看文件的分组
		FilefolderTestUtil.fileFolder(this,"Images", ".bmp");

		solo.sleep(Resource.TIME_BIG);
	}
}
