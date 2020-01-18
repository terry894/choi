<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <main>
                <h2 class="main title">회원가입 폼</h2>
                
                <div class="breadcrumb" style="margin-top:-20px;">
                    <h3 class="hidden">경로</h3>
                    <img src="../images/member/step2.png" alt="회원가입 1단계" />
                </div>
                
                <script>
                	window.addEventListener("load", function(){
                		var idText = document.querySelector("#id-text");
                		var pwd1Input = document.querySelector("#pwd1-input");
                		var pwd2Input = document.querySelector("#pwd2-input");
                		var form1  = document.querySelector("#form1");
                		                		
                		var duplicatedStateSpan = document.querySelector("#duplicated-state");
                		
                		var tid = null;
                		var idChecked = false;
                		
                		idText.
                		
                		form1.onsubmit = function(e){
                			/* if(!idChecked) {
                				idText.setCustomValidity("유효하지 않은 아이디입니다.");
                				e.preventDefault();
                			}
                			else
                				idText.setCustomValidity("");          */        				
                		};
                		
                		idText.oninput = function(e){
                			var id = idText.value;
                			//   "get-member?id="+id
                			var request = new XMLHttpRequest();
                			request.open("GET", "duplicated?id="+id);
                			request.onload = function(){
                				if(request.responseText == "false"){
                					idChecked = true;
                					duplicatedStateSpan.innerText = "사용가능한 아이디입니다.";
                				}
                				else{
                					idChecked = false;
                					duplicatedStateSpan.innerText = "이미 사용중인 아이디입니다.";
                				}
                			};
                			
                			if(tid != null){
                				clearTimeout(tid);
                				tid = null;
                			}
                			
                			tid = setTimeout(function() {							
	                			request.send();
	                			tid = null;
							}, 500);
                		};
                		                		
                		pwd2Input.oninput = function(){
                			if(pwd1Input.value != pwd2Input.value)
                				pwd2Input.setCustomValidity("아따 거시기 비밀번호가 일치하지 않습니다.");
                			else
                				pwd2Input.setCustomValidity("");
             
                		};
                		
                	});
                </script>
                <style>
                
                	input[type='text']:invalid,
                	input[type='password']:invalid,
                	input[type='number']:invalid {
                		background: red;
                	}
                	input:invalid:required	{
                		background-image: linear-gradient(to right, pink, lightgreen);
                	}
                	input:valid{
                		background: lime;
                	}
                </style>
                <form id="form1" method="post">
                    <fieldset>
                        <legend class="hidden">회원정보</legend>
                        <table class="table margin-top first">
                            <tbody>                                
                                <tr>
                                    <th><label>아이디</label></th>
                                    <td colspan="3" class="text-align-left indent">
                                        <input id="id-text" type="text" name="id" class="width-half"  required  value="" placeholder="영문과 숫자 6~20자 이내" pattern="^\w{6,20}$" />
                                        <input class="btn-text btn-default" type="button"  autocomplete="off" id="id-check-button" value="중복확인" >	
                                        <span id="duplicated-state"></span>							
                                    </td>
                                </tr>
                                <tr>
                                    <th><label>비밀번호</label></th>
                                    <td colspan="3" class="text-align-left indent">
                                        <input id="pwd1-input"  type="password" name="pwd" class="" required placeholder="비밀번호 입력" >
                                    </td>
                                </tr>
                                <tr>
                                    <th><label>비밀번호 확인</label></th>
                                    <td colspan="3" class="text-align-left indent"><input id="pwd2-input" class="" name="pwd2" type="password" required /></td>
                                </tr>
                                <tr>
                                    <th><label>이름</label></th>
                                    <td colspan="3" class="text-align-left indent"><input class="width-half" name="name" type="text"  value="" required="required"/></td>
                                </tr>
                                <!-- <tr>
                                    <th><label>필명</label></th>
                                    <td colspan="3" class="text-align-left indent"><input class="width-half" name="nicname" type="text" /></td>
                                </tr> -->
                                <tr>
                                    <th><label>성별</label></th>
                                    <td colspan="3" class="text-align-left indent">
                                        
                                        
                                        <input class="width-half" name="gender" placeholder="선택"  required list="gender-list" >
                                        <datalist id="gender-list">
                                            <option  value="남성">남성</option>
                                            <option  value="여성">여성</option>
                                        </datalist>
                                        <!-- 
                                        <select class="width-half" name="gender" required>
                                            <option value="">선택</option>
                                            <option  value="남성">남성</option>
                                            <option  value="여성">여성</option>
                                        </select> -->
                                    </td>
                                </tr>
                                <tr>
                                    <th><label>생년월일</label></th>
                                    <td colspan="3" class="text-align-left indent">
                                        
                                        
                                        
                                        
                                        <input name="birthday" type="date" class="width-half" required placeholder="예) 2000-02-17"  value=""/>
                                        <!-- <input name="y" type="text" class="width-small margin-hor" style="margin-left:0px;" />년
                                        <input name="m" type="text" class="width-small margin-hor" />월
                                        <input name="d" type="text" class="width-small margin-hor" />일 -->
                                        <input type="radio" name="isLunar" value="0" class="vertical-middle margin-hor" checked />양력
                                        <input type="radio" name="isLunar" value="1" class="vertical-middle margin-hor"  />음력
                                    </td>
                                </tr>
                                <tr>
                                    <th><label>나이</label></th>
                                    <td colspan="3" class="text-align-left indent"><input name="age" type="number" min="1" max="200" class="width-half" placeholder="1~200 세까지의 값을 입력하세요" required  value=""/></td>
                                </tr>
                                <tr>
                                    <th><label>핸드폰번호</label></th>
                                    <td colspan="3" class="text-align-left indent"><input name="phone" type="text" class="width-half" pattern="^01[016789]-\d{3,4}-\d{4}$" placeholder="예) 010-1111-2222" required  value=""/></td>
                                </tr>
                                <tr>
                                    <th><label>이메일</label></th>
                                    <td colspan="3" class="text-align-left indent"><input name="email" type="email" class="width-half" required placeholder="예) user@newlecture.com"  value=""/></td>
                                </tr>                                
                                                                
                                <tr>
                                    <td colspan="4">
                                        <input type="hidden" name="" value="" />
                                        <input id="submit-Button" type="submit" name="btn" value="확인" style="height: 30px; margin:20px;" class="btn-text btn-default" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </main>