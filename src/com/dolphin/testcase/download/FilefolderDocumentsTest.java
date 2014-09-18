package com.dolphin.testcase.download;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Resource;

/**
 * 下载管理——各个文件夹的定义--文档(Documents)  pass
 * @author chhzhang
 *
 */
public class FilefolderDocumentsTest extends BaseTest {

	// 文档(Documents)
	public void test_FilefolderDocuments() {
		solo.sleep(Resource.TIME_BIG);
		// 下载任意文件→下载框中修改文件的后缀为以下的格式：
		// （*.pdf,*.doc, *.dot, *.xls, *.xlt, *.ppt, *.pot, *.pps, *.ics, *.icz,
		// *.csv,*.css, *.htm, *.html, *.323, *.uls, *.mml, *.txt, *.asc,
		// *.text, *.diff,
		// *.po, *.rtx, *.rtf, *.ts, *.phps, *.tsv, *.xml, *.bib, *.boo, *.h++,
		// *.hpp, *.hxx, *.hh, *.c++, *.cpp, *.cxx, *.h, *.htc, *.csh, *.c, *.d,
		// *.hs, *.java, *.lhs, *.moc, *.p, *.pas, *.gcd, *.etx, *.tcl, *.tex,
		// *.ltx, *.sty, *.cls, *.vcs, *.vcf）
		// →下载完成后查看文件的分组
		FilefolderTestUtil.fileFolder(this, "Documents", ".doc");

		solo.sleep(Resource.TIME_BIG);

	}
}
