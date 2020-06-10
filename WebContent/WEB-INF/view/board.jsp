<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.util.ArrayList"%>
<%@page import="board.board"%>
<%@page import="dao.dao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
</head>
<body>
	<form action="/javascript/manegement" method="get">
	<input type="submit" value="管理者画面">
	</form>

	<form action="/javascript/toppage" method="post">
	    <label for="name">名前</label><br />
	    <input type="text" name="name" id="name">
	    <br><br>
	    <label for="mail">メールアドレス</label><br />
	    <input type="text" name="mail" id="mail">
	    <br><br>
	    <label for="comment">メッセージ</label><br />
	    <input type="text" name="comment" id="comment">
	    <br><br>
	    <input type="submit" value="投稿">
 	</form><br><br>
 	<img src="./upload/?"">
 	<form method="POST" enctype="multipart/form-data" action="/javascript/upload">
	<input type="file" name="file"/><br />
	<input type="submit" value="アップロード" />
	</form>


 <br><br>

	<%
	ArrayList<board> List = (ArrayList<board>)request.getAttribute("list");
	for(int i = 0 ; i < List.size() ; i++){
		board b = List.get(i);
	%>

	<form id="form">
		<p>--------------------------------------------------------------------------</p>
		<p>コメント：<%=b.getComment() %></p>
		<p>投稿者：<%=b.getName() %> メールアドレス：<%=b.getMail() %> 投稿時間:<%=b.getTime() %> 編集時間:<%=b.getTime2() %></p>
	</form>


	<%
	}
	%>



</body>
</html>