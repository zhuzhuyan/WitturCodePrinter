package com.djzho.print;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class WitturCE implements Printable {

	/** 打印机名称 */
	private String printerName = "ZDesigner 105SLPlus-300dpi ZPL";

	/** 证书编号 */
	private String certNO = "ATV JFGKIKJHJI";
	/** 串联 */
	private String series = "SERIES:YYYY-AAAAAAAAA";
	/** 序列号（9位码） */
	private String serialNumber = "S/N: XXXXXX";
	/** 销售单号 */
	private String salesOrdNo = "SALES ORD.NO:XXXXXX";
	/** 产品日期 */
	private String productDate = "2015/11/12";
	/** 类别 */
	private String type = "TYPE: BB/T";

	/** 数据库中的序列码+9位码 */
	private String code = "C2L2M000M00N080#160409000000000000004101";
	/** 物料号 */
	private String item = "2L2M000M00N080";
	/** 物料描述 */
	private String desc = "AA01C挂件PL= 800SF50";
	/** 流水号 */
	private String sno = "151502020";

	/** 左边距 */
	private double paddingLeft = 0;

	/** 上边距 */
	private double paddingTop = 0;

	/**
	 * 设置边距。
	 * 
	 * @param paddingLeft
	 * @param paddingTop
	 */
	public void setPadding(double paddingLeft, double paddingTop) {
		this.paddingLeft = paddingLeft;
		this.paddingTop = paddingTop;
	}

	public WitturCE(String printerName, String certNO, String series, String sn, String salesOrdNo, String productDate,
			String type, String code, String item, String desc, String sno) {
		super();
		this.printerName = printerName;
		this.certNO = certNO;
		this.series = series;
		this.serialNumber = sn;
		this.salesOrdNo = salesOrdNo;
		this.productDate = productDate;
		this.type = type;
		this.code = code;
		this.item = item;
		this.desc = desc;
		this.sno = sno;
	}

	public WitturCE() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.printerName + "\t" + this.certNO + "\t" + this.series + "\t" + this.serialNumber + "\t"
				+ this.salesOrdNo + "\t" + this.productDate + "\t" + this.type + "\t" + this.code + "\t" + this.item
				+ "\t" + this.desc + "\t" + this.sno;
	}

	/**
	 * @param Graphic指明打印的图形环境
	 * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×
	 *            842点）
	 * @param pageIndex指明页号
	 **/
	public int print(Graphics gra, PageFormat pf, int pageIndex) throws PrinterException {
		Component c = null;
		// 转换成Graphics2D
		Graphics2D g2 = (Graphics2D) gra;
		// 设置打印颜色为黑色
		g2.setColor(Color.black);

		// 打印起点坐标
		double x = pf.getImageableX();
		double y = pf.getImageableY();

		switch (pageIndex) {
		case 0:
			// 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
			// Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
			Font font = new Font("新宋体", Font.BOLD, 7);
			g2.setFont(font);// 设置字体
			// BasicStroke bs_3=new BasicStroke(0.5f);
			// float[] dash1 = {2.0f};
			// 设置打印线的属性。
			// 1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
			// g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT,
			// BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
			// g2.setStroke(bs_3);//设置线宽
			// float heigth = font.getSize2D();// 字体高度
			// -1- 用Graphics2D直接输出
			// 首字符的基线(右下部)位于用户空间中的 (x, y) 位置处
			// g2.drawLine(10,10,200,300);
			Image codeImg = null;
			try {
				codeImg = ImageIO.read(new File("C:\\toolsZ\\codeZ\\wittur.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g2.drawImage(codeImg, (int) (x + 0), (int) (y + 104), c);

			g2.drawString(certNO, (float) (x + 72), (float) (y + 56));
			g2.drawString(series, (float) (x + 99), (float) (y + 67));
			g2.drawString(serialNumber, (float) (x + 88), (float) (y + 78));
			g2.drawString(salesOrdNo, (float) (x + 130), (float) (y + 90));
			g2.drawString(type, (float) (x + 94), (float) (y + 101));
			g2.drawString(productDate, (float) (x + 113), (float) (y + 135));

			/** #号索引 */
			int indexSharp = code.indexOf('#');

			// 绘制项目号
			g2.drawString("项目号：", (float) (x + 236), (float) (y + 28));
			g2.drawString(code.substring(indexSharp + 7, indexSharp + 13), (float) (x + 236), (float) (y + 38));

			// 绘制流水号
			g2.drawString("流水号：", (float) (x + 236), (float) (y + 62));
			g2.drawString(sno, (float) (x + 236), (float) (y + 72));

			// 绘制物料号
			g2.drawString("物料号：", (float) (x + 236), (float) (y + 96));
			g2.drawString(item, (float) (x + 236), (float) (y + 106));

			// 绘制物料描述
			g2.drawString("物料描述：", (float) (x + 236), (float) (y + 130));
			int len = desc.length();
			if (len > 15) {
				g2.drawString(desc.substring(0, 15), (float) (x + 236), (float) (y + 140));
				g2.drawString(desc.substring(15, len), (float) (x + 236), (float) (y + 148));
			} else {
				g2.drawString(desc, (float) (x + 236), (float) (y + 140));
			}

			return PAGE_EXISTS;
		default:
			return NO_SUCH_PAGE;
		}

	}

	public static void main(String[] args) {

		// 通俗理解就是书、文档
		Book book = new Book();
		// 设置成竖打
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.LANDSCAPE);
		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper p = new Paper();
		p.setSize(156, 324);// 纸张大小
		p.setImageableArea(0, 0, 156, 324);// A4(595 X
											// 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
		pf.setPaper(p);
		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new WitturCE(), pf);

		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();

		HashAttributeSet hs = new HashAttributeSet();

		String printerName = "ZDesigner 105SLPlus-300dpi ZPL";

		hs.add(new PrinterName(printerName, null));

		PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);

		try {
			job.setPrintService(pss[0]);
		} catch (PrinterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 设置打印类
		job.setPageable(book);

		try {
			// 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
			// boolean a=job.printDialog();
			// if(a)
			// {
			job.print();
			// }
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	public void printcode() {

		// 通俗理解就是书、文档
		Book book = new Book();
		// 设置成竖打
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.LANDSCAPE);
		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper p = new Paper();
		p.setSize(156, 324);// 纸张大小
		p.setImageableArea(paddingTop, -paddingLeft, 156, 324);// A4(595 X
		// 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
		pf.setPaper(p);
		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new WitturCE(printerName, certNO, series, serialNumber, salesOrdNo, productDate, type, code, item,
				desc, sno), pf);

		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();

		HashAttributeSet hs = new HashAttributeSet();

		hs.add(new PrinterName(printerName, null));

		PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);

		try {
			job.setPrintService(pss[0]);
		} catch (PrinterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 设置打印类
		job.setPageable(book);

		try {
			// 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
			// boolean a=job.printDialog();
			// if(a)
			// {
			job.print();
			// }
		} catch (PrinterException e) {
			e.printStackTrace();
		}

	}
}