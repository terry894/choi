/**
 *저작자:
 *저작일시:
 *저작권:
 */

var com = {};
com.newlecture = {};


com.newlecture.Explorer = function(selector){
	this.resourceURL=null;
	this.selector = null;
	
	if(selector != undefined)
	   this.selector =selector;
	
	this.container=document.querySelector(this.selector);
	this.ul = document.createElement("ul");
	this.container.append(this.ul);
}

com.newlecture.Explorer.prototype = {

	setResourceURL : function(url){
		this.resourceURL = url;
	},
	setSelector : function(selector){
		this.selector=selector;
	},
	load: function(){
		var url = this.resourceURL.split("=")[1] + "/";
		
		var request = new XMLHttpRequest();
		request.addEventListener(
				"load", function() {
					var json = JSON.parse(request.responseText);
					this.ul.innerHTML="";
					//안에있는 this는 밖에 있는 this와는 다름 
					for(var i=0 ; i<json.length ;i++){
					var li = document.createElement("li");
					li.innerHTML='<a download href="/'+url+json[i].name+'">'+json[i].name+'</a>(<a href="del?n=">삭제</a>)';
					this.ul.append(li)
					}
				}.bind(this));

		request.open("GET", this.resourceURL/* ==>"explorer?path=resource/upload"*/);
		request.send();
	}
};