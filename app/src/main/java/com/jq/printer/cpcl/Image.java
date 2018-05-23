package com.jq.printer.cpcl;

import android.R.string;

import com.jq.printer.PrinterParam;

public class Image extends BaseCPCL{
	
	public Image(PrinterParam param) {
		super(param);
		// TODO Auto-generated constructor stub
	}

public boolean Image_drawout(int width, int height, int x, int y, String data)
{
	String CPCLCmd = "EG " + width + " " + height + " " + x + " " + y +" " + data;
    return portSendCmd(CPCLCmd);
}


}
