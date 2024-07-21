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
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
      input:focus, textarea:focus {
        outline: none;
      }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="board.do?method=update&seq=${board.seq}" method="post">
            <table class="table table-striped"
                   style="text-align: center; border: 1px solid #dddddd">
                <thead>
                <tr>
                    <th colspan="2" style="text-align:center ; background-color: #dddddd;">수정하기</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="text" class="form-control"  name="nickname" value="${board.nickname}"
                               maxlength="50" disabled></td>
                    <td><input type="text" class="form-control" name="date" value="${board.date}"
                               maxlength="50" disabled></td>

                </tr>
                <tr>
                    <td colspan="2" style="text-align:center ; background-color: #dddddd;"><input type="text" class="form-control"  name="title" value="${board.title}"
                               maxlength="50"></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center ; background-color: #dddddd;"><textarea type="text" class="form-control"  name="content"
                                  maxlength="2048" style="height:350px">${board.content}</textarea></td>
                </tr>
                </tbody>
            </table>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button class="btn btn-outline-dark" type="submit">수정하기</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
