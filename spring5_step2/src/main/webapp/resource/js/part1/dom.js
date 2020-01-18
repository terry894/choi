// --- ex11 - Ajax로 게시글 관리하기 ------------------------------------
window.addEventListener("load", function(){
	
	var load = function(page){    	
    	
    	// 클릭된 페이지의 레코드를 가져오는
    	var request = new XMLHttpRequest();
    	//var pIndex = e.target.href.lastIndexOf("p=");
    	//var page =  e.target.href.substring(pIndex+2);
    	//alert(pIndex + ", " + page);
        request.open("GET", "../../notice/list-json?p="+page);        
        //request.onreadystatechange = function(){
        //	if(request.readyState != 4)
        //		return;
        
        request.onload = function(){
        	
        	var list = JSON.parse(request.responseText);
            
            var trTemplate = section.querySelector(".tr-template");
            tbody.innerHTML = "";
            
            for(var i=0; i<list.length; i++){
    	        var cloneTr = document.importNode(trTemplate.content, true);
    	
    	        var tds = cloneTr.querySelectorAll("td");//.children (X) : 화면에 보이는 넘들만 가능하다.;
    	        tds[0].innerText = list[i].id;
    	        var aTag = tds[1].firstElementChild;
    	        // <a href="" data-id="1" data-writer-id="newlec">제목</a>
    	        aTag.dataset.id = list[i].id;
    	        //aTag.dataset["writer-id"] = list[i].writerId;
    	        aTag.innerText = list[i].title;
    	        tds[2].innerText = list[i].writerId;
    	        tds[3].innerText = list[i].regDate;
    	        tds[4].innerText = list[i].hit;
    	
    	        tbody.append(cloneTr);
            }
        };
        request.send();// 요청한 후에 기다림....
    };
	
    var section = document.querySelector("#ex11");

    var tbody = section.querySelector("table tbody");
    var loadButton = section.querySelector(".load-button");
    var regButton = section.querySelector(".reg-button");
    var pager = section.querySelector(".pager");
    
    load(1); // 기본 페이지 1 <-- 요거 주석....
           
    /*=== Handler 들==================================*/
    // 제목을 클릭하면 상세 내용을 보는 핸들러
    tbody.onclick = function(e){
    	
    	if(!e.target.parentNode.classList.contains("title"))
    		return;
    	e.preventDefault();
    	
    	// 현재 tr을 찾기부터 
        var currentTr = e.target.parentNode.parentNode;
        var nextTr = currentTr.nextElementSibling;
    	
        if(nextTr.classList.contains("detail")){
        	alert("이미 있잖아~");
        	return;
        }
        
        //현재 타킷의 부모(td)의 막내가 img 라면
        if(e.target.parentNode.lastElementChild.nodeName == "IMG"){
        	alert("이미 일하고 있거든? 자꾸 왜그래~~");
        	return;
        }
    	
    	// 현재 target으로 부터 전송할 식별자(id)를 얻는다.
    	var id = e.target.dataset.id;
    	    	
    	// 자세한 정보를 요청.
    	// Ajax 아이콘을 추가하고
    	var ajaxIcon = document.createElement("img");
    	ajaxIcon.src = "../../images/ajax-loader-tr.gif";
    	e.target.parentNode.append(ajaxIcon); 
    	
    	var request = new XMLHttpRequest();
        request.open("GET", "../../notice/detail-json?id="+id, true);        
        request.onload = function(){
        	var notice = JSON.parse(request.responseText);
        	
        	var template = section.querySelector(".detail-template");
        	var cloneTr = document.importNode(template.content, true);
        	
	        var td = cloneTr.querySelector("td");
	        td.innerHTML = notice.content;
	        
	        // 이제 td를 추가(X)-> 삽입(O)
	        
	        tbody.insertBefore(cloneTr, nextTr);
	        // Ajax 아이콘을 제거한다.
	        //e.target.parentNode.removeChild(ajaxIcon); 
	        ajaxIcon.remove(); 
	        ajaxIcon = null;
        	
        };
        request.send();
    	
    };
    
    
    regButton.onclick = function(){
    	
    	//1.데이터를 준비하고
    	var form = section.querySelector(".form");
        var id = form.querySelector('input[name="id"]').value;
        var title = form.querySelector('input[name="title"]').value;
        var writerId = form.querySelector('input[name="writerId"]').value;
        var regDate = form.querySelector('input[name="regDate"]').value;
        var hit = form.querySelector('input[name="hit"]').value;
        
        
        //var data = "id="+id+"&title="+title+"&writerId="+writerId
        
        title = encodeURI(title);
        console.log(title);
        
        var data = [
        					["id",id], 
        					["title", title],
        					["writerId", writerId],
        					["regDate", regDate],
        					["hit", hit]
        				];
        var sendData = [];
        
        for(var i=0; i<data.length; i++)
        	sendData[i] = data[i].join("="); // k=2
                        
        sendData = sendData.join("&");
        
        console.log(data);
        console.log(sendData);
                    	
    	//2.데이터를 전송
        var request = new XMLHttpRequest();
        request.open("POST", "../../notice/reg-json", true);
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        request.onload = function(){
        	//3.결과에 따라서 조치를 한다.
        	// 3-1 결과 값을 확인하고
        	console.log(request.responseText);
        	
        	// 3-2 새로운 목록을 다시 요청한다.
        	
        	// 3-3 요청한 데이터로 화면을 갱신한다.
        	load(1); 
        	
        };
        request.send(sendData);
        
    	
    	
    };
    
    pager.onclick = function(e){
    	if(e.target.nodeName != "A")
    		return;
    	
    	e.preventDefault();
    	
    	load(e.target.innerText);
    };

    loadButton.onclick = function(){
        var request = new XMLHttpRequest();
        request.open("GET", "../../notice/list-json", false);
        request.send();

        var list = JSON.parse(request.responseText);
        
        // 방법 4 : 템블릿을 복사해서 사용하기
        var trTemplate = section.querySelector(".tr-template");
        
        for(var i=0; i<list.length; i++){
	        var cloneTr = document.importNode(trTemplate.content, true);
	
	        var tds = cloneTr.querySelectorAll("td");//.children (X) : 화면에 보이는 넘들만 가능하다.;
	        tds[0].innerText = list[i].id;
	        tds[1].firstElementChild.innerText = list[i].title;
	        tds[2].innerText = list[i].writerId;
	        tds[3].innerText = list[i].regDate;
	        tds[4].innerText = list[i].hit;
	
	        tbody.append(cloneTr);
        }
        
        
        // 1. 서버에서 데이터를 가져와서
        // 2. 화면을 갱신한다.

        /*
        Ajax : [A]syncrous [JA]vascript [X]ml
        자바스크립트를 이용해 "서버로 데이터를 비동기로 요청" 후 
        그 데이터로 화면을 갱신하는 기술

        2000년 초..
        1998년부터 사용되기 시작했어요
        해커에 의해서(해박한 지식을 가진 사람) -> 권한을 넘는 짓거리..
        98 : 윈도우즈 98 버전 , System lib ActiveX dll 추가
        Visual Basic 이 사용할 수 있는 웹 요청 라이브러리가 추가

        XmlHttpRequest : 해커들(해박한지식을 가진 자) 어? 어? ActiveX?
        Javascript도? 성공 -> XmlHttpRequest 브라우저에 심자.




        Web v2.0
        웹의 지침? 방향? 어떤 회사에 투자하시오.
        정리 : 망한 회사 / 성공한 회사
        
        */
    };
});

