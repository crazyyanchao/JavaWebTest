function click(e) {
	if (document.all) {
		if (event.button == 1 || event.button == 2 || event.button == 3) {
			oncontextmenu = 'return false';
		}
	}
	if (document.layers) {
		if (e.which == 3) {
			oncontextmenu = 'return false';
		}
	}
}
if (document.layers) {
	document.captureEvents(Event.MOUSEDOWN);
}
document.onmousedown = click;
document.oncontextmenu = new Function("return false;")

var travel = true
var hotkey = 17 /* hotkey即为热键的键值,是ASII码,这里99代表c键 */
if (document.layers)
	document.captureEvents(Event.KEYDOWN)

function gogo(e) {
	if (document.layers) {
		if (e.which == hotkey && travel) {
			//alert("操作错误.或许是您按错了按键!");
			return false;
		}
	} else if (document.all) {
		if (event.keyCode == hotkey && travel) {
			//alert("操作错误.或许是您按错了按键!");
			return false;
		}
	}
	if(event.ctrlKey){   
        event.returnValue=false;   
    }
	//116为F5，8为退格，
    if(window.event.keyCode==116)
	{   
		window.event.keyCode=0;
		window.event.returnValue=false; //禁止F5
	}
    //屏蔽F12
//    if(window.event.keyCode=='123')
//	{   
//		window.event.keyCode=0;
//		window.event.returnValue=false;
//	}
	//屏蔽ctrl+n
    if(event.ctrlKey && event.keyCode == 78) 
    	event.returnValue = false; 
    if((window.event.altKey)&& 
    		((window.event.keyCode==37)|| //屏蔽 Alt+ 方向键 ← 
    		 (window.event.keyCode==39)) ) //屏蔽 Alt+ 方向键 → 
    { 
    	//alert("不准你使用ALT+方向键前进或后退网页！"); 
    	event.returnValue=false; 
    }
    if( window.event.altKey && window.event.keyCode == 115) //屏蔽Alt+F4 
    { 
	    //window.showModelessDialog("about:blank", "", "dialogWidth:1px;dialogheight:1px"); 
    	window.event.returnValue = false; 
    } 
}
document.onkeydown = gogo;
//屏蔽F1
window.onhelp = function(){return false;}   