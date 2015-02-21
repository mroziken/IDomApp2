package com.example.idom;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public  class listenerToggleButtonClick implements OnClickListener{
	String urlOn;
	String urlOff;
    String url;
	ToggleButton tb;
	boolean state;

  public listenerToggleButtonClick(ToggleButton tb,String urlOn,String urlOff,Boolean state) {
	  this.urlOn=urlOn;
	  this.urlOff=urlOff;
      this.url = "";
	  this.tb = tb;
      this.state=state;
  }
  
  public void onClick(View v) {
	  String url;
	  if (this.state){
		  this.url=this.urlOff;
          this.state=false;
	  }
	  else{
		  this.url=this.urlOn;
          this.state=true;
	  }
      Log.v("listenerToggleButtonClick",this.url);
      HandleJSON obj = new HandleJSON(this.url);
      obj.fetchJSON();
      while(obj.parsingComplete);
      String YY = obj.getYear();
      String MM = obj.getMonth();
      String DD = obj.getDay();
      String HH = obj.getHour();
      String mm = obj.getMinute();
      String SS = obj.getSeconds();
      String ms = obj.getMSeconds();
      if (checkResult(v,YY,MM,DD,HH,mm,SS,ms)){
          tb.setChecked(state);
      }
      else{
    	  Toast.makeText(v.getContext(), "Problem with processing command on host", Toast.LENGTH_LONG).show();
      }
  }

    private boolean checkResult(View v,String YY, String MM, String DD, String HH, String mm, String SS, String ms) {

	    String url="http://192.168.1.130:8080/check_cmd_proc?";
	    url=url+"YY="+YY+"&MM="+MM+"&DD="+DD+"&HH="+HH+"&mm="+mm+"&SS="+SS+"&ms="+ms;
	    Log.v("listenerToggleButtonClick",url);
	    HandleJSON obj = new HandleJSON(url);
        obj.fetchJSON();
        while(obj.parsingComplete);
	    return obj.getResult();
    }
}