// --- ex10 - 노드 조작하기 ------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#ex10");

    var tbody = section.querySelector("table tbody");
    var addButton = section.querySelector(".add-button");
    var regButton = section.querySelector(".reg-button");
    
    var notices = [
        {
            id:1, 
            title:"제모이야요", 
            writerId:"newlec", 
            regDate:"2019-11-23", 
            hit:20
        },
        {
            id:2, 
            title:"제목이지요..바붕", 
            writerId:"newlec", 
            regDate:"2019-11-23", 
            hit:12
        }
    ];

    regButton.onclick = function(){
        var form = section.querySelector(".form");
        var id = form.querySelector('input[name="id"]').value;
        var title = form.querySelector('input[name="title"]').value;
        var writerId = form.querySelector('input[name="writerId"]').value;
        var regDate = form.querySelector('input[name="regDate"]').value;
        var hit = form.querySelector('input[name="hit"]').value;

        // 방법 4 : 템블릿을 복사해서 사용하기
        var trTemplate = section.querySelector(".tr-template");
        var cloneTr = document.importNode(trTemplate.content, true);

        var tds = cloneTr.querySelectorAll("td");//.children (X) : 화면에 보이는 넘들만 가능하다.;
        tds[0].innerText = id;
        tds[1].firstElementChild.innerText = title;
        tds[2].innerText = writerId;
        tds[3].innerText = regDate;
        tds[4].innerText = hit;

        tbody.append(cloneTr);
    };

    addButton.onclick = function(){

        // 방법 4 : 템블릿을 복사해서 사용하기
        var trTemplate = section.querySelector(".tr-template");
        var cloneTr = document.importNode(trTemplate.content, true);

        var tds = cloneTr.querySelectorAll("td");//.children (X) : 화면에 보이는 넘들만 가능하다.;
        tds[0].innerText = notices[0].id;
        tds[1].firstElementChild.innerText = notices[0].title;
        tds[2].innerText = notices[0].writerId;
        tds[3].innerText = notices[0].regDate;
        tds[4].innerText = notices[0].hit;

        tbody.append(cloneTr);

        // 방법 3 : 기존 레코드 복사해서 사용하기
        /*
        var cloneTr = tbody.firstElementChild.cloneNode(true);
        var tds = cloneTr.children;
        tds[0].innerText = notices[0].id;
        tds[1].firstElementChild.innerText = notices[0].title;
        tds[2].innerText = notices[0].writerId;
        tds[3].innerText = notices[0].regDate;
        tds[4].innerText = notices[0].hit;

        tbody.append(cloneTr);
        */

        // 방법 2 : innerHTML 이용하기
        /* 
        var trHtml = '<tr> \
                            <td>6</td> \
                            <td class="title indent text-align-left"><a href="detail.html">뉴렉쌤 9월 초 국기과정 모집 안내</a> [22]</td> \
                            <td>newlec</td> \
                            <td> \
                                2019-06-11 \
                            </td> \
                            <td>517</td> \
                        </tr>';

        tbody.innerHTML += trHtml;
        */

        // 방법 1 : 직접 만들기
        // var tr = document.createElement("tr");
        // var tds = [];
        // for(var i=0; i<5; i++){
        //     var td = document.createElement("td");
        //     tds[i] = td;
        //     tr.append(tds[i]);
        // }

        // var txt1 = document.createTextNode("7");
        // tds[0].append(txt1);
        
        // tbody.append(tr);




        
    };
});
// --- ex9 - 노드 조작하기 ------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#ex9");

    var textButton = section.querySelector(".text-button");
    var imgButton = section.querySelector(".img-button");
    var linkButton = section.querySelector(".link-button");
    var delButton = section.querySelector(".del-button");
    var stage = section.querySelector(".stage");

    textButton.onclick = function(){
        var textNode = document.createTextNode("하하하");//텍스트 노드 생성방법
        //stage.appendChild(textNode);
        stage.append(textNode);
    };

    imgButton.onclick = function(){
        var img = document.createElement("img");
        img.src = "../../images/img1.jpeg";

        stage.append(img);
    };

    linkButton.onclick = function(){
        var txt = document.createTextNode("다움");        
        var link = document.createElement("a");
        link.append(txt);
        link.href="http://www.daum.net";
        
        stage.append(link);
    };

    delButton.onclick = function(){
        //stage.removeChild(stage.lastChild); // 모든 노드를 대상으로 막내
        //stage.removeChild(stage.lastElementChild); // 엘리먼트만 대상으로 막내를 찾는다.
        stage.lastChild.remove();
    };
});

