<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>達成おめでとう！</title>
</head>
<body>
    <form method="post" action="RewardServlet">
        <input type="hidden" name="name" value="${loggedInAccount.name}">
        <input type="hidden" name="pass" value="${loggedInAccount.pass}">
    </form>
    <div class="bg_pattern Crown"></div>
		<h1>達成おめでとう！</h1>

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
    
    <div class="images"></div>
    <style>
    	@charset "UTF-8";

		* {
			margin:0;
			padding:0;
			font-family: 'Zen Kaku Gothic Antique', sans-serif;
			box-sizing: border-box;
		}

		body {
		 	color: #0B1013;
		    /*background-color: #E0E0E0;*/
		    display: flex;
		    flex-direction: column;
		    text-align: center;
		    justify-content: center;
		    align-items: center;
		    margin: 0 auto;
		    height: 100vh; /* 画面の高さいっぱいに表示 */
		    /*overflow-x: hidden;/* 横スクロールのみ禁止 */
		}
		h1{
			font-size: 35px;
			margin-bottom: 20px;
		}

		.flexitem{
			font-size: 30px;
		}

		.images {
			/*display: inline-block;*/
			background-image: url('images/goal.png');
			background-size: cover;
		  	height: 300px;
		  	width: 300px;
		}

		.bg_pattern {
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 100vw;
		    height: 100vh;
		    background-color: #e6e6fa;
		    opacity: 0.4;
		    z-index: -1;
		}
		
		.Crown {
		    background-image: linear-gradient(45deg, #ffff00 25%, transparent 25%), linear-gradient(315deg, #ffff00 25%, #e6e6fa 25%);
		    background-position: 10px 0, 20px 0, 0 0, 0 0;
		    background-size: 20px 20px;
		    background-repeat: repeat;
		}

    </style>
</body>
</html>
