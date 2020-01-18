window.addEventListener("load", function() {
	var socket = null;
    var connButton = document.querySelector("#conn-button");
    var sendButton = document.querySelector("#send-button");
    var chatInput = document.querySelector("#chat-input");

    connButton.onclick = function() {
        //접속
        socket = new WebSocket("ws:192.168.0.3:8080/chat");

        //접속완료 이벤트
        socket.onopen = function() {
            console.log("접속완료");
        };

        //접속종료 이벤트
        socket.onclose = function() {
            console.log("접속해제");
        };

        //이벤트 메세지 도착
        socket.onmessage = function(e) {
            console.log("편지 도착");
            console.log(e.data);
        };  
    };
    
    sendButton.onclick = function() {
    	if(socket != null) {
    		for(var i = 0 ; i < 100; i++)
    			socket.send(chatInput.value);
    	}
    		
    };
});