// --- ex8 - 이벤트 객체 and 이벤트 버블링 ------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#ex8");

    var targetButton = section.querySelector("input[type=file]");
    var fileButton = section.querySelector(".file-button");
    fileButton.onclick = function(){
         //표준인데 IE 에서는 호환이 안됨.
        var event = new MouseEvent("click",{
                        view:window,
                        bubbles:true,
                        cancelable:true
                    });
        //var event = document.createEvent("??"); // 모든 브라우저에서 호환됨.

        targetButton.dispatchEvent(event);
    };
});
// --- ex7 - 이벤트 객체 and 이벤트 버블링 ------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#ex7");

    var ul = section.querySelector("div:last-child ul");
    var imgs = section.querySelectorAll("div:last-child img");

    // === 2차 수정본 =================================
    // 자잘하게 반복되는 자식에게 부여할 반복적인 이벤트를 
    // 버블링을 이용해서 그 부모에게 한번만 적용하자.
    
    // 이부분을 부모에게 한번만 적용하자.   
    ul.onclick = function(e){
        console.log(e.target.tagName);
        console.log(e.currentTarget.tagName);

        console.log("img가 클릭되었어요");
        var clickedImg = e.target;
        
        var img = section.querySelector(".stage img");
        img.src = clickedImg.src;
    };

    // 기본행위를 가지는 태그들 <a><input><img> ....
    ul.firstElementChild.onclick = function(e){
        console.log(e.target.tagName);
        console.log(e.currentTarget.tagName);

        e.preventDefault();
        e.stopPropagation();

        alert("이전 페이지");
    };

    // imgs[0].parentElement/*li노드*/.onclick = function(){
    //     console.log("li가 클릭되었어요");
    // }

    // imgs[0].parentElement.parentElement/*ul노드*/.onclick = function(){
    //     console.log("ul이 클릭되었어요");
    // }

    // === 1차 수정본 =================================
    // var imgClickHandler = function(e){
    //     console.log("img가 클릭되었어요");
    //     var clickedImg = e.target;

    //     var img = section.querySelector(".stage img");
    //     img.src = clickedImg.src;
    // };

    // for(var i in imgs)
    //     imgs[i].onclick = imgClickHandler;


    // === 초기 버전 ==========================================
    
    // imgs[0].onclick = function(e){
    //     var clickedImg = e.target;
        
    //     var img = section.querySelector(".stage img");
    //     //현재 클릭된 이미지의 객체를 얻을 수만 있다만 이 부분을 제대로 고칠 수 있을텐데
    //     // 문제점 1 : 수정할 때 html과 js를 같이 수정해야 한다.
    //     // 문제점 2 : imgs[0]/[1]/[2] 클릭 이벤트 핸들러가 거의 동일하지만 하나로 합칠 수가 없다.
    //     //           만약에 img.src = clickedImg.src; 처럼 한다면 함수 하나로 끝일텐데
    //     img.src = clickedImg.src;
    //     // img.src = "../../images/img1.jpeg";
    // };


});

