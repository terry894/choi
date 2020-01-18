<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<main>
<h2 class="main title">공지사항 등록</h2>

<div class="breadcrumb">
	<h3 class="hidden">breadlet</h3>
	<ul>
		<li>home</li>
		<li>게시글 관리</li>
		<li>공지사항</li>
	</ul>
</div>

<form action="reg" method="post" enctype="multipart/form-data">
	<div class="margin-top first">
		<h3 class="hidden">공지사항 입력</h3>
		<table class="table">
			<tbody>
				<tr>
					<th>제목</th>
					<td class="text-align-left text-indent text-strong text-orange"
						colspan="3"><input type="text" name="title" /></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3" class="text-align-left text-indent"><input
						type="file" name="file" /></td>
				</tr>
				<style>
#drop-zone::after {
	content: ", 여기에 드래그 하세요";
}

.invalid {
	background: red;
}

.invalid::after {
	content: ", 유효한 콘텐츠가 아닙니다" !important;
	/*  id가 더 우선순위에 있기 변경 안됨  -> !important 로 우선순위 변경 */
}

.normal {
	background: white;
}

#progress-bar {
	background: green;
	width: 40px;
	height: 20px;
	text-align: right;
}

#progress-bar>span {
	line-height: 20px;
	right: -25px;
	position: relative;
}
</style>
				<tr>
					<td id="drop-zone" colspan="4" style="height: 100px">
						<div id="progress-bar">
							<span>여기</span>
						</div>
					</td>


					<script>
						window
								.addEventListener(
										"load",
										function() {
											var dropZone = document
													.querySelector("#drop-zone");
											dropZone.ondragenter = function(e) {
												e.stopPropagation(); //상위전파 중단 
												e.preventDefault(); //
												if (e.dataTransfer.types != "Files")
													// 전송되는 파일 형식 = Files인 것
													dropZone.classList
															.add("invalid")
													//class 이름 "invalid" 를 추가 

												console.log("enter");
											};
											dropZone.ondragleave = function(e) {
												e.stopPropagation();
												e.preventDefault();

												console.log("leave");
												dropZone.classList
														.remove("invalid")
											};
											dropZone.ondragover = function(e) {

												e.stopPropagation();
												e.preventDefault();

												console.log("over");
											};

											dropZone.ondrop = function(e) {

												e.stopPropagation();
												e.preventDefault();

												if (e.dataTransfer.files.length > 1)
													alert("파일은 하나 초과 전송 불가능")

												var file = e.dataTransfer.files[0];

												if (file.size > 1024 * 1024 * 200) {
													alert("200메가 이상 초과 할 수 없습니다");
												}
												//if(file.type.indexof("image"))

												var formData = new FormData();
												formData.append("file", file);
												formData.append("title", "하하하");

												var request = new XMLHttpRequest();
												request.upload
														.addEventListener(
																"progress",
																function(e) {
																	//200메가 -> 작은 버퍼에 담김 거기서 조금씩 보냄 progress과정에서 일어나고 있는 일을 event 객체가 보여줌 
																	// e.loaded: 지금 까지 전송한 크기, e.total: 파일 크기 
																	//자주 쓰는 수학 공식 : 수열 백터 행렬 비례 다항식
																	//  :  
																	var rate = (e.loaded / e.total) * 100;
																	var progressBar = document
																			.querySelector("#progress-bar");
																	progressBar.style.width = rate
																			- 10
																			+ "%";

																});
									          	request.addEventListener(
														"load", function() {
															var ul = document.querySelector("#explorer ul");
															
															var request = new XMLHttpRequest();
												
															request.addEventListener(
																	"load", function() {
																		var json = JSON.parse(request.responseText);
																		ul.innerHTML="";
																		for(var i=0 ; i<json.length ;i++){
																		var li = document.createElement("li");
																		li.innerHTML='<a download href="/resource/upload/'+json[i].name+'">'+json[i].name+'</a>(<a href="del?n=">삭제</a>)';
																		ul.append(li)
																		}
																	});

															request.open("GET", "explorer?path=resource/upload");
															//explorer에 경로 전달 
															request.send();

															

														}); 

												request.open("POST", "upload");
												request.send(formData);

												console.log("drop");

												dropZone.classList
														.remove("invalid")
											};

										});
					</script>
					<style>
					
					#explorer{
				     height:100px;
					}
					</style>
				<tr >
					<td id="explorer" colspan=4>
					<ul>
					    <li>test.txt</li>
					</ul>
					</td>
				</tr>
				<script src="../../../resource/js/lib/explorer.js"></script>
				<script>
				var Explorer = com.newlecture.Explorer;
				
				var explorer= new Explorer("#explorer");
				explorer.setResourceURL("explorer?path=resource/upload");
				explorer.load();
				
				
				</script>
				</tr>

				<!--                         <tr>
                                    <th>첨부파일</th>
                                    <td colspan="3" class="text-align-left text-indent">
                                    <input type="file" name="file" /> </td>
                                </tr> -->
				<tr class="content">
					<td colspan="4"><textarea class="content" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" class="text-align-right"><input
						class="vertical-align" type="checkbox" id="open" name="open"
						value="true"><label for="open" class="margin-left">바로공개</label>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="margin-top text-align-center">
		<input class="btn-text btn-default" type="submit" value="등록" /> <a
			class="btn-text btn-cancel" href="list">취소</a>
	</div>
</form>

</main>