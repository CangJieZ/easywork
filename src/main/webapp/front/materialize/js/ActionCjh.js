/**
向后端提交请求. 
*/

//*************************************** begin **************************************//		
// 用于标识是否对值做了修改
var Action_valueString = "";
var myLayer = "";
var jq = "";
//*************************************** end   **************************************//		

var Action= {				
	
		loadLayer:function(lay){
			myLayer = lay.layer;
//			jq = lay.jquery;//使用layer 框架里的jq,如果不使用可以把这行去掉，并在jsp页面引入jquery,并
			//并把jq 改为$
	},
		
	ajsxSucc:function(e,succFun){
		
		/*if(e.errorMsg){
			myLayer.alert(e.errorMsg);
			if(e.errorCode=="OUT_TIME_LOGIN"){
				var ret = _appServer+'/back/logout.htm?retUrl='+$("#retUrlYYY").val();
				window.parent.location = ret;
			}
			//返回错误信息时清除Action_valueString属性
			Action.clean();
		}*/
		//ajax请求超时处理
		//var res = JSON.stringify(e);
		if(e=='-100003'){
			myLayer.alert("没有操作权限，请联系管理员！");
		}else if(e=="-100002"){
			myLayer.alert("请求超时，请重新登录！",{closeBtn: 0},function(){
				var ret = _appServer+'/login/loginout.action?outmsg=CFD09dW87H654321';
				window.parent.location = ret;
			});
			Action.clean();
		} /*else if(e.callbackFun){
			e.callbackFun();
		}*/else if(succFun){
			succFun(e);
		}
	},
	
//*************************************** begin **************************************//			
	/**
	 * 将data转化成字符串
	 */
	toStr:function(data){
		var str = "";
		if(data){
			$.each(data, function(i,obj){
				//obj = $(obj);
				str += obj.value;
			});
			return str;
		}				
		
		return str;				
	},	
	
	/**
	 * 初始化
	 * @param crudform
	 */
	init:function(crudform){
		var data = Action.getCrudData(crudform);
		Action_valueString = Action.toStr(data);
	},
	
//	initCheckData:function(){
//		 for (var i = 0; i < arguments.length; i++){
//			 //if(typeof arguments[i] == "")
//			 alert(typeof arguments[i]);
//		 }  
//	},
	
	/**
	 * 清除Action_valueString属性
	 */
	clean:function(){
		Action_valueString = "";
	},
	
	/**
	 * 校验是否重复提交
	 * @param data
	 */
	check:function(data){
		// 校验是否重复提交
		var tmpstr = this.toStr(data);
		if(Action_valueString == tmpstr)			
			return false;
		Action_valueString = tmpstr;
		return true;
	},
	
	/**
	 * ajax异步请求（检验是否重复提交）,带参数,以json格式返回
	 * @param url
	 * @param data
	 * @param succFun
	 */
	asyncCheckAndSubmit:function (url,data,succFun){
		//校验是否重复提交
		if(!Action.check(data)){
			alert('您未做任何修改，不需要提交');
			return;
		}
		$.ajax({
			type: "POST",
			url: url,
			async:true,
			dataType: "json",
			data:data,
			success: function(e){
				Action.ajsxSucc(e,succFun);				
			},
			error:function(e){		
				Action_valueString = "";		
				alert('后台出错，请查看日志');
			}
		});
	},
	
	/**
	 * ajax非异步请求（检验是否重复提交）,带参数,以json格式返回
	 * @param url
	 * @param data
	 * @param succFun
	 */
	checkAndSubmit:function (url,data,succFun){
		//校验是否重复提交
		if(!Action.check(data)){
			alert('您未做任何修改，不需要提交');
			return;
		}
		$.ajax({
			type: "POST",
			url: url,
			async:false,
			dataType: "json",
			data:data,
			success: function(e){
				Action.ajsxSucc(e,succFun);
			},
			error:function(e){
				alert('后台出错，请查看日志');
			}
		});
	},
//*************************************** end   **************************************//		
	
	/**
	 * ajax异步请求,不带参数,以json格式返回
	 */
	jsonAsyncAct:function (url,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:true,
			dataType: "json",
			success: function(e){
				Action.ajsxSucc(e,succFun);
			},
			error:function(e){alert('后台出错，请查看日志');}
		});
	},
	/**
	 * ajax异步请求,不带参数,以html格式返回
	 */
	htmlAsyncAct:function (url,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:true,
			dataType: "html",
			success: function(e){
				Action.ajsxSucc(e,succFun);
			},
			error:function(e){alert('后台出错，请查看日志');}
		});
	},
	
	/**
	 * ajax异步请求,带参数,以json格式返回
	 */
	jsonAsyncActByData:function(url,data,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:true,
			dataType: "json",
			data:data,
			success: function(e){
				Action.ajsxSucc(e,succFun);					
			},
			error:function(e){
				alert('后台出错，请查看日志');
			}
		});
	},
	
	/**
	 * ajax异步请求,带参数,以html格式返回
	 */
	htmlAsyncActByData:function(url,data,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:true,
			dataType: "html",
			data:data,
			success: function(e){
				Action.ajsxSucc(e,succFun);
			},
			error:function(e){
				alert('后台出错，请查看日志');
			}
		});
	},
	
	/**
	 * ajax非异步请求,不带参数,以json格式返回
	 */
	jsonAct:function (url,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:false,
			dataType: "json",
			success: function(e){
				Action.ajsxSucc(e,succFun);
			},
			error:function(e){alert('后台出错，请查看日志');}
		});
	},
	
	/**
	 * ajax非异步请求,不带参数,以html格式返回
	 */
	htmlAct:function (url,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:false,
			dataType: "html",
			success: function(e){
				Action.ajsxSucc(e,succFun);
			},
			error:function(e){alert('后台出错，请查看日志');}
		});
	},
	
	/**
	 * ajax非异步请求,带参数,以json格式返回
	 */
	jsonActByData:function(url,data,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:false,
			dataType: "json",
			data:data,
			success: function(e){
					Action.ajsxSucc(e,succFun);
			},
			error:function(e){
				myLayer.alert('后台出错，请查看日志');
			}
		});
	},

	/**
	 * ajax非异步请求,带参数,以html格式返回
	 */
	htmlActByData:function(url,data,succFun){
		$.ajax({
			type: "POST",
			url: url,
			async:false,
			dataType: "html",
			data:data,
			success: function(e){
				Action.ajsxSucc(e,succFun);
			},
			error:function(e){
				alert('后台出错，请查看日志');
			}
		});
	},
	
	/**
	从url取得数据
	*/
	getData:function(url){
		var ret;
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			async:false,
			success: function(e){
				//if(e.msg){
				//	alert(e.msg)
				//}
				Action.ajsxSucc(e,null);
				ret = e;
			},
			error:function(e){alert('后台出错，请查看日志');}
		});
		return ret;
	},
	
	toAjaxUrl:function(url,ajax){
		if(!url)
			return "";
		if(url.indexOf("?")== -1)
			url = url +"?ajax="+ajax;
		else{
			if(url.indexOf("ajax") == -1)
				url = url + "&ajax=" + ajax;
		}
		return url;
	},
	
	toAjaxData:function(data,val){
		if(data){
			if($.isArray(data))
				data[data.length] = {name:"ajax",value:val};
			else
				data["ajax"] = val;
		}else{
			data = [];
			data["ajax"] = val;
		}
		return data;
	},
	
	getCrudData:function(crudform){
		if(!crudform)
			crudform = "crudform";
		var input_arr = jq("#" + crudform + " :input");
		var data = [];
		for(var i = 0,y = 0;i<input_arr.length;i++){
			if(input_arr[i].type != "button" && input_arr[i].name != ""){
				var _val = $.trim(input_arr[i].value);
				var value={name:input_arr[i].name,value:_val};
				data[y] = value;
				y++;
			}
		}
		return data;
	},
	
	getSchData:function(){
		var input_arr = jq("#schform :input");
		var data = [];
		for(var i = 0,y = 0;i<input_arr.length;i++){
			if(input_arr[i].type != "button"){
				var _val = $.trim(input_arr[i].value);
				var value={name:input_arr[i].name,value:_val};
				data[y] = value;
				y++;
			}
		}
		return data;
	},
	
	reflush_data:function(schUrl,divId){
		var data = Action.getSchData();
		if(!divId)
			divId = "data";
		Action.htmlAsyncActByData(schUrl, data, function(data){
			jq("#"+divId).html(data);
			//loadPermission();
		});
		
	},
	
	getItemsByUrl:function(url,valueName,textName){
		var e = Action.getData(url);
		var datas = e.list;
		var items = new Array;
		for(var i = 0; i < datas.length; i++){
			var item = {};
			item['value'] = datas[i][valueName];
			item['text'] = datas[i][textName];
			item.data=datas[i];
			items.push(item);
		}
		return items
	}
	
	/**
	 * 根据URL获取下列选项：可匹配正则表达式
	 */