// --- ex6 - 엘리먼트 흩뿌리기와 위치 바꾸기 ------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#ex6");
    var scatButton = section.querySelector(".scat-button");
    var swapButton = section.querySelector(".swap-button");
    var divBoxes = section.querySelectorAll(".stage div");

    // 지금 이 코드에는 해서는 안되는 코드가 남아 있습니다.
    // 다음에 더 공부하면 알게 될 내용임. 지금은 일단 그렇다는 거로다가
    for(var i=0; i<divBoxes.length; i++)
        divBoxes[i].onclick = function(e){
            for(var key in e)
                console.log(key);
           //클릭된 놈.style.background = "pink";
        };

    var offset = 1;
    scatButton.onclick = function(){
        for(var i=0; i<divBoxes.length; i++)
            divBoxes[i].className = "pos"+i;
            //divBoxes[i].classList.add("pos"+i);
    };

    swapButton.onclick = function(){
        offset++ // -> 0->1->2...->5->0->1

        for(var i=0; i<divBoxes.length; i++){
            divBoxes[i].className = "pos"+(i+offset)%3;
            //divBoxes[i].classList.add("pos"+(i+offset)%6);
        }
    };
});
// --- ex5 - setTimeout/setInterval ------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#ex5");
    var countDownButton = section.querySelector(".countdown-button");
    var countDownTextBox = section.querySelector(".status input");
    var countDownSpan = section.querySelector(".progress span:last-child");
    var progressBar = section.querySelector(".progress span:first-child");
    var tid = null;

    countDownButton.onclick = function(){
        if(tid != null)
            return;

        // progressBar 의 현재 너비를 알아내서
        var style = window.getComputedStyle(progressBar);
        var width = parseInt(style.width); // px 단위가 잘려 나감.
        // 100분율로 계산해서 얼마나 수치를 줄여야 하는지 단위 크기를 정한다.
        var size = width/10;
        console.log(size);

        tid = setInterval(function(){
            var count = parseInt(countDownTextBox.value);
            count--;

            if(count < 0){
                clearInterval(tid);
                tid = null;
                return;
            }
            countDownTextBox.value = count;
            countDownSpan.innerText = count;              
            width -= size;
            progressBar.style.width = width + "px";


        }, 1000);

    };
});

// --- ex4 -Selectors API ------------------------------------
window.addEventListener("load", function(){
    //var section = document.getElementById("ex4");
    var section = document.querySelector("#ex4");
    //var moveButton = section.getElementsByClassName("move-button")[0];
    var moveButton = section.querySelector(".move-button");
    var box1 = section.querySelector(".stage div:first-child");    
    var box2 = section.querySelector(".stage div:first-child+div");

    moveButton.onclick = function(){
        // CSS에 설정한 초기 값은 getComputedStyle로 스타일을 계산한 객체를 통해서 얻는구나
        var box1Style = window.getComputedStyle(box1);

        var left = box1Style.left;
        var top = box1Style.top;

        console.log(left + ", " + top);

        box2.style.left = left;
        box2.style.top = top;
    };

});

