<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.AccountDAO"%>
<%@ page import="model.Login"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><title>あすばか</title>

</head>
<body>
	<!--<h1>明日やろうは馬鹿野郎</h1>-->
<div class="main">
	<div class="containerA">
		<!-- 目標の詳細-->
		<div class="card goal">
			<!--<h2>目標</h2>-->
			<p><c:out value="${account.objective}" /></p>
		</div>
		<!-- ランダムな名言 -->
		<div class="quote">
	   		<c:out value="${randomQuoteAndAuthor}" />
	   	</div>
		<!-- ランダムな犬・猫の画像 -->
		<div class="image-container">
	    	<img src="${animalImagePath}" alt="動物の画像" />
		</div>		
	</div>
	
	<div class="containerB">
		<!-- カレンダーの表示 -->
		<h1 id="calendar-title"></h1>
		<div id="calendar" class="calendar-wrap"></div>
		<button id="prevMonth" class="month">前の月</button>
		<button id="nextMonth" class="month">次の月</button>
		
		<!-- 代わりに使用するイラストを表示する要素 -->
		<td>
		    <span class="date">${day}</span>
		    <div class="custom-checkbox" data-date="${date}">
		        <img class="mark-icon" src="images/mark.png" alt="Mark">
		    </div>
		</td>
		
		
		<!-- その日の目標達成を確認するボタン -->
		<div>
			<h2>今日の習慣</h2>
			<form method="post" action="DayServlet.java">
			    <input type="hidden" name="name" value="${account.name}">
	    		<input type="hidden" name="pass" value="${account.pass}">
				<button id="achievedButton" class="button">やった！</button>
			</form>
		</div>
		
		<!-- 残り日数-->
		<p>
			残り日数:
			<c:out value="${account.day}" />
			日
		</p>
		
		<!-- 66日間の継続達成度の表示 プログレスバー-->
		<div class="achieve">
			<h2>達成度</h2>
			<progress value="<c:out value="${account.count}" />" max="66"></progress>
			<c:out value="${account.count}" />
			/日 / 66日
		</div>

	</div>
		
