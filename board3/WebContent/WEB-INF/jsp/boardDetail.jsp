<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
</head>
<body>

	<c:if test="${loginUser.i_user == data.i_user}">
		<div>
			<a href="/boardDetail?i_board=${data.i_board}&typ=mod"><button>수정</button></a>
			<a href="/boardDel?i_board=${data.i_board}"><button>삭제</button>	</a>
		</div>
	</c:if>

	<div>${data.title}, ${data.ctnt}, ${data.r_dt}, ${data.userNm}</div>
	<script>
	if ('${msg}' != "") {
		alert('${msg}');
	}
	</script>
</body>
</html>