<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Document</title>
    <style>
      * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
      }

      .pagination .active {
        font-weight: bold;
      }

      .buttons {
        position: relative;
        padding: 1rem 0;
        display: inline-flex;
        justify-content: center;
      }

      .button.active,
      .button:hover {
        color: #1f975d;
        font-weight: 600;
        text-decoration: underline;
      }

      body {
        width: 100vw;
        height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
      }

      #board-table {
        width: 800px;
        border-collapse: collapse;
      }

      .content {
        padding: 0.3em 0;
        border-bottom: 1px solid rgba(0, 0, 0, 0.08);
      }

      .content__seq,
      .content__writer,
      .content__title,
      .content__date,
      .content__see {
        flex-basis: 15%;
      }

      th {
        text-align: left;
      }
    </style>
</head>
<body>
<nav>
    <select id="select">
        <option value="title">title</option>
        <option value="content">content</option>
        <option value="nickname">nickname</option>
    </select>
    <input id="search-field" name="search-field" size="50">

    <input type="button" id="search-button" name="search" value="search">
</nav>
<div class="container">
    <table id="board-table">
        <thead>
        <tr class="content">
            <th>#</th>
            <th>Title</th>
            <th>Nickname</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <!-- board data will be added here -->
        </tbody>
    </table>
</div>
<div class="buttons"></div>

<script>
  document.addEventListener('DOMContentLoaded', () => {
    const my_tbody = document.querySelector('tbody');
    const paginationContainer = document.querySelector('.buttons');
    let boardData = [];
    const showRowsPerPage = 10;
    let currentPage = 1;

    // AJAX 요청으로 데이터 가져오기
    fetch('/board.do?method=boards') // 서블릿 URL
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      boardData = data; // 가져온 데이터를 boardData에 할당
      renderTable(); // 테이블 렌더링
      setupPagination(); // 페이지네이션 설정
    })
    .catch(error => console.error('Error:', error));

    function renderTable() {
      const startIndex = (currentPage - 1) * showRowsPerPage;
      const endIndex = startIndex + showRowsPerPage;
      my_tbody.innerHTML = ''; // 기존 데이터 제거
      boardData.slice(startIndex, endIndex).forEach(data => {
        const row = document.createElement('tr');
        row.classList.add('content');
        row.innerHTML =
            "<td>" + data.seq + "</td>" +
            "<td><a href='/board.do?method=insertDo&seq=" + data.seq + "'>" + data.title + "</a></td>" +
            "<td>" + data.nickname + "</td>" +
            "<td>" + data.date + "</td>";
        my_tbody.appendChild(row);
      });
      highlightCurrentPageButton();
    }

    function setupPagination() {
      const totalPages = Math.ceil(boardData.length / showRowsPerPage);
      paginationContainer.innerHTML = ''; // 기존 버튼 제거

      for (let i = 1; i <= totalPages; i++) {
        const pageButton = document.createElement('button');
        pageButton.classList.add('button');
        pageButton.textContent = i;
        pageButton.addEventListener('click', () => {
          currentPage = i;
          renderTable();
        });
        paginationContainer.appendChild(pageButton);
      }
      highlightCurrentPageButton();
    }

    function highlightCurrentPageButton() {
      const pageButtons = paginationContainer.querySelectorAll('button');
      pageButtons.forEach(btn => {
        btn.classList.toggle('active', btn.textContent === currentPage.toString());
      });
    }
  });

  const searchData = document.getElementById('search-field').value;
  const data = {search:'searchData'};
  document.getElementById('search-field').addEventListener('keyup',()=>{
    let select_value = document.getElementById('select').value;
    fetch('/board.do?method='+select_value,{
      method:'POST',
      headers:{
        'Content-Type':'application/json'
      },
      body:JSON.stringify(data)
    })
    .then(response=>{
      if(!response.ok){
        throw new Error(`HTTP error! status: ${response.status}`)
      }
      return response.json();
    })
    .then(reponseData=>{
      alert('Response:',reponseData)
    })
    .catch(error=>{
      alert('Error:',error)
    })
  })

</script>
</body>
</html>