</div>

	<script src="script.js"></script>

	<style>
	
		* {
			margin: 0;
			padding: 0;
			font-family: 'Zen Kaku Gothic Antique', sans-serif;
		}
		
		html,body{
			height:100%;
			width:100%;
			margin-left : auto;
			margin-right : auto;
			text-align : center;
		}
		
		body {
			color: #0B1013;
			background-color: #E0E0E0;
		}
		
		h1 {
			text-align: center;
			font-size: 35px;
			padding: 15px 0 0 0;
		}
		
		h2,p {
			text-align: center;
			font-size: 20px;
			margin-top: 10px;
		}
		
		/*２段組み*/
		.main{
		 	display:flex;
		 }
		 .containerA{
		 	margin: 10px;
			width: 800px;
            float: right;
		}
		
		.containerB{
			margin: 50px 0px 0px 30px;
		}
		
		 /*猫画像*/
		.image-container {
		    display: flex;
		    justify-content: center;  
		    align-items: center;
		}
		
		.image-container img{
    		width: 400px;
    		height: 400px;
    		object-fit: cover;/* 縦横比を保持 */
    		border-radius:15px;
    		box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    		margin:0px 0px 20px 0px;
		}
		
		/*名言*/
		.quote {
		    position: relative;
		    padding: 1rem;
		    border-radius: 10px;
		    border: 1px solid #ffff7a;
		    background-color: #ffff7a;
		    color: #0B1013;
		    font-weight: bold;
		    font-size: 18px;
		    margin-bottom: 20px;
		} 
		
		.quote:before {
		    position: absolute;
		    top: 3px;
		    left: 3px;
		    width: 100%;
		    height: 100%;
		    border-radius: 10px;
		    border: 3px solid #0C4842;
		    content: "";
		}
		
		.quote:after {
		    position: absolute;
		    bottom: -31px;
		    left: 50px;
		    transform: skew(-25deg);
		    height: 25px;
		    width: 15px;
		    border-right: 3px solid #333;
		    background-color: #E0E0E0;
		    content: "";
		}
		
		/*目標*/
		.card {
		    position: relative;
		    padding: .5rem .5rem 1rem .5rem ;
		    margin: 30px 20px 10px 170px;
		    width: 450px;
		    height: 40px;
		    border: 2px solid #2E5C6E;
		    border-radius: 5px;
		    background-color: #efefef;
		    justify-content: center;  
		    align-items: center;
		}
		
		.card.goal::before {
		    position: absolute;
		    top: -2.05rem;
		    left: 50%;
		    width: 30px;
		    height: 15px;
		    line-height: 5px;
		    transform: translateX(-50%);
		    border-radius: 30px 30px 0 0;
		    border: 2px solid #333;
		    border-bottom: transparent;
		    padding: 1rem 1rem 0 1rem;
		    background: #2E5C6E;
		    color: #FBE251;
		    font-size: 0.8rem;
		    text-align: center;
		    text-transform: uppercase;
		    content: '目標';
		}
		
		.card p {
		    color: #0B1013;
		    line-height: 1.5;
		}
		
		/*やったボタン*/
		.button {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    width: 250px;
		    margin: 0 auto;
		    padding: .9em 2em;
		    border: none;
		    border-radius: 25px;
		    background-color: #2E5C6E;
		    color: #FBE251;
		    font-weight: 600;
		    font-size: 1em;
		}
		
		.button:hover {
		    animation: anime-button .3s linear infinite;
		}
		/* チェックボックスを非表示にするスタイル */
		/* input[type="checkbox"] {
    		display: none;
		} */

		/* 代わりに使用するイラストのスタイル */
		.custom-checkbox {
		    display: inline-block;
		    width: 20px; /* 適切な幅と高さを設定してください */
		    height: 20px;
		    background: url('images/mark.png') no-repeat;;
		    background-size: contain;
		    cursor: pointer;
		} 
		
		/* チェックされた日付にイラストを表示するスタイル */
		.custom-checkbox.checked {
		    background: url('images/mark.png') no-repeat;;
		    background-size: contain;
		}
		
		/* 初めはマークアイコンを非表示にする */
	 	.custom-checkbox .mark-icon {
		    display: none;
		} 
		
		/* .checked クラスが追加されたときにマークアイコンを表示する */
	 	.custom-checkbox.checked .mark-icon {
		    display: inline-block;
		}
 
		
		
		@keyframes anime-button {
		    20% {
		        transform: translate(-2px, 2px);
		    }
		    40% {
		        transform: translate(-2px, -2px);
		    }
		    60% {
		        transform: translate(2px, 2px);
		    }
		    80% {
		        transform: translate(2px, -2px);
		    }
		}
		
		/* カレンダーの月移動ボタン*/
		.month {
			justify-content: center;
			align-items: center;
			padding: .5em 1em;
			margin:0 auto;
		    border: 1px solid #49535b;
		    border-radius: 25px;
		    background-color: #fff;
		    color: #49535b;
		}
		
		/* カレンダー*/
		
		#calendar-title{
			font-size: 20px;
			margin-top: 20px;
		}
		#calendar td {
		    padding: 8px;   
		}


		/* レスポンシブ */
		@media (max-width: 900px) {
		    .main {
		        flex-direction: column;
		    }
		
		    .containerA,
		    .containerB {
		        width: 80%;
		        margin: 0 auto; /* 左右の余白を自動調整 */
		    }
		    
		    #calendar{
				padding: 4% 20%;
				margin: 0 auto;
				margin-right: 20px;
			}
		
		    .image-container img {
		        width: 80%; /* 画像の幅を80%に設定 */
		    }
		    
		    .card{
		    	margin: 0px auto;
		    	margin-top: 35px;
		    	margin-bottom: 20px;
		    	width: 80%;
		    }
		    
		    .achieve{
		    	margin-bottom: 4%;
		    }
		}
		
		@media (max-width: 600px) {
			#calendar{
				padding: 4% 15%;
			}
		}
		
	</style>
</body>
</html>