//	regOptionByUrl:function(url,params){
//		var items = Action.regItemsByUrl(url, params.valueReg, params.textReg);
//		
//		if(items){
//			var selboxes = params.ids;
//			for(var j=0; j<selboxes.length; j++){
//				var id = selboxes[j];
//				var selbox = $("#" + id);
//				selbox.html("");
//				var op = $("<option></option>");
//				op.val("");
//				op.text("-请选择-");
//				selbox.append(op);
//				for(var i=0; i<items.length; i++){
//					var item = items[i];
//					op = $("<option></option>");
//					op.val(item.value);
//					op.text(item.text);
//					selbox.append(op);
//				}
//			}
//		}
//	},
	
	/**
	 * 根据URL获取items
	 */
//	regItemsByUrl:function(url, valueReg, textReg){
//		
//		var reg = /\#\(\w+\)/ig;
//		var textFieldRegs = textReg.match(reg);
//		var valueFieldRegs = valueReg.match(reg);
//		
//		if(!textFieldRegs && !textFieldRegs)
//			return Action.getItemsByUrl(url, valueReg, textReg);
//		
//		var textFlag = true;
//		var valueFlag = true;
//		var textFields;
//		var valueFields;
//		if(textFieldRegs){
//			textFields = [];
//			for(var i = 0; i < textFieldRegs.length; i++){
//				var fieldReg = textFieldRegs[i];
//				textFields[i] = fieldReg.substring(2,fieldReg.length-1);
//			}
//		}else
//			textFlag = false;
//		
//		if(valueFieldRegs){
//			valueFields = [];
//			for(var i = 0; i < valueFieldRegs.length; i++){
//				var fieldReg = valueFieldRegs[i];
//				valueFields[i] = fieldReg.substring(2,fieldReg.length-1);
//			}
//		}else
//			valueFlag = false;
//		
//		var e = Action.getData(url);
//		var datas
//		if(e && e.list)
//			datas = e.list;
//		else
//			datas = e;
//		if(datas){
//			var items = new Array;
//			for(var i = 0; i < datas.length; i++){
//				var item = {};
//				if(valueFlag){
//					var value = valueReg;
//					for(var j = 0; j < valueFields.length; j++){
//						var valueField = valueFields[j];
//						var val = datas[i][valueField];
//						value = value.replace(valueFieldRegs[j], val);
//					}
//					item['value'] = value;
//				}else
//					item['value'] = datas[i][valueReg];
//				if(textFlag){
//					var text = textReg;
//					for(var j = 0; j < textFields.length; j++){
//						var textField = textFields[j];
//						var tex = datas[i][textField];
//						text = text.replace(textFieldRegs[j], tex);
//					}
//					item['text'] = text;
//				}else
//					item['text'] = datas[i][textReg];
//				item.data=datas[i];
//				items.push(item);
//			}
//			return items;
//		}
//	},
	
