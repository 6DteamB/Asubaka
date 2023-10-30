<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name=”viewport” content=”width=device-width,initial-scale=1″>
    <title>達成おめでとう！</title>
</head>
<body>
    <form method="post" action="RewardServlet">
        <input type="hidden" name="name" value="${loggedInAccount.name}">
        <input type="hidden" name="pass" value="${loggedInAccount.pass}">
    </form>
    
    <div class="cat">
    
		<h1>Congratulations!</h1>
	
	    <div class="flexbox">
	    	<div class="flexitem">
			    <c:out value="${loggedInAccount.name}さん" />
			</div>
			<div class="flexitem">
			    <c:out value="達成した目標: ${loggedInAccount.objective}" />
			</div>
			<div class="flexitem">
			    <c:out value="ご褒美: ${loggedInAccount.reward}" />
			</div>
	   </div>
   </div>

    <style>
    	@charset "UTF-8";
		@import url('https://fonts.googleapis.com/css2?family=Zen+Kaku+Gothic+Antique:wght@300;400;500&display=swap');
		
		* {
			margin:0;
			padding:0;
			font-family: 'Zen Kaku Gothic Antique', sans-serif;
			box-sizing: border-box;
		}

		body {
			background: #1f3134;
		 	color: #e7e7eb;
		    display: flex;
		    flex-direction: column; 
		    text-align: center;
		    justify-content: center;
		    align-items: center;
		    margin: 0 auto;
		    /*overflow-x: hidden;/* 横スクロールのみ禁止 */
		}
		
		h1{
			font-size:7em;
			text-align:center;
			/*line-height: 0.95em;*/
			font-weight:bold;
			color: transparent;
			background: repeating-linear-gradient(45deg,
				#F5B090 0.1em 0.2em,
				#FCD7A1 0.2em 0.3em,
				#FFF9B1 0.3em 0.4em,
				#A5D4AD 0.4em 0.5em,
				#A3BCE2 0.5em 0.6em,
				#A59ACA 0.7em 0.8em,
				#CFA7CD 0.8em 0.9em);
			-webkit-background-clip: text;
			letter-spacing: .1em;
		}

		.flexbox{
			margin-top: 20px;
			letter-spacing: .5em;
		}
		
		.flexitem{
			font-size: 45px;
			font-weight: bold;
		}
		
		/*猫の画像*/
		.cat {
			/*display: inline-block;*/
			background-image: url('images/14.jpg');
			background-size: cover;
		  	/*height: 500px;
		  	width: 500px;*/
		  	width: 100%;
		  	height: 100vh;
			background-position: center center;
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-color: #1f3134;
		}
		
		@media (max-width: 900px) {
			h1{
				font-size: 10vw;
			}
		}
		
    </style>
    
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/canvas-confetti@1.3.2/dist/confetti.browser.min.js"></script>
	<script>
		var end = Date.now() + (15 * 1000);
	
		// go Buckeyes!
		var colors = ['#fcc800', '#706caa', '#e95464'];
	
		(function frame() {
		  confetti({
		    particleCount: 3,
		    angle: 60,
		    spread: 55,
		    origin: { x: 0 },
		    colors: colors
		  });
		  confetti({
		    particleCount: 3,
		    angle: 120,
		    spread: 55,
		    origin: { x: 1 },
		    colors: colors
		  });
	
		  if (Date.now() < end) {
		    requestAnimationFrame(frame);
		  }
		}());
	</script>
</body>
</html>
