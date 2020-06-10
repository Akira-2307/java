<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.board"%>
<%@page import="dao.dao"%>
<!doctype html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
</head>
<body>
<form action ="/javascript/insert">
	<input type=submit value="投稿画面">
</form>


	<%
	ArrayList<board> List = (ArrayList<board>)request.getAttribute("list");
	for(int i = 0 ; i < List.size() ; i++){
		board b = List.get(i);
	%>

	<form id="form">
		<p>--------------------------------------------------------------------------</p>
		<p id="<%=i %>">コメント：<%=b.getComment() %></p>
		<p id ="<%=i %>">投稿者：<%=b.getName() %> メールアドレス：<%=b.getMail() %> 投稿時間:<%=b.getTime() %> 編集時間:<%=b.getTime2() %></p><br>

	</form>
	<%
	}
	%>
	<form action ="/javascript/submission">
		編集する投稿者名
		<input type="text" name="name" id="name">
	    <input type="submit" value="削除">
	</form>
	<form action ="/javascript/change">
		編集する投稿者名
		<input type="text" name="name" id="name">
	    <input type="submit" value="編集">
	</form>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>








</script>
​
</body>
</html>