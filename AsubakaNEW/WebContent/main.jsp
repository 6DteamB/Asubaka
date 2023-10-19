<title>あすばか</title>

</head>
<body>
	<h1>明日やろうは馬鹿野郎</h1>
<div class="main">
	<div class="containerA">
		<!-- ランダムな名言 -->
		<div class="quote">
	   		<c:out value="${randomQuoteAndAuthor}" />
	   	</div>
		<!-- ランダムな犬・猫の画像 -->
		<div class="image-container">
	    	<img src="${animalImagePath}" alt="動物の画像" />
		</div>
	</div>
	
	<div class="center-container">
		<!-- 目標の詳細-->
		<div>
			<h2>目標</h2>
			<c:out value="${account.objective}" />
		</div>
	
		<!-- その日の目標達成を確認するボタン -->
		<div>
			<h2>今日の習慣</h2>
			<form method="post" action="DayServlet.java">
			    <input type="hidden" name="name" value="${account.name}">
	    		<input type="hidden" name="pass" value="${account.pass}">
				<button id="achievedButton" class="button">やった！</button>
			</form>
		</div>
	
		<!-- 66日間の継続達成度の表示 プログレスバー-->
		<div>
			<h2>達成度</h2>
			<progress value="<c:out value="${account.count}" />" max="66"></progress>
			<c:out value="${account.count}" />
			/日 / 66日
		</div>
	
		<!-- 残り日数-->
		<p>
			残り日数:
			<c:out value="${account.day}" />
			日
		</p>
		<!-- カレンダーの表示 -->
		<h1 id="calendar-title"></h1>
		<div id="calendar" class="calendar-wrap"></div>
		<button id="prevMonth" class="month">前の月</button>
		<button id="nextMonth" class="month">次の月</button>
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
			font-size: 25px;
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
			margin: 20px 0px 0px 0px;
		}
		
		 /*猫画像*/
		.image-container {
		    display: flex;
		    justify-content: center;  
		    align-items: center;
		}
		
		.image-container img{
    		width: 450px;
    		height: 450px;
    		object-fit: cover;/* 縦横比を保持 */
    		border-radius:15px;
    		box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    		margin:0px 0px 20px 0px;
		}
		
		/*名言*/
		.quote{
			inline-size: 560px;
  			overflow-wrap: break-word;
  			text-align: center; 
			position: relative;
			margin: 2em 0 2em 75px;
			padding: 10px;
			background: #43676b;
			border-radius: 30px;
			color: #fff;
		}
		
		.quote:before {  
		    content: "";
		    position: absolute;
		    left: -38px;
		    width: 13px;
		    height: 12px;
		    bottom: 0;
		    background: #43676b;
		    border-radius: 50%;
		}
		
		.quote:after {
		    content: "";
		    position: absolute;
		    left: -24px;
		    width: 20px;
		    height: 18px;
		    bottom: 3px;
		    background: #43676b;
		    border-radius: 50%;
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
		    background-color: #5b6b76;
		    color: #fff;
		    font-weight: 600;
		    font-size: 1em;
		}
		
		.button:hover {
		    animation: anime-button .3s linear infinite;
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


		/* レスポンシブ*/
		@media (max-width: 600px) {
		    .image-container img {
		        width: 450px;
		        height: 450px;
		    }
		    .main{
			 	display:block;
		 	}
		}
		
	</style>
</body>
</html>