// --- ex3 -노드 순회하기------------------------------------
window.addEventListener("load", function(){
    var section = document.getElementById("ex3");
    var prevButton = section.getElementsByClassName("prev-button")[0];
    var nextButton = section.getElementsByClassName("next-button")[0];
    var upButton = section.getElementsByClassName("up-button")[0];
    var downButton = section.getElementsByClassName("down-button")[0];
    var stage = section.getElementsByClassName("stage")[0];

    var currentNode  = stage.getElementsByClassName("current")[0];
    

    prevButton.onclick = function(){        
        if(currentNode.previousElementSibling == null){
            alert("더 이상 앞의 엘리먼트가 존재하지 않습니다.");
            return;
        }

        currentNode.classList.remove("current");
        currentNode = currentNode.previousElementSibling;
        currentNode.classList.add("current");      
        idx--;
    };

    nextButton.onclick = function(){
        // 노드와 엘리먼트 노드의 차이점 : 노드 타입 12가지 알아볼 것
        // 노드 순회할 때의 유의점
        currentNode.classList.remove("current");
        currentNode = currentNode.nextElementSibling;
        //currentNode.nextSibling.classList.add("current");
        currentNode.classList.add("current");
        idx++;
    };

    upButton.onclick = function(){
        currentNode.classList.remove("current");
        currentNode = currentNode.parentElement;
        console.log(currentNode.nodeName);
        currentNode.classList.add("current");   
    };
    var idx = 0;
    downButton.onclick = function(){
        currentNode.classList.remove("current");
        currentNode = currentNode.children[idx];//currentNode.firstElementChild;
        currentNode.classList.add("current"); 
    };

});
// --- ex2 -스타일 다루기 예제------------------------------------
window.addEventListener("load", function(){
    var section = document.getElementById("ex2");
    var widthTextBox = section.getElementsByClassName("width")[0];
    var heightTextBox = section.getElementsByClassName("height")[0];
    var button = section.getElementsByClassName("button")[0];
    var img1 = section.getElementsByTagName("img")[0];

    // 스타일 속성을 다룰 때의 주의 사항
    var borderStyleTextBox = section.getElementsByClassName("border-style")[0];
    var borderColorTextBox = section.getElementsByClassName("border-color")[0];
    var borderWidthTextBox = section.getElementsByClassName("border-width")[0];
    

    button.onclick = function(){
        var width = widthTextBox.value+"px"; // 1번 주의 사항 : 수치의 단위는 반드시 포함해야 함.
        var height = heightTextBox.value+"px";
        var borderStyle = borderStyleTextBox.value;
        var borderColor = borderColorTextBox.value;
        var borderWidth = borderWidthTextBox.value;

        img1.style.width = width;
        img1.style.height = height;
        img1.style["border-style"] = borderStyle;
        img1.style.borderColor = borderColor;
        var attrName = "border-width";
        img1.style[attrName] = borderWidth;
    };

});
// --- ex1 -------------------------------------
window.addEventListener("load", function(){
    var section = document.getElementById("ex1");
    var xTextBox = section.getElementsByClassName("x")[0];
    var yTextBox = section.getElementsByClassName("y")[0];
    var calcButton = section.getElementsByClassName("calc")[0];
    var resultTextBox = section.getElementsByClassName("result")[0];

    calcButton.onclick = function(){
        var x  = parseInt(xTextBox.value);
        var y  = parseInt(yTextBox.value);

        var result  = x + y;
        resultTextBox.value = result;        
    };

});

/*
    스크립트 언어 + 플랫폼(DOM+CSSOM):문서가 메모리에 로드 된 객체
    1. id로 엘리먼트(노드객체)를 이용할 수 있다.
    2. document.getElementById("이름:id");
    3. 엘리먼트 객체를 얻으면 그 속성은 모두 html 속성과 같다.
       txt.value = 3;
    4. 스타일 속성은? txt.style.width 단. 주의  border-width(X) borderWidth
    5. 노드 순회... html 속성과는 거리먼 노드만의 속성들과 기능들
       노드가 뭐냐 => w3에서 제공하는 객체 모델의 추상 인터페이스
       문서내의 모든 요소들은 객체화 될 때 각자 형식을 가진다.
       tag -> Element (Node)
       text -> Text (Node)
       comment -> Comment (Node)
       Node는 모든 형식의 노드를 대상을 ㅗ하지만
       ElementNode라고 하면 Element 만을 대상으로 한다.는 말을 하고 싶었던 것이었다니요..




*/