//	getOptionByUrl:function(url,params){
//		var data = Action.getData(url);
//		var items
//		if(data && data.list)
//			items = data.list;
//		else
//			items = data;
//		
//		if(items){
//			var selboxes = params.ids;
//			var textfield = params.text;
//			var valuefield = params.value;
//			for(var j=0; j<selboxes.length; j++){
//				var id = selboxes[j];
//				var selbox = $("#" + id);
//				selbox.html("");
//				var op = $("<option></option>");
//				op.val("");
//				op.text("-请选择-");
//				selbox.append(op);
//				for(var i=0; i<items.length; i++){
//					var item = items[i];
//					op = $("<option></option>");
//					op.val(item[valuefield]);
//					op.text(item[textfield]);
//					selbox.append(op);
//				}
//			}
//		}
//	},
//	getOptionByData:function(data,params){
//		var id = params.id;
//		var textfield = params.text;
//		var valuefield = params.value;
//		var selbox = $("#" + id);
//		selbox.html("");
//		var op = $("<option></option>");
//		op.val("");
//		op.text("-请选择-");
//		selbox.append(op);
//		
//		if(data){
//			for(var i=0; i<data.length; i++){
//				var item = data[i];
//				op = $("<option></option>");
//				op.val(item[valuefield]);
//				op.text(item[textfield]);
//				selbox.append(op);
//			}
//		}
//	}
}


