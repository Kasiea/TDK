package com.jq.ui.express_form;

import com.newmedical.R;
import com.jq.printer.JQPrinter;
import com.jq.printer.cpcl.CPCL;
import com.jq.printer.cpcl.Text.FONT_FAMILY;
import com.jq.ui.DemoApplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ExpressFormMainActivity extends Activity {

	private Button buttonPrint;
	private JQPrinter printer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_express_form_main);

		buttonPrint = (Button) findViewById(R.id.buttonExpressFormPrint);

		DemoApplication app = (DemoApplication) getApplication();
		printer = app.printer;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.express_form_main, menu);
		return true;
	}

	private boolean getPrinterState() {
		if (!printer.getPrinterState(3000)) {
			Toast.makeText(this, "获取打印机状态失败", Toast.LENGTH_SHORT).show();
			return false;
		}

		if (printer.isCoverOpen) {
			Toast.makeText(this, "打印机纸仓盖未关闭", Toast.LENGTH_SHORT).show();
			return false;
		} else if (printer.isNoPaper) {
			Toast.makeText(this, "打印机缺纸", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private boolean print() {
		if (!printer.getCPCLsupport()) {
			Toast.makeText(this, "不支持CPCL，请设置正确的打印机型号！", Toast.LENGTH_SHORT)
					.show();
			return false;
		}
		if (!EXP341_PrintSenderHorizontal(3000))
			return false;
		return true;
	}

	public boolean EXP341_PrintSenderHorizontal(int timeout) {
		// 记录打印机SDK的每一次调用结果，如果为false，立刻返回，不再继续调用SDK指令
		CPCL cpcl = printer.cpcl;
		int orgX = 0, orgY = 0;
		int x = 0, y = 0;
		// EXP341.jp_page_create(78, 86, false);
		if (!cpcl.page.start(60 * 8, 576, 1))
			return false;
//		cpcl.page.setPageWidth(576);

		x += orgX;
		y += orgY;

		x = 40;
		
		cpcl.text.setFont(48, 0, false, true);	
	    cpcl.text._setFontSetMagCPCL(0, 1);
		cpcl.text.drawOut(x, y, "setmage 0 1");
		y += 40;
		cpcl.text._setFontSetMagCPCL(1, 0);
		cpcl.text.drawOut(x, y, "setmage 1 0");
		y += 40;
		cpcl.text._setFontSetMagCPCL(1, 1);
		cpcl.text.drawOut(x, y, "setmage 1 1");
		y += 40;
		cpcl.text._setFontSetMagCPCL(1, 2);
		cpcl.text.drawOut(x, y, "setmage 1 2");
		y += 50;
		cpcl.text._setFontSetMagCPCL(2, 1);
		cpcl.text.drawOut(x, y, "setmage 2 1");
		y += 40;
		cpcl.text.setFont(48, 0, false, false);	
		cpcl.text._setFontSetMagCPCL(2, 2);
		cpcl.text.drawOut(x, y, "setmage 2 2");
		y += 40;
		cpcl.text._setFontSetMagCPCL(1, 3);
		cpcl.text.drawOut(x, y, "setmage 1 3");
		y += 70;
		cpcl.text._setFontSetMagCPCL(3, 1);
		cpcl.text.drawOut(x, y, "setmage 3 1");
		y += 40;
		cpcl.text._setFontSetMagCPCL(3, 3);
		cpcl.text.drawOut(x, y, "setmage 3 3");
		cpcl.graphic.inverse_line(x, y, x+400, y, 45);
		
//		cpcl.text._setFontSetMagCPCL(0, 0);
		
//		cpcl.text.setFont(48, 0, true, false);	
//		cpcl.text.drawOut(x, y, "test 48");
//		y += 40;
//		cpcl.text.setFont(32, 0, true, false);	
//		cpcl.text.drawOut(x, y, "test 32");
//		y += 40;
//		cpcl.text.setFont(24, 0, true, false);	
//		cpcl.text.drawOut(x, y, "test 24");
//		
//		y += 40;
		
//		cpcl.text.setFont(48, 0, true, false);		
//		cpcl.text.drawOut(x, y, "中铁物流集团");
//		cpcl.text.setFont(24, 0, true, false);
//
//		x += 8;
//		y += 52;
//		cpcl.text.drawOut(x, y, "全国客服:400-000-5566");
//		y += 26;
//		cpcl.text.drawOut(x, y, "官方网站:www.ztky.com");
//
//		cpcl.text.setFont(24, 0, true, false);
//		cpcl.text.drawOut(52 * 8, 6 * 8, "发货人联");
//
//		y += 30;
//		cpcl.graphic.line(0, y, 575, y, 3); // 画横线
//		cpcl.graphic.line(40, 0, 40, y, 3); // 画竖线
//
//		String bar = "2222232364";
//		y += 8;
//		cpcl.barcode.code128(0, y, 72, 2, bar);
//		cpcl.text.setFont(40, 0, true, false);
//		y += 80;
//		cpcl.text.drawOut(0, y, bar);
//
//		y -= 80;
//		cpcl.text.setFont(16, 0, true, false);
//		cpcl.text.drawOut(37 * 8, y, "扫描右侧二维码");

		// 打印二维码
//		cpcl.barcode.Barcode_Text(7, 0, 5);
//		cpcl.barcode.QRCode(54 * 8, y, 4, 4, "www.ztky.com");
//		
//
//		y += 20;
//		cpcl.text.drawOut(37 * 8, y, " 访问中铁官网");
//		y += 20;
//		cpcl.text.drawOut(37 * 8, y, " 了解更多信息");
//		y += 76;
//
//		cpcl.graphic.line(0, y, 50 * 8, y, 3); // 画横线

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //发货人信息 EXP341.jp_page_set_font(font, 3, 0, true, false, false); flag
		 * = EXP341.jp_draw_text(x, 30 + y, string.Format("发货人:{0}", "王二")); if
		 * (!flag) return; flag = EXP341.jp_draw_text(x, 33 + y,
		 * string.Format("发货公司:{0}", "中铁物流集团北京分公司")); if (!flag) return;
		 * 
		 * //如果发货地址长度超过了20个字符，那么就分成两行打印 string sendAddress =
		 * "北京市朝阳区黑庄户乡大鲁店张台路98号"; if (sendAddress.Length >= 20) { flag =
		 * EXP341.jp_draw_text(x, 36 + y, string.Format("发货地址:{0}",
		 * sendAddress.Substring(0, 18))); if (!flag) return; flag =
		 * EXP341.jp_draw_text(x, 39 + y, string.Format("         {0}",
		 * sendAddress.Substring(18))); if (!flag) return; } else { flag =
		 * EXP341.jp_draw_text(x, 39 + y, string.Format("发货地址:{0}",
		 * sendAddress)); if (!flag) return; }
		 * 
		 * flag = EXP341.jp_draw_text(x, 42 + y, string.Format("联系电话:{0}",
		 * "13912345678")); if (!flag) return; flag = EXP341.jp_draw_line(0, 46
		 * + y, 78, 46 + y, 3); //画横线 if (!flag) return;
		 * 
		 * //收货人信息 EXP341.jp_page_set_font(font, 3, 0, true, false, false); flag
		 * = EXP341.jp_draw_text(x, 47 + y, string.Format("收货人:{0}", "李小明")); if
		 * (!flag) return; flag = EXP341.jp_draw_text(x, 50 + y,
		 * string.Format("收货公司:{0}", "中铁互联有限公司")); if (!flag) return;
		 * 
		 * string receiveAddress = "天津市和平区南京路219号";
		 * 
		 * //如果收货地址长度超过了20个字符，那么就分成两行打印 if (receiveAddress.Length >= 20) { flag
		 * = EXP341.jp_draw_text(x, 53 + y, string.Format("收货地址:{0}",
		 * receiveAddress.Substring(0, 18))); if (!flag) return; flag =
		 * EXP341.jp_draw_text(x, 56 + y, string.Format("         {0}",
		 * receiveAddress.Substring(18))); if (!flag) return; } else { flag =
		 * EXP341.jp_draw_text(x, 56 + y, string.Format("收货地址:{0}",
		 * receiveAddress)); if (!flag) return; } flag = EXP341.jp_draw_text(x,
		 * 59 + y, string.Format("联系电话:{0}", "18600001234")); if (!flag) return;
		 * flag = EXP341.jp_draw_line(x, 63 + y, 78, 63 + y, 3); //画横线 if
		 * (!flag) return;
		 * 
		 * //单号信息 EXP341.jp_page_set_font(font, 3, 0, true, false, false); flag
		 * = EXP341.jp_draw_text(x, 64 + y, string.Format("品名:{0}", "药品")); if
		 * (!flag) return; flag = EXP341.jp_draw_text(x, 67 + y,
		 * string.Format("件数:{0}件   计费重量:{1}kg", "18", "500")); if (!flag)
		 * return; flag = EXP341.jp_draw_text(x, 70 + y,
		 * string.Format("重量:{0}kg   声明价值:{1}元", "500", "36000")); if (!flag)
		 * return; flag = EXP341.jp_draw_text(x, 73 + y,
		 * string.Format("体积:{0}M³  运费:{1}元", "2.2", "850")); if (!flag) return;
		 * flag = EXP341.jp_draw_text(x, 76 + y,
		 * string.Format("             总费用:{0}元 ", "936")); if (!flag) return;
		 * 
		 * flag = EXP341.jp_draw_line(50, 63 + y, 50, 85, 3); //画竖线 if (!flag)
		 * return; EXP341.jp_page_set_font(font, 3, 0, true, false, false); flag
		 * = EXP341.jp_draw_text(51 + x, 64 + y, string.Format("配送方式:{0}",
		 * "送货")); if (!flag) return; flag = EXP341.jp_draw_text(51 + x, 67 + y,
		 * string.Format("付款方式:{0}", "现货")); if (!flag) return;
		 * 
		 * //如果增值服务长度超过3，就分两行打印 string huizhiyaoqiu = "签回单"; if
		 * (huizhiyaoqiu.Trim().Length >= 3) { flag = EXP341.jp_draw_text(51 +
		 * x, 70 + y, string.Format("增值服务:{0}", huizhiyaoqiu.Trim().Substring(0,
		 * 2))); if (!flag) return; flag = EXP341.jp_draw_text(57 + x, 73 + y,
		 * string.Format("{0}", huizhiyaoqiu.Substring(2))); if (!flag) return;
		 * } else { flag = EXP341.jp_draw_text(51 + x, 73 + y,
		 * string.Format("增值服务:{0}", huizhiyaoqiu.Trim())); if (!flag) return; }
		 * flag = EXP341.jp_draw_text(51 + x, 76 + y, "代收贷款:"); if (!flag)
		 * return;
		 */y += 10;
		 
//		cpcl.text.drawOut(51 + x, y, "3600元");		
		return cpcl.page.print();
//		return cpcl.feedMarkBegin();
	}

	public void buttonExpressFormPrint_click(View view) {
		buttonPrint.setText("打印");
		buttonPrint.setVisibility(Button.INVISIBLE);

		if (!printer.isOpen) {
			this.finish();
			return;
		}

//		if (!getPrinterState()) {
//			buttonPrint.setVisibility(Button.VISIBLE);
//			return;
//		}

		if (print())
			;
		buttonPrint.setVisibility(Button.VISIBLE);

	}
}
