<%@page import="com.guardian.myhome.vo.LessorImgVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.4.1.js"  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
	LessorImgVO lessorImg = (LessorImgVO)request.getAttribute("lessorImg");
	
	// 2023\02\28 => 2023/02/08 => \ => /
	String uploadPath = lessorImg.getUploadPath().replace("\\", "//");
	String uuid = lessorImg.getUuid();
	String fileName = lessorImg.getFileName();
	
	String imgPath = uploadPath + "/" + uuid + "_" + fileName;
%> 

<img id="lessorImg" src="/member/display?fileName=<%= imgPath %>" style="max-width: 100%; height:auto;">


</body>
</html>