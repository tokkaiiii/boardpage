<%--
  Created by IntelliJ IDEA.
  User: heosangbeom
  Date: 2024. 7. 16.
  Time: 오후 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
      table {
        width: 100%;
        border-collapse: collapse;
      }
      th, td {
        border-bottom: 1px solid black;
        padding: 8px;
      }
      input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
      }
      input[type=submit] {
        width: auto;
        padding: 14px 20px;
      }
      .button-container {
        display: flex;
        justify-content: flex-end;
      }
      .content-container{
        padding: 0;
      }
      .content-area{
        border: none;
        border-radius: 5px;
        width: 100%;
      }
      textarea:focus{
        outline: none;
      }
      input:focus{
        outline: none;
      }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="board.do?method=update&seq=${board.seq}" method="post">
            <table>
                <thead>
                <tr>
                    <th colspan="6"><input type="text" name="title"  value="${board.title}"></th>
                </tr>
                <tr>
                    <th>작성자</th>
                    <th><input type="text" name="nickname" value="${board.nickname}"></th>
                    <th>날짜</th>
                    <th><input type="text" name="date" value="${board.date}"></th>
                    <th>조회수</th>
                    <th>조회수DB</th>
                </tr>
                </thead>
                <tbody>
                <tr><td colspan="6" class="content-container">
                <textarea class="content-area" name="" id="" cols="30" rows="10" maxlength="2048">
                    ${board.content}
                </textarea>
                </td></tr>
                </tbody>
            </table>
            <div class="button-container">
                <button class="button" type="button">수정</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
