'use strict'
function renderPagination(currentPage, totalCount) {
  if (totalCount <= 5) return;

  var totalPage = Math.ceil(totalCount / 20);
  var pageGroup = Math.ceil(currentPage / 10);

  var last = pageGroup * 10;
  if (last > totalPage) last = totalPage;
  var first = last - (10 - 1) <= 0 ? 1 : last - (10 - 1);
  var next = last + 1;
  var prev = first - 1;

  const fragmentPage = document.createDocumentFragment();
  if (prev > 0) {
    var allpreli = document.createElement('li');
    allpreli.insertAdjacentHTML("beforeend", `<a href='#' id='allprev'>&lt;&lt;</a>`);

    var preli = document.createElement('li');
    preli.insertAdjacentHTML("beforeend", `<a href='#' id='prev'>&lt;</a>`);

    fragmentPage.appendChild(allpreli);
    fragmentPage.appendChild(preli);
  }

  for (var i = first; i <= last; i++) {
    const li = document.createElement("li");
    li.insertAdjacentHTML("beforeend", `<a href='#' id='page-${i}' data-num='${i}'>${i}</a>`);
    fragmentPage.appendChild(li);
  }

  if (last < totalPage) {
    var allendli = document.createElement('li');
    allendli.insertAdjacentHTML("beforeend", `<a href='#' id='allnext'>&gt;&gt;</a>`);

    var endli = document.createElement('li');
    endli.insertAdjacentHTML("beforeend", `<a href='#' id='next'>&gt;</a>`);

    fragmentPage.appendChild(endli);
    fragmentPage.appendChild(allendli);
  }

  document.getElementById('js-pagination').innerHTML = ''; // 기존 페이지네이션 삭제
  document.getElementById('js-pagination').appendChild(fragmentPage);
  // 페이지 목록 생성

  document.querySelectorAll('#js-pagination a').forEach(function (pageLink) {
    pageLink.classList.remove('active');
  });
  document.querySelector(`#js-pagination a#page-${currentPage}`).classList.add('active');

  document.querySelectorAll("#js-pagination a").forEach(function (pageLink) {
    pageLink.addEventListener("click", function (e) {
      e.preventDefault();
      var $item = e.target;
      var $id = $item.getAttribute("id");
      var selectedPage = $item.textContent;

      if ($id == "next") selectedPage = next;
      if ($id == "prev") selectedPage = prev;
      if ($id == "allprev") selectedPage = 1;
      if ($id == "allnext") selectedPage = totalPage;

      // 페이지 번호를 서버로 전송
      window.location.href = 'board.do?method=page&page=' + selectedPage;
    });
  });
}

// 예시로 현재 페이지와 총 게시물 수를 설정
document.addEventListener('DOMContentLoaded', function() {
  renderPagination(1, 100); // 현재 페이지를 1로 설정하고 총 게시물 수를 100으로 설정
});
