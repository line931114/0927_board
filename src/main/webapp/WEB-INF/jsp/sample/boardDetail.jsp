<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<%@ include file="/WEB-INF/include-header.jspf" %>
</head>
<body>

	<table class="board_view">
	
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<caption>게시글 상세</caption>
	
		<tbody>
			<tr>
				<th scope="row">글 번호 </th>
				<td>${map.IDX }</td>
				<th scope="row">조회수</th>
				<td>${map.HIT_CNT }</td>
			</tr>
			
			<tr>
				<th scope="row">작성자</th>
				<td>${map.CREA_ID }</td>
				
				<th scope="row">작성시간</th>
				<td>${map.CREA_DTM }</td>
			</tr>
			
			<tr>
				<th scope="row"> 제목</th>
				<td colspan="3">${map.TITLE }</td>
			</tr>
			
			<tr>
				<td colspan="4">${map.CONTENTS }</td>
			</tr>
			
			<tr>
				<th scope="row">첨부파일</th>
				<td colspan="3">
		<c:choose>
			<c:when test="${empty list }">
				업로드 파일이 없습니다.		
			</c:when>
				<c:otherwise>
						<c:forEach var="row" items="${list }">
						<input type="hidden" id="IDX" value="${row.IDX }">
						<a href="#this" name="file">${row.ORIGINAL_FILE_NAME }</a>(${row.FILE_SIZE }kb)
						</c:forEach>
				</c:otherwise>
		</c:choose>

				</td>
			</tr>
		</tbody>
	</table>
	

	<a href="#this" class="btn" id="list">목록으로</a>
	<a href="#this" class="btn" id="update">수정하기</a>

	<%@ include file="/WEB-INF/include-body.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#list").on("click",function(e){
				e.preventDefault();
				fn_openBoardList();
			});
			
			$("#update").on("click",function(e){
				e.preventDefault();
				fn_openBoardUpdate();
			});
		});
		
		
		function fn_openBoardList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
			comSubmit.submit();
		}
		
		function fn_openBoardUpdate(){
			var idx = "${map.IDX}";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardUpdate.do' />");
			comSubmit.addParam("IDX", idx);
			comSubmit.submit();
		}
		
		
	
	</script>

</body>
</html>